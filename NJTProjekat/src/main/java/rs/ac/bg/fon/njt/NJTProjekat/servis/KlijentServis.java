/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.servis;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import rs.ac.bg.fon.njt.NJTProjekat.dto.impl.KlijentDto;
import rs.ac.bg.fon.njt.NJTProjekat.dto.impl.ZaposleniDto;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.Klijent;
import rs.ac.bg.fon.njt.NJTProjekat.mapper.impl.KlijentMapper;
import rs.ac.bg.fon.njt.NJTProjekat.repository.impl.KlijentRepository;

/**
 *
 * @author hallo
 */
@Service
public class KlijentServis {

    private final KlijentRepository klijentRepository;
    private final KlijentMapper klijentMapper;

    public List<KlijentDto> findAll() {
        return klijentRepository.findAll().stream().map(klijentMapper::toDto).collect(Collectors.toList());
    }

    @Autowired
    public KlijentServis(KlijentRepository klijentRepository, KlijentMapper klijentMapper) {
        this.klijentRepository = klijentRepository;
        this.klijentMapper = klijentMapper;
    }

    public KlijentDto findById(Integer id) throws Exception {
        return klijentMapper.toDto(klijentRepository.findById(id));
    }

    public KlijentDto create(KlijentDto klijentDto) {
        Klijent klijent = klijentMapper.toEntity(klijentDto);
        klijentRepository.save(klijent);
        return klijentMapper.toDto(klijent);
    }

    public void deleteById(Integer id) {
        klijentRepository.deleteById(id);
    }

    public KlijentDto update(KlijentDto klijentDto) {
        Klijent updated = klijentMapper.toEntity(klijentDto);
        klijentRepository.save(updated);
        return klijentMapper.toDto(updated);
    }

}
