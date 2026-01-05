/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema; 
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.server.ResponseStatusException;
import rs.ac.bg.fon.njt.NJTProjekat.dto.impl.SportskiTerenDto;
import rs.ac.bg.fon.njt.NJTProjekat.servis.SportskiTerenServis;

/**
 *
 * @author AsusTuf
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sportski_teren")
public class SportskiTerenController {

    private final SportskiTerenServis sportskiTerenServis;

    public SportskiTerenController(SportskiTerenServis sportskiTerenServis) {
        this.sportskiTerenServis = sportskiTerenServis;
    }

    @GetMapping
    @Operation(summary = "Vrati sve sportske terene.")
    @ApiResponse(responseCode = "200", content = {
        @Content(
                array = @ArraySchema(schema = @Schema(implementation = SportskiTerenDto.class)),
                mediaType = "application/json"
        )
    })
    public ResponseEntity<List<SportskiTerenDto>> getAll() {
        return new ResponseEntity<>(sportskiTerenServis.findAll(), HttpStatus.OK);
    }
    
    @GetMapping ("/{id}")
    public ResponseEntity<SportskiTerenDto> getById(
            @NotNull(message = "Ne bi trebalo da bude null.")
            @PathVariable(value = "id") Integer id){
                try{
                    return new ResponseEntity<>(sportskiTerenServis.findById(id), HttpStatus.OK);
                } catch(Exception ex){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "SportskiTerenController exception");
                }
            }
    
    @PostMapping
    @Operation(summary = "Kreiraj nov sportski teren.")

    public ResponseEntity<SportskiTerenDto> addSportskiTeren(@Valid @RequestBody SportskiTerenDto sportskiTerenDto) {
        try {
            System.out.println(sportskiTerenDto);
            SportskiTerenDto saved = sportskiTerenServis.create(sportskiTerenDto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska prilikom cuvanja sportskog terena. " + ex.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Integer id) {
        try {
            sportskiTerenServis.deleteById(id);
            return new ResponseEntity<>("Sportski teren je uspesno obrisan.", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Sportski teren ne postoji: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Azuriranje sportskog terena.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema (implementation = SportskiTerenDto.class), mediaType  ="application/json")
})
     
           
public ResponseEntity<SportskiTerenDto> updateSportskiTeren(@PathVariable Integer id, @Valid @RequestBody SportskiTerenDto sportskiTerenDto){
        try {
            sportskiTerenDto.setIdSportskiTeren(id);
            SportskiTerenDto updated = sportskiTerenServis.update(sportskiTerenDto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
             
        }catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska prilikom azuriranja");
        }
}
}
