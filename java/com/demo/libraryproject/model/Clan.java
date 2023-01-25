package com.demo.libraryproject.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Clan")
public class Clan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clana")
    private int id;

    @Column(name = "ime")
    private String ime;

    @Column(name = "prezime")
    private String prezime;

    @Column(name = "username")
    private String username;

    @Column(name = "lozinka")
    private String lozinka;

    @Column(name = "kategorija")
    private String kategorija;

    @Column(name = "aktivan")
    private String aktivan;

    @OneToMany(mappedBy = "clan", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<UzeteKnjige> uzeteKnjige;

    @OneToMany(mappedBy = "clan", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Recenzija> recenzije;

    public Clan() {
    }

    public Clan(String ime, String prezime, String username, String lozinka) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.lozinka = lozinka;
    }

    public Clan(String ime, String prezime, String username, String lozinka, String kategorija, String aktivan) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.lozinka = lozinka;
        this.kategorija = kategorija;
        this.aktivan = aktivan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public List<UzeteKnjige> getUzeteKnjige() {
        return uzeteKnjige;
    }

    public void setUzeteKnjige(List<UzeteKnjige> uzeteKnjige) {
        this.uzeteKnjige = uzeteKnjige;
    }

    public String getAktivan() {
        return aktivan;
    }

    public void setAktivan(String aktivan) {
        this.aktivan = aktivan;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        this.lozinka = encoder.encode(lozinka);
    }

    public List<Recenzija> getRecenzije() {
        return recenzije;
    }

    public void setRecenzije(List<Recenzija> recenzije) {
        this.recenzije = recenzije;
    }

    @Override
    public String toString() {
        return "Clan{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", username='" + username + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", kategorija='" + kategorija + '\'' +
                ", aktivan='" + aktivan + '\'' +
                ", uzeteKnjige=" + uzeteKnjige +
                ", recenzije=" + recenzije +
                '}';
    }

    public String getImeIPrezime() {
        return this.ime + " " + this.prezime;
    }

    public List<Knjiga> getKnjigeSaRecenzijom(){
        List<Knjiga> knjigeSaRecenzijom = new ArrayList<>();
        if(this.recenzije != null) {
            for (Recenzija recenzija : this.recenzije) {
                if (!knjigeSaRecenzijom.contains(recenzija.getKnjiga())) {
                    knjigeSaRecenzijom.add(recenzija.getKnjiga());
                }
            }
        }
        return knjigeSaRecenzijom;

    }

}

