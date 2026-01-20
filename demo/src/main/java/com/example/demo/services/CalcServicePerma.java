package com.example.demo.services;

import com.example.demo.model.dtos.OsDto;
import com.example.demo.model.entitys.OsEntity;
import com.example.demo.repository.OsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class CalcServicePerma {

    @Autowired
    OsRepository osRepository;

    public OsDto makeOs(OsDto osDto){

        if(osRepository.existsByOsNumber(osDto.osNumber())){
            throw new RuntimeException("This Os have exists!");
        }
        OsEntity osEntity= new OsEntity(osDto);

        osRepository.save(osEntity);
        return osDto;
    }

    public OsDto getOsEntity(@RequestParam Integer os){
        Optional<OsEntity> osEntity=osRepository.findByOsNumber(os);
        if(!(osRepository.findByOsNumber(osEntity.get().getOsNumber()).isEmpty())){
            throw new RuntimeException("404 not found");
        }
        OsEntity osEntity1=osEntity.get();
        OsDto convertEntityToDtoOs=new OsDto(osEntity1.getContract(),osEntity1.getOsNumber(),
                osEntity1.getOccurrence(),osEntity1.getUnit(),osEntity1.getScreeningDate(),osEntity1.getDistanceBaseOs(),osEntity1.getArea(),
                osEntity1.getLatitude(),osEntity1.getLongitude(),osEntity1.getResponsibleScreening());

        return convertEntityToDtoOs;
    }


}
