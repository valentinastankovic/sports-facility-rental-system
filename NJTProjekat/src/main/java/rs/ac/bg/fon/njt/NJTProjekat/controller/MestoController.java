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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import rs.ac.bg.fon.njt.NJTProjekat.dto.impl.KlijentDto;
import rs.ac.bg.fon.njt.NJTProjekat.dto.impl.MestoDto;
import rs.ac.bg.fon.njt.NJTProjekat.dto.impl.ZaposleniDto;
import rs.ac.bg.fon.njt.NJTProjekat.servis.KlijentServis;
import rs.ac.bg.fon.njt.NJTProjekat.servis.MestoServis;

/**
 *
 * @author hallo
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/mesto")
public class MestoController {
     private final MestoServis mestoServis;

    public MestoController(MestoServis mestoServis) {
        this.mestoServis = mestoServis;
    }

    @GetMapping
    @Operation(summary = "Vrati sva mesta.")
    @ApiResponse(responseCode = "200", content = {
        @Content(
                array = @ArraySchema(schema = @Schema(implementation = MestoDto.class)),
                mediaType = "application/json"
        )
    })
    public ResponseEntity<List<MestoDto>> getAll() {
        return new ResponseEntity<>(mestoServis.findAll(), HttpStatus.OK);
    }
    
    @GetMapping ("/{id}")
    public ResponseEntity<MestoDto> getById(
            @NotNull(message = "Ne bi trebalo da bude null.")
            @PathVariable(value = "id") Integer id){
                try{
                    return new ResponseEntity<>(mestoServis.findById(id), HttpStatus.OK);
                } catch(Exception ex){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "MestoController exception");
                }
            }
    
     @PostMapping
    @Operation(summary = "Kreiraj novo mesto.")
    @ApiResponse(responseCode = "201", content = {
        @Content(schema = @Schema(implementation = MestoDto.class), mediaType = "application/json")
    })

    public ResponseEntity<MestoDto> addMesto(@Valid @RequestBody @NotNull MestoDto mestoDto) {
        try {
            System.out.println(mestoDto);
            MestoDto saved = mestoServis.create(mestoDto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska prilikom cuvanja mesta. " + ex.getMessage());
        }
    }
    
}
