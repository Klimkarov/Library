package com.example.library.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.library.entity.Book;

@Transactional // for Delete Query Method
@Repository
@EnableJpaRepositories
public interface BookRepository extends JpaRepository<Book, Integer> {

	// 1 Get All Order By ProductionYearAsc
	@Query("SELECT b FROM Book b")
	List<Book> GetAll();

	// 4 Get All Order By ProductionYearDesc
	// @Query("SELECT b FROM Book b ORDER BY b.productionYear Desc")
	List<Book> findAllByOrderByProductionYearDesc();

	// 5 Get All Order By ProductionYearAsc
	// @Query("SELECT b FROM Book b ORDER BY b.productionYear Asc")
	List<Book> findAllByOrderByProductionYearAsc();

	// 8 Get All Ebook And PrintCopy Books By Athors LastName
	// we can make input by one LETTER or word
	List<Book> findAllByAuthor_LastNameStartsWithIgnoreCase(String string);

	// 9 Get All Ebook And PrintCopy Books By Athors LastName
	// this method work only with input full word - lastName
	List<Book> findAllByAuthor_LastNameIgnoreCase(String lastName);

	// 10 Get With Query All Ebook And PrintCopy Books By Athors LastName StartWith input WORD
	// @Query("SELECT b FROM Book b WHERE b.author.lastName LIKE %?1%") - work with letter and word
    @Query("SELECT b FROM Book b WHERE b.author.lastName=:letter") // - Only with word
	List<Book> Query(@Param("letter") String letter);

	// 12, 13 Find all Authors with Their Books
	@Query(nativeQuery = true, value = "SELECT * FROM Book author_id")
	List<Book> findByAuthor();

	// 14 Get All Authors with id > 3
	@Query(nativeQuery = true, value = "select * from book where author_id > 3")
	List<Book> getAllAuthorsUp3();

	// 17 Get First And Last Book By Year
	Book findTop1ByOrderByProductionYearAsc();
	Book findTop1ByOrderByProductionYearDesc();

	// 29 Delete Book By Id
	@Modifying
	@Query("delete from Book b where b.id=:id")
	void deleteBookByIdQuery(@Param ("id") Integer id);

	

	
	

}
