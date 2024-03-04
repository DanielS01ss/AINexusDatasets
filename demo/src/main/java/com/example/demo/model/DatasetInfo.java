package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "DatasetInfo")
public class DatasetInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dataset_info_seq")
    @SequenceGenerator(name = "dataset_info_seq", sequenceName = "dataset_info_seq", allocationSize = 1)
    @JsonProperty("id")
    private Integer id;

    @Column
    @JsonProperty("about")
    private String about;

    @Column
    @JsonProperty("keywords")
    private List<String> keywords;

    @Column
    @JsonProperty("authors")
    private List<String> authors;

    @Column
    @JsonProperty("publish")
    private LocalDate publish;

    @Column
    @JsonProperty("datasetid")
    private Integer datasetid;

}
