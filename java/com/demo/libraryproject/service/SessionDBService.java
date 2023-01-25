package com.demo.libraryproject.service;

import com.demo.libraryproject.model.SessionDB;
import com.demo.libraryproject.repository.SessionDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionDBService {

    @Autowired
    private SessionDBRepository repository;

    public SessionDB findById(String id){
        Optional<SessionDB> result = repository.findById(id);
        SessionDB sessionDB = null;

        if(result.isPresent()){
            sessionDB = result.get();
        } else {
            throw new RuntimeException("Cannot find session with id " + id);
        }
        return sessionDB;
    }

    public List<SessionDB> findAll(){
        return repository.findAll();
    }

    public void save(SessionDB sessionDB){
        repository.save(sessionDB);
    }

    public void deleteById(String id){
        repository.deleteById(id);
    }

    public SessionDB findByUsername(String username){
        return repository.findByUsername(username);
    }

    public boolean existsByUsername(String username){
        return repository.existsByUsername(username);
    }
}
