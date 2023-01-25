package com.demo.libraryproject.controller;

import com.demo.libraryproject.model.Clan;
import com.demo.libraryproject.model.Knjiga;
import com.demo.libraryproject.model.Recenzija;
import com.demo.libraryproject.model.UzeteKnjige;
import com.demo.libraryproject.security.CustomUserDetails;
import com.demo.libraryproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private UsersBooksService usersBooksService;
    @Autowired
    private ReviewService reviewService;

    @Bean
    private ReviewService reviewService(){
        return new ReviewServiceImpl();
    }

    // user related endpoints

    @GetMapping("/homepage")
    public String adminHomepage(){
        return "managers/admin-homepage";
    }

    @GetMapping("/users")
    public String listUsers(Model model){
        List<Clan> clanovi = userService.findAll();
        model.addAttribute("clanovi", clanovi);
        return "managers/list-users";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Clan clan = new Clan();
        model.addAttribute("clan", clan);
        return "managers/add-user-form";
    }

    @PostMapping("/add")
    public String addUser(@AuthenticationPrincipal CustomUserDetails loggedUser, @ModelAttribute("clan") Clan clan){
        userService.save(clan);
        return "redirect:/admin/users";
    }

    @PostMapping("/update")
    public String updateUser(@AuthenticationPrincipal CustomUserDetails loggedUser, @ModelAttribute("clan") Clan clan){
        userService.save(clan);
        return "redirect:/admin/users";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("idClana") int id, Model model){
        Clan clan = userService.findById(id);
        model.addAttribute("clan", clan);
        return "managers/update-user-form";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@AuthenticationPrincipal CustomUserDetails loggedUser, @RequestParam("idClana") int id){
        userService.deleteById(id);
        return "redirect:/admin/users";
    }

    // books related endpoints


    @GetMapping("/books")
    public String listBooks(Model model){
        List<Knjiga> knjige = bookService.findAll();
        model.addAttribute("knjige", knjige);
        return "managers/list-books";
    }

    @GetMapping("/deleteBook")
    public String deleteBook(@AuthenticationPrincipal CustomUserDetails loggedUser, @RequestParam("idKnjige") int id){
        bookService.deleteById(id);
        return "redirect:/admin/books";
    }

    @GetMapping("/showFormForAddBook")
    public String showFormForAddBook(Model model){
        Knjiga knjiga = new Knjiga();
        model.addAttribute("knjiga",knjiga);

        return "managers/add-book-form";
    }

    @PostMapping("/addBook")
    public String addBook(@AuthenticationPrincipal CustomUserDetails loggedUser, @ModelAttribute("knjiga") Knjiga knjiga){
        bookService.save(knjiga);
        return "redirect:/admin/books";
    }

    @GetMapping("/showFormForBookUpdate")
    public String showFormForBookUpdate(@RequestParam("idKnjige") int id, Model model){
        Knjiga knjiga = bookService.findById(id);
        model.addAttribute("knjiga", knjiga);
        return "managers/update-book-form";
    }

    @PostMapping("/updateBook")
    public String updateBook(@AuthenticationPrincipal CustomUserDetails loggedUser, @ModelAttribute("knjiga") Knjiga knjiga){
        bookService.save(knjiga);
        return "redirect:/admin/books";
    }

    // users - books related endpoints


    @GetMapping("/usersbooks")
    public String listUsersBooks(Model model){
        List<UzeteKnjige> uzeteKnjige = usersBooksService.findAll();
        model.addAttribute("uzeteKnjige", uzeteKnjige);
        return "managers/list-users-books";
    }

    @GetMapping("/requests")
    public String requests(Model model){
        List<UzeteKnjige> uzeteKnjige = usersBooksService.findAllByuObradiEquals("DA");
        model.addAttribute("uzeteKnjige", uzeteKnjige);
        return "managers/list-users-books-requests";
    }

    @GetMapping("/process")
    public String process(@RequestParam("idUzeteKnjige") int id, @AuthenticationPrincipal CustomUserDetails loggedUser, Model model){
        UzeteKnjige uzeteKnjige = usersBooksService.findById(id);
        usersBooksService.obradiZahtev(uzeteKnjige);
        model.addAttribute("uzeteKnjige", uzeteKnjige);
        return "redirect:/admin/requests";
    }

    @GetMapping("/reviews")
    public String reviews(@RequestParam("idKnjige") int id, Model model){
        List<Recenzija> recenzije = reviewService.findAllByKnjiga_Id(id);
        model.addAttribute("recenzije", recenzije);
        return "managers/book-reviews";
    }

}
