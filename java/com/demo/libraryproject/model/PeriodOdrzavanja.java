package com.demo.libraryproject.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "period_odrzavanja")
public class PeriodOdrzavanja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "pocetak")
    private LocalDateTime pocetak;

    @Column(name = "kraj")
    private LocalDateTime kraj;

    @Column(name = "komentar")
    private String komentar;

    public PeriodOdrzavanja(LocalDateTime pocetak, LocalDateTime kraj, String komentar) {
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.komentar = komentar;
    }

    public PeriodOdrzavanja() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getPocetak() {
        return pocetak;
    }

    public void setPocetak(LocalDateTime pocetak) {
        this.pocetak = pocetak;
    }

    public LocalDateTime getKraj() {
        return kraj;
    }

    public void setKraj(LocalDateTime kraj) {
        this.kraj = kraj;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public boolean isBetween(LocalDateTime dateTime){
        if(dateTime.compareTo(this.pocetak)>0 && dateTime.compareTo(this.kraj)<0){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "PeriodOdrzavanja{" +
                "id=" + id +
                ", pocetak=" + pocetak +
                ", kraj=" + kraj +
                ", komentar='" + komentar + '\'' +
                '}';
    }
}
