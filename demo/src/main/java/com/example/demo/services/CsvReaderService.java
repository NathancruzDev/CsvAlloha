package com.example.demo.services;

import com.example.demo.model.UnitEnum;
import com.example.demo.model.dtos.OsDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;


@Service
public class CsvReaderService {

    @Transactional
    public List<OsDto> fileCsvReader(MultipartFile fileCsv) {

        if(fileCsv==null || fileCsv.isEmpty()){
            throw new RuntimeException("File not exists.");
        }

        List<OsDto> result = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileCsv.getInputStream()))) {

            String line;
            bufferedReader.readLine();
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
        LocalDate screeningDate = LocalDate.parse(
                screeningDateStr.trim(),
                DateTimeFormatter.ofPattern("dd/MM/yyyy")
        );

        String distanceBaseOsStr = obj.get(5);
        Double distanceBaseOs=Double.parseDouble(distanceBaseOsStr.trim());

        String area = obj.get(6);

        String latitudeStr = obj.get(7);
        Double latitude = removePointerDouble(obj.get(7).trim());

        String longitudeStr = obj.get(8);
        Double longitude= removePointerDouble(obj.get(8).trim());
        String responsibleScreening = obj.get(9);

        return new OsDto(null,contract,osNumber,ocurrence,unit,screeningDate,
                distanceBaseOs,area,
                latitude,longitude,responsibleScreening);
    }

    private Double removePointerDouble(String number){
        if(number == null || number.isEmpty()){
            return 0.0;
        }
        String parse = number.trim().replace(".", "");
        return Double.parseDouble(parse);
    }
}


