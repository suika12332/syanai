package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PodSyainService {
	@Autowired
	private PodSyainInterface podSyainInterface;
	
	public Optional<Podsyain> log(String id, String pw) {
		// TODO Auto-generated method stub
		return podSyainInterface.findByIdAndPw(id, pw);
	}
	public void syaintouroku(Podsyain podsyain) {
		podSyainInterface.save(podsyain);
	}

}
