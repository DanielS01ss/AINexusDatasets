package com.example.demo.repository;

import com.example.demo.model.DatasetInfo;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataSetInfoRepository extends JpaRepository<DatasetInfo, Integer>{
}
