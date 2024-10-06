# Meals Planner App

**Meals Planner** is an Android application designed to help users plan their weekly meals efficiently. The app offers meal suggestions, search functionality, and the ability to save favorite meals for offline access. It integrates with the [TheMealDB API](https://themealdb.com/api.php) to fetch meal data, making meal planning easy and fun for the user.

## Features
- **Meal of the Day**: Provides a random meal suggestion to inspire the user.
- **Meal Search**: Users can search for meals using different criteria, including:
  - Country
  - Ingredient
  - Category
- **Categories and Countries**: Displays a list of meal categories and countries, allowing users to browse popular meals.
- **Favorites**: Users can add or remove meals from their favorite list. 
Favorite meals are stored locally using Room database for offline viewing.
- **Meal Planning**: Users can plan their meals for future days, adding meals to breakfast, lunch, or dinner slots.
- **Offline Access**: Users can browse their favorite meals and the meal plan for the week without an active internet connection.
- **Meal Details**: When selecting a meal, users can view:
  1. Meal name
  2. Image of the meal
  3. The origin country
  4. Ingredients (visualized where possible)
  5. Step-by-step instructions
  6. An embedded video showcasing the recipe (via YouTube integration)
  7. A button to add or remove the meal from favorites
- **Splash Screen**: An animated splash screen, powered by [Lottie](https://lottiefiles.com/), enhances the user experience on app launch.

## Project Journey

### 1. **App Structure and MVP Design**
   The Meals Planner App was built using the **Model-View-Presenter (MVP)** design pattern to ensure separation of concerns and enhance maintainability. The app includes several key components:
   - **Model**: Manages the data, including interactions with the API and local Room database.
   - **View**: Displays meal information, search results, categories, and the meal of the day.
   - **Presenter**: Acts as the intermediary between the model and the view, handling user interactions and retrieving data.

### 2. **API Integration**
   The app fetches meal data from the [TheMealDB API](https://themealdb.com/api.php). Key API endpoints used include:
   - Random meal endpoint for the "Meal of the Day" feature
   - Category, country, and ingredient search endpoints
   - Category, country, and ingredient list endpoints
   - Search endpoint depend on category, country, and ingredient search endpoints
   - Look up for a meal by id
   - Video embedding for detailed meal steps via YouTube player in a WebView

### 3. **Local Data Storage (Room)**
   Room database is used to persist user favorites locally, allowing offline access. Users can view and manage their favorite and planned meals even without internet connectivity. This includes:
   - Adding/removing meals from favorites and planner
   - Viewing favorite and planned meals during offline scenarios

### 4. **Search Feature**
   The app allows users to search for meals based on:
   - **Category**: Searches by predefined meal categories (e.g., dessert, seafood).
   - **Country**: Allows users to view meals from different countries.
   - **Ingredient**: Finds meals that include a specific ingredient.
   
   The search functionality is customizable, enabling the user to quickly filter and find relevant meal options.

### 5. **Meal Details**
   When a user selects a meal, they can view comprehensive meal details:
   - Meal name and image
   - Origin country
   - List of ingredients, optionally displayed with images where possible
   - Step-by-step cooking instructions
   - Embedded YouTube video for easy recipe viewing
   - The ability to add the meal for favorites or planner

### 6. **User Meal Plan**
   The app allows users to organize their meals for future days into breakfast, lunch, and dinner. Users can easily plan their weekly meals and view the planned meals offline if needed.

### 7. **Splash Screen and Animations**
   The app features a splash screen with an engaging Lottie animation that enhances the initial user experience. This was implemented using the [Lottie](https://lottiefiles.com/) animation framework, providing a visually appealing and smooth app entry point.

### 8. **Offline Mode**
   The Meals Planner App is designed to function without network connectivity for essential features like browsing favorite & planned meals and reviewing the user's weekly meal plan.

## Code Highlights

- **MealRepository**: Manages data fetching from the remote API (`MealRemoteDataStructure`) and local storage (`MealLocalDataSource`), offering a clean interface for accessing meals.

- **Room Database**: Used to persist user favorite & planned meals locally, enabling offline access.

## Installation and Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/NadaElsayed1/Meal-planner.git