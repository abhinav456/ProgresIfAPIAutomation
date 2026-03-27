package api.testcases.ding.user.MerchantDiscovery;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.Base.BaseTest;
import api.utilities.ConfigManager;
import io.restassured.response.Response;

public class MerchantDiscovery extends BaseTest {
	@Test(priority=1)
	  public void GetMerchantDetail() {
		  
		  Response response =
				  given()
	              .header("Authorization", 
	                      "Bearer " + ConfigManager.getProperty("access_token"))
	                      .queryParam("lat", 3.1390)
	                      .queryParam("lng",101.6869)
		            .when()
		                    .get("/ding/merchant/2")
		            .then()
		                    .extract().response();

		    Assert.assertEquals(response.getStatusCode(), 200);
		    Assert.assertTrue(response.getTime() < 5000);
		    test.info("Response Body: " + response.asPrettyString());
		    String actualMessage = response.jsonPath().getString("message");
		    Assert.assertEquals(actualMessage, "Merchant details retrieved successfully");
		}
	
	@Test(priority=2)
	  public void MerchantDashboard() {
		  
		  Response response =
				  given()
	              .header("Authorization", 
	                      "Bearer " + ConfigManager.getProperty("access_token"))
	                      .queryParam("userLat", 3.1390)
	                      .queryParam("userLng",101.6869)
		            .when()
		                    .get("/ding/merchant-dashboard")
		            .then()
		                    .extract().response();

		    Assert.assertEquals(response.getStatusCode(), 200);
		    Assert.assertTrue(response.getTime() < 5000);
		    test.info("Response Body: " + response.asPrettyString());
		    String actualMessage = response.jsonPath().getString("message");
		    Assert.assertEquals(actualMessage, "Dashboard data retrieved successfully");
		}
	
	@Test(priority=3)
	  public void DashboardMerchant() {
		  
		  Response response =
				  given()
	              .header("Authorization", 
	                      "Bearer " + ConfigManager.getProperty("access_token"))
	                     
		            .when()
		                    .get("/ding/dashboard")
		            .then()
		                    .extract().response();

		    Assert.assertEquals(response.getStatusCode(), 200);
		    Assert.assertTrue(response.getTime() < 5000);
		    test.info("Response Body: " + response.asPrettyString());
		}
	
	@Test(priority=4)
	  public void GetAllFavourites() {
		  
		  Response response =
				  given()
	              .header("Authorization", 
	                      "Bearer " + ConfigManager.getProperty("access_token"))
	              .queryParam("lat", 89.421998333333335)
                  .queryParam("lng",-122.08)
                  .queryParam("offset", 0)
                  .queryParam("limit",40)
		            .when()
		                    .get("/ding/dashboard")
		            .then()
		                    .extract().response();

		    Assert.assertEquals(response.getStatusCode(), 200);
		    Assert.assertTrue(response.getTime() < 5000);
		    test.info("Response Body: " + response.asPrettyString());
		   
		}
}
