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
import rs.ac.bg.fon.njt.NJTProjekat.entity.MyEntity;

/**
 *
 * @author hallo
 */
@Entity
@Table(name = "mesto")
public class Mesto implements MyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMesto;
    private String naziv;

    public Mesto() {
    }

    public Mesto(int idMesto, String naziv) {
        this.idMesto = idMesto;
        this.naziv = naziv;
    }
     public Mesto(Integer idMesto) {
        this.idMesto = idMesto;
        
    }

    public Integer getIdMesto() {
        return idMesto;
    }

    public void setIdMesto(Integer idMesto) {
        this.idMesto = idMesto;
    }
    
    

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    
    
}
