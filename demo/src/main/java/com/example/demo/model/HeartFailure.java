package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="heart_failure")
public class HeartFailure {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "heart_failure_seq")
    @SequenceGenerator(name = "heart_failure_seq", sequenceName = "heart_failure_seq", allocationSize = 1)
    private Integer id;


    public HeartFailure(Integer age, Integer anaemia,Integer creatinine_phosphokinase, Integer diabetes, Integer ejection_fraction,Integer high_blood_pressure, Integer platelets,Double serum_creatinine,Integer serum_sodium,Integer sex, Integer smoking, Integer time, Integer death_event){
        this.age = age;
        this.anaemia = anaemia;
        this.creatinine_phosphokinase = creatinine_phosphokinase;
        this.diabetes = diabetes;
        this.ejection_fraction = ejection_fraction;
        this. high_blood_pressure = high_blood_pressure;
        this.platelets = platelets;
        this.serum_creatinine = serum_creatinine;
        this.serum_sodium = serum_sodium;
        this.sex = sex;
        this.smoking = smoking;
        this.time = time;
        this.death_event = death_event;
    }

    @Column
    @JsonProperty("age")
    private Integer age;

    @Column
    @JsonProperty("anaemia")
    private Integer anaemia;

    @Column
    @JsonProperty("creatinine_phosphokinase")
    private Integer creatinine_phosphokinase;

    @Column
    @JsonProperty("diabetes")
    private Integer diabetes;

    @Column
    @JsonProperty("ejection_fraction")
    private Integer ejection_fraction;

    @Column
    @JsonProperty("high_blood_pressure")
    private Integer high_blood_pressure;

    @Column
    @JsonProperty("platelets")
    private Integer platelets;

    @Column
    @JsonProperty("serum_creatinine")
    private Double serum_creatinine;

    @Column
    @JsonProperty("serum_sodium")
    private Integer serum_sodium;

    @Column
    @JsonProperty("sex")
    private Integer sex;

    @Column
    @JsonProperty("smoking")
    private Integer smoking;

    @Column
    @JsonProperty("time")
    private Integer time;

    @Column
    @JsonProperty("death_event")
    private Integer death_event;
}
