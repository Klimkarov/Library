package com.example.library.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.library.entity.Author;
import com.example.library.entity.Ebook;
import com.example.library.entity.PrintCopy;
import com.example.library.entity.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.EbookRepository;

@Service
public class LibraryService {

	@Autowired
	BookRepository bookRepo;

	@Autowired
	EbookRepository ebookRepo;
	
	@Autowired
	AuthorRepository authorRepo;

	// 1 Get All
	public List<Book> showAll() {
		return bookRepo.findAll();
	}

	// 2 Get Book By Id
	public Book getBookById(Integer id) {
		Book b = bookRepo.findById(id).get();
		return b;
	}

	// 3 Get Ebook By Id (ResponseStatus)
	public ResponseEntity<Ebook> getEbookById(Integer id) {
		Ebook b = ebookRepo.findById(id).get();
		if (b == null) {
			return new ResponseEntity<Ebook>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Ebook>(b, HttpStatus.OK);
	}

	// 4 Get All Books By ProductionYear Desc
	public List<Book> showAllBooksByProductionYearDesc() {
		return bookRepo.findAllByOrderByProductionYearDesc();
	}

	// 5 Get All Ebook And PrintCopy By ProductionYearAsc
	public String GetAllEbookAndPrintCopyByProductionYearAsc() {
		List<Object> listAll = new ArrayList<>();

		// One way for filter
		List<Book> b1 = bookRepo.findAll(Sort.by(Sort.Direction.ASC, "productionYear"));

		// Another way for filter
		List<Book> c = bookRepo.findAllByOrderByProductionYearAsc();
		for (Book book : c) {

			listAll.add(book.getProductionYear());
			listAll.add(book.getEBook());
			listAll.add(book.getPrintCopy());
		}
		return "Start from oldest book:" + listAll;
	}

	// 6 Get All Books By input Author LastName (input in Stream - HardCoding)
	public List<Book> getAllBooksByAuthorName() {

		List<Book> listBook = bookRepo.findAll();
		List<Book> filtered = listBook.stream().filter(p -> p.getAuthor().getLastName().startsWith("Klimkarov"))
				.collect(Collectors.toList());

		return filtered;
	}

	// 7 Get All Ebook And PrintCopy Books By Athors Name
	public List<Object> getBookByAuthorName(String name) {
		List<Object> listBook = new ArrayList<Object>();
		List<Book> a = bookRepo.findAll();
		for (Book book : a) {
			if (book.getAuthor().getFirstName().equalsIgnoreCase(name)) {
				listBook.add(book.getAuthor());
				listBook.add(book.getEBook());
				listBook.add(book.getPrintCopy());
				return listBook;
			}else if(book.getAuthor().getFirstName() != (name)){
				System.out.println("Not exist Author with that LastName"); // print in console
			}
		}

		return null;
	}

	// 8 Get All Ebook And PrintCopy Books By Athors LastName
	// we can make input by one LETTER or word
	public List<Object> findAllBooksByAthorsLastNameStartWith(String string) {
		List<Object> obj = new ArrayList<>();

		List<Book> b = bookRepo.findAllByAuthor_LastNameStartsWithIgnoreCase(string);
		for (Book book : b) {
			if (book != null) {
				obj.add(book.getAuthor());
				obj.add(book.getEBook());
				obj.add(book.getPrintCopy());
				return obj;
			
			}else if(book.getAuthor().getLastName() != (string)){
				System.out.println("Not exist Author with that LastName");
			}else {
				System.out.println("The proces is finished");
			}
		}
		return null;
	}

	// 9 Get All Ebook And PrintCopy Books By Athors LastName
	// this method work only with input full word - lastName
	public List<Object> findAllBooksByAthorLastNameStartWith(String lastName) {
		List<Object> obj = new ArrayList<>();

		List<Book> b = bookRepo.findAllByAuthor_LastNameIgnoreCase(lastName);
		for (Book book : b) {
			if (book != null) {
				obj.add(book.getAuthor());
				obj.add(book.getEBook());
				obj.add(book.getPrintCopy());
				return obj;
			}
		}

		return null;
	}

	// 12 Get All Authors With All Their Books
	public List<Object> getAllAuthorsWithAlltheirBook() {
		List<Object> listAuthors = new ArrayList<>();

		List<Book> list = bookRepo.findByAuthor();

		for (Book book : list) {
			listAuthors.add(book.getTittle());
			listAuthors.add(book.getAuthor());
			listAuthors.add(book.getEBook());
			listAuthors.add(book.getPrintCopy());
			continue;
		}
		return listAuthors;
	}

	// 13 Get All Authors with id > 3
	public List<Object> allAbove3() {

		List<Object> listAuthors = new ArrayList<>();
		List<Book> strList = bookRepo.findByAuthor();
		List<Book> filtered = strList.stream().filter(x -> x.getAuthor().getId() > 3).collect(Collectors.toList());
		for (Book book : filtered) {
			listAuthors.add(book.getAuthor());
		}
		return listAuthors;
	}

	// 17 Get First And Last Book By Year
	public String getFirstAndLastBookByYear() {

		Book firstBook = bookRepo.findTop1ByOrderByProductionYearAsc();
		Book secondBook = bookRepo.findTop1ByOrderByProductionYearDesc();

		List<Object> list = new ArrayList<>();
		list.add(firstBook);
		list.add(secondBook);

		// return list;

		return "The oldest book is: " + firstBook + " The yungest book is: " + secondBook;
	}

	// 18 Only Get the Latest Book with STREAM
	public List<Object> getLatestBook() {

		List<Object> latestBook = new ArrayList<>();

		int max = bookRepo.findAll().stream().max(Comparator.comparing(Book::getProductionYear)).get()
				.getProductionYear();

		List<Book> youngestBook = bookRepo.findAll().stream().filter(m -> m.getProductionYear() == max)
				.collect(Collectors.toList());

		for (Book book : youngestBook) {
			latestBook.add(book.getProductionYear());
			// o.add(book.getAuthor());
			latestBook.add(book.getEBook());
			latestBook.add(book.getPrintCopy());
		}

		return latestBook;
	}

	// 19 Get Only the Youngest Book with STREAM
	public List<Object> getOldestBook() {

		List<Object> youngestBook = new ArrayList<>();

		int min = bookRepo.findAll().stream().min(Comparator.comparing(Book::getProductionYear)).get()
				.getProductionYear();

		List<Book> oldestBook = bookRepo.findAll().stream().filter(m -> m.getProductionYear() == min)
				.collect(Collectors.toList());

		for (Book book : oldestBook) {
			youngestBook.add(book.getProductionYear());
			// o.add(book.getAuthor());
			youngestBook.add(book.getEBook());
			youngestBook.add(book.getPrintCopy());
		}

		return youngestBook;
	}

	// 20 Create New Empty Book
	public Book createNewEmptyBook(Book book) {
		bookRepo.save(book);
		return book;
	}

	// 21 Create new Ebook (set with Object - HordCoding)
	public Ebook createNewEbookWithObject(Ebook ebook) {
		ebook.setFormat("Tittle.html5");
		ebook.setMb(500);
		ebookRepo.save(ebook);
		return ebook;
	}

	
	// 22 Create new Book with List
	List<Book> newBook = new ArrayList<>();
	
	public List<Book> createNewBookWithList() {
		newBook.add(new Book(18, "evre", 5, 55, null, null, null));
		List<Book> newBook = new ArrayList<>();
		
		bookRepo.saveAll(newBook);
		return newBook;
	}

	// 23 Create new Ebook From Postman with JSON
	public Ebook createEbookFromPostman(Ebook ebook) {
		ebookRepo.save(ebook);
		return ebook;
	}

	// 24 Update Ebook by id (HardCoding)
	public String updateEbook(Integer id) {

		Ebook ebookUpdate = ebookRepo.findById(id).get();

		if (ebookUpdate.getId() == id) {
			ebookUpdate.setFormat("Tittle6.pdf");
			ebookUpdate.setMb(22);
			ebookRepo.save(ebookUpdate);
		}

		return "Update was sacessfull " + ebookUpdate;
	}

	// 25 Update Ebook By Id with JSON (id put in JSON)
	public Ebook updateEbookWithJSON(Ebook ebook) {
		ebookRepo.save(ebook);
		return ebook;
	}

	/* 26 updateByIdWithJSON (povikuvam vo url so id, a sodrzinata se menuva so JSON)
	 (vo JSON moze da ima i da nema id) */
	public String updateByIdWithJSON(Integer id, Ebook ebookJSON) {

		Ebook ebook = ebookRepo.findById(id).get();

		if (ebook.getId().equals(id)) {
			ebook.setFormat(ebookJSON.getFormat());
			ebook.setMb(ebookJSON.getMb());

			return "Update Ebook: " + ebookRepo.save(ebook);
		}
		return null;
	}

	// 27 Get Book By Id And Update Name Author
	public Book getBookByIdAndChangeNameAuthor(Integer id) {
		List<Book> list = bookRepo.findAll();
		for (Book book : list) {
			if (book.getId() == id) {
				book.getAuthor().setFirstName("Sasko");
				bookRepo.save(book);
				return book;
			}
		}
		return null;
	}

	// 28 Delete Book by id
	public void deleteBook(Integer id) {
		bookRepo.deleteById(id);
	}
	
    // 30 Delete Book By Id with List
	public void delete(Integer id) {
		this.newBook.remove(id);
	}
	
	
//	-------------------
	// 8 Scanner - Get Book And Author Name by input NAME (for this method need main method or run class)
		public List<Object> getBookAndAuthorName() {
			
			Scanner sc = new Scanner(System.in);
			String name = sc.nextLine();
			
			List<Object> listBooksandAuthors = new ArrayList<>();
			
			List<Book> book = bookRepo.findAll();
			for (Book books : book) {
				if (name.equalsIgnoreCase(books.getAuthor().getFirstName())) {
					listBooksandAuthors.add(books.getEBook());
					listBooksandAuthors.add(books.getPrintCopy());
					
					System.out.println(listBooksandAuthors);
					return listBooksandAuthors;
				}
			}

			return null;
		}
}


