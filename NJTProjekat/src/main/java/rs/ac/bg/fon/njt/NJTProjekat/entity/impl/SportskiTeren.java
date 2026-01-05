/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.entity.impl;


/**
 *
 * @author AsusTuf
 */
import jakarta.persistence.*;
import rs.ac.bg.fon.njt.NJTProjekat.entity.MyEntity;


@Entity
@Table(name="sportski_teren")
public class SportskiTeren implements MyEntity{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSportskiTeren;
    private String nazivTerena;
    private String lokacija;
    private double cenaPoSatu;
    
    @ManyToOne
    @JoinColumn(name = "TipTerenaId")
    private TipTerena tipTerena;

    public SportskiTeren() {
    }

    public SportskiTeren(Integer idSportskiTeren, String nazivTerena, String lokacija, double cenaPoSatu, TipTerena tipTerena) {
        this.idSportskiTeren = idSportskiTeren;
        this.nazivTerena = nazivTerena;
        this.lokacija = lokacija;
        this.cenaPoSatu = cenaPoSatu;
        this.tipTerena = tipTerena;
      
    }

    public SportskiTeren(Integer idSportskiTeren) {
        this.idSportskiTeren = idSportskiTeren;
    }

    public Integer getIdSportskiTeren() {
        return idSportskiTeren;
    }

    public void setIdSportskiTeren(Integer idSportskiTeren) {
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

    public TipTerena getTipTerena() {
        return tipTerena;
    }

    public void setTipTerena(TipTerena tipTerena) {
        this.tipTerena = tipTerena;
    }

   
}
