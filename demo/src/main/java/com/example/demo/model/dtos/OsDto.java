package com.example.demo.model.dtos;

import com.example.demo.model.UnitEnum;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record OsDto(

        @NotNull(message = "Contract cannot be null")
        @Min(value = 1, message = "Contract must be at least 1")
        @Max(value = 20, message = "Contract cannot exceed 20")
        Integer contract,

        @NotNull(message = "OS number cannot be null")
        @Min(value = 1, message = "OS number must be at least 1")
        @Max(value = 30, message = "OS number cannot exceed 30")
        Integer osNumber,
        @NotBlank(message = "This field needs some information.")
        String occurence,
        @NotNull(message = "Unit must be specified")
        UnitEnum unit,
        @NotNull(message = "Screening date is required")
        @PastOrPresent(message = "Screening date must be in the present or past")
        LocalDate screeningDate,
        @NotNull
        Double distanceBaseOs,
        @NotBlank
        String area,
        @NotNull
        Double latitude,
        @NotNull
        Double longitude,
        @NotBlank
        @Size(min=4,max=80)
        String responsibleScreening
) {


    @Override
    public String toString() {
        return String.format("""
        {
          "contract": "%s",
          "OsNumber": "%s",
          "Occurrence": "%s",
          "Unit": %s,
          "DistanceBaseToOs": %.6f,
          "Area": %s,
          "Latitude": %.6f,
          "Longitude": %.6f,
          "ResponsibleScreening": %s
        }
        """);
    }
}
