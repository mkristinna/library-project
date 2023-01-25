package com.demo.libraryproject.service;

import com.demo.libraryproject.model.Knjiga;
import com.demo.libraryproject.model.Recenzija;

import java.util.List;

public interface ReviewService {
    public List<Recenzija> findAll();
    public Recenzija findById(int id);
    public void save(Recenzija recenzija);
    public void deleteById(int id);
    public List<Recenzija> findAllByKnjiga_Id(int id);
}
