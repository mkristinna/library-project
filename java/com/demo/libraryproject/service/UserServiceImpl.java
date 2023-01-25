package com.demo.libraryproject.service;

import com.demo.libraryproject.model.Clan;
import com.demo.libraryproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(){}

    @Override
    public List<Clan> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Clan findById(int id) {
        Optional<Clan> result = userRepository.findById(id);
        Clan clan = null;

        if(result.isPresent()){
            clan = result.get();
        } else {
            throw new RuntimeException("Ne postoji clan");
        }
        return clan;
    }

    @Override
    public void save(Clan clan) {
        userRepository.save(clan);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public Clan findClanByUsername(String username){
        return userRepository.findClanByUsername(username);
    }

    @Override
    public Clan create(Clan clan) {
        if(clan.getAktivan() == null){
            clan.setAktivan("DA");
        } else if(clan.getKategorija() == null){
            clan.setKategorija("USER");
        }

        userRepository.save(clan);
        return clan;
    }
}
