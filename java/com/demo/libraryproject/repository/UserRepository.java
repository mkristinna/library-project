package com.demo.libraryproject.repository;

import com.demo.libraryproject.model.Clan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Clan, Integer> {
    public Clan findClanByUsername(String username);
}
