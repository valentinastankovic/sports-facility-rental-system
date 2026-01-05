/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.mapper.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.NJTProjekat.dto.impl.IznajmljivanjeDto;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.Iznajmljivanje;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.Klijent;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.Mesto;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.Recenzija;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.SportskiTeren;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.Zaposleni;
import rs.ac.bg.fon.njt.NJTProjekat.mapper.DtoEntityMapper;

/**
 *
 * @author AsusTuf
 */
@Component
public class IznajmljivanjeMapper implements DtoEntityMapper<IznajmljivanjeDto, Iznajmljivanje>{

    @Override
    public IznajmljivanjeDto toDto(Iznajmljivanje e) {
        Integer ZaposleniId = e.getZaposleni() != null ? e.getZaposleni().getIdZaposleni() : null;
        Integer KlijentId = e.getKlijent() != null ? e.getKlijent().getIdKlijent() : null;
        Integer SportskiTerenId = e.getSportskiTeren() != null ? e.getSportskiTeren().getIdSportskiTeren() : null;
        Integer recenzijaId = e.getRecenzija()!= null ? e.getRecenzija().getIdRecenzija(): null;
        IznajmljivanjeDto dto = new IznajmljivanjeDto(e.getIdIznajmljivanje(), e.getDatumPlacanja(), e.getDatumIznajmljivanja(), e.getVremeOd(), e.getVremeDo(), e.getUkupnoSati(), e.getUkupanIznos(), e.getNacinPlacanja(),ZaposleniId, KlijentId, SportskiTerenId,recenzijaId);
        return dto;
    }

    @Override
    public Iznajmljivanje toEntity(IznajmljivanjeDto t) {
        Zaposleni Zaposleni = t.getZaposleniId() != null ? new Zaposleni(t.getZaposleniId()) : null;
        Klijent Klijent = t.getKlijentId() != null ? new Klijent(t.getKlijentId()) : null;
        SportskiTeren SportskiTeren = t.getSportskiTerenId() != null ? new SportskiTeren(t.getSportskiTerenId()) : null;
        Recenzija recenzija = t.getRecenzijaId()!= null ? new Recenzija(t.getRecenzijaId()) : null;
        Iznajmljivanje entity = new Iznajmljivanje(t.getIdIznajmljivanje(), t.getDatumIznajmljivanja(), t.getDatumPlacanja(), t.getVremeOd(), t.getVremeDo(), t.getUkupnoSati(), t.getUkupanIznos(), t.getNacinPlacanja(), Zaposleni, Klijent, SportskiTeren,recenzija);
        return entity;
    }
    
}
