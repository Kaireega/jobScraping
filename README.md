# jobScraping

Spring Boot REST API that scrapes Indeed job listings via Selenium, stores results in PostgreSQL, and returns JSON.

---

## Tech stack

- Java 22, Spring Boot 3.3.2
- Selenium 4.23, Jsoup
- PostgreSQL, Spring Data JPA
- Lombok, JUnit 5, Mockito

---

## Quick start

### Prerequisites

- Java 22
- PostgreSQL running locally
- Chrome browser (Selenium Manager handles ChromeDriver)

### 1. Database setup

```sql
CREATE DATABASE kairee;
```

### 2. Configure credentials

Set environment variables or use defaults in `application.properties`:

```bash
export DB_URL=jdbc:postgresql://localhost:5432/kairee
export DB_USERNAME=kairee
export DB_PASSWORD=your_password_here
```

### 3. Run

```bash
git clone https://github.com/Kaireega/jobScraping.git
cd jobScraping
./gradlew bootRun
```

### 4. Search jobs

```bash
curl "http://localhost:8080/jobs/search?jobTitle=Software%20Developer&numberOfPages=1"
```

### 5. Run tests

```bash
./gradlew test
```

---

## API

| Endpoint | Method | Parameters |
|----------|--------|------------|
| `/jobs/search` | GET | `jobTitle` (string), `numberOfPages` (int) |

**Response example:**

```json
[
  {
    "title": "Software Developer",
    "company": "Example Corp",
    "url": "https://www.indeed.com/viewjob?jk=..."
  }
]
```

---

## Project structure

```
jobScraping/
├── src/main/java/com/example/jobSCraping/
│   ├── JobScrapingApplication.java
│   ├── controller/JobController.java
│   ├── service/JobSearchService.java
│   ├── model/Job.java
│   └── repository/JobRepo.java
└── src/main/resources/application.properties
```

---

## Author

**Kai'ree Gay** — [GitHub](https://github.com/Kaireega)
