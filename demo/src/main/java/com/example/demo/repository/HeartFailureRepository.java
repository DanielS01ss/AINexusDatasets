package com.example.demo.repository;

import com.example.demo.model.HeartFailure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartFailureRepository extends JpaRepository<HeartFailure,Integer> {

}