package com.demo.libraryproject.service;

import com.demo.libraryproject.model.PeriodOdrzavanja;

import java.util.List;

public interface PeriodOdrzavanjaService {
    public List<PeriodOdrzavanja> findAll();
    public PeriodOdrzavanja findById(int id);
    public void save(PeriodOdrzavanja periodOdrzavanja);
    public void deleteById(int id);
}
