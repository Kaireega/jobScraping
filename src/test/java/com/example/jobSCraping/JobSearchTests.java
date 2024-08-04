package com.example.jobSCraping;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class JobSearchTests {

    private WebDriver driver;
    private WebDriverWait wait;
    private JobSearch jobSearch;

    @BeforeEach
    public void setUp() {
        driver = Mockito.mock(WebDriver.class);
        wait = Mockito.mock(WebDriverWait.class);
        jobSearch = new JobSearch();
    }

    @Test
    public void testPerformJobSearch() throws InterruptedException {
        String jobTitle = "Software Engineer";
        int numberOfPages = 1;

        // Mock the behavior of the WebDriver and WebDriverWait
        WebElement searchField = Mockito.mock(WebElement.class);
        WebElement searchButton = Mockito.mock(WebElement.class);
        WebElement jobCardsContainer = Mockito.mock(WebElement.class);
        WebElement jobList = Mockito.mock(WebElement.class);
        List<WebElement> jobItems = new ArrayList<>();
        WebElement jobItem = Mockito.mock(WebElement.class);
        jobItems.add(jobItem);

        when(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("text-input-what")))).thenReturn(searchField);
        when(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='yosegi-InlineWhatWhere-primaryButton']")))).thenReturn(searchButton);
        when(driver.findElement(By.id("mosaic-provider-jobcards"))).thenReturn(jobCardsContainer);
        when(jobCardsContainer.findElement(By.tagName("ul"))).thenReturn(jobList);
        when(jobList.findElements(By.tagName("li"))).thenReturn(jobItems);

        // Mock the job item elements
        WebElement jobTitleElement = Mockito.mock(WebElement.class);
        WebElement aTag = Mockito.mock(WebElement.class);
        WebElement spanTag = Mockito.mock(WebElement.class);
        WebElement companyLocationElement = Mockito.mock(WebElement.class);
        WebElement companyNameSpan = Mockito.mock(WebElement.class);

        when(jobItem.findElements(By.cssSelector("h2.jobTitle"))).thenReturn(List.of(jobTitleElement));
        when(jobTitleElement.findElement(By.tagName("a"))).thenReturn(aTag);
        when(aTag.findElement(By.tagName("span"))).thenReturn(spanTag);
        when(spanTag.getAttribute("title")).thenReturn("Software Engineer");
        when(aTag.getAttribute("href")).thenReturn("http://example.com/job1");

        when(jobItem.findElements(By.cssSelector("div.company_location"))).thenReturn(List.of(companyLocationElement));
        when(companyLocationElement.findElement(By.cssSelector("span"))).thenReturn(companyNameSpan);
        when(companyNameSpan.getText()).thenReturn("Example Company");

        // Mock the next page button
        WebElement nextPageButton = Mockito.mock(WebElement.class);
        when(driver.findElement(By.cssSelector("a[data-testid='pagination-page-next']"))).thenReturn(nextPageButton);

        // Perform the job search
        List<JobListing> jobListings = jobSearch.performJobSearch(driver, jobTitle, numberOfPages);

        // Verify the results
        assertEquals(1, jobListings.size());
        JobListing jobListing = jobListings.get(0);
        assertEquals("Software Engineer", jobListing.title);
        assertEquals("Example Company", jobListing.company);
        assertEquals("http://example.com/job1", jobListing.url);


    }
}