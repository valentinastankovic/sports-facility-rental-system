/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.servis;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.NJTProjekat.dto.impl.IznajmljivanjeDto;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.Iznajmljivanje;
import rs.ac.bg.fon.njt.NJTProjekat.mapper.impl.IznajmljivanjeMapper;
import rs.ac.bg.fon.njt.NJTProjekat.repository.impl.IznajmljivanjeRepository;

/**
 *
 * @author AsusTuf
 */
@Service
public class IznajmljivanjeServis {
    private final IznajmljivanjeRepository iznajmljivanjeRepository;
    private final IznajmljivanjeMapper iznajmljivanjeMapper;
    
    public List<IznajmljivanjeDto> findAll(){
        return iznajmljivanjeRepository.findAll().stream().map(iznajmljivanjeMapper::toDto).collect(Collectors.toList());
    }
    
    @Autowired
    public IznajmljivanjeServis(IznajmljivanjeRepository iznajmljivanjeRepository, IznajmljivanjeMapper iznajmljivanjeMapper) {
        this.iznajmljivanjeRepository = iznajmljivanjeRepository;
        this.iznajmljivanjeMapper = iznajmljivanjeMapper;
    }
    
    public IznajmljivanjeDto findById(Integer id) throws Exception{
        return iznajmljivanjeMapper.toDto(iznajmljivanjeRepository.findById(id));
    }
    
    public IznajmljivanjeDto create(IznajmljivanjeDto iznajmljivanjeDto) {
        Iznajmljivanje iznajmljivanje = iznajmljivanjeMapper.toEntity(iznajmljivanjeDto);
        iznajmljivanjeRepository.save(iznajmljivanje);
        return iznajmljivanjeMapper.toDto(iznajmljivanje);
    }

//    public void deleteById(Integer id) {
//        iznajmljivanjeRepository.deleteById(id);
//    }
//
    public IznajmljivanjeDto update(IznajmljivanjeDto iznajmljivanjeDto) {
        Iznajmljivanje updated = iznajmljivanjeMapper.toEntity(iznajmljivanjeDto);
        iznajmljivanjeRepository.save(updated);
        return iznajmljivanjeMapper.toDto(updated);
    }

    
}
