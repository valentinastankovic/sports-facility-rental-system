/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import rs.ac.bg.fon.njt.NJTProjekat.dto.Dto;

/**
 *
 * @author AsusTuf
 */
public class RecenzijaDto implements Dto{
    private Integer idRecenzija;
    @NotNull(message = "Datum je obavezan.")
    private LocalDate datumRecenzije;
    @NotNull(message = "Ocena je obavezna.")
    private int ocena;
    @NotBlank(message = "Tekst je obavezan.")
    @Size(max = 500, message = "Tekst moze imati najvise 500 karaktera.")
    private String tekst;

    public RecenzijaDto() {
    }

    public RecenzijaDto(Integer idRecenzija, LocalDate datumRecenzije, int ocena, String tekst) {
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
