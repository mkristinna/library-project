package com.demo.libraryproject.service;

import com.demo.libraryproject.model.Knjiga;
import com.demo.libraryproject.model.PeriodOdrzavanja;
import com.demo.libraryproject.repository.PeriodOdrzavanjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionCacheOptimizer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeriodOdrzavanjaServiceImpl implements PeriodOdrzavanjaService{

    @Autowired
    private PeriodOdrzavanjaRepository periodOdrzavanjaRepository;

    @Override
    public List<PeriodOdrzavanja> findAll() {
        return periodOdrzavanjaRepository.findAll();
    }

    @Override
    public PeriodOdrzavanja findById(int id) {
        Optional<PeriodOdrzavanja> result = periodOdrzavanjaRepository.findById(id);
        PeriodOdrzavanja periodOdrzavanja = null;

        if(result.isPresent()){
            periodOdrzavanja = result.get();
        } else {
            throw new RuntimeException("id " + id + " nije pronadjen.");
        }

        return periodOdrzavanja;
    }

    @Override
    public void save(PeriodOdrzavanja periodOdrzavanja) {
        periodOdrzavanjaRepository.save(periodOdrzavanja);
    }

    @Override
    public void deleteById(int id) {
        periodOdrzavanjaRepository.deleteById(id);
    }
}
