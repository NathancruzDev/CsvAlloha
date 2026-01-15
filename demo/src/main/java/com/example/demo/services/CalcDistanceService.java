package com.example.demo.services;

import com.example.demo.model.entitys.OsEntity;
import com.example.demo.model.entitys.TechnicalEntity;

public class CalcDistanceService  {

    private final Double fuel=6.22;

    public Double distance(Double longitude1 , Double latitude1, Double  longitude2,Double latitude2){
            return 1.0;
    }

    public Double expensiveAvoided(OsEntity os, TechnicalEntity technical){
        Double distanceAvoided=distance(os.getLongitude(),os.getLatitude(), technical.getLongitude(), technical.getLatitude());
        return distanceAvoided* technical.getKmXLCar()* fuel;
    }


}
