package derivco;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import org.testng.annotations.Test;
/*
 * Ingredients test cases.
 */
public final class IngredientTests extends Base {

    String ingredientName;

    @Test(priority = 1, description = "Veryfing if the ingrediant is non-alcohol"
                                    + " and non ABV")
    public void isIngrediantNonAlcholAndNonAbv() {
        ingredientName = "Salt";
        int index = 0;
        searchAndReturnResponse("i", ingredientName);

        assertEquals(response.getStatusCode(), 200);
        assertEquals(getKeyValue(index, IngredientKeys.idIngredient.toString()),
                     "439");
        assertEquals(getKeyValue(index, IngredientKeys.strIngredient.toString()),
                     ingredientName);
        assertNotNull(getKeyValue(index, IngredientKeys.strDescription.toString()));
        assertEquals(getKeyValue(index, IngredientKeys.strType.toString()),
                     "Mineral");
        assertEquals(getKeyValue(index, IngredientKeys.strAlcohol.toString()),
                     "No");
        assertNull(getKeyValue(index, IngredientKeys.strABV.toString()));
    }

    @Test(priority = 2, description = "Veryfing if the ingrediant is alcohol and"
                                    + " ABV")
    public void isIngrediantAlcholAndAbv() {
        ingredientName = "Vodka";
        int index = 0;
        searchAndReturnResponse("i", ingredientName);

        assertEquals(response.statusCode(), 200);
        assertEquals(getKeyValue(index, IngredientKeys.idIngredient.toString()),
                     "1");
        assertEquals(getKeyValue(index, IngredientKeys.strIngredient.toString()),
                     ingredientName);
        assertNotNull(getKeyValue(index, IngredientKeys.strDescription.toString()));
        assertEquals(getKeyValue(index, IngredientKeys.strType.toString()),
                     ingredientName);
        assertEquals(getKeyValue(index, IngredientKeys.strAlcohol.toString()),
                     "Yes");
        assertNotNull(getKeyValue(index, IngredientKeys.strABV.toString()));
    }

    @Test(priority = 3, description = "Veryfing if the ingrediant name is invalid")
    public void isIngrediantResponceNull() {
        ingredientName = "invalidName";
        searchAndReturnResponse("i", ingredientName);

        assertEquals(response.statusCode(), 200);
        assertNull(response.jsonPath().get(IngredientKeys.ingredients.toString()));
    }

    @Test(priority = 4, description = "Veryfing ingrediant with exact id")
    public void isIngrediantWithExactIdGetReturned() {
        String id = "552";
        lookUpAndReturnResponce("iid", id);

        assertEquals(getKeyValue(0, IngredientKeys.idIngredient.toString()), id);
   }

    @Override
    protected String getKeyValue(int index, String key) {
        return response.jsonPath().getString
            (IngredientKeys.ingredients.toString() + "["+index+"]." + key);
    }

    // Ingredient response keys.
    private enum IngredientKeys {
        idIngredient, strIngredient, strDescription, strType, strAlcohol, strABV,
        ingredients
    }
}
