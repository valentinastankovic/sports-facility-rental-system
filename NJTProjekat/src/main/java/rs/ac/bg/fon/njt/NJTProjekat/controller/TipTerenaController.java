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
import rs.ac.bg.fon.njt.NJTProjekat.dto.impl.TipTerenaDto;
import rs.ac.bg.fon.njt.NJTProjekat.servis.TipTerenaServis;

/**
 *
 * @author AsusTuf
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tip_terena")
public class TipTerenaController {
    private final TipTerenaServis tipTerenaServis;

    public TipTerenaController(TipTerenaServis tipTerenaServis) {
        this.tipTerenaServis = tipTerenaServis;
    }

    @GetMapping
    @Operation(summary = "Vrati sve tipove terena.")
    @ApiResponse(responseCode = "200", content = {
        @Content(
                array = @ArraySchema(schema = @Schema(implementation = TipTerenaDto.class)),
                mediaType = "application/json"
        )
    })
    public ResponseEntity<List<TipTerenaDto>> getAll() {
        return new ResponseEntity<>(tipTerenaServis.findAll(), HttpStatus.OK);
    }
    
    @GetMapping ("/{id}")
    public ResponseEntity<TipTerenaDto> getById(
            @NotNull(message = "Ne bi trebalo da bude null.")
            @PathVariable(value = "id") Integer id){
                try{
                    return new ResponseEntity<>(tipTerenaServis.findById(id), HttpStatus.OK);
                } catch(Exception ex){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "TipTerenaController exception");
                }
            }
    
    @PostMapping
    @Operation(summary = "Kreiraj nov tip terena.")
    @ApiResponse(responseCode = "201", content = {
        @Content(schema = @Schema(implementation = TipTerenaDto.class), mediaType = "application/json")
    })

    public ResponseEntity<TipTerenaDto> addTipTerena(@Valid @RequestBody @NotNull TipTerenaDto tipTerenaDto) {
        try {
            System.out.println(tipTerenaDto);
            TipTerenaDto saved = tipTerenaServis.create(tipTerenaDto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska prilikom cuvanja tipa terena. " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Integer id) {
        try {
            tipTerenaServis.deleteById(id);
            return new ResponseEntity<>("Tip terena je uspesno obrisan.", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Tip terena ne postoji: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Azuriranje tipa terena.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema (implementation =TipTerenaDto.class), mediaType  ="application/json")
})
     
           
public ResponseEntity<TipTerenaDto> updateTipTerena(@PathVariable Integer id, @Valid @RequestBody TipTerenaDto tipTerenaDto){
        try {
            tipTerenaDto.setIdTipTerena(id);
            TipTerenaDto updated = tipTerenaServis.update(tipTerenaDto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
             
        }catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska prilikom azuriranja");
        }
}
    
}
