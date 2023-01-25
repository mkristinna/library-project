package com.demo.libraryproject.controller;

import com.demo.libraryproject.model.Clan;
import com.demo.libraryproject.model.Knjiga;
import com.demo.libraryproject.service.BookService;
import com.demo.libraryproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminRestController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping("/books")
    public List<Knjiga> getBooks(){
        return bookService.findAll();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Knjiga> getBook(@PathVariable int id){
        Knjiga knjiga = bookService.findById(id);
        if(knjiga == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(knjiga);
        }
    }

    @PostMapping("/books/add")
    public Knjiga addBook(@RequestBody Knjiga knjiga){
        Knjiga knjiga1 = bookService.create(knjiga);
        return knjiga1;
    }


    @GetMapping("/clanovi")
    public List<Clan> getUsers(){
        return userService.findAll();
    }

    @GetMapping("/clanovi/{id}")
    public ResponseEntity<Clan> getUser(@PathVariable int id){
        Clan clan = userService.findById(id);
        if(clan == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(clan);
        }
    }

    @PostMapping("/clanovi/add")
    public Clan addUser(@RequestBody Clan clan){
        Clan clan1 = userService.create(clan);
        return clan1;
    }

}
