package com.example.BODEGASTCCAPI.controllers;

import com.example.BODEGASTCCAPI.models.Merchandise;
import com.example.BODEGASTCCAPI.models.Sender;
import com.example.BODEGASTCCAPI.services.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/soluciontcc/v1/merchandise")
public class MerchandiseController {

    @Autowired
    private MerchandiseService merchandiseService;

    @PostMapping
    public ResponseEntity<Merchandise> storeMerchandise(@RequestBody Merchandise merchandiseData) {
        Merchandise savedMerchandise = merchandiseService.storeMerchandise(merchandiseData);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMerchandise);
    }

    @GetMapping
    public ResponseEntity<List<Merchandise>> findAllMerchandise() {
        List<Merchandise> merchandiseList = merchandiseService.findAllMerchandise();
        return ResponseEntity.status(HttpStatus.OK).body(merchandiseList);
    }

    @GetMapping("/{merchandiseId}")
    public ResponseEntity<Merchandise> findMerchandiseById(@PathVariable Long merchandiseId) {
        Merchandise merchandise = merchandiseService.findMerchandiseById(merchandiseId);
        return ResponseEntity.status(HttpStatus.OK).body(merchandise);
    }

    @GetMapping("/name/{merchandiseName}")
    public ResponseEntity<Merchandise> findMerchandiseByName(@PathVariable String merchandiseName) {

        Merchandise merchandise = merchandiseService.findMerchandiseByName(merchandiseName).isPresent() ?
                merchandiseService.findMerchandiseByName(merchandiseName).get() : null;
        return ResponseEntity.status(HttpStatus.OK).body(merchandise);
    }

    @PutMapping("/{merchandiseId}")
    public ResponseEntity<Merchandise> updateMerchandise(@PathVariable Long merchandiseId, @RequestBody Merchandise merchandiseData) {
        Merchandise updatedMerchandise = merchandiseService.updateMerchandise(merchandiseId, merchandiseData);
        return ResponseEntity.status(HttpStatus.OK).body(updatedMerchandise);
    }

    @DeleteMapping("/{merchandiseId}")
    public ResponseEntity<?> deleteMerchandise(@PathVariable Long merchandiseId) {
        merchandiseService.deleteMerchandise(merchandiseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
