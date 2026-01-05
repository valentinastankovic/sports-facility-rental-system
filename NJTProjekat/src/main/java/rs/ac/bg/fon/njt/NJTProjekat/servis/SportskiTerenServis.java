/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.servis;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.NJTProjekat.dto.impl.SportskiTerenDto;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.SportskiTeren;
import rs.ac.bg.fon.njt.NJTProjekat.mapper.impl.SportskiTerenMapper;
import rs.ac.bg.fon.njt.NJTProjekat.repository.impl.SportskiTerenRepository;

/**
 *
 * @author AsusTuf
 */

@Service
public class SportskiTerenServis {
    private final SportskiTerenRepository sportskiTerenRepository;
    private final SportskiTerenMapper sportskiTerenMapper;
    
    public List<SportskiTerenDto> findAll(){
        return sportskiTerenRepository.findAll().stream().map(sportskiTerenMapper::toDto).collect(Collectors.toList());
    }
    
    @Autowired
    public SportskiTerenServis(SportskiTerenRepository sportskiTerenRepository, SportskiTerenMapper sportskiTerenMapper) {
        this.sportskiTerenRepository = sportskiTerenRepository;
        this.sportskiTerenMapper = sportskiTerenMapper;
    }
    
    public SportskiTerenDto findById(Integer id) throws Exception{
        return sportskiTerenMapper.toDto(sportskiTerenRepository.findById(id));
    }
    
    public SportskiTerenDto create(SportskiTerenDto sportskiTerenDto) {
        SportskiTeren sportskiTeren = sportskiTerenMapper.toEntity(sportskiTerenDto);
        sportskiTerenRepository.save(sportskiTeren);
        return sportskiTerenMapper.toDto(sportskiTeren);
    }

    public void deleteById(Integer id) {
        sportskiTerenRepository.deleteById(id);
    }

    public SportskiTerenDto update(SportskiTerenDto sportskiTerenDto) {
        SportskiTeren updated = sportskiTerenMapper.toEntity(sportskiTerenDto);
        sportskiTerenRepository.save(updated);
        return sportskiTerenMapper.toDto(updated);
    }
    
}
