package com.ivan.cookbook.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api")
public class CookBookRestController {

    @Value("${api.key}")
    private String apiKey;

    private final String BASE_API_URL = "https://www.themealdb.com/api/json/v1/";

    private String fetchDataFromApi(String endpoint) {
        RestTemplate restTemplate = new RestTemplate();
        String apiURL = BASE_API_URL + apiKey + "/" + endpoint;
        return restTemplate.getForObject(apiURL, String.class);
    }

    @GetMapping("/categories")
    public String getCategories() {
        return fetchDataFromApi("categories.php");
    }
}
