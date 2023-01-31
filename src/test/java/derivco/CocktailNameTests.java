package derivco;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.testng.annotations.Test;
/*
 * Cocktail name test cases.
 */
public class CocktailNameTests extends Base {

    String drinkName;

    @Test(priority = 1, description = "Veryfing response when drink name is invalid")
    public void isIngrediantResponceNull() {
        drinkName = "invalidName";
        searchAndReturnResponse("s", drinkName).then().statusCode(200)
            .body(DrinkKeys.drinks.toString(), IsNull.nullValue());
    }

    @Test(priority = 2, description = "Veryfing number of drinks for Margarita")
    public void isDrinkNameHasmultipleRecord() {
        drinkName = "Margarita";
        searchAndReturnResponse("s", drinkName).then().assertThat()
            .body(DrinkKeys.drinks.toString() + ".size()", IsEqual.equalTo(6));
    }

    @Test(priority = 3, description = "Veryfing drink name has Margarita")
    public void isDrinkNameContainsProvidedValue() {
        drinkName = "Margarita";
        searchAndReturnResponse("s", drinkName);
        // size of drinks is 6 based on the last test case.
        for(int i = 0; i < 6; i++) {
            assertTrue(getKeyValue
                (i, DrinkKeys.strDrink.toString()).contains(drinkName));
        }
    }

    @Test(priority = 4, description = "Veryfing drink mandatory fields are not null")
    public void areMandatoryFieldsNotNull() {
        drinkName = "Margarita";
        searchAndReturnResponse("s", drinkName);

        for(int i = 0; i < 6; i++) {
            assertNotNull(getKeyValue(i, DrinkKeys.strCategory.toString()));
            assertNotNull(getKeyValue(i, DrinkKeys.strAlcoholic.toString()));
            assertNotNull(getKeyValue(i, DrinkKeys.strGlass.toString()));
            assertNotNull(getKeyValue(i, DrinkKeys.strInstructions.toString()));
            assertNotNull(getKeyValue(i, DrinkKeys.strIngredient1.toString()));
            assertNotNull(getKeyValue(i, DrinkKeys.strMeasure1.toString()));
            assertNotNull(getKeyValue
                (i,DrinkKeys.strCreativeCommonsConfirmed.toString()));
        }
    }

    @Test(priority = 5, description = "Veryfing drinks start with provided letter")
    public void areDrinksStartingWithLetterGetReturned() {
        String letter = "A";
        searchAndReturnResponse("f", letter);
        int jsonPathSize
            = response.jsonPath().getList(DrinkKeys.drinks.toString()).size();

        for (int i = 0; i < jsonPathSize; i++) {
            assertTrue
                (getKeyValue(i,
                             DrinkKeys.strDrink.toString()).startsWith(letter));
        }
    }

     @Test(priority = 6, description = "Veryfing drink with exact id")
     public void isDrinkWithExactIdGetReturned() {
         String id = "11007";
         lookUpAndReturnResponce("i", id);

         assertEquals(getKeyValue(0, DrinkKeys.idDrink.toString()), id);
    }

    @Override
    protected String getKeyValue(int index, String key) {
        return response.jsonPath().getString
            (DrinkKeys.drinks.toString() + "["+index+"]." + key);
    }

    // Required fields in drink response
    private enum DrinkKeys {
        drinks, idDrink, strDrink, strTags, strCategory, strAlcoholic, strGlass,
        strInstructions, strIngredient1, strMeasure1, strCreativeCommonsConfirmed,
        dateModified
    }
}