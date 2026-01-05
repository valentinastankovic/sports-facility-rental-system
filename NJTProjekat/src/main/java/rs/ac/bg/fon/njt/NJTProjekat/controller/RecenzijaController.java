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
import rs.ac.bg.fon.njt.NJTProjekat.dto.impl.RecenzijaDto;
import rs.ac.bg.fon.njt.NJTProjekat.servis.RecenzijaServis;

/**
 *
 * @author AsusTuf
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/recenzija")
public class RecenzijaController {
    private final RecenzijaServis recenzijaServis;

    public RecenzijaController(RecenzijaServis recenzijaServis) {
        this.recenzijaServis = recenzijaServis;
    }

    @GetMapping
    @Operation(summary = "Vrati sve recenzije.")
    @ApiResponse(responseCode = "200", content = {
        @Content(
                array = @ArraySchema(schema = @Schema(implementation = RecenzijaDto.class)),
                mediaType = "application/json"
        )
    })
    public ResponseEntity<List<RecenzijaDto>> getAll() {
        return new ResponseEntity<>(recenzijaServis.findAll(), HttpStatus.OK);
    }
    
    @GetMapping ("/{id}")
    public ResponseEntity<RecenzijaDto> getById(
            @NotNull(message = "Ne bi trebalo da bude null.")
            @PathVariable(value = "id") Integer id){
                try{
                    return new ResponseEntity<>(recenzijaServis.findById(id), HttpStatus.OK);
                } catch(Exception ex){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Recenzija exception");
                }
            }
    
    @PostMapping
    @Operation(summary = "Kreiraj novu recenziju.")
    @ApiResponse(responseCode = "201", content = {
        @Content(schema = @Schema(implementation = RecenzijaDto.class), mediaType = "application/json")
    })

    public ResponseEntity<RecenzijaDto> addRecenzija(@Valid @RequestBody @NotNull RecenzijaDto recenzijaDto) {
        try {
            RecenzijaDto saved = recenzijaServis.create(recenzijaDto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska prilikom cuvanja recenzije. " + ex.getMessage());
        }
    }
}
