package com.demo.libraryproject.service;

import com.demo.libraryproject.model.Knjiga;
import com.demo.libraryproject.model.Recenzija;
import com.demo.libraryproject.model.UzeteKnjige;
import com.demo.libraryproject.repository.ReviewRepository;
import com.demo.libraryproject.repository.UserRepository;
import com.demo.libraryproject.repository.UsersBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(){}

    @Override
    public List<Recenzija> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Recenzija findById(int id) {
        Optional<Recenzija> result = reviewRepository.findById(id);
        Recenzija recenzija = null;

        if(result.isPresent()){
            recenzija = result.get();
        } else {
            throw new RuntimeException("id " + id + " recenzije nije pronadjen.");
        }

        return recenzija;
    }

    @Override
    public void save(Recenzija recenzija) {
        reviewRepository.save(recenzija);
    }

    @Override
    public void deleteById(int id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public List<Recenzija> findAllByKnjiga_Id(int id) {
        return reviewRepository.findAllByKnjiga_Id(id);
    }
}
