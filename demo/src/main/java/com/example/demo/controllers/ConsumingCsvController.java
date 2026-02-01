package com.example.demo.controllers;

import com.example.demo.model.dtos.OsActiveDto;
import com.example.demo.model.dtos.OsDto;
import com.example.demo.services.CalcServicePerma;
import com.example.demo.services.CsvReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/csv")
public class ConsumingCsvController {
    @Autowired
    private final CsvReaderService csvReaderService;

    @Autowired
    private final CalcServicePerma calcServicePerma;

    public ConsumingCsvController(CsvReaderService csvReaderService, CalcServicePerma calcServicePerma) {
        this.csvReaderService = csvReaderService;
        this.calcServicePerma = calcServicePerma;
    }


    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<List<OsDto>> uploadCsv(@RequestParam("file") MultipartFile file){
        List<OsDto> result=calcServicePerma.makeOsByCsvList(file);
        return  ResponseEntity.ok(result);
    }
}
