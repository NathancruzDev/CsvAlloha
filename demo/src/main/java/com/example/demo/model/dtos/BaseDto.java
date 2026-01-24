package com.example.demo.model.dtos;

import com.example.demo.model.UnitEnum;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.autoconfigure.web.WebProperties;

public record BaseDto(

        Integer id,
        @NotNull
        UnitEnum unit,
        @NotBlank
        String address,
        @NotBlank
        String area,
        @NotNull
        Double latitude,
        @NotNull
        Double longitude
) {

    @Override
    public String toString() {
        return String.format("""
        {
          "unit": "%s",
          "address": "%s",
          "area": "%s",
          "latitude": %.6f,
          "longitude": %.6f
        }
        """, unit, address, area, latitude, longitude);
    }
}
