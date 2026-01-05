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
public class MestoDto implements Dto {
    private int idMesto;
    @NotBlank(message = "Naziv mesta je obavezan.")
    private String naziv;

    public MestoDto() {
    }

    public MestoDto(int idMesto, String naziv) {
        this.idMesto = idMesto;
        this.naziv = naziv;
    }

    public int getIdMesto() {
        return idMesto;
    }

    public void setIdMesto(int idMesto) {
        this.idMesto = idMesto;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

  
    
    
}
