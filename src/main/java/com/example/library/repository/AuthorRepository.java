package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.library.entity.Author;

@Repository
@EnableJpaRepositories
public interface AuthorRepository extends JpaRepository<Author, Integer>{

}
