package com.example.demo.controller;


import com.example.demo.model.*;
import com.example.demo.repository.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/dataset")
@RequiredArgsConstructor
public class DataSetController {

    private final DataSetInfoRepository dataSetInfoRepository;
    private final AllDatasetsRepository allDatasetsRepository;

    private final HeartFailureRepository heartFailureRepository;

    private final RedWineRepository redWineRepository;

    private final WhiteWineRepository whiteWineRepository;

    private final WaterPotabilityRepository waterPotabilityRepository;

    @GetMapping("/all-datasets")
    public ResponseEntity<List<AllDatasets>> getAllDatasets(){
        List<AllDatasets> allDatasets = allDatasetsRepository.findAll();
        return ResponseEntity.ok(allDatasets);
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
        if(idParsed <=0 || idParsed>=5 ){
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
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
