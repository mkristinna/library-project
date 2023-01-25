package com.demo.libraryproject.repository;

import com.demo.libraryproject.model.SessionDB;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionDBRepository extends MongoRepository<SessionDB, String> {

    public SessionDB findByUsername(String username);
    public boolean existsByUsername(String username);
}
