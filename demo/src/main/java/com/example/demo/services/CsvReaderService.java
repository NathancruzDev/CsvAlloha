package com.example.demo.services;

import com.example.demo.model.UnitEnum;
import com.example.demo.model.dtos.OsDto;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;


@Service
public class CsvReaderService {
    String path="C:Downloads\teste";
    File fileCsv=new File(path);

    public List<OsDto> fileCsvReader(File fileCsv) {

        if(!fileCsv.exists()){
            throw new RuntimeException("File not exists.");
        }

        List<OsDto> result = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileCsv))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] columns = line.split(",");

                if (columns.length != 10) {
                    throw new RuntimeException("Invalid row, expected 10 columns:" + line);
                }

                ArrayList<String> values = new ArrayList<>(List.of(columns));

                OsDto dto = makeDto(values);

                result.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return result;

    }

    public  OsDto makeDto(ArrayList<String> obj){

        String contractStr = obj.get(0);
        String osNumberStr = obj.get(1);

        Integer contract=Integer.parseInt(contractStr);
        Integer osNumber=Integer.parseInt(osNumberStr);

        String ocurrence = obj.get(2);

        String unitStr = obj.get(3);
        UnitEnum unit=UnitEnum.valueOf(unitStr.trim().toUpperCase());

        String screeningDateStr = obj.get(4);
        LocalDate screeningDate= LocalDate.parse(
                screeningDateStr.trim(),
                DateTimeFormatter.ofLocalizedDate(FormatStyle.valueOf("dd/MM/yyyy"))
        );

        String distanceBaseOsStr = obj.get(5);
        Double distanceBaseOs=Double.parseDouble(distanceBaseOsStr.trim());

        String area = obj.get(6);

        String latitudeStr = obj.get(7);
        Double latitude=Double.parseDouble(latitudeStr.trim());

        String longitudeStr = obj.get(8);
        Double longitude=Double.parseDouble(longitudeStr.trim());
        String responsibleScreening = obj.get(9);

        return new OsDto(contract,osNumber,ocurrence,unit,screeningDate,
                distanceBaseOs,area,
                latitude,longitude,responsibleScreening);
    }
}
