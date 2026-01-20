package com.example.demo.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TechnicalDto (
        @NotBlank(message = "Name cannot be null.")
        String name,
        @NotNull(message = "OS number cannot be null")
        Integer osNumber,
        @NotNull(message = "Contract number cannot be null")
        Integer contract,
        @NotNull(message = "This field needs some information(latitude).")
        Double latitude,
        @NotNull(message = "This field needs some information(longitude).")
        Double longitude,
        @NotBlank(message = "Car name cannot be blank.")
        String car,
        @NotNull(message = "This field needs some information(KmCarXL).")
        Double kmCarXL

) {


}
