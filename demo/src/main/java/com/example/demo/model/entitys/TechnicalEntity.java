package com.example.demo.model.entitys;

import com.example.demo.repository.CoordinateInterface;

public class TechnicalEntity  {
    String name;

    Integer osNumber;
    Integer contract;

    Double latitude;
    Double longitude;

    String car;
    //Kilometro que o carro faz por Litro = KmCarXL
    Double kmCarXL;

    public TechnicalEntity(String name, Integer osNumber, Integer contract, Double latitude, Double longitude, String car, Double kmCarXL) {
        this.name = name;
        this.osNumber = osNumber;
        this.contract = contract;
        this.latitude = latitude;
        this.longitude = longitude;
        this.car = car;
        this.kmCarXL = kmCarXL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOsNumber() {
        return osNumber;
    }

    public void setOsNumber(Integer osNumber) {
        this.osNumber = osNumber;
    }

    public Integer getContract() {
        return contract;
    }

    public void setContract(Integer contract) {
        this.contract = contract;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public Double getKmXLCar() {
        return kmCarXL;
    }

    public void setKmXLCar(Double kmCarXL) {
        this.kmCarXL = kmCarXL;
    }
}
