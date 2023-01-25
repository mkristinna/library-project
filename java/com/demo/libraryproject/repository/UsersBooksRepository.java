package com.demo.libraryproject.repository;

import com.demo.libraryproject.model.UzeteKnjige;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersBooksRepository extends JpaRepository<UzeteKnjige, Integer> {

    public List<UzeteKnjige> findAllByClan_UsernameAndDatumUzimanjaIsNotNullAndDatumVracanjaIsNull(String username);
    public List<UzeteKnjige> findAllByuObradiEquals(String name);
    public List<UzeteKnjige> findAllByClan_Username(String username);
    public List<UzeteKnjige> findAllByClan_UsernameAndAndDatumVracanjaIsNotNull(String username);
}
