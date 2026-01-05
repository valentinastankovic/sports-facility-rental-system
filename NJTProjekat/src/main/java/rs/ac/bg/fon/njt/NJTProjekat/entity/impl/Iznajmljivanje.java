/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.entity.impl;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import rs.ac.bg.fon.njt.NJTProjekat.entity.MyEntity;

/**
 *
 * @author AsusTuf
 */
@Entity
@Table(name = "iznajmljivanje")

public class Iznajmljivanje implements MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIznajmljivanje;
    private LocalDate datumPlacanja;
    private LocalDate datumIznajmljivanja;
    private LocalTime vremeOd;
    private LocalTime vremeDo;
    private int ukupnoSati;
    private double ukupanIznos;
    private String nacinPlacanja;
    
    @ManyToOne
    @JoinColumn(name = "ZaposleniId")
    private Zaposleni zaposleni;
    @ManyToOne
    @JoinColumn(name = "KlijentId")
    private Klijent klijent;
    @ManyToOne
    @JoinColumn(name = "SportskiTerenId")
    private SportskiTeren sportskiTeren;
    @ManyToOne
    @JoinColumn(name = "RecenzijaId")
    private Recenzija recenzija;
    

    public Iznajmljivanje() {
    }

    public Iznajmljivanje(Integer idIznajmljivanje, LocalDate datumPlacanja, LocalDate datumIznajmljivanja, LocalTime vremeOd, LocalTime vremeDo, int ukupnoSati, double ukupanIznos, String nacinPlacanja, Zaposleni zaposleni, Klijent klijent, SportskiTeren sportskiTeren, Recenzija recenzija) {
        this.idIznajmljivanje = idIznajmljivanje;
        this.datumPlacanja = datumPlacanja;
        this.datumIznajmljivanja = datumIznajmljivanja;
        this.vremeOd = vremeOd;
        this.vremeDo = vremeDo;
        this.ukupnoSati = ukupnoSati;
        this.ukupanIznos = ukupanIznos;
        this.nacinPlacanja = nacinPlacanja;
        this.zaposleni = zaposleni;
        this.klijent = klijent;
        this.sportskiTeren = sportskiTeren;
        this.recenzija = recenzija;
    }

    

    public Integer getIdIznajmljivanje() {
        return idIznajmljivanje;
    }

    public void setIdIznajmljivanje(Integer idIznajmljivanje) {
        this.idIznajmljivanje = idIznajmljivanje;
    }

    public LocalDate getDatumPlacanja() {
        return datumPlacanja;
    }

    public void setDatumPlacanja(LocalDate datumPlacanja) {
        this.datumPlacanja = datumPlacanja;
    }

    public LocalDate getDatumIznajmljivanja() {
        return datumIznajmljivanja;
    }

    public void setDatumIznajmljivanja(LocalDate datumIznajmljivanja) {
        this.datumIznajmljivanja = datumIznajmljivanja;
    }

    public LocalTime getVremeOd() {
        return vremeOd;
    }

    public void setVremeOd(LocalTime vremeOd) {
        this.vremeOd = vremeOd;
    }

    public LocalTime getVremeDo() {
        return vremeDo;
    }

    public void setVremeDo(LocalTime vremeDo) {
        this.vremeDo = vremeDo;
    }

    public int getUkupnoSati() {
        return ukupnoSati;
    }

    public void setUkupnoSati(int ukupnoSati) {
        this.ukupnoSati = ukupnoSati;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public String getNacinPlacanja() {
        return nacinPlacanja;
    }

    public void setNacinPlacanja(String nacinPlacanja) {
        this.nacinPlacanja = nacinPlacanja;
    }

    public Recenzija getRecenzija() {
        return recenzija;
    }

    public void setRecenzija(Recenzija recenzija) {
        this.recenzija = recenzija;
    }

    

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public SportskiTeren getSportskiTeren() {
        return sportskiTeren;
    }

    public void setSportskiTeren(SportskiTeren sportskiTeren) {
        this.sportskiTeren = sportskiTeren;
    }

}
