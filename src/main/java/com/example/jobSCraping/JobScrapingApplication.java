package com.example.jobSCraping;
import com.example.jobSCraping.model.Job;
import com.example.jobSCraping.service.JobSearch;
import com.example.jobSCraping.service.SendJobToDatabase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class JobScrapingApplication {

	public static void main(String[] args) {
		// Set the path to the ChromeDriver executable
		// Initialize WebDriver
		System.setProperty("webdriver.chrome.driver", "/Users/kairee/Downloads/chromedriver-mac-x64/chromedriver");
		WebDriver driver = new ChromeDriver();

		List<Job> jobListings ;

		// try-catch statement to execute a Job Search
		// return a list of objects, each object contains three attributes : Job title , job link , company name
		try {
			JobSearch jobSearch = new JobSearch();
			 jobListings = jobSearch.performJobSearch(driver, "Software Developer", 1); // Specify the number of pages to scrape

			// Print the job listings
			for (Job jobListing : jobListings) {
				System.out.println(jobListing);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}

		// try-catch statement to save listing to database

		try{
			SendJobToDatabase loadjob = new SendJobToDatabase();


		}catch(Exception e) {
			e.printStackTrace();
		}



	}
}
