/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.entity.impl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import rs.ac.bg.fon.njt.NJTProjekat.entity.MyEntity;

/**
 *
 * @author hallo
 */
@Entity
@Table(name = "zaposleni", uniqueConstraints = {@UniqueConstraint(name = "uk_user_username", columnNames = "username"),
    @UniqueConstraint(name = "uk_user_email", columnNames = "email")
})
public class Zaposleni implements MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idZaposleni;
    @Column(nullable = false, length=50)
    private String ime;
    @Column(nullable = false, length=50)
    private String prezime;
    @Column(nullable = false, length=70)
    private String email;
    @Column(nullable = false, length=50)
    private String username;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Uloge uloga = Uloge.ZAPOSLENI;

    public Zaposleni() {
    }

    public Zaposleni(Integer idZaposleni, String ime, String prezime, String email, String username, String password) {
        this.idZaposleni = idZaposleni;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.username = username;
        this.password = password;
    }
    
    public Zaposleni(Integer idZaposleni) {
        this.idZaposleni = idZaposleni;
    }

    

    public Integer getIdZaposleni() {
        return idZaposleni;
    }

    public void setIdZaposleni(Integer idZaposleni) {
        this.idZaposleni = idZaposleni;
    }
    

    public void setIdZaposleni(int idZaposleni) {
        this.idZaposleni = idZaposleni;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Uloge getUloga() {
        return uloga;
    }

    public void setUloga(Uloge uloga) {
        this.uloga = uloga;
    }
    

}
