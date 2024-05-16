package com.ivan.cookbook.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivan.cookbook.dtos.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MealController {

    @Value("${api.key}")
    private String apiKey;
    private final String BASE_API_URL = "https://www.themealdb.com/api/json/v1/";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/search")
    public String showSearchForm() {
        return "searchForm";
    }

    @GetMapping("/searchMeal")
    public String searchMeal(@RequestParam("mealName") String mealName, Model model) {
        String apiUrl = BASE_API_URL + apiKey + "/search.php?s=" + mealName;
        String searchResultsJson = restTemplate.getForObject(apiUrl, String.class);
        model.addAttribute("searchResults", searchResultsJson);
        return "searchResults";
    }

    @GetMapping("/categories")
    public String getCategories(Model model) {
        ResponseEntity<String> response = restTemplate.getForEntity(BASE_API_URL + apiKey + "/categories.php", String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode categoriesNode = root.get("categories");
            List<CategoryDTO> categories = new ArrayList<>();

            if (categoriesNode != null && categoriesNode.isArray()) {
                for (JsonNode categoryNode : categoriesNode) {
                    CategoryDTO categoryDTO = new CategoryDTO();
                    categoryDTO.setIdCategory(categoryNode.get("idCategory").asText());
                    categoryDTO.setStrCategory(categoryNode.get("strCategory").asText());
                    categoryDTO.setStrCategoryDescription(categoryNode.get("strCategoryDescription").asText());
                    categories.add(categoryDTO);
                }
            }

            model.addAttribute("categories", categories);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // Handle or log the exception
        }

        return "categories";
    }
}
