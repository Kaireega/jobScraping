package com.example.jobSCraping;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.Thread.sleep;

public class JobSearch {

    public List<JobListing>  performJobSearch(WebDriver driver, String jobTitle, int numberOfPages) throws InterruptedException {
        // Navigate to Indeed homepage
        driver.get("https://www.indeed.com/?from=gnav-homepage");

        // Wait until the input field is present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("text-input-what")));

        // Enter the job title in the input field
        searchField.sendKeys(jobTitle);

        // Find and click the search button
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='yosegi-InlineWhatWhere-primaryButton']")));
        searchButton.click();

        // Optionally wait for results to load
        sleep(9000); // Adjust sleep time as needed
        int currentPage = 0;

        String titleText = "";
        String jobHref = "";
        String companyName ="";
        List<JobListing> jobListings = new ArrayList<>();


        while (currentPage < numberOfPages) {
            // Find the job listings container
            WebElement jobCardsContainer = driver.findElement(By.id("mosaic-provider-jobcards"));

            // Find the <ul> tag inside the job cards container
            WebElement jobList = jobCardsContainer.findElement(By.tagName("ul"));


            // Iterate over <li> tags to extract job information
            List<WebElement> jobItems = jobList.findElements(By.tagName("li"));


            for (WebElement job : jobItems) {
                try {
                    // Check if the <li> element contains an <h2> tag with the class "jobTitle"

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

                    if (!job.findElements(By.cssSelector("div.company_location")).isEmpty()) {
                        WebElement companyLocationElement = job.findElement(By.cssSelector("div.company_location"));
                        WebElement companyNameSpan = companyLocationElement.findElement(By.cssSelector("span"));

                        // Get the company name text
                         companyName = companyNameSpan.getText();
                        // Print the company name
                        System.out.println("Company Name: " + companyName);
                    }


                } catch (NoSuchElementException e) {
                    // Skip to the next iteration if any element is not found
                    System.out.println("Required element not found in this <li> element, skipping to the next.");
                    continue;
                } catch (Exception e) {
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
            jobListings.add(new JobListing(titleText, companyName, jobHref));


        }
        return jobListings;
    }

}