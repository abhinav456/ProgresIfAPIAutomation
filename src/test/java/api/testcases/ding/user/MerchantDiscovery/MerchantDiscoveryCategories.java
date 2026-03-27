package api.testcases.ding.user.MerchantDiscovery;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.Base.BaseTest;
import api.utilities.ConfigManager;
import io.restassured.response.Response;

public class MerchantDiscoveryCategories extends BaseTest{
	String CategoryId;
	@Test(priority=1)
	  public void GetAllCategories() {
		  
		  Response response =
				  given()
	              .header("Authorization", 
	                      "Bearer " + ConfigManager.getProperty("access_token"))
		            .when()
		                    .get("/ding/categories")
		            .then()
		                    .extract().response();

		    Assert.assertEquals(response.getStatusCode(), 200);
		    Assert.assertTrue(response.getTime() < 5000);
		    test.info("Response Body: " + response.asPrettyString());
		    CategoryId = response.jsonPath().getString("data[0].id");
		    System.out.println("Category ID Is : "+CategoryId);
		}
	
	@Test(priority=2)
	  public void GetSingleID() {
		  
		  Response response =
				  given()
	              .header("Authorization", 
	                      "Bearer " + ConfigManager.getProperty("access_token"))
	                      .queryParam("id", CategoryId)
		            .when()
		                    .get("/ding/categories")
		            .then()
		                    .extract().response();

		    Assert.assertEquals(response.getStatusCode(), 200);
		    Assert.assertTrue(response.getTime() < 5000);
		    test.info("Response Body: " + response.asPrettyString());
		    
		}
	
	@Test(priority=3)
	  public void GetAllCategoriesBaseLImitOffset() {
		  
		  Response response =
				  given()
	              .header("Authorization", 
	                      "Bearer " + ConfigManager.getProperty("access_token"))
	                      .queryParam("offset", 0)
	                      .queryParam("limit", 40)
		            .when()
		                    .get("/ding/categories")
		            .then()
		                    .extract().response();

		    Assert.assertEquals(response.getStatusCode(), 200);
		    Assert.assertTrue(response.getTime() < 5000);
		    test.info("Response Body: " + response.asPrettyString());
		    
		}
	
	@Test(priority=4)
	  public void GetCategorieswithSortByOrderLimitOffset() {
		  
		  Response response =
				  given()
	              .header("Authorization", 
	                      "Bearer " + ConfigManager.getProperty("access_token"))
	                      .queryParam("offset", 0)
	                      .queryParam("limit", 10)
	                      .queryParam("sortby", "name")
	                      .queryParam("sortorder", "desc")
		            .when()
		                    .get("/ding/categories")
		            .then()
		                    .extract().response();

		    Assert.assertEquals(response.getStatusCode(), 200);
		    Assert.assertTrue(response.getTime() < 5000);
		    test.info("Response Body: " + response.asPrettyString());
		    
		}
}
