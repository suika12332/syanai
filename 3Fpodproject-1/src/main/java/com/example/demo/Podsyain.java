package com.example.demo;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Podsyain {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bango;
	private String id; 
	private String pw; 
	private String name;
}
