package com.demo.libraryproject.service;

import com.demo.libraryproject.model.Clan;
import com.demo.libraryproject.model.Knjiga;
import com.demo.libraryproject.model.UzeteKnjige;

import java.text.ParseException;
import java.util.List;

public interface UsersBooksService {
    public List<UzeteKnjige> findAll();

    public UzeteKnjige findById(int id);

    public void save(UzeteKnjige uzeteKnjige);

    public void deleteById(int id);

    public List<UzeteKnjige> findAllByClan_UsernameAndDatumUzimanjaAndDatumVracanjaIsNull(String username);

    public void vratiKnjigu(UzeteKnjige uzeteKnjige) throws ParseException;

    public void uzmiKnjigu(Knjiga knjiga, String username);

    public List<UzeteKnjige> findAllByuObradiEquals(String uObradi);

    public void obradiZahtev(UzeteKnjige uzeteKnjige);

    public List<UzeteKnjige> findAllByUsername(String username);

    public List<UzeteKnjige> findAllByClan_UsernameAndAndDatumVracanjaIsNotNull(String username);

}
