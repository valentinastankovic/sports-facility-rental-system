/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.mapper.impl;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.NJTProjekat.dto.impl.ZaposleniDto;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.Zaposleni;
import rs.ac.bg.fon.njt.NJTProjekat.mapper.DtoEntityMapper;

/**
 *
 * @author hallo
 */
@Component
public class ZaposleniMapper implements DtoEntityMapper<ZaposleniDto, Zaposleni>{

    @Override
    public ZaposleniDto toDto(Zaposleni e) {
        if(e==null) return null;
        ZaposleniDto dto = new ZaposleniDto(e.getIdZaposleni(), e.getIme(), e.getPrezime(), e.getEmail(), e.getUsername(), e.getPassword(), e.getUloga());
        return dto;
    }

    @Override
    public Zaposleni toEntity(ZaposleniDto t) {
        if(t==null) return null;
        Zaposleni entity = new Zaposleni(t.getIdZaposleni(), t.getIme(), t.getPrezime(), t.getEmail(), t.getUsername(), t.getPassword());
        return entity;
    }
    
}
