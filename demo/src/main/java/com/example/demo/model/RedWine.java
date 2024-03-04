package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="red_wine")
public class RedWine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "red_wine_seq")
    @SequenceGenerator(name = "red_wine_seq", sequenceName = "red_wine_seq", allocationSize = 1)
    private Integer id;

    public RedWine(Double fixed_acidity, Double volatile_acidity, Double citric_acid, Double residual_sugar, Double chlorides, Double free_sulfur_dioxide, Double total_sulfur_dioxide, Double density, Double ph, Double sulphates, Double alcohol, Double quality) {
        this.fixed_acidity = fixed_acidity;
        this.volatile_acidity = volatile_acidity;
        this.citric_acid = citric_acid;
        this.residual_sugar = residual_sugar;
        this.chlorides = chlorides;
        this.free_sulfur_dioxide = free_sulfur_dioxide;
        this.total_sulfur_dioxide = total_sulfur_dioxide;
        this.density = density;
        this.ph = ph;
        this.sulphates = sulphates;
        this.alcohol = alcohol;
        this.quality = quality;
    }

    @Column
    @JsonProperty("fixed_acidity")
    private Double fixed_acidity;

    @Column
    @JsonProperty("volatile_acidity")
    private Double volatile_acidity;

    @Column
    @JsonProperty("citric_acid")
    private Double citric_acid;

    @Column
    @JsonProperty("residual_sugar")
    private Double residual_sugar;

    @Column
    @JsonProperty("chlorides")
    private Double chlorides;

    @Column
    @JsonProperty("free_sulfur_dioxide")
    private Double free_sulfur_dioxide;

    @Column
    @JsonProperty("total_sulfur_dioxide")
    private Double total_sulfur_dioxide;

    @Column
    @JsonProperty("density")
    private Double density;

    @Column
    @JsonProperty("ph")
    private Double ph;

    @Column
    @JsonProperty("sulphates")
    private Double sulphates;

    @Column
    @JsonProperty("alcohol")
    private Double alcohol;

    @Column
    @JsonProperty("quality")
    private Double quality;

}
