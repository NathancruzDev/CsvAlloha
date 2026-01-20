package com.example.demo.services;

import com.example.demo.client.GeoapifyClient;
import com.example.demo.model.dtos.JSON.GeoApifyJson.DistanceResponseDto;
import com.example.demo.model.dtos.OsDto;
import com.example.demo.model.dtos.TechnicalDto;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.Locale;

/*
 *https://myprojects.geoapify.com/projects
 *
 *  */
@Service
public class CalcDistanceService extends GeoapifyClient {

    private final Double fuel=6.22;
    GeoapifyClient geoapifyClient=new GeoapifyClient();
    public DistanceResponseDto distancexKm(TechnicalDto technicalDto, OsDto osDto){

        try{
            String distanced=geoapifyClient.distance(technicalDto.latitude(), technicalDto.longitude(), osDto.latitude(), osDto.longitude());
            Double distanceCalculated=(Double.parseDouble(distanced))/1000;
            return new DistanceResponseDto(distanceCalculated);
        }catch (RuntimeException e){
            throw new RuntimeException();
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


}
