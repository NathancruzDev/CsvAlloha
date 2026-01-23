package com.example.demo.services;

import com.example.demo.model.dtos.OsDto;
import com.example.demo.model.entitys.OsEntity;
import com.example.demo.repository.OsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CalcServicePerma {

    @Autowired
    OsRepository osRepository;

    @Autowired
    CsvReaderService csvReaderService;

    public OsDto makeOs(OsDto osDto){

        if(osRepository.existsByOsNumber(osDto.osNumber())){
            throw new RuntimeException("This Os have exists!");
        }
        OsEntity osEntity= new OsEntity(osDto);

        osRepository.save(osEntity);
        return osDto;
    }
    public List<OsDto> makeOsByCsvList(File filePath) {
        List<OsDto> osDtoList = csvReaderService.fileCsvReader(filePath);
        List<OsEntity> osEntitys = new ArrayList<>();

        for (int i = 0; i < osDtoList.size(); i++) {
            OsDto osTemp = osDtoList.get(i);
            OsEntity osEntity = new OsEntity();
            osEntity.setContract(osTemp.contract());
            osEntity.setOsNumber(osTemp.osNumber());
            osEntity.setOccurrence(osTemp.occurence());
            osEntity.setUnit(osTemp.unit());
            osEntity.setScreeningDate(osTemp.screeningDate());
            osEntity.setDistanceBaseOs(osTemp.distanceBaseOs());
            osEntity.setArea(osTemp.area());
            osEntity.setLatitude(osTemp.latitude());
            osEntity.setLongitude(osTemp.longitude());
            osEntity.setResponsibleScreening(osTemp.responsibleScreening());
            osEntitys.add(osEntity);
        }
        osRepository.saveAll(osEntitys);
        return osDtoList;
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
