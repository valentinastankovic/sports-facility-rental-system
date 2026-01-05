/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.mapper.impl;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.NJTProjekat.dto.impl.KlijentDto;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.Klijent;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.Mesto;
import rs.ac.bg.fon.njt.NJTProjekat.mapper.DtoEntityMapper;

/**
 *
 * @author hallo
 */
@Component
public class KlijentMapper implements DtoEntityMapper<KlijentDto, Klijent> {

    @Override
    public KlijentDto toDto(Klijent e) {
        Integer MestoId = e.getMesto() != null ? e.getMesto().getIdMesto() : null;
        KlijentDto dto = new KlijentDto(e.getIdKlijent(), e.getIme(), e.getPrezime(), e.getBrojTelefona(), e.getBrojLicneKarte(), e.getEmail(), MestoId);
        return dto;
    }

    @Override
    public Klijent toEntity(KlijentDto t) {
        Mesto mesto = t.getMesto_id() != null ? new Mesto(t.getMesto_id()) : null;
        Klijent entity = new Klijent(t.getIdKlijent(), t.getIme(), t.getPrezime(), t.getBroj_telefona(), t.getBroj_licne_karte(), t.getEmail(), mesto);
        return entity;
    }
    
}
