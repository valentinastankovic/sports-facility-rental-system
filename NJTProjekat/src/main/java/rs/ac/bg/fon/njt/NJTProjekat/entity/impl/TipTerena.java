/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.entity.impl;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import rs.ac.bg.fon.njt.NJTProjekat.entity.MyEntity;
import jakarta.persistence.*;

/**
 *
 * @author AsusTuf
 */
@Entity
@Table(name="tip_terena")
public class TipTerena implements MyEntity{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipTerena;
    private String tip;
    private String opis;

    public TipTerena() {
    }

    public TipTerena(Integer idTipTerena, String tip, String opis) {
        this.idTipTerena = idTipTerena;
        this.tip = tip;
        this.opis = opis;
    }
    
    public TipTerena(Integer idTipTerena) {
        this.idTipTerena = idTipTerena;
        
    }

    public Integer getIdTipTerena() {
        return idTipTerena;
    }

    public void setIdTipTerena(Integer idTipTerena) {
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
