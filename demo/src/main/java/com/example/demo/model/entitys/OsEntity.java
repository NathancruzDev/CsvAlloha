package com.example.demo.model.entitys;

import com.example.demo.model.dtos.OsDto;
import com.example.demo.repository.CoordinateInterface;
import com.example.demo.model.UnitEnum;

import java.time.LocalDate;

public class OsEntity implements CoordinateInterface {

    private Integer contract;
    private Integer osNumber;
    private String occurrence;
    private UnitEnum unit;
    //Data da Triagem
    private LocalDate screeningDate;
    private Double distanceBaseOs;
    private String area;
    private Double latitude;
    private Double longitude;
    private String responsibleScreening;

    public OsEntity(Integer contract, Integer osNumber, String occurrence, UnitEnum unit, LocalDate screeningDate,
                    Double distanceBaseOs, String area, Double latitude, Double longitude, String responsibleScreening) {
        this.contract = contract;
        osNumber = osNumber;
        this.occurrence = occurrence;
        this.unit = unit;
        this.screeningDate = screeningDate;
        this.distanceBaseOs = distanceBaseOs;
        this.area = area;
        this.latitude = latitude;
        this.longitude = longitude;
        this.responsibleScreening = responsibleScreening;
    }

    public OsEntity() {
    }

    public OsEntity(OsDto osDto) {
        this.contract=osDto.contract();
        this.osNumber=osDto.osNumber();
        this.occurrence = osDto.occurence();
        this.unit = osDto.unit();
        this.screeningDate = osDto.screeningDate();
        this.distanceBaseOs = osDto.distanceBaseOs();
        this.area = osDto.area();
        this.latitude =  osDto.latitude();
        this.longitude = osDto.longitude();
        this.responsibleScreening = osDto.responsibleScreening();

    }

    public Integer getContract() {
        return contract;
    }

    public void setContract(Integer contract) {
        this.contract = contract;
    }

    public Integer getOsNumber() {
        return osNumber;
    }

    public void setOsNumber(Integer osNumber) {
        this.osNumber = osNumber;
    }

    public String getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(String occurrence) {
        this.occurrence = occurrence;
    }

    public UnitEnum getUnit() {
        return unit;
    }

    public void setUnit(UnitEnum unit) {
        this.unit = unit;
    }

    public LocalDate getScreeningDate() {
        return screeningDate;
    }

    public void setScreeningDate(LocalDate screeningDate) {
        this.screeningDate = screeningDate;
    }

    public Double getDistanceBaseOs() {
        return distanceBaseOs;
    }

    public void setDistanceBaseOs(Double distanceBaseOs) {
        this.distanceBaseOs = distanceBaseOs;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getResponsibleScreening() {
        return responsibleScreening;
    }

    public void setResponsibleScreening(String responsibleScreening) {
        this.responsibleScreening = responsibleScreening;
    }


    @Override
    public String area() {
        return "";
    }

    @Override
    public Double getLatitude() {
        return 0.0;
    }

    @Override
    public Double getLongitude() {
        return 0.0;
    }
}
