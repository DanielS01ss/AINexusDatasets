package com.example.demo.repository;

import com.example.demo.model.AllDatasets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AllDatasetsRepository extends JpaRepository<AllDatasets,Integer> {
    List<AllDatasets> findByDatabaseType(String database_type);
}
