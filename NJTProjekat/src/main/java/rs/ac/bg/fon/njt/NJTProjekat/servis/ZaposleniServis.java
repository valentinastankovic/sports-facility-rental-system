/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.servis;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.NJTProjekat.dto.impl.ZaposleniDto;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.Zaposleni;
import rs.ac.bg.fon.njt.NJTProjekat.mapper.impl.ZaposleniMapper;
import rs.ac.bg.fon.njt.NJTProjekat.repository.impl.ZaposleniRepository;

/**
 *
 * @author hallo
 */
@Service
public class ZaposleniServis {
    private final ZaposleniRepository zaposleniRepository;
    private final ZaposleniMapper zaposleniMapper;
    
    public List<ZaposleniDto> findAll(){
        return zaposleniRepository.findAll().stream().map(zaposleniMapper::toDto).collect(Collectors.toList());
    }
    
    @Autowired
    public ZaposleniServis(ZaposleniRepository zaposleniRepository, ZaposleniMapper zaposleniMapper) {
        this.zaposleniRepository = zaposleniRepository;
        this.zaposleniMapper = zaposleniMapper;
    }
    
    public ZaposleniDto findById(Integer id) throws Exception{
        return zaposleniMapper.toDto(zaposleniRepository.findById(id));
    }

    public ZaposleniDto create(ZaposleniDto zaposleniDto) {
        Zaposleni zaposleni = zaposleniMapper.toEntity(zaposleniDto);
        zaposleniRepository.save(zaposleni);
        return zaposleniMapper.toDto(zaposleni);
    }

    public void deleteById(Integer id) {
        zaposleniRepository.deleteById(id);
    }

    public ZaposleniDto update(ZaposleniDto zaposleniDto) {
        
        Zaposleni updated = zaposleniMapper.toEntity(zaposleniDto);
        zaposleniRepository.save(updated);
        return zaposleniMapper.toDto(updated);
    }
}
