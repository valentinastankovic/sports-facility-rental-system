/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.entity.impl;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import rs.ac.bg.fon.njt.NJTProjekat.entity.MyEntity;

/**
 *
 * @author AsusTuf
 */
@Entity
@Table(name="recenzija")
public class Recenzija implements MyEntity {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRecenzija;
    private LocalDate datumRecenzije;
    private int ocena;
    private String tekst;

    public Recenzija() {
    }

    public Recenzija(Integer idRecenzija) {
        this.idRecenzija = idRecenzija;
    }
    
    public Recenzija(Integer idRecenzija, LocalDate datumRecenzije, int ocena, String tekst) {
        this.idRecenzija = idRecenzija;
        this.datumRecenzije = datumRecenzije;
        this.ocena = ocena;
        this.tekst = tekst;
    }

    public Integer getIdRecenzija() {
        return idRecenzija;
    }

    public void setIdRecenzija(Integer idRecenzija) {
        this.idRecenzija = idRecenzija;
    }

    public LocalDate getDatumRecenzije() {
        return datumRecenzije;
    }

    public void setDatumRecenzije(LocalDate datumRecenzije) {
        this.datumRecenzije = datumRecenzije;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }
    
    
}
