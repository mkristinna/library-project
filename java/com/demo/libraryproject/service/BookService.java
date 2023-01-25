package com.demo.libraryproject.service;

import com.demo.libraryproject.model.Knjiga;

import java.util.List;

public interface BookService {
    public List<Knjiga> findAll();
    public Knjiga findById(int id);
    public void save(Knjiga knjiga);
    public void deleteById(int id);
    public Knjiga create(Knjiga knjiga);
}
