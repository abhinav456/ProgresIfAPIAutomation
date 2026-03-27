package api.testcases.dingDev.Admin;


import api.Base.BaseTest;
import api.payload.CompanyAmend;
import api.utilities.ConfigManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import java.util.Map;

//Below Test Cases are Related to Admin We don't have Admin access now
public class CompanyAmendTest extends BaseTest {

	@Test(priority = 1)
	public void getAllCompanyAmends() {

	    Response response =
	    		 given()
                 .header("Authorization", 
                         "Bearer " + ConfigManager.getProperty("access_token"))
	                    .queryParam("id", 1)
	            .when()
	                    .get("/ding/company-amends")
	            .then()
	                    .extract().response();

	    Assert.assertEquals(response.getStatusCode(), 200);
	    Assert.assertTrue(response.getTime() < 5000);
	}

	@Test(priority = 2)
	public void createCompanyAmend() {

	    Map<String, Object> requestBody =
	            CompanyAmend.createCompanyAmendPayload(
	                    123,
	                    true,
	                    false,
	                    "2024-01-01T00:00:00Z",
	                    "2024-12-31T23:59:59Z",
	                    true
	            );

	    Response response =
	    		 given()
                 .header("Authorization", 
                         "Bearer " + ConfigManager.getProperty("access_token"))
	                    .contentType("application/json")
	                    .body(requestBody)
	            .when()
	                    .post("/ding/company-amends")
	            .then()
	                    .extract().response();

	    Assert.assertEquals(response.getStatusCode(), 200);
	    Assert.assertTrue(response.getTime() < 5000);

	    test.info("Response Body: " + response.asPrettyString());
	}
}