package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "all_datasets")
public class AllDatasets {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dataset_info_seq")
    @SequenceGenerator(name = "dataset_info_seq", sequenceName = "dataset_info_seq", allocationSize = 1)
    @JsonProperty("id")
    private Integer id;

    @Column
    @JsonProperty("dataset_name")
    private String dataset_name;

    @Column
    @JsonProperty("dataset_id")
    private Integer dataset_id;

    @Column
    @JsonProperty("dataset_info_id")
    private Integer dataset_info_id;

    @Column
    @JsonProperty("database_name")
    private String database_name;

    @Column
    @JsonProperty("database_type")
    private String databaseType;

    public String getDataset_name(){
        return this.database_name;
    }
}
