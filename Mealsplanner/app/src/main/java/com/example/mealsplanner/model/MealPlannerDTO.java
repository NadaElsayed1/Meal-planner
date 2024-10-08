package com.example.mealsplanner.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "meal_planner")
public class MealPlannerDTO implements Serializable {

    @PrimaryKey
    @NonNull
    private String idMeal;
    private String date;

    public MealPlannerDTO() {
    }

    private String mealType;
    private String idCategory;
    private String strCategory;
    private String strCategoryThumb;
    private String strMeal;
    private String strDrinkAlternate;
    private String strArea;
    private String strInstructions;
    private String strMealThumb;
    private String strTags;
    private String strYoutube;
    private String strIngredient;
    private String strIngredient1;
    private String strIngredient2;
    private String strIngredient3;
    private String strIngredient4;
    private String strIngredient5;
    private String strIngredient6;
    private String strIngredient7;
    private String strIngredient8;
    private String strIngredient9;
    private String strIngredient10;
    private String strIngredient11;
    private String strIngredient12;
    private String strIngredient13;
    private String strIngredient14;
    private String strIngredient15;
    private String strIngredient16;
    private String strIngredient17;
    private String strIngredient18;
    private String strIngredient19;
    private String strIngredient20;
    private String strMeasure1;
    private String strMeasure2;
    private String strMeasure3;
    private String strMeasure4;
    private String strMeasure5;
    private String strMeasure6;
    private String strMeasure7;
    private String strMeasure8;
    private String strMeasure9;
    private String strMeasure10;
    private String strMeasure11;
    private String strMeasure12;
    private String strMeasure13;
    private String strMeasure14;
    private String strMeasure15;
    private String strMeasure16;
    private String strMeasure17;
    private String strMeasure18;
    private String strMeasure19;
    private String strMeasure20;
    private String strSource;
    private String strImageSource;
    private String strCreativeCommonsConfirmed;
    private String dateModified;
    private List<String> ingredients;

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setMeasures(List<String> measures) {
        this.measures = measures;
    }

    private List<String> measures;

