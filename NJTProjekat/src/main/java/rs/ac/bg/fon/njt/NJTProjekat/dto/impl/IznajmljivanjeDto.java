/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.dto.impl;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import rs.ac.bg.fon.njt.NJTProjekat.dto.Dto;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.Klijent;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.SportskiTeren;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.Zaposleni;

/**
 *
 * @author AsusTuf
 */
public class IznajmljivanjeDto implements Dto{
    
    private int idIznajmljivanje;
    @NotNull(message = "Datum placanja je obavezan.")
    private LocalDate datumPlacanja;
    @NotNull(message = "Datum iznajmljivanja je obavezan.")
    private LocalDate datumIznajmljivanja;
    @NotNull(message = "Vreme od je obavezno.")
    private LocalTime vremeOd;
    @NotNull(message = "Vreme do je obavezno.")
    private LocalTime vremeDo;
    @NotNull(message = "Ukupno sati je obavezno.")
    @Positive(message = "Ukupno sati mora biti veća od nule.")
    private int ukupnoSati;
    @NotNull(message = "Ukupan iznos je obavezan.")
    @Positive(message = "Ukupan iznos mora biti veći od nule.")
    private double ukupanIznos;
    @NotBlank(message = "Nacin placanja je obavezan.")
    private String nacinPlacanja;
    private Integer zaposleniId;
    private Integer klijentId;
    private Integer sportskiTerenId;
    private Integer recenzijaId;

    public IznajmljivanjeDto() {
    }

    public IznajmljivanjeDto(int idIznajmljivanje, LocalDate datumPlacanja, LocalDate datumIznajmljivanja, LocalTime vremeOd, LocalTime vremeDo, int ukupnoSati, double ukupanIznos, String nacinPlacanja, Integer zaposleniId, Integer klijentId, Integer sportskiTerenId, Integer recenzijaId) {
        this.idIznajmljivanje = idIznajmljivanje;
        this.datumPlacanja = datumPlacanja;
        this.datumIznajmljivanja = datumIznajmljivanja;
        this.vremeOd = vremeOd;
        this.vremeDo = vremeDo;
        this.ukupnoSati = ukupnoSati;
        this.ukupanIznos = ukupanIznos;
        this.nacinPlacanja = nacinPlacanja;
        this.zaposleniId = zaposleniId;
        this.klijentId = klijentId;
        this.sportskiTerenId = sportskiTerenId;
        this.recenzijaId = recenzijaId;
    }

   

    public int getIdIznajmljivanje() {
        return idIznajmljivanje;
    }

    public void setIdIznajmljivanje(int idIznajmljivanje) {
        this.idIznajmljivanje = idIznajmljivanje;
    }

    public LocalDate getDatumPlacanja() {
        return datumPlacanja;
    }

    public void setDatumPlacanja(LocalDate datumPlacanja) {
        this.datumPlacanja = datumPlacanja;
    }

    public LocalDate getDatumIznajmljivanja() {
        return datumIznajmljivanja;
    }

    public void setDatumIznajmljivanja(LocalDate datumIznajmljivanja) {
        this.datumIznajmljivanja = datumIznajmljivanja;
    }

    public LocalTime getVremeOd() {
        return vremeOd;
    }

    public void setVremeOd(LocalTime vremeOd) {
        this.vremeOd = vremeOd;
    }

    public LocalTime getVremeDo() {
        return vremeDo;
    }

    public void setVremeDo(LocalTime vremeDo) {
        this.vremeDo = vremeDo;
    }

    public int getUkupnoSati() {
        return ukupnoSati;
    }

    public void setUkupnoSati(int ukupnoSati) {
        this.ukupnoSati = ukupnoSati;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public String getNacinPlacanja() {
        return nacinPlacanja;
    }

    public void setNacinPlacanja(String nacinPlacanja) {
        this.nacinPlacanja = nacinPlacanja;
    }

   

    public Integer getZaposleniId() {
        return zaposleniId;
    }

    public void setZaposleniId(Integer zaposleniId) {
        this.zaposleniId = zaposleniId;
    }

    public Integer getKlijentId() {
        return klijentId;
    }

    public void setKlijentId(Integer klijentId) {
        this.klijentId = klijentId;
    }

    public Integer getSportskiTerenId() {
        return sportskiTerenId;
    }

    public void setSportskiTerenId(Integer sportskiTerenId) {
        this.sportskiTerenId = sportskiTerenId;
    }

    public Integer getRecenzijaId() {
        return recenzijaId;
    }

    public void setRecenzijaId(Integer recenzijaId) {
        this.recenzijaId = recenzijaId;
    }
    
    
}
