/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.dto.impl;

import rs.ac.bg.fon.njt.NJTProjekat.dto.Dto;

/**
 *
 * @author hallo
 */
public class ZaposleniUpdateResponse implements Dto {
    private ZaposleniDto zaposleni;
    private String token;

    public ZaposleniUpdateResponse(ZaposleniDto zaposleni, String token) {
        this.zaposleni = zaposleni;
        this.token = token;
    }

    public ZaposleniDto getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(ZaposleniDto zaposleni) {
        this.zaposleni = zaposleni;
    }

    

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
