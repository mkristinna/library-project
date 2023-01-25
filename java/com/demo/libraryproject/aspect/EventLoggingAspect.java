package com.demo.libraryproject.aspect;

import com.demo.libraryproject.model.Clan;
import com.demo.libraryproject.model.Event;
import com.demo.libraryproject.model.Knjiga;
import com.demo.libraryproject.model.UzeteKnjige;
import com.demo.libraryproject.security.CustomUserDetails;
import com.demo.libraryproject.service.BookService;
import com.demo.libraryproject.service.EventService;
import com.demo.libraryproject.service.UserService;
import com.demo.libraryproject.service.UsersBooksService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class EventLoggingAspect {

    @Autowired
    EventService eventService;
    @Autowired
    BookService bookService;
    @Autowired
    UsersBooksService usersBooksService;
    @Autowired
    UserService userService;

    /*
    @After("execution(public String com.demo.libraryproject.controller.LoginController.login(..))")
    public void logging(JoinPoint joinPoint){
        System.out.println("logging is executing...");
        Object[] args = joinPoint.getArgs();
        CustomUserDetails loggedUser = (CustomUserDetails)args[0];
        Event event = new Event();
        event.setLoggedUser(loggedUser.getUsername());
        event.setKategorija(loggedUser.getAuthorities().toString());
        event.setTime(LocalDateTime.now());
        event.setNaziv("Logovanje");
        event.setDetalji("Korisnik se ulogovao");
        event.setStatus("Uspesno");
        eventService.save(event);

    }
*/
    @After("execution(public String com.demo.libraryproject.controller.UserController.borrowBook(..))")
    public void borrowingBook(JoinPoint joinPoint){
        System.out.println("borrowing book...");
        Object[] args = joinPoint.getArgs();
        CustomUserDetails loggedUser = (CustomUserDetails)args[0];
        Integer idKnjige = (Integer)args[1];
        Knjiga knjiga = bookService.findById(idKnjige);
        Event event = new Event();
        event.setLoggedUser(loggedUser.getUsername());
        event.setKategorija(loggedUser.getAuthorities().toString());
        event.setTime(LocalDateTime.now());
        event.setNaziv("Zahtev za knjigom");
        event.setDetalji(knjiga.getNaziv() + " - " + knjiga.getAutor());
        event.setStatus("Uspesno");
        eventService.save(event);
    }

    @After("execution(public String com.demo.libraryproject.controller.AdminController.process(..))")
    public void processingBookRequest(JoinPoint joinPoint){
        System.out.println("processing book request...");
        Object[] args = joinPoint.getArgs();
        Integer idUzeteKnjige = (Integer)args[0];
        UzeteKnjige uzeteKnjige = usersBooksService.findById(idUzeteKnjige);
        CustomUserDetails loggedUser = (CustomUserDetails)args[1];
        Event event = new Event();
        event.setLoggedUser(loggedUser.getUsername());
        event.setKategorija(loggedUser.getAuthorities().toString());
        event.setTime(LocalDateTime.now());
        event.setNaziv("Obrada zahteva za knjigom");
        event.setDetalji("Korisnik " + uzeteKnjige.getClan().getImeIPrezime() + " je uzeo knjigu " + uzeteKnjige.getKnjiga().getNaziv() + ", " + uzeteKnjige.getKnjiga().getAutor());
        event.setStatus("Uspesno");
        eventService.save(event);
    }

    @After("execution(public String com.demo.libraryproject.controller.AdminController.addUser(..))")
    public void addingUser(JoinPoint joinPoint){
        System.out.println("adding user...");
        Object[] args = joinPoint.getArgs();
        CustomUserDetails loggedUser = (CustomUserDetails)args[0];
        Clan clan = (Clan)args[1];
        Event event = new Event();
        event.setLoggedUser(loggedUser.getUsername());
        event.setKategorija(loggedUser.getAuthorities().toString());
        event.setTime(LocalDateTime.now());
        event.setNaziv("Dodavanje clana");
        event.setDetalji("Dodat clan " + clan.getImeIPrezime());
        event.setStatus("Uspesno");
        eventService.save(event);
    }

    @After("execution(public String com.demo.libraryproject.controller.AdminController.updateUser(..))")
    public void updatingUser(JoinPoint joinPoint){
        System.out.println("updating user...");
        Object[] args = joinPoint.getArgs();
        CustomUserDetails loggedUser = (CustomUserDetails)args[0];
        Clan clan = (Clan)args[1];
        Event event = new Event();
        event.setLoggedUser(loggedUser.getUsername());
        event.setKategorija(loggedUser.getAuthorities().toString());
        event.setTime(LocalDateTime.now());
        event.setNaziv("Izmena clana");
        event.setDetalji("Izmenjen clan " + clan.getImeIPrezime());
        event.setStatus("Uspesno");
        eventService.save(event);
    }

    @After("execution(public String com.demo.libraryproject.controller.AdminController.addBook(..))")
    public void addingBook(JoinPoint joinPoint){
        System.out.println("adding book...");
        Object[] args = joinPoint.getArgs();
        CustomUserDetails loggedUser = (CustomUserDetails)args[0];
        Knjiga knjiga = (Knjiga) args[1];
        Event event = new Event();
        event.setLoggedUser(loggedUser.getUsername());
        event.setKategorija(loggedUser.getAuthorities().toString());
        event.setTime(LocalDateTime.now());
        event.setNaziv("Dodavanje knjige");
        event.setDetalji("Dodata knjiga " + knjiga.getNaziv() + " - " + knjiga.getAutor());
        event.setStatus("Uspesno");
        eventService.save(event);
    }

    @After("execution(public String com.demo.libraryproject.controller.AdminController.updateBook(..))")
    public void updatingBook(JoinPoint joinPoint){
        System.out.println("updating book...");
        Object[] args = joinPoint.getArgs();
        CustomUserDetails loggedUser = (CustomUserDetails)args[0];
        Knjiga knjiga = (Knjiga) args[1];
        Event event = new Event();
        event.setLoggedUser(loggedUser.getUsername());
        event.setKategorija(loggedUser.getAuthorities().toString());
        event.setTime(LocalDateTime.now());
        event.setNaziv("Izmena knjige");
        event.setDetalji("Izmenjena knjiga " + knjiga.getNaziv() + " - " + knjiga.getAutor());
        event.setStatus("Uspesno");
        eventService.save(event);
    }

    @After("execution(public String com.demo.libraryproject.controller.UserController.returnBook(..))")
    public void returningBook(JoinPoint joinPoint){
        System.out.println("returning book...");
        Object[] args = joinPoint.getArgs();

        CustomUserDetails loggedUser = (CustomUserDetails) args[0];
        Integer idUzeteKnjige = (Integer) args[1];
        UzeteKnjige uzeteKnjige = usersBooksService.findById(idUzeteKnjige);

        Event event = new Event();
        event.setKategorija(loggedUser.getAuthorities().toString());
        event.setTime(LocalDateTime.now());
        event.setNaziv("Vracanje knjige");
        event.setLoggedUser(loggedUser.getUsername());
        event.setDetalji("Knjiga " + uzeteKnjige.getKnjiga().getNaziv() + ", " + uzeteKnjige.getKnjiga().getAutor() + " vracena");
        event.setStatus("Uspesno");
        eventService.save(event);
    }

    @Before("execution(public String com.demo.libraryproject.controller.AdminController.deleteBook(..))")
    public void deletingBook(JoinPoint joinPoint){
        System.out.println("deleting book...");
        Object[] args = joinPoint.getArgs();
        Integer idKnjige = (Integer)args[1];
        Knjiga knjiga = bookService.findById(idKnjige);
        CustomUserDetails loggedUser = (CustomUserDetails)args[0];
        Event event = new Event();
        event.setLoggedUser(loggedUser.getUsername());
        event.setKategorija(loggedUser.getAuthorities().toString());
        event.setTime(LocalDateTime.now());
        event.setNaziv("Brisanje knjige");
        event.setDetalji("Knjiga " + knjiga.getNaziv() + ", " + knjiga.getAutor() + " obrisana");
        event.setStatus("Uspesno");
        eventService.save(event);
    }

    @Before("execution(public String com.demo.libraryproject.controller.AdminController.deleteUser(..))")
    public void deletingUser(JoinPoint joinPoint){
        System.out.println("deleting user...");
        Object[] args = joinPoint.getArgs();
        Integer idClana = (Integer)args[1];
        Clan clan = userService.findById(idClana);
        CustomUserDetails loggedUser = (CustomUserDetails)args[0];
        Event event = new Event();
        event.setLoggedUser(loggedUser.getUsername());
        event.setKategorija(loggedUser.getAuthorities().toString());
        event.setTime(LocalDateTime.now());
        event.setNaziv("Brisanje clana");
        event.setDetalji("Clan " + clan.getUsername() + " obrisan");
        event.setStatus("Uspesno");
        eventService.save(event);
  }


}
