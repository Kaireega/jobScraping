package com.example.jobSCraping.controller;

import com.example.jobSCraping.model.Job;
import com.example.jobSCraping.service.JobSearchService;
import org.springframework.web.bind.annotation.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/jobs")
public class JobController {
    private final JobSearchService jobSearchService;
    public JobController(JobSearchService jobSearchService) {
        this.jobSearchService = jobSearchService;
    }

    @GetMapping("/search")
    public List<Job> searchJobs(@RequestParam String jobTitle, @RequestParam int numberOfPages) throws InterruptedException
    {
        WebDriver driver = new ChromeDriver();
        List<Job> jobs = jobSearchService.performJobSearch(driver, jobTitle, numberOfPages);
        jobSearchService.saveJobsToDatabase(jobs);
        driver.quit();
        return jobs;
    }
}
