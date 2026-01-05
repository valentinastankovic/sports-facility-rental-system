/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.mapper.impl;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.NJTProjekat.dto.impl.SportskiTerenDto;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.SportskiTeren;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.TipTerena;
import rs.ac.bg.fon.njt.NJTProjekat.mapper.DtoEntityMapper;

@Component
public class SportskiTerenMapper implements DtoEntityMapper<SportskiTerenDto, SportskiTeren> {

    @Override
    public SportskiTerenDto toDto(SportskiTeren e) {
        Integer tipTerenaId = e.getTipTerena() != null ? e.getTipTerena().getIdTipTerena() : null;
        SportskiTerenDto dto = new SportskiTerenDto(
                e.getIdSportskiTeren(),
                e.getNazivTerena(),
                e.getLokacija(),
                e.getCenaPoSatu(),
                tipTerenaId
        );
        return dto;
    }

    @Override
    public SportskiTeren toEntity(SportskiTerenDto t) {
        // Minimalna ispravka: kreira se TipTerena samo sa ID-em, JPA će povezati
        TipTerena tipTerena = t.getTipTerenaId() != null ? new TipTerena(t.getTipTerenaId()) : null;

        SportskiTeren entity = new SportskiTeren(
                t.getIdSportskiTeren(),
                t.getNazivTerena(),
                t.getLokacija(),
                t.getCenaPoSatu(),
                tipTerena
        );
        return entity;
    }
}

    
    

