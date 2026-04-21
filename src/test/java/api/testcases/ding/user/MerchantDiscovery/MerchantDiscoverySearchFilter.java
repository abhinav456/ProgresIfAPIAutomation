package api.testcases.ding.user.MerchantDiscovery;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.Base.BaseTest;
import api.payload.MerchantDiscovery;
import api.utilities.ConfigManager;
import io.restassured.response.Response;

public class MerchantDiscoverySearchFilter extends BaseTest {
	@Test(priority=1)
	  public void MerchantSearch() {
		  Map<String, Object> requestSearchMerchant =
				  MerchantDiscovery.SearchMerchant(
		            		"Coffee",
		            		 100   
		            );

		    Response response =
		    		 given()
	               .header("Authorization", 
	                       "Bearer " + ConfigManager.getUserToken())
		                    .contentType("application/json")
		                    .body(requestSearchMerchant)
		            .when()
		                    .post("/ding/search/merchants")
		            .then()
		                    .extract().response();

		    Assert.assertEquals(response.getStatusCode(), 200);
		    Assert.assertTrue(response.getTime() < 5000);
		    
		    test.info("Response Body: " + response.asPrettyString());    
		    
	  }
	
	@Test(priority=2)
	  public void GetNewJoiner() {
		  
		  Response response =
				  given()
	              .header("Authorization", 
	                      "Bearer " + ConfigManager.getUserToken())
	                      .queryParam("offset", 0)
	                      .queryParam("limit",12)
	                      .queryParam("favourites",true)
		            .when()
		                    .get("/ding/newjoiner")
		            .then()
		                    .extract().response();

		    Assert.assertEquals(response.getStatusCode(), 200);
		    Assert.assertTrue(response.getTime() < 5000);
		    test.info("Response Body: " + response.asPrettyString());
		    
		}
}
