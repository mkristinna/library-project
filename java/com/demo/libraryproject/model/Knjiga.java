package com.demo.libraryproject.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Knjiga")
public class Knjiga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_knjige")
    private int id;

    @Column(name = "naziv")
    public String naziv;

    @Column(name = "autor")
    private String autor;

    @Column(name = "godina_izdanja")
    private Integer godinaIzdanja;

    @Column(name = "isbn")
    private Long isbn;

    @OneToMany(mappedBy = "knjiga", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<UzeteKnjige> spisakUzetih;

    @OneToMany(mappedBy = "knjiga", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Recenzija> recenzije;

    @Transient
    private String odeljak;

    public Knjiga(){}

    public Knjiga(String naziv, String autor, int godinaIzdanja, long isbn) {
        this.naziv = naziv;
        this.autor = autor;
        this.godinaIzdanja = godinaIzdanja;
        this.isbn = isbn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getGodinaIzdanja() {
        return godinaIzdanja;
    }

    public void setGodinaIzdanja(Integer godinaIzdanja) {
        this.godinaIzdanja = godinaIzdanja;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public List<UzeteKnjige> getSpisakUzetih() {
        return spisakUzetih;
    }

    public void setSpisakUzetih(List<UzeteKnjige> spisakUzetih) {
        this.spisakUzetih = spisakUzetih;
    }

    public List<Recenzija> getRecenzije() {
        return recenzije;
    }

    public void setRecenzije(List<Recenzija> recenzije) {
        this.recenzije = recenzije;
    }

    @Override
    public String toString() {
        return "Knjiga{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", autor='" + autor + '\'' +
                ", godinaIzdanja=" + godinaIzdanja +
                ", isbn=" + isbn +
                ", spisakUzetih=" + spisakUzetih +
                ", recenzije=" + recenzije +
                '}';
    }

    public boolean getUzeta(){
        if(this.spisakUzetih != null) {
            for (UzeteKnjige uzeteKnjige : this.spisakUzetih) {
                if (uzeteKnjige.getKnjiga().equals(this) && uzeteKnjige.getDatumVracanja() == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getOdeljak() {
        return odeljak;
    }

    public void setOdeljak(String odeljak) {
        this.odeljak = odeljak;
    }
}
