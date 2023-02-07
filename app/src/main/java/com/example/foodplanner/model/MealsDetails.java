package com.example.foodplanner.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "MealDetails")
public class MealsDetails implements Serializable {

    @NonNull



@PrimaryKey
    private String idMeal;
    private String strMeal;
    private String strDrinkAlternate;
    private String strCategoryl;

  public MealsDetails( String strMeal, String strArea, String strMealThumb, String strInstructions,
  String strYoutube ){

      this.strMeal = strMeal;
      this.strArea = strArea;
      this.strMealThumb = strMealThumb;
      this.strInstructions = strInstructions;
      this.strYoutube= strYoutube;


  }

    private String strArea;
    private String strInstructions;
    private String strMealThumb;
    private String strTags;
    private String strYoutube;
    private String strIngerdient1;
    private String strIngerdient2;
    private String strIngerdient3;
    private String strIngerdient4;
    private String strIngerdient5;
    private String strIngerdient6;
    private String strIngerdient7;
    private String strIngerdient8;
    private String strIngerdient9;
    private String strIngerdient10;
    private String strIngerdient11;
    private String strIngerdient12;
    private String strIngerdient13;
    private String strIngerdient14;
    private String strIngerdient15;
    private String strIngerdient16;
    private String strIngerdient17;
    private String strIngerdient18;
    private String strIngerdient19;
    private String strIngerdient20;
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
    private String strCreativeCommonsConfirmed;
    private String dateModified;


