package com.demo.libraryproject.repository;

import com.demo.libraryproject.model.PeriodOdrzavanja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodOdrzavanjaRepository extends JpaRepository<PeriodOdrzavanja, Integer> {
}
