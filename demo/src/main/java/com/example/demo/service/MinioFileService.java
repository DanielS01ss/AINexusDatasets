package com.example.demo.service;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MinioFileService {

    @Autowired
    private MinioClient minioClient;

    public static List<Map<String, String>> parseCSVArray(String[] csvArray) {
        // Extrage capul de tabel din primul element al array-ului
        String[] headers = csvArray[0].split(",");

        // Initializează lista pentru a stoca obiectele rezultate
        List<Map<String, String>> result = new ArrayList<>();

        // Iterează peste fiecare linie din array-ul CSV, începând de la indexul 1 pentru a omite capul de tabel
        for (int i = 1; i < csvArray.length; i++) {
            String[] values = csvArray[i].split(",");
            // Verifică dacă numărul de câmpuri din linie corespunde cu numărul de coloane din capul de tabel

            // Construiește un obiect Map pentru a reprezenta rândul curent din tabel
            Map<String, String> row = new HashMap<>();
            // Asociază fiecare valoare cu numele corespunzător din capul de tabel
            for (int j = 0; j < headers.length; j++) {
                String columnName = headers[j].trim().toLowerCase().replace(" ", "-");
                if( j < values.length){
                    row.put(columnName, values[j].trim());
                } else {
                    row.put(columnName, "");
                }

            }
            // Adaugă obiectul Map în lista rezultat
            result.add(row);
        }

        return result;
    }

    public List<Map<String, String>> readCsvFileFromMinio(String bucketName, String objectName, int numberOfLines) throws Exception {
        // Descarcă fișierul CSV din MinIO
        List<String> first10Lines = new ArrayList<>();
        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket("datasets")
                        .object(objectName)
                        .build())){
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                 CSVParser csvParser = CSVFormat.DEFAULT.parse(reader)) {
                int linesRead = 0;
                for (CSVRecord record : csvParser) {
                    if (linesRead >= 10) {
                        break;
                    }
                    // Iterăm peste fiecare valoare a înregistrării și o adăugăm în listă
                    StringBuilder line = new StringBuilder();
                    for (String value : record) {
                        line.append(value).append(",");
                    }
                    // Eliminăm virgula finală și adăugăm linia în listă
                    line.deleteCharAt(line.length() - 1);
                    first10Lines.add(line.toString());
                    linesRead++;
                }
            }

            String[] parsedLines = first10Lines.toArray(new String[0]);
            List<Map<String, String>> parsedFirst10Lines = parseCSVArray(parsedLines);
            return parsedFirst10Lines;
        }
        catch(Exception ex) {
            System.out.println("There was an error while fetching data from minio!");
            System.out.println(ex);

        }
          return null;
    }
}