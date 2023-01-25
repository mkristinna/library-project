package com.demo.libraryproject.service;

import com.demo.libraryproject.model.Clan;
import com.demo.libraryproject.model.Knjiga;
import com.demo.libraryproject.model.UzeteKnjige;
import com.demo.libraryproject.repository.UserRepository;
import com.demo.libraryproject.repository.UsersBooksRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsersBooksServiceImpl implements UsersBooksService{

    @Autowired
    private UsersBooksRepository usersBooksRepository;
    @Autowired
    private UserRepository userRepository;

    public UsersBooksServiceImpl(){}

    @Override
    public List<UzeteKnjige> findAll() {
        return usersBooksRepository.findAll();
    }

    @Override
    public UzeteKnjige findById(int id) {
        Optional<UzeteKnjige> result = usersBooksRepository.findById(id);
        UzeteKnjige uzeteKnjige = null;

        if(result.isPresent()){
            uzeteKnjige = result.get();
        }
        else {
            throw new RuntimeException("Id not found");
        }

        return uzeteKnjige;
    }

    @Override
    public void save(UzeteKnjige uzeteKnjige) {
        usersBooksRepository.save(uzeteKnjige);
    }

    @Override
    public void deleteById(int id) {
        usersBooksRepository.deleteById(id);
    }

    @Override
    public List<UzeteKnjige> findAllByClan_UsernameAndDatumUzimanjaAndDatumVracanjaIsNull(String username) {
        return usersBooksRepository.findAllByClan_UsernameAndDatumUzimanjaIsNotNullAndDatumVracanjaIsNull(username);
    }

    @Override
    public void vratiKnjigu(UzeteKnjige uzeteKnjige) throws ParseException {
        LocalDate date = LocalDate.now();
        uzeteKnjige.setDatumVracanja(date);
        usersBooksRepository.save(uzeteKnjige);
    }

    @Override
    public void uzmiKnjigu(Knjiga knjiga, String username) {
        UzeteKnjige uzeteKnjige = new UzeteKnjige();
        uzeteKnjige.setKnjiga(knjiga);
        uzeteKnjige.setClan(userRepository.findClanByUsername(username));
        uzeteKnjige.setuObradi("DA");
        usersBooksRepository.save(uzeteKnjige);
    }

    @Override
    public List<UzeteKnjige> findAllByuObradiEquals(String uObradi) {
        return usersBooksRepository.findAllByuObradiEquals(uObradi);
    }

    @Override
    public void obradiZahtev(UzeteKnjige uzeteKnjige) {
        LocalDate date = LocalDate.now();
        uzeteKnjige.setuObradi("NE");
        uzeteKnjige.setDatumUzimanja(date);
        usersBooksRepository.save(uzeteKnjige);
    }

    @Override
    public List<UzeteKnjige> findAllByUsername(String username) {
        return usersBooksRepository.findAllByClan_Username(username);
    }

    @Override
    public List<UzeteKnjige> findAllByClan_UsernameAndAndDatumVracanjaIsNotNull(String username) {
        return usersBooksRepository.findAllByClan_UsernameAndAndDatumVracanjaIsNotNull(username);
    }
}
