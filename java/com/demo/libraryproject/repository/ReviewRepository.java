package com.demo.libraryproject.repository;

import com.demo.libraryproject.model.Recenzija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReviewRepository extends JpaRepository<Recenzija, Integer> {
    public List<Recenzija> findAllByKnjiga_Id(int id);
}