    @NonNull
    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(@NonNull String idMeal) {
        this.idMeal = idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrDrinkAlternate() {
        return strDrinkAlternate;
    }

    public void setStrDrinkAlternate(String strDrinkAlternate) {
        this.strDrinkAlternate = strDrinkAlternate;
    }

    public String getStrCategoryl() {
        return strCategoryl;
    }

    public void setStrCategoryl(String strCategoryl) {
        this.strCategoryl = strCategoryl;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getStrTags() {
        return strTags;
    }

    public void setStrTags(String strTags) {
        this.strTags = strTags;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }

    public String getStrIngerdient1() {
        return strIngerdient1;
    }

    public void setStrIngerdient1(String strIngerdient1) {
        this.strIngerdient1 = strIngerdient1;
    }

    public String getStrIngerdient2() {
        return strIngerdient2;
    }

    public void setStrIngerdient2(String strIngerdient2) {
        this.strIngerdient2 = strIngerdient2;
    }

    public String getStrIngerdient3() {
        return strIngerdient3;
    }

    public void setStrIngerdient3(String strIngerdient3) {
        this.strIngerdient3 = strIngerdient3;
    }

    public String getStrIngerdient4() {
        return strIngerdient4;
    }

    public void setStrIngerdient4(String strIngerdient4) {
        this.strIngerdient4 = strIngerdient4;
    }

    public String getStrIngerdient5() {
        return strIngerdient5;
    }

    public void setStrIngerdient5(String strIngerdient5) {
        this.strIngerdient5 = strIngerdient5;
    }

    public String getStrIngerdient6() {
        return strIngerdient6;
    }

    public void setStrIngerdient6(String strIngerdient6) {
        this.strIngerdient6 = strIngerdient6;
    }

    public String getStrIngerdient7() {
        return strIngerdient7;
    }

    public void setStrIngerdient7(String strIngerdient7) {
        this.strIngerdient7 = strIngerdient7;
    }

    public String getStrIngerdient8() {
        return strIngerdient8;
    }

    public void setStrIngerdient8(String strIngerdient8) {
        this.strIngerdient8 = strIngerdient8;
    }

    public String getStrIngerdient9() {
        return strIngerdient9;
    }

    public void setStrIngerdient9(String strIngerdient9) {
        this.strIngerdient9 = strIngerdient9;
    }

    public String getStrIngerdient10() {
        return strIngerdient10;
    }

    public void setStrIngerdient10(String strIngerdient10) {
        this.strIngerdient10 = strIngerdient10;
    }

    public String getStrIngerdient11() {
        return strIngerdient11;
    }

    public void setStrIngerdient11(String strIngerdient11) {
        this.strIngerdient11 = strIngerdient11;
    }

    public String getStrIngerdient12() {
        return strIngerdient12;
    }

    public void setStrIngerdient12(String strIngerdient12) {
        this.strIngerdient12 = strIngerdient12;
    }

    public String getStrIngerdient13() {
        return strIngerdient13;
    }

    public void setStrIngerdient13(String strIngerdient13) {
        this.strIngerdient13 = strIngerdient13;
    }

    public String getStrIngerdient14() {
        return strIngerdient14;
    }

    public void setStrIngerdient14(String strIngerdient14) {
        this.strIngerdient14 = strIngerdient14;
    }

    public String getStrIngerdient15() {
        return strIngerdient15;
    }

    public void setStrIngerdient15(String strIngerdient15) {
        this.strIngerdient15 = strIngerdient15;
    }

    public String getStrIngerdient16() {
        return strIngerdient16;
    }

    public void setStrIngerdient16(String strIngerdient16) {
        this.strIngerdient16 = strIngerdient16;
    }

    public String getStrIngerdient17() {
        return strIngerdient17;
    }

    public void setStrIngerdient17(String strIngerdient17) {
        this.strIngerdient17 = strIngerdient17;
    }

    public String getStrIngerdient18() {
        return strIngerdient18;
    }

    public void setStrIngerdient18(String strIngerdient18) {
        this.strIngerdient18 = strIngerdient18;
    }

    public String getStrIngerdient19() {
        return strIngerdient19;
    }

    public void setStrIngerdient19(String strIngerdient19) {
        this.strIngerdient19 = strIngerdient19;
    }

    public String getStrIngerdient20() {
        return strIngerdient20;
    }

    public void setStrIngerdient20(String strIngerdient20) {
        this.strIngerdient20 = strIngerdient20;
    }

    public String getStrMeasure1() {
        return strMeasure1;
    }

    public void setStrMeasure1(String strMeasure1) {
        this.strMeasure1 = strMeasure1;
    }

    public String getStrMeasure2() {
        return strMeasure2;
    }

    public void setStrMeasure2(String strMeasure2) {
        this.strMeasure2 = strMeasure2;
    }

    public String getStrMeasure3() {
        return strMeasure3;
    }

    public void setStrMeasure3(String strMeasure3) {
        this.strMeasure3 = strMeasure3;
    }

    public String getStrMeasure4() {
        return strMeasure4;
    }

    public void setStrMeasure4(String strMeasure4) {
        this.strMeasure4 = strMeasure4;
    }

    public String getStrMeasure5() {
        return strMeasure5;
    }

    public void setStrMeasure5(String strMeasure5) {
        this.strMeasure5 = strMeasure5;
    }

    public String getStrMeasure6() {
        return strMeasure6;
    }

    public void setStrMeasure6(String strMeasure6) {
        this.strMeasure6 = strMeasure6;
    }

    public String getStrMeasure7() {
        return strMeasure7;
    }

    public void setStrMeasure7(String strMeasure7) {
        this.strMeasure7 = strMeasure7;
    }

    public String getStrMeasure8() {
        return strMeasure8;
    }

    public void setStrMeasure8(String strMeasure8) {
        this.strMeasure8 = strMeasure8;
    }

    public String getStrMeasure9() {
        return strMeasure9;
    }

    public void setStrMeasure9(String strMeasure9) {
        this.strMeasure9 = strMeasure9;
    }

    public String getStrMeasure10() {
        return strMeasure10;
    }

    public void setStrMeasure10(String strMeasure10) {
        this.strMeasure10 = strMeasure10;
    }

    public String getStrMeasure11() {
        return strMeasure11;
    }

    public void setStrMeasure11(String strMeasure11) {
        this.strMeasure11 = strMeasure11;
    }

    public String getStrMeasure12() {
        return strMeasure12;
    }

    public void setStrMeasure12(String strMeasure12) {
        this.strMeasure12 = strMeasure12;
    }

    public String getStrMeasure13() {
        return strMeasure13;
    }

    public void setStrMeasure13(String strMeasure13) {
        this.strMeasure13 = strMeasure13;
    }

    public String getStrMeasure14() {
        return strMeasure14;
    }

    public void setStrMeasure14(String strMeasure14) {
        this.strMeasure14 = strMeasure14;
    }

    public String getStrMeasure15() {
        return strMeasure15;
    }

    public void setStrMeasure15(String strMeasure15) {
        this.strMeasure15 = strMeasure15;
    }

    public String getStrMeasure16() {
        return strMeasure16;
    }

    public void setStrMeasure16(String strMeasure16) {
        this.strMeasure16 = strMeasure16;
    }

    public String getStrMeasure17() {
        return strMeasure17;
    }

    public void setStrMeasure17(String strMeasure17) {
        this.strMeasure17 = strMeasure17;
    }

    public String getStrMeasure18() {
        return strMeasure18;
    }

    public void setStrMeasure18(String strMeasure18) {
        this.strMeasure18 = strMeasure18;
    }

    public String getStrMeasure19() {
        return strMeasure19;
    }

    public void setStrMeasure19(String strMeasure19) {
        this.strMeasure19 = strMeasure19;
    }

    public String getStrMeasure20() {
        return strMeasure20;
    }

    public void setStrMeasure20(String strMeasure20) {
        this.strMeasure20 = strMeasure20;
    }

    public String getStrSource() {
        return strSource;
    }

    public void setStrSource(String strSource) {
        this.strSource = strSource;
    }

    public String getStrCreativeCommonsConfirmed() {
        return strCreativeCommonsConfirmed;
    }

    public void setStrCreativeCommonsConfirmed(String strCreativeCommonsConfirmed) {
        this.strCreativeCommonsConfirmed = strCreativeCommonsConfirmed;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }



}
