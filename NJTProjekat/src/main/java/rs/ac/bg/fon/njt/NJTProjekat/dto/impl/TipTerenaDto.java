/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.dto.impl;

import jakarta.validation.constraints.*;
import rs.ac.bg.fon.njt.NJTProjekat.dto.Dto;

/**
 *
 * @author AsusTuf
 */
public class TipTerenaDto implements Dto{
    private int idTipTerena;
    @NotEmpty(message = "Tip je obavezan.")
    private String tip;
    @NotBlank(message = "Opis je obavezan.")
    @Size(max = 500, message = "Opis moze imati najvise 500 karaktera.")
    private String opis;

    public TipTerenaDto() {
    }

    public TipTerenaDto(int idTipTerena, String tip, String opis) {
        this.idTipTerena = idTipTerena;
        this.tip = tip;
        this.opis = opis;
    }

    public int getIdTipTerena() {
        return idTipTerena;
    }

    public void setIdTipTerena(int idTipTerena) {
        this.idTipTerena = idTipTerena;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
    
    
}
