package com.example.demo.controllers;

import com.example.demo.model.dtos.OsDto;
import com.example.demo.repository.OsRepository;
import com.example.demo.services.CalcServicePerma;
import com.example.demo.services.CsvReaderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;



@RestController
@RequestMapping("csvB2B")
public class CsvController {

    @Autowired
    OsRepository osInterface;
    @Autowired
    CsvReaderService csvReaderService;
    @Autowired
    CalcServicePerma calcServicePerma;

    @PostMapping("upOsCsv")
    @Transactional
    public ResponseEntity<OsDto> postOsCsv(@RequestParam OsDto osDto, UriComponentsBuilder uriComponentsBuilder){
        OsDto createdOs=calcServicePerma.makeOs(osDto);
        var uri= uriComponentsBuilder.path("csvB2B/upOsCsv/{osNumber}").buildAndExpand(createdOs.osNumber()).toUri();
        return ResponseEntity.created(uri).body(createdOs);
    }

    @GetMapping("getOsCSV")
    public ResponseEntity<OsDto> getOsCsv(@RequestParam Integer os){
        OsDto osReturned=calcServicePerma.getOsEntity(os);
        return ResponseEntity.ok(osReturned);
    }
}