    public MealPlannerDTO(MealDTO mealDTO, String date, String mealType) {
        this.idMeal = mealDTO.getIdMeal();
        this.strMeal = mealDTO.getStrMeal();
        this.strCategory = mealDTO.getStrCategory();
        this.strCategoryThumb = mealDTO.getStrCategoryThumb();
        this.strArea = mealDTO.getStrArea();
        this.strInstructions = mealDTO.getStrInstructions();
        this.strMealThumb = mealDTO.getStrMealThumb();
        this.strTags = mealDTO.getStrTags();
        this.strYoutube = mealDTO.getStrYoutube();
        this.date = date;
        this.mealType = mealType;
        this.strIngredient1 = mealDTO.getStrIngredient1();
        this.strIngredient2 = mealDTO.getStrIngredient2();
        this.strIngredient3 = mealDTO.getStrIngredient3();
        this.strIngredient4 = mealDTO.getStrIngredient4();
        this.strIngredient5 = mealDTO.getStrIngredient5();
        this.strIngredient6 = mealDTO.getStrIngredient6();
        this.strIngredient7 = mealDTO.getStrIngredient7();
        this.strIngredient8 = mealDTO.getStrIngredient8();
        this.strIngredient9 = mealDTO.getStrIngredient9();
        this.strIngredient10 = mealDTO.getStrIngredient10();
        this.strIngredient11 = mealDTO.getStrIngredient11();
        this.strIngredient12 = mealDTO.getStrIngredient12();
        this.strIngredient13 = mealDTO.getStrIngredient13();
        this.strIngredient14 = mealDTO.getStrIngredient14();
        this.strIngredient15 = mealDTO.getStrIngredient15();
        this.strIngredient16 = mealDTO.getStrIngredient16();
        this.strIngredient17 = mealDTO.getStrIngredient17();
        this.strIngredient18 = mealDTO.getStrIngredient18();
        this.strIngredient19 = mealDTO.getStrIngredient19();
        this.strIngredient20 = mealDTO.getStrIngredient20();
        this.strMeasure1 = mealDTO.getStrMeasure1();
        this.strMeasure2 = mealDTO.getStrMeasure2();
        this.strMeasure3 = mealDTO.getStrMeasure3();
        this.strMeasure4 = mealDTO.getStrMeasure4();
        this.strMeasure5 = mealDTO.getStrMeasure5();
        this.strMeasure6 = mealDTO.getStrMeasure6();
        this.strMeasure7 = mealDTO.getStrMeasure7();
        this.strMeasure8 = mealDTO.getStrMeasure8();
        this.strMeasure9 = mealDTO.getStrMeasure9();
        this.strMeasure10 = mealDTO.getStrMeasure10();
        this.strMeasure11 = mealDTO.getStrMeasure11();
        this.strMeasure12 = mealDTO.getStrMeasure12();
        this.strMeasure13 = mealDTO.getStrMeasure13();
        this.strMeasure14 = mealDTO.getStrMeasure14();
        this.strMeasure15 = mealDTO.getStrMeasure15();
        this.strMeasure16 = mealDTO.getStrMeasure16();
        this.strMeasure17 = mealDTO.getStrMeasure17();
        this.strMeasure18 = mealDTO.getStrMeasure18();
        this.strMeasure19 = mealDTO.getStrMeasure19();
        this.strMeasure20 = mealDTO.getStrMeasure20();
        this.ingredients = List.of(
                mealDTO.getStrIngredient1(),
                mealDTO.getStrIngredient2(),
                mealDTO.getStrIngredient3(),
                mealDTO.getStrIngredient4(),
                mealDTO.getStrIngredient5(),
                mealDTO.getStrIngredient6(),
                mealDTO.getStrIngredient7(),
                mealDTO.getStrIngredient8(),
                mealDTO.getStrIngredient9(),
                mealDTO.getStrIngredient10(),
                mealDTO.getStrIngredient11(),
                mealDTO.getStrIngredient12(),
                mealDTO.getStrIngredient13(),
                mealDTO.getStrIngredient14(),
                mealDTO.getStrIngredient15(),
                mealDTO.getStrIngredient16(),
                mealDTO.getStrIngredient17(),
                mealDTO.getStrIngredient18(),
                mealDTO.getStrIngredient19(),
                mealDTO.getStrIngredient20()
        );

        this.measures = List.of(
                mealDTO.getStrMeasure1(),
                mealDTO.getStrMeasure2(),
                mealDTO.getStrMeasure3(),
                mealDTO.getStrMeasure4(),
                mealDTO.getStrMeasure5(),
                mealDTO.getStrMeasure6(),
                mealDTO.getStrMeasure7(),
                mealDTO.getStrMeasure8(),
                mealDTO.getStrMeasure9(),
                mealDTO.getStrMeasure10(),
                mealDTO.getStrMeasure11(),
                mealDTO.getStrMeasure12(),
                mealDTO.getStrMeasure13(),
                mealDTO.getStrMeasure14(),
                mealDTO.getStrMeasure15(),
                mealDTO.getStrMeasure16(),
                mealDTO.getStrMeasure17(),
                mealDTO.getStrMeasure18(),
                mealDTO.getStrMeasure19(),
                mealDTO.getStrMeasure20()
        );


    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public List<String> getMeasures() {
        return measures;
    }
    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public void setStrDrinkAlternate(String strDrinkAlternate) {
        this.strDrinkAlternate = strDrinkAlternate;
    }

    public void setStrCategoryThumb(String strCategoryThumb) {
        this.strCategoryThumb = strCategoryThumb;
    }
    public String getIdCategory() {
        return idCategory;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getStrCategoryThumb() {
        return strCategoryThumb;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public void setStrTags(String strTags) {
        this.strTags = strTags;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }

    public String getStrIngredient() {
        return strIngredient;
    }

    public void setStrIngredient(String strIngredient) {
        this.strIngredient = strIngredient;
    }


    public void setStrIngredient1(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    public void setStrIngredient2(String strIngredient2) {
        this.strIngredient2 = strIngredient2;
    }

    public void setStrIngredient3(String strIngredient3) {
        this.strIngredient3 = strIngredient3;
    }

    public void setStrIngredient4(String strIngredient4) {
        this.strIngredient4 = strIngredient4;
    }

    public void setStrIngredient5(String strIngredient5) {
        this.strIngredient5 = strIngredient5;
    }

    public void setStrIngredient6(String strIngredient6) {
        this.strIngredient6 = strIngredient6;
    }

    public void setStrIngredient7(String strIngredient7) {
        this.strIngredient7 = strIngredient7;
    }

    public void setStrIngredient8(String strIngredient8) {
        this.strIngredient8 = strIngredient8;
    }

    public void setStrIngredient9(String strIngredient9) {
        this.strIngredient9 = strIngredient9;
    }

    public void setStrIngredient10(String strIngredient10) {
        this.strIngredient10 = strIngredient10;
    }

    public void setStrIngredient11(String strIngredient11) {
        this.strIngredient11 = strIngredient11;
    }

    public void setStrIngredient12(String strIngredient12) {
        this.strIngredient12 = strIngredient12;
    }

    public void setStrIngredient13(String strIngredient13) {
        this.strIngredient13 = strIngredient13;
    }

    public void setStrIngredient14(String strIngredient14) {
        this.strIngredient14 = strIngredient14;
    }

    public void setStrIngredient15(String strIngredient15) {
        this.strIngredient15 = strIngredient15;
    }

    public void setStrIngredient16(String strIngredient16) {
        this.strIngredient16 = strIngredient16;
    }

    public void setStrIngredient17(String strIngredient17) {
        this.strIngredient17 = strIngredient17;
    }

    public void setStrIngredient18(String strIngredient18) {
        this.strIngredient18 = strIngredient18;
    }

    public void setStrIngredient19(String strIngredient19) {
        this.strIngredient19 = strIngredient19;
    }

    public void setStrIngredient20(String strIngredient20) {
        this.strIngredient20 = strIngredient20;
    }

    public void setStrMeasure1(String strMeasure1) {
        this.strMeasure1 = strMeasure1;
    }

    public void setStrMeasure2(String strMeasure2) {
        this.strMeasure2 = strMeasure2;
    }

    public void setStrMeasure3(String strMeasure3) {
        this.strMeasure3 = strMeasure3;
    }

    public void setStrMeasure4(String strMeasure4) {
        this.strMeasure4 = strMeasure4;
    }

    public void setStrMeasure5(String strMeasure5) {
        this.strMeasure5 = strMeasure5;
    }

    public void setStrMeasure6(String strMeasure6) {
        this.strMeasure6 = strMeasure6;
    }

    public void setStrMeasure7(String strMeasure7) {
        this.strMeasure7 = strMeasure7;
    }

    public void setStrMeasure8(String strMeasure8) {
        this.strMeasure8 = strMeasure8;
    }

    public void setStrMeasure9(String strMeasure9) {
        this.strMeasure9 = strMeasure9;
    }

    public void setStrMeasure10(String strMeasure10) {
        this.strMeasure10 = strMeasure10;
    }

    public void setStrMeasure11(String strMeasure11) {
        this.strMeasure11 = strMeasure11;
    }

    public void setStrMeasure12(String strMeasure12) {
        this.strMeasure12 = strMeasure12;
    }

    public void setStrMeasure13(String strMeasure13) {
        this.strMeasure13 = strMeasure13;
    }

    public void setStrMeasure14(String strMeasure14) {
        this.strMeasure14 = strMeasure14;
    }

    public void setStrMeasure15(String strMeasure15) {
        this.strMeasure15 = strMeasure15;
    }

    public void setStrMeasure16(String strMeasure16) {
        this.strMeasure16 = strMeasure16;
    }

    public void setStrMeasure17(String strMeasure17) {
        this.strMeasure17 = strMeasure17;
    }

    public void setStrMeasure18(String strMeasure18) {
        this.strMeasure18 = strMeasure18;
    }

    public void setStrMeasure19(String strMeasure19) {
        this.strMeasure19 = strMeasure19;
    }

    public void setStrMeasure20(String strMeasure20) {
        this.strMeasure20 = strMeasure20;
    }

    public void setStrSource(String strSource) {
        this.strSource = strSource;
    }

    public void setStrImageSource(String strImageSource) {
        this.strImageSource = strImageSource;
    }

    public void setStrCreativeCommonsConfirmed(String strCreativeCommonsConfirmed) {
        this.strCreativeCommonsConfirmed = strCreativeCommonsConfirmed;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public String getStrDrinkAlternate() {
        return strDrinkAlternate;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public String getStrArea() {
        return strArea;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public String getStrTags() {
        return strTags;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public String getStrIngredient1() {
        return strIngredient1;
    }

    public String getStrIngredient2() {
        return strIngredient2;
    }

    public String getStrIngredient3() {
        return strIngredient3;
    }

    public String getStrIngredient4() {
        return strIngredient4;
    }

    public String getStrIngredient5() {
        return strIngredient5;
    }

    public String getStrIngredient6() {
        return strIngredient6;
    }

    public String getStrIngredient7() {
        return strIngredient7;
    }

    public String getStrIngredient8() {
        return strIngredient8;
    }

    public String getStrIngredient9() {
        return strIngredient9;
    }

    public String getStrIngredient10() {
        return strIngredient10;
    }

    public String getStrIngredient11() {
        return strIngredient11;
    }

    public String getStrIngredient12() {
        return strIngredient12;
    }

    public String getStrIngredient13() {
        return strIngredient13;
    }

    public String getStrIngredient14() {
        return strIngredient14;
    }

    public String getStrIngredient15() {
        return strIngredient15;
    }

    public String getStrIngredient16() {
        return strIngredient16;
    }

    public String getStrIngredient17() {
        return strIngredient17;
    }

    public String getStrIngredient18() {
        return strIngredient18;
    }

    public String getStrIngredient19() {
        return strIngredient19;
    }

    public String getStrIngredient20() {
        return strIngredient20;
    }

    public String getStrMeasure1() {
        return strMeasure1;
    }

    public String getStrMeasure2() {
        return strMeasure2;
    }

    public String getStrMeasure3() {
        return strMeasure3;
    }

    public String getStrMeasure4() {
        return strMeasure4;
    }

    public String getStrMeasure5() {
        return strMeasure5;
    }

    public String getStrMeasure6() {
        return strMeasure6;
    }

    public String getStrMeasure7() {
        return strMeasure7;
    }

    public String getStrMeasure8() {
        return strMeasure8;
    }

    public String getStrMeasure9() {
        return strMeasure9;
    }

    public String getStrMeasure10() {
        return strMeasure10;
    }

    public String getStrMeasure11() {
        return strMeasure11;
    }

    public String getStrMeasure12() {
        return strMeasure12;
    }

    public String getStrMeasure13() {
        return strMeasure13;
    }

    public String getStrMeasure14() {
        return strMeasure14;
    }

    public String getStrMeasure15() {
        return strMeasure15;
    }

    public String getStrMeasure16() {
        return strMeasure16;
    }

    public String getStrMeasure17() {
        return strMeasure17;
    }

    public String getStrMeasure18() {
        return strMeasure18;
    }

    public String getStrMeasure19() {
        return strMeasure19;
    }

    public String getStrMeasure20() {
        return strMeasure20;
    }

    public String getStrSource() {
        return strSource;
    }

    public String getStrImageSource() {
        return strImageSource;
    }

    public String getStrCreativeCommonsConfirmed() {
        return strCreativeCommonsConfirmed;
    }

    public String getDateModified() {
        return dateModified;
    }

}
