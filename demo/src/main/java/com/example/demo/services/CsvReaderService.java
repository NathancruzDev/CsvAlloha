package com.example.demo.services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.util.List;

@Service
public class CsvReaderService {
    String path;
    File fileCsv=new File(path);
    
    public RuntimeException fileCsvReader(File fileCsv){
        try(FileReader fileReader=new FileReader(fileCsv)){
            
        }catch(Exception e){
            return new RuntimeException("Error");
        }
    }
}
