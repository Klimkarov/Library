package com.example.library.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table
@Data
@NoArgsConstructor
@ToString
public class PrintCopy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer pages;
	private Double weigth;
	
	
	public PrintCopy(Integer pages, Double weigth) {
		super();
		this.pages = pages;
		this.weigth = weigth;
	}
	
	
	
	

}
