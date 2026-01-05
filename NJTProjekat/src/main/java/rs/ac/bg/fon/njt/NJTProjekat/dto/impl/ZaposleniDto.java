/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import rs.ac.bg.fon.njt.NJTProjekat.dto.Dto;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.Uloge;

/**
 *
 * @author hallo
 */
public class ZaposleniDto implements Dto {

    private Integer idZaposleni;
    @NotBlank(message = "Ime je obavezno.")
    private String ime;
    @NotBlank(message = "Prezime je obavezno.")
    private String prezime;
    @NotBlank(message = "Email je obavezan.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.com$", message = "Email mora sadržati @ i završavati se sa .com")
    private String email;
    @NotBlank(message = "Username je obavezan.")
    @Size(max = 12, message = "Username moze imati najvise 12 karaktera.")
    private String username;
    @NotBlank(message = "Password je obavezan.")
    private String password;
    private Uloge uloga;

    public ZaposleniDto() {
    }

    public ZaposleniDto(Integer idZaposleni, String ime, String prezime, String email, String username, String password, Uloge uloga) {
        this.idZaposleni = idZaposleni;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.username = username;
        this.password = password;
        this.uloga = uloga;
    }
    
    

    public Uloge getUloga() {
        return uloga;
    }

    public void setUloga(Uloge uloga) {
        this.uloga = uloga;
    }

    

    public Integer getIdZaposleni() {
        return idZaposleni;
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

}
