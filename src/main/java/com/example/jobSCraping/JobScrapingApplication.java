package com.example.jobSCraping;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JobScrapingApplication {

	public static void main(String[] args) {
		// Set the path to the ChromeDriver executable
		System.setProperty("webdriver.chrome.driver", "/Users/kairee/Downloads/chromedriver-mac-x64/chromedriver");

		// Initialize WebDriver
		WebDriver driver = new ChromeDriver();

		try {
			JobSearch jobSearch = new JobSearch();
			List<JobListing> jobListings = jobSearch.performJobSearch(driver, "Software Engineer", 5); // Specify the number of pages to scrape

			// Print the job listings
			for (JobListing jobListing : jobListings) {
				System.out.println(jobListing);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}

}
}
