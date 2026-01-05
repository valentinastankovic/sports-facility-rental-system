/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.dto.impl;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;
import rs.ac.bg.fon.njt.NJTProjekat.dto.Dto;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.TipTerena;

/**
 *
 * @author AsusTuf
 */
public class SportskiTerenDto implements Dto {

    private int idSportskiTeren;
    @NotEmpty(message = "Naziv je obavezan.")
    private String nazivTerena;
    @NotBlank(message = "Lokacija je obavezna.")
    private String lokacija;
    @NotNull(message = "Cena po satu je obavezna.")
    @Positive(message = "Cena po satu mora biti veća od nule.")
    private double cenaPoSatu;
    
//    @NotNull(message = "Tip terena je obavezan.")
//    private TipTerena tipTerena;
    
    private Integer tipTerenaId;

    public SportskiTerenDto() {
    }

    public SportskiTerenDto(int idSportskiTeren, String nazivTerena, String lokacija, double cenaPoSatu,  Integer tipTerenaId) {
        this.idSportskiTeren = idSportskiTeren;
        this.nazivTerena = nazivTerena;
        this.lokacija = lokacija;
        this.cenaPoSatu = cenaPoSatu;
        this.tipTerenaId = tipTerenaId;
    }

    

    public int getIdSportskiTeren() {
        return idSportskiTeren;
    }

    public void setIdSportskiTeren(int idSportskiTeren) {
        this.idSportskiTeren = idSportskiTeren;
    }

    public String getNazivTerena() {
        return nazivTerena;
    }

    public void setNazivTerena(String nazivTerena) {
        this.nazivTerena = nazivTerena;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public double getCenaPoSatu() {
        return cenaPoSatu;
    }

    public void setCenaPoSatu(double cenaPoSatu) {
        this.cenaPoSatu = cenaPoSatu;
    }

    

    public Integer getTipTerenaId() {
        return tipTerenaId;
    }

    public void setTipTerenaId(Integer tipTerenaId) {
        this.tipTerenaId = tipTerenaId;
    }

  

}
