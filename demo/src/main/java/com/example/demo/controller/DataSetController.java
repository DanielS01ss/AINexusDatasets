package com.example.demo.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.MinioFileService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/dataset")
@RequiredArgsConstructor
public class DataSetController {

    @Autowired
    private MinioFileService minioService;

    private final DataSetInfoRepository dataSetInfoRepository;
    private final AllDatasetsRepository allDatasetsRepository;

    private final HeartFailureRepository heartFailureRepository;

    private final RedWineRepository redWineRepository;

    private final WhiteWineRepository whiteWineRepository;

    private final WaterPotabilityRepository waterPotabilityRepository;

    @GetMapping("/all-datasets")
    public ResponseEntity<List<AllDatasets>> getAllDatasets(@RequestParam(value = "email", defaultValue = "") String email){
        if(email.length() != 0){
            List<AllDatasets> datasetForUser = allDatasetsRepository.findByDatabaseType(email);
            Pageable firstFour = PageRequest.of(0, 4);
            List<AllDatasets> firstFourDatasets = allDatasetsRepository.findAll(firstFour).getContent();
            // Creează o nouă listă pentru a combina listele existente
            List<AllDatasets> combinedList = new ArrayList<>(firstFourDatasets);
            // Adaugă toate elementele din a doua listă în lista combinată
            combinedList.addAll(datasetForUser);
            return ResponseEntity.ok(combinedList);
        } else {
            Pageable firstFour = PageRequest.of(0, 4);
            List<AllDatasets> firstFourDatasets = allDatasetsRepository.findAll(firstFour).getContent();
            return ResponseEntity.ok(firstFourDatasets);
        }

    }


    @GetMapping("/dataset-info")
    public ResponseEntity<DatasetInfo> getDataSetSnippet (@RequestParam(name = "id", required = true) String id){
        Integer parsedId = Integer.parseInt(id);
        Optional<DatasetInfo> datasetInfo = dataSetInfoRepository.findById(parsedId);
        DatasetInfo d1 = new DatasetInfo();
         if(datasetInfo.isPresent()){
             d1 = datasetInfo.get();
             return ResponseEntity.ok(d1);
         }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/fetch-snippet")
    public ResponseEntity<?> getSnippet (@RequestParam(name = "id", required = true) String id){
        Integer idParsed = Integer.parseInt(id);
        if(idParsed <=0 ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(idParsed == 1) {
            List<HeartFailure> heartFailureList = heartFailureRepository.findAll();
            List<HeartFailure> limitedList = heartFailureList.stream()
                    .limit(10)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(limitedList);
        } else if (idParsed == 2){
            List<RedWine> redWineList = redWineRepository.findAll();
            List<RedWine> limitedList = redWineList.stream()
                    .limit(10)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(limitedList);
        } else if (idParsed == 3){
            List<WaterPotability> waterPotabilities = waterPotabilityRepository.findAll();
            List<WaterPotability> limitedList = waterPotabilities.stream()
                    .limit(10)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(limitedList);
        } else if (idParsed == 4){
            List<WhiteWine> whiteWineList = whiteWineRepository.findAll();
            List<WhiteWine> limitedList = whiteWineList.stream()
                    .limit(10)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(limitedList);
        } else {
            //daca fisierul are un ID mai mare de 4 inseamna ca este din MINIO asa ca o sa apelam functia aferenta
            Integer parsedId = Integer.parseInt(id);
            Optional<AllDatasets> allDatasets = allDatasetsRepository.findById(parsedId);

            if(allDatasets.isPresent()){
                AllDatasets foundDataset = allDatasets.get();

                try{

                    List<Map<String, String>> csvFileData = minioService.readCsvFileFromMinio("datasets",foundDataset.getDataset_name(),10);
                    return ResponseEntity.ok(csvFileData);
                } catch (Exception ex){
                    System.out.println("There was an error while fetching data from MINIO");
                    System.out.println(ex);
                }
            } else{
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }


        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
