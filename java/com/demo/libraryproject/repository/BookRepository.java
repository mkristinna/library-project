package com.demo.libraryproject.repository;

import com.demo.libraryproject.model.Knjiga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Knjiga, Integer> {

}
