package com.example.demo.repository;

import com.example.demo.model.AllDatasets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllDatasetsRepository extends JpaRepository<AllDatasets,Integer> {
}
