package com.example.library.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.library.entity.Ebook;

import lombok.Data;

@Entity
@Data
public class Ebook {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String format;
	private Integer mb;
	
	public Ebook() {
		super();
	}
	
	public Ebook(String format, Integer mb) {
		super();
		this.format = format;
		this.mb = mb;
	}

	@Override
	public String toString() {
		return "Ebook [id=" + id + ", format=" + format + ", mb=" + mb + "]";
	}
	
	

}
