package com.ivan.cookbook.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class MealController {

    @Value("${api.key}")
    private String apiKey;
    @GetMapping("/search")
    public String showSearchForm() {
        return "searchForm";
    }
    @GetMapping("/searchMeal")
    public String searchMeal(@RequestParam("mealName") String mealName, Model model) {
        String apiUrl = "https://www.themealdb.com/api/json/v1/" + apiKey +"/search.php?s=" + mealName;
        RestTemplate restTemplate = new RestTemplate();
        String searchResultsJson = restTemplate.getForObject(apiUrl, String.class);
        model.addAttribute("searchResults", searchResultsJson);
        return "searchResults";
    }
}
