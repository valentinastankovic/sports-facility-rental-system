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
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import rs.ac.bg.fon.njt.NJTProjekat.dto.impl.KlijentDto;
import rs.ac.bg.fon.njt.NJTProjekat.dto.impl.ZaposleniDto;
import rs.ac.bg.fon.njt.NJTProjekat.servis.KlijentServis;


/**
 *
 * @author hallo
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/klijent")
public class KlijentController {
     private final KlijentServis klijentServis;

    public KlijentController(KlijentServis klijentServis) {
        this.klijentServis = klijentServis;
    }

    @GetMapping
    @Operation(summary = "Vrati sve klijente.")
    @ApiResponse(responseCode = "200", content = {
        @Content(
                array = @ArraySchema(schema = @Schema(implementation = KlijentDto.class)),
                mediaType = "application/json"
        )
    })
    public ResponseEntity<List<KlijentDto>> getAll() {
        return new ResponseEntity<>(klijentServis.findAll(), HttpStatus.OK);
    }
    
    @GetMapping ("/{id}")
    public ResponseEntity<KlijentDto> getById(
            @NotNull(message = "Ne bi trebalo da bude null.")
            @PathVariable(value = "id") Integer id){
                try{
                    return new ResponseEntity<>(klijentServis.findById(id), HttpStatus.OK);
                } catch(Exception ex){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "KlijentController exception");
                }
            }
    
    @PostMapping
    @Operation(summary = "Kreiraj novog klijenta.")
//    @ApiResponse(responseCode = "201", content = {
//        @Content(schema = @Schema(implementation = KlijentDto.class), mediaType = "application/json")
//    })

    public ResponseEntity<KlijentDto> addKlijent(@Valid @RequestBody KlijentDto klijentDto) {
        try {
            System.out.println(klijentDto);
            KlijentDto saved = klijentServis.create(klijentDto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska prilikom cuvanja klijenta. " + ex.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Integer id) {
        try {
            klijentServis.deleteById(id);
            return new ResponseEntity<>("Klijent je uspesno obrisan.", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Klijent ne postoji: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Azuriranje klijenta.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema (implementation =KlijentDto.class), mediaType  ="application/json")
})
     
           
public ResponseEntity<KlijentDto> updateKlijent(@PathVariable Integer id, @Valid @RequestBody KlijentDto klijentDto){
        try {
            klijentDto.setIdKlijent(id);
            KlijentDto updated = klijentServis.update(klijentDto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
             
        }catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska prilikom azuriranja");
        }
}
    
    
}
