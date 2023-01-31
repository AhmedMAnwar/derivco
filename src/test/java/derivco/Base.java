package derivco;

import java.io.IOException;

import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import propertyfile.PropertyFileLoader;
/*
 * Class presenting the base of test cases.
 */
public abstract class Base {

    protected PropertyFileLoader propertyFileLoader;
    protected Response response;

    @BeforeTest
    public void setupConfiguartion() throws IOException {
        this.propertyFileLoader = new PropertyFileLoader();
        RestAssured.baseURI = propertyFileLoader.getBaseUrl();
    }

    abstract protected String getKeyValue(int index, String key);

    // Protected methods.
    protected Response searchAndReturnResponse(String queryParam, String value) {
        response = RestAssured.given().queryParam(queryParam, value).when()
            .get(propertyFileLoader.getSearchUrl());
        return response;
    }

    protected Response lookUpAndReturnResponce(String queryParam, String value) {
        response = RestAssured.given().queryParam(queryParam, value).when()
            .get(propertyFileLoader.getLookUpUrl());
        return response;
    }
}
