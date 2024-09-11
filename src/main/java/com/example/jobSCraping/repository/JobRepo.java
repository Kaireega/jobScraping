package com.example.jobSCraping.repository;

import com.example.jobSCraping.model.Job;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface JobRepo extends CrudRepository<Job, Long> {
}