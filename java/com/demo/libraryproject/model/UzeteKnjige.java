package com.demo.libraryproject.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "Uzete_knjige")
public class UzeteKnjige {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_clana")
    private Clan clan;

    @ManyToOne
    @JoinColumn(name = "id_knjige")
    private Knjiga knjiga;

    @Column(name = "u_obradi")
    private String uObradi;

    @Column(name = "datum_uzimanja")
    private LocalDate datumUzimanja;

    @Column(name = "datum_vracanja")
    private LocalDate datumVracanja;

    public UzeteKnjige(){}

    public UzeteKnjige(Clan clan, Knjiga knjiga, LocalDate datumUzimanja, LocalDate datumVracanja) {
        this.clan = clan;
        this.knjiga = knjiga;
        this.datumUzimanja = datumUzimanja;
        this.datumVracanja = datumVracanja;
    }

    public UzeteKnjige(Clan clan, Knjiga knjiga, LocalDate datumUzimanja) {
        this.clan = clan;
        this.knjiga = knjiga;
        this.datumUzimanja = datumUzimanja;
    }

    public UzeteKnjige(Clan clan, Knjiga knjiga) {
        this.clan = clan;
        this.knjiga = knjiga;
    }

    public UzeteKnjige(Clan clan, Knjiga knjiga, String uObradi) {
        this.clan = clan;
        this.knjiga = knjiga;
        this.uObradi = uObradi;
    }

    public UzeteKnjige(Clan clan, Knjiga knjiga, String uObradi, LocalDate datumUzimanja, LocalDate datumVracanja) {
        this.clan = clan;
        this.knjiga = knjiga;
        this.uObradi = uObradi;
        this.datumUzimanja = datumUzimanja;
        this.datumVracanja = datumVracanja;
    }

    public UzeteKnjige(Clan clan, Knjiga knjiga, String uObradi, LocalDate datumUzimanja) {
        this.clan = clan;
        this.knjiga = knjiga;
        this.uObradi = uObradi;
        this.datumUzimanja = datumUzimanja;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

    public LocalDate getDatumUzimanja() {
        return datumUzimanja;
    }

    public void setDatumUzimanja(LocalDate datumUzimanja) {
        this.datumUzimanja = datumUzimanja;
    }

    public LocalDate getDatumVracanja() {
        return this.datumVracanja;
    }

    public void setDatumVracanja(LocalDate datumVracanja) {
        this.datumVracanja = datumVracanja;
    }

    public String getuObradi() {
        if(uObradi == null){
            return "";
        }
        return uObradi;
    }

    public void setuObradi(String uObradi) {
        this.uObradi = uObradi;
    }

    @Override
    public String toString() {
        return "UzeteKnjige{" +
                "clan=" + clan +
                ", knjiga=" + knjiga +
                ", datumUzimanja=" + datumUzimanja +
                ", datumVracanja=" + datumVracanja +
                '}';
    }

    public boolean twoWeeksPassed(){
        LocalDate now = LocalDate.now();
        if(ChronoUnit.DAYS.between(this.datumUzimanja, now) >= 14){
            return true;
        }
        return false;
    }

}
