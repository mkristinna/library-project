package com.demo.libraryproject.service;

import com.demo.libraryproject.model.Clan;

import java.util.List;

public interface UserService {
    public List<Clan> findAll();
    public Clan findById(int id);
    public void save(Clan clan);
    public void deleteById(int id);
    public Clan findClanByUsername(String username);
    public Clan create(Clan clan);
}
