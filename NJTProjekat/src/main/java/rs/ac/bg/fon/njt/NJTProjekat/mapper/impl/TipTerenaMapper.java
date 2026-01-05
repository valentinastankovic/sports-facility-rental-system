/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.mapper.impl;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.NJTProjekat.dto.impl.TipTerenaDto;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.TipTerena;
import rs.ac.bg.fon.njt.NJTProjekat.mapper.DtoEntityMapper;

/**
 *
 * @author AsusTuf
 */
@Component
public class TipTerenaMapper implements DtoEntityMapper<TipTerenaDto,TipTerena>{

    @Override
    public TipTerenaDto toDto(TipTerena e) {
        TipTerenaDto dto = new TipTerenaDto(e.getIdTipTerena(), e.getTip(), e.getOpis());
        return dto;
    }

    @Override
    public TipTerena toEntity(TipTerenaDto t) {
        TipTerena entity = new TipTerena(t.getIdTipTerena(), t.getTip(), t.getOpis());
        return entity;
    }
    
}
