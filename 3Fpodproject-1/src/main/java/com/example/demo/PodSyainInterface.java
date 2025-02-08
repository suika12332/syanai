package com.example.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PodSyainInterface extends JpaRepository<Podsyain,Integer>{

	Optional<Podsyain> findByIdAndPw(String id, String pw);

}
