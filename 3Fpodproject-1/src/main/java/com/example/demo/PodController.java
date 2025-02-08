package com.example.demo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.hibernate.query.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PodController {
	
	@Autowired
	private PodService podService;
	
	@Autowired
	private PodSyainService podSyainService;
	
	@Autowired
	private PodRepository podRepository;
	@RequestMapping("/tourokugamen")
	public String modoru() {
		return "index";
	}
	@RequestMapping("/podlist")
	public String listpage() {
		return "mokuroku";
	}
	@RequestMapping("/podkaiinform")
	public String kaiinform() {
		return "kaiintouroku";
	}
	@RequestMapping("/log")
	public String log() {
		return "login";
	}
	@RequestMapping("/podzentai")
	public String getAll(@ModelAttribute Shigoto shigoto, 
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
			Model model){
		Page<Shigoto> zentailist = podService.subete(pageable); 
		int nowPage = zentailist.getPageable().getPageNumber()+1;
		int startPage = Math.max(nowPage-4,1);
		int endPage = Math.min(nowPage+5, zentailist.getTotalPages());
		System.out.println(startPage+"ページ");
		int size = 0;
		for (Shigoto shigoto2 : zentailist) {
			size += 1;
			System.out.println(shigoto2.getTouroku_date());	
		}
			model.addAttribute("zentailist", zentailist);
			model.addAttribute("nowPage", nowPage);
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("listsize", "全部"+podRepository.findAll().size()+"件です！！！");
			return "mokuroku";
		
	}
	@PostMapping("/podjoken")
	public @ResponseBody List<Shigoto> jokenList(@ModelAttribute Shigoto shigoto,
			@RequestParam(value = "iraibango1")String iraibango1,
			@RequestParam(value = "iraibango2")String iraibango2,
			@RequestParam(value = "iraibango3")String iraibango3,		
			@RequestParam(value = "shukkabi2")LocalDate shukkabi2, 
			@PageableDefault(page = 0, size = 7, sort = "id", direction = Sort.Direction.DESC)
			Model model){
		//System.out.println(shigoto.getSagyousya());
		//System.out.println(shukkabi2);
		System.out.println(iraibango2);
		if(iraibango1 != null && iraibango1 != "") {
		shigoto.setIraibango(iraibango1);
		}
		if(iraibango2 != null && iraibango2 != "") {
			shigoto.setIraibango(iraibango2);
		}
		if(iraibango3 != null && iraibango3 != "") {
			shigoto.setIraibango(iraibango3);
		}
		if(iraibango1 != null && iraibango1 != "" && iraibango2 != null && iraibango2 =="") {
			shigoto.setIraibango(iraibango1+"-"+iraibango2);
		}
		if(iraibango2 != null && iraibango2 != "" && iraibango3 != null && iraibango2 =="") {
			shigoto.setIraibango(iraibango2+"-"+iraibango3);
		}
		if(iraibango1 != null && iraibango1 != "" && iraibango2 != null && iraibango2 != "" && iraibango3 != null || iraibango2 =="") {
			shigoto.setIraibango(iraibango1+"-"+iraibango2+"-"+iraibango3);
		}
		System.out.println("依頼書の番号は"+shigoto.getIraibango()+"です");
		List<Shigoto> joken = podService.jokenkensaku(shigoto.getIraibango(),shigoto.getKakoujouhou(),shigoto.getShurui(),shigoto.getShukkabi(),shukkabi2,shigoto.getSagyousya());
		model.addAttribute("joukenlist", joken);
		model.addAttribute("joukenlistsize", joken.size());
		
		return joken;
		
	}
	@RequestMapping("/podkuwashiku")
	public String shosai(@ModelAttribute Shigoto shigoto, Model model) {
		System.out.println(shigoto.getTitle());
		Optional<Shigoto> shousaiList = podService.joken(shigoto.getTitle());
		
		if(shousaiList == null) {
			return null;
		}else {		
			Shigoto shigoto2 = shousaiList.get();
			System.out.println(shigoto2.getKaisyamei());
			//model.addAttribute("shosai", shousaiList);
			model.addAttribute("shigoto", shigoto2);
			return "shousai";
		}
	}
	@PostMapping("/touroku")
	public String name(@ModelAttribute Shigoto shigoto, Model model) {
		System.out.println("作業者 = "+ shigoto.getSagyousya());
		System.out.println("日付け = "+ shigoto.getShukkabi());
		podService.touroku(shigoto);
		model.addAttribute("sagyousya", shigoto.getSagyousya());
		return "list";
	}
	
	@PostMapping("/podsyaininsert")
	public String syaintouroku(@ModelAttribute Podsyain podsyain, Model model) {
		/*
		 * String pass = WebSecurityConfig.encrypt(podsyain.getPw());
		 * podsyain.setPw(pass);
		 */
		String pwnaoshi = UserSha256.encrypt(podsyain.getPw());
		podsyain.setPw(pwnaoshi);
		System.out.println(podsyain.getPw());
		podSyainService.syaintouroku(podsyain);		
		System.out.println(podsyain.getName()+"です！！");
		return "login";
		
	}
	@PostMapping("/podlogin")
	public String Podlogin(@ModelAttribute Podsyain podsyain, Model model, HttpServletRequest httpServletRequest) {
		System.out.println(podsyain.getPw());
		String pwshusei = UserSha256.encrypt(podsyain.getPw());
		podsyain.setPw(pwshusei);
		System.out.println(podsyain.getPw());
		Optional<Podsyain> login= podSyainService.log(podsyain.getId(),podsyain.getPw());
		if(login != null) {
			HttpSession httpSession = httpServletRequest.getSession();
			httpSession.setAttribute("loginsyain", login.get().getName());
			System.out.println(login.get().getName());
			//model.addAttribute("login", login.get().getName());
			
		}
		return "mokuroku";
	}
	@RequestMapping("/podlogout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView;
	}
	@GetMapping("/podsakujo")
	public @ResponseBody String sakujo(@RequestParam(value = "id") Integer id, Model model) {		
		System.out.println(id);
		podRepository.deleteById(id);
		return "削除完了";
	}
}
