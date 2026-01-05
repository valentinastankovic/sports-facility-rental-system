/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.dto.impl;

import jakarta.validation.constraints.NotBlank;
import rs.ac.bg.fon.njt.NJTProjekat.dto.Dto;

/**
 *
 * @author hallo
 */
public class KlijentDto implements Dto {
    private int idKlijent;
    @NotBlank(message = "Ime je obavezno.")
    private String ime;
    @NotBlank(message = "Prezime je obavezno.")
    private String prezime;
    @NotBlank(message = "Broj telefona je obavezan.")
    private String broj_telefona;
    @NotBlank(message = "Broj licne karte je obavezan.")
    private String broj_licne_karte;
    @NotBlank(message = "Email je obavezan.")
    private String email;
    private Integer mesto_id;

    public KlijentDto() {
    }

    public KlijentDto(int idKlijent, String ime, String prezime, String broj_telefona, String broj_licne_karte, String email, Integer mesto_id) {
        this.idKlijent = idKlijent;
        this.ime = ime;
        this.prezime = prezime;
        this.broj_telefona = broj_telefona;
        this.broj_licne_karte = broj_licne_karte;
        this.email = email;
        this.mesto_id = mesto_id;
    }

  

    
    public int getIdKlijent() {
        return idKlijent;
    }

    public void setIdKlijent(int idKlijent) {
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

    public String getBroj_telefona() {
        return broj_telefona;
    }

    public void setBroj_telefona(String broj_telefona) {
        this.broj_telefona = broj_telefona;
    }

    public String getBroj_licne_karte() {
        return broj_licne_karte;
    }

    public void setBroj_licne_karte(String broj_licne_karte) {
        this.broj_licne_karte = broj_licne_karte;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMesto_id() {
        return mesto_id;
    }

    public void setMesto_id(Integer mesto_id) {
        this.mesto_id = mesto_id;
    }

    
    
    
    
}
