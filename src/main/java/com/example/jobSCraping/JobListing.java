package com.example.jobSCraping;

public class JobListing {
    String title;
    String company;
    String url;
//    String company;

    JobListing(String title, String company ,String url) {
        this.title = title;
        this.company = company;
        this.url = url;

    }

    @Override
    public String toString() {
        return "JobListing {" +
                " title=' " + title + '\'' +
                " company=' " + company + '\'' +
                " url='" + url + '\'' ;
    }
}