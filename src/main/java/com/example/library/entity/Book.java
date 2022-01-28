package com.example.library.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Service;

import com.example.library.entity.Author;
import com.example.library.entity.Ebook;
import com.example.library.entity.PrintCopy;

import lombok.Data;

@Entity
@Table
@Data
@Service
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String tittle;
	private Integer ISBN;
	private Integer productionYear;
	
	@ManyToOne//(cascade = CascadeType.ALL)
	//@JoinColumn(referencedColumnName = "id")
	private Author author;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "id")
	private PrintCopy printCopy;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "id")
	private Ebook eBook;
	
	public Book() {
		super();
	}

	public Book(String tittle, Integer iSBN, Integer productionYear, Author author, PrintCopy printCopy, Ebook eBook) {
		super();
		this.tittle = tittle;
		ISBN = iSBN;
		this.productionYear = productionYear;
		this.author = author;
		this.printCopy = printCopy;
		this.eBook = eBook;
	}

	public Book(int i, String string, int j, int k, Object object, Object object2, Object object3) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", tittle=" + tittle + ", ISBN=" + ISBN + ", productionYear=" + productionYear + ", author=" + author
				+ ", printCopy=" + printCopy + ", eBook=" + eBook + "]";
	}

}
