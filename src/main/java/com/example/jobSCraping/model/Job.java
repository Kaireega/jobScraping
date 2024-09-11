package com.example.jobSCraping.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 512)
    String title;
    @Column(length = 512)
    String company;

    @Column(length = 2048 ,unique = true)
    String url;

    public Job(String title, String company, String url) {
        this.title = title;
        this.company = company;
        this.url = url;
    }



}