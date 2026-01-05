/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.servis;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.NJTProjekat.dto.impl.MestoDto;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.Mesto;
import rs.ac.bg.fon.njt.NJTProjekat.mapper.impl.MestoMapper;
import rs.ac.bg.fon.njt.NJTProjekat.repository.impl.MestoRepository;

/**
 *
 * @author hallo
 */
@Service
public class MestoServis {
    private final MestoRepository mestoRepository;
    private final MestoMapper mestoMapper;
    
    public List<MestoDto> findAll(){
        return mestoRepository.findAll().stream().map(mestoMapper::toDto).collect(Collectors.toList());
    }
    
    @Autowired
    public MestoServis(MestoRepository mestoRepository, MestoMapper mestoMapper) {
        this.mestoRepository = mestoRepository;
        this.mestoMapper = mestoMapper;
    }
    
    public MestoDto findById(Integer id) throws Exception{
        return mestoMapper.toDto(mestoRepository.findById(id));
    }

    public MestoDto create(MestoDto mestoDto) {
        Mesto mesto = mestoMapper.toEntity(mestoDto);
        mestoRepository.save(mesto);
        return mestoMapper.toDto(mesto);
    }
}
