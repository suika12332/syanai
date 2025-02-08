package com.example.demo;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

@Service
public class PodService {
	@Autowired
	private PodRepository podRepository;
	
	@Autowired
	private PodSyainInterface podSyainInterface;
	
	public void touroku(Shigoto shigoto) {
		podRepository.save(shigoto);
	}

	public Page<Shigoto> subete(Pageable pageable) {	
		return podRepository.findAll(pageable);
	}
	
	
	public Optional<Shigoto> joken(String title) {
		return podRepository.findByTitle(title);
	}

	public List<Shigoto> jokenkensaku(String irabango, String kakoujouhou, int shurui, LocalDate shukkabi,LocalDate shukkabi2, String sagyousya) {
		System.out.println(shukkabi);
		System.out.println(shukkabi2);
		return podRepository.Jouken(irabango,kakoujouhou,shurui,shukkabi,shukkabi2,sagyousya);
	}
	public void tourokukaijo(Long id) {
		// TODO Auto-generated method stub
		System.out.println("id:"+id);
		podRepository.deleteById(id);
	}
	/*
	 * public int syaininsert(String id, String pw, String name) { return
	 * podRepository.savequery(id,pw,name); }
	 */

	
}
