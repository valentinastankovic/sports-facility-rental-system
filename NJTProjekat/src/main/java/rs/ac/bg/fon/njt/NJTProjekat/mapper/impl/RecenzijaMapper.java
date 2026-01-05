/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.mapper.impl;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.NJTProjekat.dto.impl.RecenzijaDto;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.Recenzija;
import rs.ac.bg.fon.njt.NJTProjekat.mapper.DtoEntityMapper;

/**
 *
 * @author AsusTuf
 */
@Component
public class RecenzijaMapper implements DtoEntityMapper<RecenzijaDto,Recenzija>{

    @Override
    public RecenzijaDto toDto(Recenzija e) {
        RecenzijaDto dto = new RecenzijaDto(e.getIdRecenzija(), e.getDatumRecenzije(), e.getOcena(), e.getTekst());
        return dto;
    }

    @Override
    public Recenzija toEntity(RecenzijaDto t) {
        Recenzija entity = new Recenzija(t.getIdRecenzija(), t.getDatumRecenzije(), t.getOcena(), t.getTekst());
        return entity;
    }
    
}
