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
import rs.ac.bg.fon.njt.NJTProjekat.dto.impl.IznajmljivanjeDto;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.Iznajmljivanje;
import rs.ac.bg.fon.njt.NJTProjekat.servis.IznajmljivanjeServis;

/**
 *
 * @author AsusTuf
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/iznajmljivanje")
public class IznajmljivanjeController {
    private final IznajmljivanjeServis iznajmljivanjeServis;

    public IznajmljivanjeController(IznajmljivanjeServis iznajmljivanjeServis) {
        this.iznajmljivanjeServis = iznajmljivanjeServis;
    }

    @GetMapping
    @Operation(summary = "Vrati sva iznajmljivanja.")
    @ApiResponse(responseCode = "200", content = {
        @Content(
                array = @ArraySchema(schema = @Schema(implementation = Iznajmljivanje.class)),
                mediaType = "application/json"
        )
    })
    public ResponseEntity<List<IznajmljivanjeDto>> getAll() {
        return new ResponseEntity<>(iznajmljivanjeServis.findAll(), HttpStatus.OK);
    }
    
    @GetMapping ("/{id}")
    public ResponseEntity<IznajmljivanjeDto> getById(
            @NotNull(message = "Ne bi trebalo da bude null.")
            @PathVariable(value = "id") Integer id){
                try{
                    return new ResponseEntity<>(iznajmljivanjeServis.findById(id), HttpStatus.OK);
                } catch(Exception ex){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "IznajmljivanjeController exception");
                }
            }
    
    @PostMapping
    @Operation(summary = "Kreiraj novo iznajmljivanje.")

    public ResponseEntity<IznajmljivanjeDto> addIznajmljivanje(@Valid @RequestBody IznajmljivanjeDto iznajmljivanjeDto) {
        try {
            System.out.println(iznajmljivanjeDto);
            IznajmljivanjeDto saved = iznajmljivanjeServis.create(iznajmljivanjeDto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska prilikom cuvanja iznajmljivanja. " + ex.getMessage());
        }
    }
    
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> delete(@PathVariable(value = "id") Integer id) {
//        try {
//            iznajmljivanjeServis.deleteById(id);
//            return new ResponseEntity<>("Iznajmljivanje je uspesno obrisano.", HttpStatus.OK);
//        } catch (Exception ex) {
//            return new ResponseEntity<>("Iznajmljivanje ne postoji: " + id, HttpStatus.NOT_FOUND);
//        }
//    }

    @PutMapping("/{id}")
    @Operation(summary = "Azuriranje iznajmljivanja.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema (implementation = IznajmljivanjeDto.class), mediaType  ="application/json")
})
     
           
public ResponseEntity<IznajmljivanjeDto> updateIznajmljivanje(@PathVariable Integer id, @Valid @RequestBody IznajmljivanjeDto iznajmljivanjeDto){
        try {
            iznajmljivanjeDto.setIdIznajmljivanje(id);
            IznajmljivanjeDto updated = iznajmljivanjeServis.update(iznajmljivanjeDto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
             
        }catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska prilikom azuriranja");
        }
}
}
