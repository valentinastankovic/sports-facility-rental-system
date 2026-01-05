/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.servis;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.NJTProjekat.dto.impl.TipTerenaDto;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.TipTerena;
import rs.ac.bg.fon.njt.NJTProjekat.mapper.impl.TipTerenaMapper;
import rs.ac.bg.fon.njt.NJTProjekat.repository.impl.TipTerenaRepository;

/**
 *
 * @author AsusTuf
 */
@Service
public class TipTerenaServis {
    private final TipTerenaRepository tipTerenaRepository;
    private final TipTerenaMapper tipTerenaMapper;
    
    public List<TipTerenaDto> findAll(){
        return tipTerenaRepository.findAll().stream().map(tipTerenaMapper::toDto).collect(Collectors.toList());
    }
    
    @Autowired
    public TipTerenaServis(TipTerenaRepository tipTerenaRepository, TipTerenaMapper tipTerenaMapper) {
        this.tipTerenaRepository = tipTerenaRepository;
        this.tipTerenaMapper = tipTerenaMapper;
    }
    
    public TipTerenaDto findById(Integer id) throws Exception{
        return tipTerenaMapper.toDto(tipTerenaRepository.findById(id));
    }
    
    public TipTerenaDto create(TipTerenaDto tipTerenaDto) {
        TipTerena tipTerena = tipTerenaMapper.toEntity(tipTerenaDto);
        tipTerenaRepository.save(tipTerena);
        return tipTerenaMapper.toDto(tipTerena);
    }

    public void deleteById(Integer id) {
        tipTerenaRepository.deleteById(id);
    }

    public TipTerenaDto update(TipTerenaDto tipTerenaDto) {
        
        TipTerena updated = tipTerenaMapper.toEntity(tipTerenaDto);
        tipTerenaRepository.save(updated);
        return tipTerenaMapper.toDto(updated);
    }
    
}
