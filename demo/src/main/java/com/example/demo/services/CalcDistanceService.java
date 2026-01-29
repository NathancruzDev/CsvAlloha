package com.example.demo.services;

import com.example.demo.client.GeoapifyClient;
import com.example.demo.model.dtos.AccumulatedExpensiveDto;
import com.example.demo.model.dtos.JSON.GeoApifyJson.DistanceResponseDto;
import com.example.demo.model.dtos.OsDto;
import com.example.demo.model.dtos.TechnicalDto;
import com.example.demo.model.entitys.AccumulatedExpenseEntity;
import com.example.demo.model.entitys.OsEntity;
import com.example.demo.repository.OsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.Locale;

/*
 *https://myprojects.geoapify.com/projects
 *
 *  */
@Service
public class CalcDistanceService extends GeoapifyClient {
    @Autowired
    OsRepository osRepository;

    private final Double fuel=6.22;

    GeoapifyClient geoapifyClient=new GeoapifyClient();

    public DistanceResponseDto distancexKm(TechnicalDto technicalDto, OsDto osDto){

        try{
            String distanced=geoapifyClient.distance(technicalDto.latitude(), technicalDto.longitude(), osDto.latitude(), osDto.longitude());
            Double distanceCalculated=(Double.parseDouble(distanced))/1000;
            return new DistanceResponseDto(distanceCalculated);
        }catch (RuntimeException e){
            throw new RuntimeException("The distance could not be calculated.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
   public String expensiveAvoided(OsDto osDto,TechnicalDto technicalDto){
       Double distance=distancexKm(technicalDto,osDto).distanceKm();
       Double calcLiteers=distance/ technicalDto.kmCarXL();
       Double totalCust=calcLiteers*fuel;

       NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
       return  "R$"+nf.format(totalCust);
    }

    public Double distanceCalculatedByGeoLocation(Double latitude1,Double longitude1,Double latitude2,Double longitude2){
        try{
            String distanced= geoapifyClient.distance(latitude1,longitude1,latitude2,longitude2);
            return Double.parseDouble(distanced)/1000;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
