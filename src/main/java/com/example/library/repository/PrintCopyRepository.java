package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.library.entity.PrintCopy;

@Repository
@EnableJpaRepositories
public interface PrintCopyRepository extends JpaRepository<PrintCopy, Integer>{

}
