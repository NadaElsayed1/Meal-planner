package com.example.mealsplanner.model;

import java.util.ArrayList;

public class CategoryResponse {
    public ArrayList<CategoryDTO> categories;

    public CategoryResponse(ArrayList<CategoryDTO> categories) {
        this.categories = categories;
    }

    public ArrayList<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<CategoryDTO> categories) {
        this.categories = categories;
    }
}
