package com.example.demo.repository;

import com.example.demo.model.RedWine;
import com.example.demo.model.WhiteWine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RedWineRepository extends JpaRepository<RedWine,Integer> {
}
