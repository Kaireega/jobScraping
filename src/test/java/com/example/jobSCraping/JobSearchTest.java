package com.example.jobSCraping;
import com.example.jobSCraping.model.Job;
import com.example.jobSCraping.service.JobSearch;
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

import static org.mockito.Mockito.*;

class JobSearchTest {
    // Class variables
    private WebDriver driver;
    private WebDriverWait wait;
    private JobSearch jobSearch;

    @BeforeEach
    public void setUp() {
        // Initialize the WebDriver mock
        driver = Mockito.mock(WebDriver.class);

        // Initialize WebDriverWait with a duration, using the mocked WebDriver
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Corrected to assign to the class variable

        // Initialize the JobSearch object with the mocked driver and wait
        jobSearch = new JobSearch();  // This ensures jobSearch is properly initialized
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

        WebElement jobTitleElement = Mockito.mock(WebElement.class);
        WebElement aTag = Mockito.mock(WebElement.class);
        WebElement spanTag = Mockito.mock(WebElement.class);
        WebElement companyLocationElement = Mockito.mock(WebElement.class);
        WebElement companyNameSpan = Mockito.mock(WebElement.class);
        WebElement nextPageButton = Mockito.mock(WebElement.class);

        // Mock interactions with WebDriver and WebDriverWait
        when(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("text-input-what")))).thenReturn(searchField);
        when(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='yosegi-InlineWhatWhere-primaryButton']")))).thenReturn(searchButton);

        when(driver.findElement(By.id("mosaic-provider-jobcards"))).thenReturn(jobCardsContainer);
        when(jobCardsContainer.findElement(By.tagName("ul"))).thenReturn(jobList);
        when(jobList.findElements(By.tagName("li"))).thenReturn(jobItems);

        when(jobItem.findElements(By.cssSelector("h2.jobTitle"))).thenReturn(List.of(jobTitleElement));
        when(jobTitleElement.findElement(By.tagName("a"))).thenReturn(aTag);
        when(aTag.findElement(By.tagName("span"))).thenReturn(spanTag);
        when(spanTag.getAttribute("title")).thenReturn("Software Engineer");
        when(aTag.getAttribute("href")).thenReturn("http://example.com/job1");

        when(jobItem.findElements(By.cssSelector("div.company_location"))).thenReturn(List.of(companyLocationElement));
        when(companyLocationElement.findElement(By.cssSelector("span"))).thenReturn(companyNameSpan);
        when(companyNameSpan.getText()).thenReturn("Example Company");

        when(driver.findElement(By.cssSelector("a[data-testid='pagination-page-next']"))).thenReturn(nextPageButton);

        // Mock interactions
        doNothing().when(searchField).sendKeys(jobTitle);
        doNothing().when(searchButton).click();
        doNothing().when(nextPageButton).click();

        // Perform the job search
        List<Job> jobListings = jobSearch.performJobSearch(driver, jobTitle, numberOfPages);

//        // Verify the results
//        assertEquals(1, jobListings.size());
//        JobListing jobListing = jobListings.get(0);
//        assertEquals("Software Engineer", jobListing.title);
//        assertEquals("Example Company", jobListing.company);
//        assertEquals("http://example.com/job1", jobListing.url);
//
//        // Verify interactions with mocks
//        verify(searchField).sendKeys(jobTitle);
//        verify(searchButton).click();
//        verify(nextPageButton).click();
//    }
}}