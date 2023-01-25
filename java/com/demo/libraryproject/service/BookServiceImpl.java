package com.demo.libraryproject.service;

import com.demo.libraryproject.model.Knjiga;
import com.demo.libraryproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

    public BookServiceImpl(){}

    @Override
    public List<Knjiga> findAll() {
        List<Knjiga> knjige = bookRepository.findAll();
        Collections.sort(knjige, new Comparator<Knjiga>() {
            @Override
            public int compare(Knjiga k1, Knjiga k2) {
                return k1.getAutor().compareTo(k2.getAutor());
            }
        });
        return knjige;
    }

    @Override
    public Knjiga findById(int id) {
        Optional<Knjiga> result = bookRepository.findById(id);
        Knjiga knjiga = null;

        if(result.isPresent()){
            knjiga = result.get();
        } else {
            throw new RuntimeException("id " + id + " knjige nije pronadjen.");
        }

        return knjiga;
    }

    @Override
    public void save(Knjiga knjiga) {
        bookRepository.save(knjiga);
    }

    @Override
    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Knjiga create(Knjiga knjiga) {
        if(knjiga.getGodinaIzdanja() == null){
            knjiga.setGodinaIzdanja(2022);
        } else if(knjiga.getAutor() == null){
            knjiga.setAutor("Nepoznat");
        } else if(knjiga.getIsbn() == null){
            knjiga.setIsbn(0L);
        } else if(knjiga.getSpisakUzetih() == null){
            knjiga.setSpisakUzetih(new ArrayList<>());
        } else if(knjiga.getRecenzije() == null){
            knjiga.setRecenzije(new ArrayList<>());
        } else if(knjiga.getOdeljak() == null){
            knjiga.setOdeljak("N");
        }
        bookRepository.save(knjiga);
        return knjiga;
    }
}
