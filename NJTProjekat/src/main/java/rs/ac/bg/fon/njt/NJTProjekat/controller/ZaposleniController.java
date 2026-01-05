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
import rs.ac.bg.fon.njt.NJTProjekat.dto.impl.ZaposleniDto;
import rs.ac.bg.fon.njt.NJTProjekat.servis.ZaposleniServis;

/**
 *
 * @author hallo
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/zaposleni")
public class ZaposleniController {

    private final ZaposleniServis zaposleniServis;

    public ZaposleniController(ZaposleniServis zaposleniServis) {
        this.zaposleniServis = zaposleniServis;
    }

    @GetMapping
    @Operation(summary = "Vrati sve zaposlene.")
    @ApiResponse(responseCode = "200", content = {
        @Content(
                array = @ArraySchema(schema = @Schema(implementation = ZaposleniDto.class)),
                mediaType = "application/json"
        )
    })
    public ResponseEntity<List<ZaposleniDto>> getAll() {
        return new ResponseEntity<>(zaposleniServis.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZaposleniDto> getById(
            @NotNull(message = "Ne bi trebalo da bude null.")
            @PathVariable(value = "id") Integer id) {
        try {
            return new ResponseEntity<>(zaposleniServis.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ZaposleniController exception");
        }
    }

    @PostMapping
    @Operation(summary = "Kreiraj novog zaposlenog.")
    @ApiResponse(responseCode = "201", content = {
        @Content(schema = @Schema(implementation = ZaposleniDto.class), mediaType = "application/json")
    })

    public ResponseEntity<ZaposleniDto> addZaposleni(@Valid @RequestBody @NotNull ZaposleniDto zaposleniDto) {
        try {
            System.out.println(zaposleniDto);
            ZaposleniDto saved = zaposleniServis.create(zaposleniDto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska prilikom cuvanja zaposlenog. " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Integer id) {
        try {
            zaposleniServis.deleteById(id);
            return new ResponseEntity<>("Zaposleni je uspesno obrisan.", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Zaposleni ne postoji: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Azuriranje zaposlenog.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema (implementation =ZaposleniDto.class), mediaType  ="application/json")
})
     
           
public ResponseEntity<ZaposleniDto> updateZaposleni(@PathVariable Integer id, @Valid @RequestBody ZaposleniDto zaposleniDto){
        try {
            zaposleniDto.setIdZaposleni(id);
            ZaposleniDto updated = zaposleniServis.update(zaposleniDto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
             
        }catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska prilikom azuriranja");
        }
}
}

