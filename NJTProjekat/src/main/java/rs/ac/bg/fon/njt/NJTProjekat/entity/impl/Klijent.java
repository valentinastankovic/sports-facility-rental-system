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
import rs.ac.bg.fon.njt.NJTProjekat.entity.MyEntity;

/**
 *
 * @author hallo
 */
@Entity
@Table(name = "klijent")
public class Klijent implements MyEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idKlijent;
    private String ime;
    private String prezime;
    private String brojTelefona;
    private String brojLicneKarte;
    private String email;
    
    @ManyToOne
    @JoinColumn(name = "MestoId")
    private Mesto mesto;

    public Klijent() {
    }

    public Klijent(Integer idKlijent, String ime, String prezime, String brojTelefona, String brojLicneKarte, String email, Mesto mesto) {
        this.idKlijent = idKlijent;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.brojLicneKarte = brojLicneKarte;
        this.email = email;
        this.mesto = mesto;
    }

    public Klijent(Integer idKlijent) {
        this.idKlijent = idKlijent;
    }

    public Integer getIdKlijent() {
        return idKlijent;
    }

    public void setIdKlijent(Integer idKlijent) {
        this.idKlijent = idKlijent;
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

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getBrojLicneKarte() {
        return brojLicneKarte;
    }

    public void setBrojLicneKarte(String brojLicneKarte) {
        this.brojLicneKarte = brojLicneKarte;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }
    
    
    
}
