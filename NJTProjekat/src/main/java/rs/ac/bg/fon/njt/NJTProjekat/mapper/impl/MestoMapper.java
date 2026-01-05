/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.mapper.impl;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.NJTProjekat.dto.impl.MestoDto;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.Mesto;
import rs.ac.bg.fon.njt.NJTProjekat.mapper.DtoEntityMapper;

/**
 *
 * @author hallo
 */
@Component
public class MestoMapper implements DtoEntityMapper<MestoDto, Mesto> {

    @Override
    public MestoDto toDto(Mesto e) {
        MestoDto dto = new MestoDto(e.getIdMesto(), e.getNaziv());
        return dto;
    }

    @Override
    public Mesto toEntity(MestoDto t) {
        Mesto entity = new Mesto(t.getIdMesto(), t.getNaziv());
        return entity;
    }
    
    
}
