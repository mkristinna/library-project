package com.demo.libraryproject.model;

import javax.persistence.*;

@Entity
@Table(name = "Recenzija")
public class Recenzija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recenzije")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_clana")
    private Clan clan;

    @ManyToOne
    @JoinColumn(name = "id_knjige")
    private Knjiga knjiga;

    @Column(name = "ocena")
    private int ocena;

    @Column(name = "komentar")
    private String komentar;

    public Recenzija(){}

    public Recenzija(Clan clan, Knjiga knjiga) {
        this.clan = clan;
        this.knjiga = knjiga;
    }

    public Recenzija(Clan clan, Knjiga knjiga, int ocena, String komentar) {
        this.clan = clan;
        this.knjiga = knjiga;
        this.ocena = ocena;
        this.komentar = komentar;
    }

    public Recenzija(Clan clan, Knjiga knjiga, int ocena) {
        this.clan = clan;
        this.knjiga = knjiga;
        this.ocena = ocena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
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

    @Override
    public String toString() {
        return "Recenzija{" +
                "id=" + id +
                ", clan=" + clan +
                ", knjiga=" + knjiga +
                ", ocena=" + ocena +
                ", komentar='" + komentar + '\'' +
                '}';
    }
}
