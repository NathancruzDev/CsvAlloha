package com.example.demo.model.dtos;


public record OsActiveDto(
       OsDto osDto,
       boolean isEnable,
       TechnicalDto technicalDto
) {
}
