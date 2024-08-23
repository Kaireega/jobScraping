package com.example.jobSCraping.service;
import com.example.jobSCraping.model.Job;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.lang.Thread.sleep;


public class JobSearch {

    public List<Job>  performJobSearch(WebDriver driver, String jobTitle, int numberOfPages) throws InterruptedException {

        // Navigate to Indeed homepage
        driver.get("https://www.indeed.com");

        // Wait until the input field is present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("text-input-what")));
        sleep(4000); // Adjust sleep time as needed

        // Enter the job title in the input field
        searchField.sendKeys(jobTitle);
        sleep(2000);

        // Find and click the search button
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='yosegi-InlineWhatWhere-primaryButton']")));
        searchButton.click();
        sleep(2000);

        // initiate variables for to retrieve data
        int currentPage = 1;
        String titleText = "";
        String jobHref = "";
        String companyName ="";
        List<Job> jobListings = new ArrayList<>();

        while (currentPage <= numberOfPages) {
            // Find the job listings container
            WebElement jobCardsContainer = driver.findElement(By.id("mosaic-provider-jobcards"));
            // Find the <ul> tag inside the job cards container
            WebElement jobList = jobCardsContainer.findElement(By.tagName("ul"));
            // Iterate over <li> tags to extract job information
            List<WebElement> jobItems = jobList.findElements(By.tagName("li"));
            for (WebElement job : jobItems) {
                try {
                    // Check if the <li> element contains an <h2> tag with the class "jobTitle"
                    // Extracts the titleText, jobHref and assigns them to variables
                    // Prints the titleText and jobHref to console
                    if (!job.findElements(By.cssSelector("h2.jobTitle")).isEmpty()) {
                        WebElement jobTitleElement = job.findElement(By.cssSelector("h2.jobTitle"));
                        WebElement aTag = jobTitleElement.findElement(By.tagName("a"));
                        WebElement spanTag = aTag.findElement(By.tagName("span"));
                         titleText = spanTag.getAttribute("title");
                         jobHref = aTag.getAttribute("href");
                        System.out.println("Job Title: " + titleText);
                        System.out.println("Job Link: " + jobHref);
                    } else {
                        // Skip this <li> element if it does not contain an <h2> with class "jobTitle"
                        continue;
                    }
                    // Check if the <li> element contains a div with the class "company_location"
                    // Extracts the company name and assigns them to variables
                    // Prints the company name to console
                    if (!job.findElements(By.cssSelector("div.company_location")).isEmpty()) {
                        WebElement companyLocationElement = job.findElement(By.cssSelector("div.company_location"));
                        WebElement companyNameSpan = companyLocationElement.findElement(By.cssSelector("span"));
                         companyName = companyNameSpan.getText();
                        System.out.println("Company Name: " + companyName);
                    }


                    
                    // Check if the <li> element contains an <h2> tag with the class "jobTitle"
                    // finds the <h2> that contains the job Title and clicks the element
//                    if (!job.findElements(By.cssSelector("h2.jobTitle")).isEmpty()) {
//                        WebElement jobTitleElement = job.findElement(By.cssSelector("h2.jobTitle"));
//                        WebElement aTag = jobTitleElement.findElement(By.tagName("a"));
//                        String jobUrl = aTag.getAttribute("href");
//
//                        // Store the current window handle
//                        String originalWindow = driver.getWindowHandle();
//
//                        // Switch to the new tab
//                        Set<String> handles=driver.getWindowHandles();
//                        for(String actual: handles) {
//                            if(!actual.equalsIgnoreCase(originalWindow)) {
////Switch to the opened tab
//                                driver.switchTo().window(actual);
////opening the URL saved.
//                                driver.get(jobUrl);
//                                sleep(5000);  // Optional: Wait for the new tab to load
//
//                            }
//                        }
//                        // Now that we're in the new tab, proceed to find the button
//                        WebElement jobDescription = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.css-14vv265.e37uo190")));
//                        sleep(5000);  // Optional: Ensure the button is ready to be clicked
//                        jobDescription.click();
//                        // Optionally, switch back to the original window/tab after you're done
//                        driver.close();  // Close the new tab
//                        driver.switchTo().window(originalWindow);
//                    }
                }
                catch (NoSuchElementException e) {
                    // Skip to the next iteration if any element is not found
                    System.out.println("Required element not found in this <li> element, skipping to the next.");
                    continue;
                }
                catch (Exception e) {
                    System.out.println("General error: " + e.getMessage());
                }

                } try {
                    WebElement nextPageButton = driver.findElement(By.cssSelector("a[data-testid='pagination-page-next']"));
                    nextPageButton.click();
                    // Wait for the next page to load
                    sleep(5000); // Adjust sleep time as needed
                    currentPage++;
                } catch (NoSuchElementException e) {
                    System.out.println("Next page button not found. Ending pagination.");
                    break;
                }
            jobListings.add(new Job(titleText, companyName, jobHref));
        }
        return jobListings;
    }

}