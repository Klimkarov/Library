package com.example.library.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.library.entity.Book;
import com.example.library.entity.Ebook;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.EbookRepository;
import com.example.library.repository.PrintCopyRepository;
import com.example.library.service.LibraryService;

@RestController(value = "/")
public class LibraryController {

	@Autowired
	BookRepository bookRepo;

	@Autowired
	EbookRepository ebookRepo;

	@Autowired
	AuthorRepository authorRepo;

	@Autowired
	PrintCopyRepository pcRepo;

	@Autowired
	LibraryService libraryService;

	@Autowired
	Book book;

	// One way to write method
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.CREATED)
	public String Hello() {
		return "Hello there, how are you  :))";
	}

	// Another shorter way to write method
	// 1 Get all from Book
	@GetMapping("/findAll")
	public List<Book> showAllFromBook() {
		return bookRepo.GetAll();
	}

	// 2 Get Book By Id
	@GetMapping("/getBookById/{id}")
	public Book getBookById(@PathVariable("id") Integer id) {
		return libraryService.getBookById(id);
	}

	// 3 Get Book By Id (ResponseStatus)
	@GetMapping("/getEbookById/{id}")
	public ResponseEntity<Ebook> getEbookById(@PathVariable("id") Integer id) {
		return libraryService.getEbookById(id);
	}

	// 4 Get All From Book Order By ProductionYear Desc
	@GetMapping("/GetAllFromBookByProductionYearDesc")
	public List<Book> GetAllByOrderByProductionYearDesc() {
		return libraryService.showAllBooksByProductionYearDesc();
	}

	/* 4.1
	@GetMapping("/getAllByProductionYear")
	public List<Book> getAllBooksByProductionYear() {
	  List<Book> listByYear = libraryService.showAllBooksByProductionYearDesc();
		return listByYear;
	}*/

	/* 5 Get All Ebook And PrintCopy By ProductionYear Asc, 
	     This method return value with value String */
	@GetMapping("/GetAllEbookAndPrintCopyByProductionYearAsc")
	public String GetAllEbookAndPrintCopyByProductionYearAsc() {
		return libraryService.GetAllEbookAndPrintCopyByProductionYearAsc();
	}

	// 6 Get All Ebook And PrintCopy Books By Athor LastName (Stream - HardCoding)
	@GetMapping("/getAllBooksByAuthorLastName")
	public List<Book> getAllBooksByAuthorName() {
		return libraryService.getAllBooksByAuthorName();
	}

	// 7 Get All Ebook And PrintCopy Books By Athors Name
	@GetMapping("/getAllBooksByAuthorName/{name}")
	public List<Object> getBooks(@PathVariable(value = "name") String name) {
		return libraryService.getBookByAuthorName(name);
	}

	/* 8 Get All Ebook And PrintCopy Books By Athors LastName
	     we can make input by one LETTER or Word */
	@GetMapping("/findAllBookOfAuthorByLastNameInputOneLetter/{letter}")
	public List<Object> findByAuthorLetter(@PathVariable("letter") String string) {
		return libraryService.findAllBooksByAthorsLastNameStartWith(string);
	}

	/* 9 Get All Ebook And PrintCopy Books By Athors LastName
	     this method work only with input full word - lastName */
	@GetMapping("/findAllEbookAndPrintCopyByAuthorLastNameStartsWithLastName/{lastName}")
	public List<Object> findAuthorsByLastName(@PathVariable("lastName") String lastName) {
		return libraryService.findAllBooksByAthorLastNameStartWith(lastName);
	}

	/* 10 Query - Get All Ebook And PrintCopy Books By Athors LastName StartWith input WORD
	     this method have 2 Query, can work with 1. letter and word; 2. only with word look the Repo */
	@GetMapping("/WithQueryFindAllEbookAndPrintCopyByAuthorLastNameStartsWithLastName/{letter}")
	public List<Book> Query(@PathVariable("letter") String letter) {
		return bookRepo.Query(letter);
	}

	/* 11 Get All Books of Authors if by input with year
	     is same with the year of Author BirthDay and Book productionYear */
	@GetMapping("/getAllBooksOfAuthors/{year}")
	public List<Object> FindAllAuthors(@PathVariable ("year") Integer year) {
		List<Object> o = new ArrayList<>();
		List<Book> lista = bookRepo.findAll();
		for (Book book : lista) {
	
			if (year.equals(book.getAuthor().getYearOfBirth()) && year.equals(book.getProductionYear())) {
				o.add(book.getAuthor().getFirstName());
				o.add(book.getEBook());
				o.add(book.getPrintCopy());
				return o;
			}
		}

		return null;
	}

	// 12 Get All Authors With All Their Books
	@GetMapping("/getAllAuthorsWithAlltheirBook")
	public List<Object> getAuthorsWithAlltheirBookr() {
		return libraryService.getAllAuthorsWithAlltheirBook();
	}

	// 13 Get All Authors with id > 3
	@GetMapping("/getAllAbove3")
	public List<Object> getAllAuthors() {
		return libraryService.allAbove3();
	}

	// 14 Query - Get All Authors with id > 3
	@GetMapping("/queryGetAllUp3")
	public List<Book> getAlesGute() {
		return bookRepo.getAllAuthorsUp3();
	}

	// 15 Get Tittle by Author lastName - From Repo
	@GetMapping("/getTittleByAuthorLastName/{lastname}")
	public List<Object> GetTittleByAuthorlastName(@PathVariable(value = "lastname") String lastName) {

		List<Object> listTittle = new ArrayList<>();

		List<Book> list = bookRepo.findAllByAuthor_LastNameIgnoreCase(lastName);
		for (Book book : list) {
			if (book.getTittle().equals(lastName))
				;
			listTittle.add(book.getTittle());
			continue;

		}
		return listTittle;
	}

	// 16 Get Author BirthDay By LastName - From Repo
	@GetMapping("/getBirthDayByAuthorlastName/{lastname}")
	public List<Object> findByAuthor(@PathVariable(value = "lastname") String lastName) {

		List<Book> o = bookRepo.findAllByAuthor_LastNameIgnoreCase(lastName);
		for (Book book : o) {
			List<Object> p = new ArrayList<>();
			p.add(book.getAuthor().getYearOfBirth());
			return p;
		}

		return null;
	}

	// 17 Get First And Last Book By Year
	@GetMapping("/getFirstAndLastBookByYear")
	public String getFirstAndLastByYear() {
		return libraryService.getFirstAndLastBookByYear();
	}

	// 18 Only Get Latest Book with Stream
	@GetMapping("/getLatestBook")
	public String latestBook() {
		List<Object> books = libraryService.getLatestBook();
		return "The latest book is: " + books;
	}

	// 19 Get Only the Youngest Book with STREAM
	@GetMapping("/getYoungestBook")
	public String OldestBook() {
		List<Object> books = libraryService.getOldestBook();
		return "The oldest book is: " + books;
	}

	// 20 Create New Empty Book
	@RequestMapping(value = "/createNewEmptyBook", method = RequestMethod.POST)
	public String createNewEmptyBook(Book book) {
		return "Formirana e nova book" + libraryService.createNewEmptyBook(book);
	}

	// 21 Create new Ebook with Object (set with HordCoding)
	@PostMapping("/createNewEbookWithObject")
	public String createNewEbook(Ebook ebook) {
		return "You created new Ebook: " + libraryService.createNewEbookWithObject(ebook);
	}

	// 22 Create new Book with List
	@PostMapping("/createNewEbookWithList")
	public List<Book> createNewEbookWithList() {
		return libraryService.createNewBookWithList();
	}

	// 23 Create new Ebook From Postman with JSON
	@PostMapping("/createNewEbookFromPostManWithJSON")
	public String createNewEbookPostman(@RequestBody Ebook ebook) {
		return "You created new Ebook: " + libraryService.createEbookFromPostman(ebook);
	}

	// 24 Update Ebook by id (HardCoding)
	@PutMapping("/updateEbook/{id}")
	public String updateEbook(@PathVariable("id") Integer id) {
		return libraryService.updateEbook(id);
	}

	// 25 Update Ebook By Id with JSON (id put in JSON)
	@PutMapping("/updateEbookWithJSON")
	public Ebook updateEbookdWithJSON(@RequestBody Ebook ebook) {
		return libraryService.updateEbookWithJSON(ebook);
	}

	/* 26 Update By Id With JSON (povikuvam vo url so id, a sodrzinata se menuva so JSON)
	      (vo JSON moze da ima i da nema id) */
	@PutMapping("/updateEbookByIdWithJSON/{id}")
	public String updateEbookByIdWithJSON(@PathVariable("id") Integer id, @RequestBody Ebook ebookJSON) {
		return libraryService.updateByIdWithJSON(id, ebookJSON);

	}

	// 27 Get Book By Id And Update Name Author
	@RequestMapping(value = "/updateById/{id}", method = RequestMethod.PUT)
	public Book updateBookById(@PathVariable("id") int id) {
		return libraryService.getBookByIdAndChangeNameAuthor(id);
	}

	// 28 Delete Ebook by ID
	@DeleteMapping(value = "/deleteBookById/{id}")
	public String deleteBookById(@PathVariable("id") Integer id) {
		libraryService.deleteBook(id);
		return "Deletion was successful with Id: " + id;
	}

	// 29 Query - Delete Book by id
	@DeleteMapping("/deleteBookByIdWithQuery/{id}")
	public void deleteBookWithQuery(@PathVariable("id") Integer id) {
		bookRepo.deleteBookByIdQuery(id);
	}

	// 30 Delete Book By Id with List
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void deleteById(@PathVariable("id") Integer id) {
		libraryService.delete(id);
	}

}
