package api.testcases.ding.user.USMS;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.Base.BaseTest;
import api.payload.Banner;
import api.utilities.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import api.payload.USMSPayload;

public class USMS extends BaseTest {
  @Test(priority=1)
  public void GetUSMSDashbaord() {
	  
	  Response response =
			  given()
              .header("Authorization", 
                      "Bearer " + ConfigManager.getUserToken())
	                    .queryParam("limit", 100)
	            .when()
	                    .get("/ding/usms/dashboard")
	            .then()
	                    .extract().response();

	    Assert.assertEquals(response.getStatusCode(), 200);
	    Assert.assertTrue(response.getTime() < 5000);
	    test.info("Response Body: " + response.asPrettyString());
	}
  
  @Test(priority=2)
  public void GetMeterDetails() {
  	 
  	  Map<String, Object> requestBodyGetMeter =
  	            USMSPayload.GetMeterDetails(
  	                    "55004884"
  	                    
  	            );

  	    Response response =
  	    		 given()
                 .header("Authorization", 
                         "Bearer " + ConfigManager.getUserToken())
  	                    .contentType("application/json")
  	                    .body(requestBodyGetMeter)
  	            .when()
  	                    .post("/ding/usms/meter-details")
  	            .then()
  	                    .extract().response();

  	    Assert.assertEquals(response.getStatusCode(), 200);
  	    Assert.assertTrue(response.getTime() < 5000);

  	    test.info("Response Body: " + response.asPrettyString());
  	}
  
  @Test(priority=3)
  public void GetMeterHistory() {
  	 
  	  Map<String, Object> requestBodyGetMeterHistory =
  	            USMSPayload.GetMeterHistory(
  	                    10,
  	                    0   
  	            );

  	    Response response =
  	    		 given()
                 .header("Authorization", 
                         "Bearer " + ConfigManager.getUserToken())
  	                    .contentType("application/json")
  	                    .body(requestBodyGetMeterHistory)
  	            .when()
  	                    .post("/ding/usms/transaction-history")
  	            .then()
  	                    .extract().response();

  	    Assert.assertEquals(response.getStatusCode(), 200);
  	    Assert.assertTrue(response.getTime() < 5000);

  	    test.info("Response Body: " + response.asPrettyString());
  	}
}
