# Job Scraping Application

## Description

The **Job Scraping Application** is a Spring Boot application that scrapes job listings from Indeed.com based on a specified job title and number of pages. It collects job titles, company names, and job URLs, and stores them in a PostgreSQL database. The application also provides a REST API to perform job searches and retrieve the data.

## Technologies Used

- **Java**
- **Spring Boot**
- **Selenium WebDriver**
- **PostgreSQL**
- **JUnit 5**
- **Mockito**
- **Lombok**

## Installation

### Prerequisites

- **Java 17** or higher
- **Gradle**
- **PostgreSQL** database
- **ChromeDriver** compatible with your version of Google Chrome

### Steps

1. **Clone the repository**

   ```
   git clone <repository-url>
   ```

2. **Navigate to the project directory**

   ```
   cd job-scraping-application
   ```

3. **Set up the PostgreSQL database**

   - Install PostgreSQL if you haven't already.
   - Create a new PostgreSQL database.
   - Update the `application.properties` file with your database credentials.

   ```
   # src/main/resources/application.properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/<your-database-name>
   spring.datasource.username=<your-username>
   spring.datasource.password=<your-password>
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   ```

4. **Configure ChromeDriver**

   - Download [ChromeDriver](https://chromedriver.chromium.org/downloads) that matches your installed version of Chrome.
   - Place the `chromedriver` executable in a known directory.
   - Update the system property in your application to point to the ChromeDriver path.

   ```java
   // Example in JobScrapingApplication.java
   System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
   ```

5. **Build the project**

   ```
   ./gradlew build
   ```

## Usage

1. **Run the application**

   ```
   ./gradlew bootRun
   ```

2. **Access the REST API**

   The application exposes an endpoint to initiate the job scraping process:

   - **Endpoint**

     ```
     GET /jobs/search
     ```

   - **Parameters**

     - `jobTitle` (String): The job title to search for.
     - `numberOfPages` (int): The number of pages to scrape.

   - **Example Request**

     ```
     http://localhost:8080/jobs/search?jobTitle=Software%20Developer&numberOfPages=1
     ```

3. **Response**

   The endpoint returns a JSON array of job listings:

   ```json
   [
     {
       "id": 1,
       "title": "Software Developer",
       "company": "Tech Company",
       "url": "https://www.indeed.com/viewjob?jk=1234567890"
     },
     ...
   ]
   ```

## Features

- **Job Scraping**: Scrapes job listings from Indeed.com based on the provided job title and number of pages.
- **Data Storage**: Stores scraped job data in a PostgreSQL database.
- **REST API**: Provides an endpoint to trigger the scraping process and retrieve job listings.
- **Unit Testing**: Includes unit tests using JUnit 5 and Mockito.

## Running Tests

Execute the following command to run unit tests:

```
./gradlew test
```

## Contributing

Contributions are welcome! Please follow these steps:

1. **Fork the repository**
2. **Create a new branch**

   ```
   git checkout -b feature/your-feature-name
   ```

3. **Commit your changes**

   ```
   git commit -m "Add your message"
   ```

4. **Push to the branch**

   ```
   git push origin feature/your-feature-name
   ```

5. **Open a Pull Request**


## Contact

For any questions or suggestions, feel free to open an issue or contact the repository owner.

---

Let me know if you need further modifications.
