package com.example.library;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.entity.Ebook;
import com.example.library.entity.PrintCopy;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.EbookRepository;
import com.example.library.repository.PrintCopyRepository;


@Transactional
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	BookRepository bookRepo;

	@Autowired
	PrintCopyRepository pcRepo;

	@Autowired
	EbookRepository ebookRepo;

	@Autowired
	AuthorRepository authorRepo;	

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		
					
			// set PrintCopy
			PrintCopy pc1 = new PrintCopy(1526, 1.57);
			pcRepo.save(pc1);
			PrintCopy pc2 = new PrintCopy(235, 0.75);
			pcRepo.save(pc2);
			PrintCopy pc3 = new PrintCopy(758, 0.9);
			pcRepo.save(pc3);
			PrintCopy pc4 = new PrintCopy(659, .591);
			pcRepo.save(pc4);
			PrintCopy pc5 = new PrintCopy(100, 0.3);
			pcRepo.save(pc5);
			PrintCopy pc6 = new PrintCopy(200, 0.4);
			pcRepo.save(pc6);
			PrintCopy pc7 = new PrintCopy(300, 0.5);
			pcRepo.save(pc7);
			PrintCopy pc8 = new PrintCopy(400, 0.6);
			pcRepo.save(pc8);
			PrintCopy pc9 = new PrintCopy(500, 0.7);
			pcRepo.save(pc9);
			PrintCopy pc10 = new PrintCopy(600, 0.8);
			pcRepo.save(pc10);
			PrintCopy pc11 = new PrintCopy(700, 0.9);
			pcRepo.save(pc11);
			PrintCopy pc12 = new PrintCopy(800, 1.0);
			pcRepo.save(pc12);
			PrintCopy pc13 = new PrintCopy(900, 1.05);
			pcRepo.save(pc13);
			PrintCopy pc14 = new PrintCopy(950, 1.2);
			pcRepo.save(pc14);
			PrintCopy pc15 = new PrintCopy(1001, 1.25);
			pcRepo.save(pc15);
			
			//set Ebook
			Ebook eb1 = new Ebook("Tittle1.epub", 120);
			ebookRepo.save(eb1);
			Ebook eb2 = new Ebook("Tittle5.pdf", 456);
			ebookRepo.save(eb2);
			Ebook eb3 = new Ebook("Tittle11.html5", 456);
			ebookRepo.save(eb3);
			Ebook eb4 = new Ebook("Tittle16.pdf", 500);
			ebookRepo.save(eb4);
			Ebook eb5 = new Ebook("Tittle17.pdf", 700);
			ebookRepo.save(eb5);
			
			// set Author
					Author a1 = new Author("Sashko", "Klimkarov", 1982);
					authorRepo.save(a1);		
					Author a2 = new Author("Zarko", "Stojanov", 1987);
					authorRepo.save(a2);
					Author a3 = new Author("Goran", "Milanov", 1983);
					authorRepo.save(a3);
					Author a4 = new Author("Blagoj", "Gorgiev", 1995);
					authorRepo.save(a4);
					Author a5 = new Author("Bojan", "Vilarov", 2005);
					authorRepo.save(a5);
					Author a6 = new Author("Blaze", "Koneski", 1975);
					authorRepo.save(a6);
			
			// set Book
			Book b1 = new Book("Tittle1", 123, 1982, a1, pc3, eb1);
			bookRepo.save(b1);
			Book b2 = new Book("Tittle2", 252, 2001, a6, pc10, null);
			bookRepo.save(b2);
			Book b3 = new Book("Tittle3", 389, 2005, a2, pc7, null);
			bookRepo.save(b3);
			Book b4 = new Book("Tittle4", 569, 2010, a1, pc1, eb1);
			bookRepo.save(b4);
			Book b5 = new Book("Tittle5", 895, 2004, a3, pc2, eb2);
			bookRepo.save(b5);
			Book b6 = new Book("Tittle6", 456, 2015, a5, pc4, null);
			bookRepo.save(b6);
			Book b7 = new Book("Tittle7", 777, 2008, a5, pc11, null);
			bookRepo.save(b7);
			Book b8 = new Book("Tittle8", 888, 2009, a4, pc14, null);
			bookRepo.save(b8);
			Book b9 = new Book("Tittle9", 999, 2020, a1, pc15, eb1);
			bookRepo.save(b9);
			Book b10 = new Book("Tittle10", 896, 2000, a6, pc6, null);
			bookRepo.save(b10);
			Book b11 = new Book("Tittle11", 454, 2021, a3, pc3, eb3);
			bookRepo.save(b11);
			Book b12 = new Book("Tittle12", 225, 1955, a3, pc5, null);
			bookRepo.save(b12);
			Book b13 = new Book("Tittle13", 289, 1985, a3, pc12, null);
			bookRepo.save(b13);
			Book b14 = new Book("Tittle14", 693, 1945, a2, pc8, null);
			bookRepo.save(b14);
			Book b15 = new Book("Tittle15", 898, 2017, a1, pc9, eb1);
			bookRepo.save(b15);
			Book b16 = new Book("Tittle16", 789, 2018, a4, null, eb4);
			bookRepo.save(b16);
			Book b17 = new Book("Tittle17", 741, 2019, a1, null, eb5);
			bookRepo.save(b17);
		
	}

}
