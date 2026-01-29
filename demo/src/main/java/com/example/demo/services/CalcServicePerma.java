package com.example.demo.services;

import com.example.demo.model.dtos.OsActiveDto;
import com.example.demo.model.dtos.OsDto;
import com.example.demo.model.entitys.AccumulatedExpenseEntity;
import com.example.demo.model.entitys.OsEntity;
import com.example.demo.repository.OsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CalcServicePerma {

    @Autowired
    OsRepository osRepository;

    @Autowired
    CsvReaderService csvReaderService;

    @Autowired
    CalcDistanceService calcDistanceService;

    @Autowired
    CalcAmountTemp calcAmountTemp;

    public OsDto makeOs(OsDto osDto){

        if(osRepository.existsByOsNumber(osDto.osNumber())){
            throw new RuntimeException("This Os have exists!");
        }
        OsEntity osEntity= new OsEntity(osDto);

        osRepository.save(osEntity);
        return osDto;
    }
    public List<OsDto> makeOsByCsvList(MultipartFile filePath) {
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
        OsDto convertEntityToDtoOs=new OsDto(null,osEntity1.getContract(),osEntity1.getOsNumber(),
                osEntity1.getOccurrence(),osEntity1.getUnit(),osEntity1.getScreeningDate(),osEntity1.getDistanceBaseOs(),osEntity1.getArea(),
                osEntity1.getLatitude(),osEntity1.getLongitude(),osEntity1.getResponsibleScreening());

        return convertEntityToDtoOs;
    }

    public List<OsDto> getAllOs(){
        return osRepository.findAll().stream()
                .map(osEntity -> new OsDto(null,
                        osEntity.getContract(),
                        osEntity.getOsNumber(),
                        osEntity.getOccurrence(),
                        osEntity.getUnit(),
                        osEntity.getScreeningDate(),
                        osEntity.getDistanceBaseOs(),
                        osEntity.getArea(),
                        osEntity.getLatitude(),
                        osEntity.getLongitude(),
                        osEntity.getResponsibleScreening()
                ))
                .collect(Collectors.toList());
    }

    //This method does two things because it's part of the business rule that, when deactivated in this system, it accumulates the cost that would have been incurred to go there.
    public void updateOsInactive(OsActiveDto osActiveDto){
            Boolean osCheck= osActiveDto.isEnable();
            OsEntity osEntity = osRepository.findByOsNumber(osActiveDto.osDto().osNumber())
                .orElseThrow(() -> new RuntimeException("This os doesn't exist in database."));

                if(!osCheck){
                    throw new RuntimeException("This os is closed");
                }

                osEntity.setEnable(false);
                String amount=calcDistanceService.expensiveAvoided(osActiveDto.osDto(),osActiveDto.technicalDto());
                String cleanFormat=amount.replace("R$", "").trim();
                cleanFormat=cleanFormat.replace(".", "").replace(",", ".");

                Double convertedAmount=Double.parseDouble(cleanFormat);

                AccumulatedExpenseEntity accumulatedExpenseEntity=new AccumulatedExpenseEntity(convertedAmount);

                osRepository.save(osEntity);

    }


    public String osAvoidedSpent(OsActiveDto osActiveDto){
         return calcDistanceService.expensiveAvoided(osActiveDto.osDto(),osActiveDto.technicalDto());
    }

    public String osAvoidedSpentByGeoLocation(Double latitude1,Double longitude1,Double latitude2,Double longitude2){
        Double distance=calcDistanceService.distanceCalculatedByGeoLocation(latitude1,longitude1,latitude2,longitude2);
        return distance + "km";
    }


    public String amountPlus(){
        return "R$" + calcAmountTemp.getAllAmountTemp();
    }


}
