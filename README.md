# Cookbook Application

This is a Spring Boot web application that allows users to search recipes.

## Used tools

- Java
- Spring Boot (Spring Security, Thymeleaf)
- MySQL
- JWT Authentication

## Dependencies

- Spring Boot Starter Data JPA
- Spring Boot Starter Security
- Spring Boot Starter Web
- MySQL Connector/J
- Thymeleaf
- JSON Web Token (JWT)
- RestTemplate
  
## Usage

1. Register a new account using the provided registration form.
2. Log in with your registered credentials.
3. Navigate through the recipe categories, search for recipes, and manage your favorites.

## Features

- User registration and authentication using JWT
- Secure access to recipe categories and search functionality
- Integration with an external recipe API for fetching recipe details
- Integration with MySQL database

### API Integration

This application integrates with The Meal DB API to fetch meal details and categories dynamically. The integration is implemented using RestTemplate to make HTTP requests to the API endpoints provided by The Meal DB.

### Endpoints

- **Meal Search Endpoint:** `/searchMeal`
  Fetches meal details based on the search query provided by the user.

- **Category Endpoint:** `/categories`
  Retrieves the list of meal categories available in The Meal DB.
