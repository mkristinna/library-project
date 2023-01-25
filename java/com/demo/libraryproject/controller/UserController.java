package com.demo.libraryproject.controller;

import com.demo.libraryproject.model.Knjiga;
import com.demo.libraryproject.model.Recenzija;
import com.demo.libraryproject.model.UzeteKnjige;
import com.demo.libraryproject.security.CustomUserDetails;
import com.demo.libraryproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private BookService bookService;
    @Autowired
    private UsersBooksService usersBooksService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;
    @Autowired
    private SessionDBService sessionDBService;


    @GetMapping("/homepage")
    public String homepage(){
        return "/users/user-homepage";
    }

    @GetMapping("/books")
    public String bookList(Model model){
        List<Knjiga> knjige = bookService.findAll();
        model.addAttribute("knjige", knjige);
        return "users/book-list";
    }

    @GetMapping("/borrowedBooks")
    public String borrowedBooks(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model){
        String username = loggedUser.getUsername();
        List<UzeteKnjige> uzeteKnjige = usersBooksService.findAllByClan_UsernameAndDatumUzimanjaAndDatumVracanjaIsNull(username);
        model.addAttribute("uzeteKnjige", uzeteKnjige);
        return "users/borrowed-books";
    }

    @GetMapping("/readBooks")
    public String readBooks(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model){
        String username = loggedUser.getUsername();
        List<UzeteKnjige> uzeteKnjige = usersBooksService.findAllByClan_UsernameAndAndDatumVracanjaIsNotNull(username);
        model.addAttribute("uzeteKnjige", uzeteKnjige);
        return "users/read-books";
    }

    @GetMapping("/returnbook")
    public String returnBook(@AuthenticationPrincipal CustomUserDetails loggedUser, @RequestParam("idUzeteKnjige") int id, Model model) throws ParseException {
        UzeteKnjige uzeteKnjige = usersBooksService.findById(id);
        usersBooksService.vratiKnjigu(uzeteKnjige);
        model.addAttribute("uzeteKnjige", uzeteKnjige);
        return "redirect:/user/readBooks";
    }

    @GetMapping("/borrowbook")
    public String borrowBook(@AuthenticationPrincipal CustomUserDetails loggedUser, @RequestParam("idKnjige") int id, Model model){
        Knjiga knjiga = bookService.findById(id);
        usersBooksService.uzmiKnjigu(knjiga, loggedUser.getUsername());
        model.addAttribute("knjiga", knjiga);
        return "redirect:/user/borrowedBooks";
    }

    @GetMapping("/showFormForAddReview")
    public String showFormForAddReview(@RequestParam("idUzeteKnjige") int id, @AuthenticationPrincipal CustomUserDetails loggedUser, Model model){
        UzeteKnjige uzeteKnjige = usersBooksService.findById(id);
        Recenzija recenzija = new Recenzija();
        recenzija.setClan(userService.findClanByUsername(loggedUser.getUsername()));
        recenzija.setKnjiga(uzeteKnjige.getKnjiga());
        model.addAttribute("recenzija", recenzija);
        return "users/review-book";
    }

    @PostMapping("/addReview")
    public String addReview(@ModelAttribute("recenzija") Recenzija recenzija){
        reviewService.save(recenzija);
        return "redirect:/user/readBooks";
    }

    @GetMapping("/reviews")
    public String reviews(@RequestParam("idKnjige") int id, Model model){
        List<Recenzija> recenzije = reviewService.findAllByKnjiga_Id(id);
        model.addAttribute("recenzije", recenzije);
        return "users/book-reviews";
    }
}
