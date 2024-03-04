package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "water_potability")
public class WaterPotability {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "water_potability_seq")
    @SequenceGenerator(name = "water_potability_seq", sequenceName = "water_potability_seq", allocationSize = 1)
    private Integer id;

    public WaterPotability(Double ph,Double hardness,Double solids, Double chloramines, Double sulfate, Double conductivity, Double organic_carbon,Double trihalomethanes,Double turbidity,Integer potability){
        this.ph = ph;
        this.hardness = hardness;
        this.solids = solids;
        this.chloramines = chloramines;
        this.sulfate = sulfate;
        this.conductivity = conductivity;
        this.organic_carbon = organic_carbon;
        this.trihalomethanes = trihalomethanes;
        this.turbidity = turbidity;
        this.potability = potability;
    }

    @Column(name = "ph")
    @JsonProperty("ph")
    private Double ph;

    @Column
    @JsonProperty("hardness")
    private Double hardness;

    @Column
    @JsonProperty("solids")
    private Double solids;

    @Column
    @JsonProperty("chloramines")
    private Double chloramines;

    @Column
    @JsonProperty("sulfate")
    private Double sulfate;

    @Column
    @JsonProperty("conductivity")
    private Double conductivity;

    @Column
    @JsonProperty("organic_carbon")
    private Double organic_carbon;

    @Column
    @JsonProperty("trihalomethanes")
    private Double trihalomethanes;

    @Column
    @JsonProperty("turbidity")
    private Double turbidity;

    @Column
    @JsonProperty("potability")
    private Integer potability;

}
