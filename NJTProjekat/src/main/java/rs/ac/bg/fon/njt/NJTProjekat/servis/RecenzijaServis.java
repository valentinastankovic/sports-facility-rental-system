/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.servis;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.NJTProjekat.dto.impl.RecenzijaDto;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.Recenzija;
import rs.ac.bg.fon.njt.NJTProjekat.mapper.impl.RecenzijaMapper;
import rs.ac.bg.fon.njt.NJTProjekat.repository.impl.RecenzijaRepository;

/**
 *
 * @author AsusTuf
 */
@Service
public class RecenzijaServis {
    private final RecenzijaRepository recenzijaRepository;
    private final RecenzijaMapper recenzijaMapper;
    
    public List<RecenzijaDto> findAll(){
        return recenzijaRepository.findAll().stream().map(recenzijaMapper::toDto).collect(Collectors.toList());
    }
    
    @Autowired
    public RecenzijaServis(RecenzijaRepository recenzijaRepository, RecenzijaMapper recenzijaMapper) {
        this.recenzijaRepository = recenzijaRepository;
        this.recenzijaMapper = recenzijaMapper;
    }
    
    public RecenzijaDto findById(Integer id) throws Exception{
        return recenzijaMapper.toDto(recenzijaRepository.findById(id));
    }
    
    public RecenzijaDto create(RecenzijaDto recenzijaDto) {
        Recenzija recenzija = recenzijaMapper.toEntity(recenzijaDto);
        recenzijaRepository.save(recenzija);
        return recenzijaMapper.toDto(recenzija);
    }

    public void deleteById(Integer id) {
        recenzijaRepository.deleteById(id);
    }

    public RecenzijaDto update(RecenzijaDto recenzijaDto) {
        
        Recenzija updated = recenzijaMapper.toEntity(recenzijaDto);
        recenzijaRepository.save(updated);
        return recenzijaMapper.toDto(updated);
    }
}
