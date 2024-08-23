plugins {
	java
	id("org.springframework.boot") version "3.3.2"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(22)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation ("org.seleniumhq.selenium:selenium-java:4.3.0")
	implementation ("org.jsoup:jsoup:1.15.3")
	// Selenium
	implementation ("org.seleniumhq.selenium:selenium-java:4.23.0")

	// JUnit
	testImplementation ("org.junit.jupiter:junit-jupiter-api:5.8.1")
	testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.8.1")

	// Mockito
	testImplementation ("org.mockito:mockito-core:5.12.0")

	// Assertions
	testImplementation ("org.assertj:assertj-core:3.26.3")


}

tasks.withType<Test> {
	useJUnitPlatform()
}
