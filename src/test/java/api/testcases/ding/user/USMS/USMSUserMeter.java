package api.testcases.ding.user.USMS;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.Base.BaseTest;
import api.payload.USMSPayload;
import api.utilities.ConfigManager;
import io.restassured.response.Response;

public class USMSUserMeter extends BaseTest{
	 String MeterID;
	 String CreatedMeterID;
	
	@Test(priority=1)
	  public void GetUSMSUserMeter() {
		  
		  Response response =
				  given()
	              .header("Authorization", 
	                      "Bearer " + ConfigManager.getUserToken())
		                    
		            .when()
		                    .get("/ding/usms/meters")
		            .then()
		                    .extract().response();

		    Assert.assertEquals(response.getStatusCode(), 200);
		    Assert.assertTrue(response.getTime() < 5000);
		    test.info("Response Body: " + response.asPrettyString());
		    
		    MeterID = response.jsonPath().getString("data[0].id");
		}
	  
	@Test(priority=2)
	  public void DeleteMeter() {
		  
		  Response response =
				  given()
	              .header("Authorization", 
	                      "Bearer " + ConfigManager.getUserToken())
		                    
		            .when()
		                    .delete("/ding/usms/meters/"+ MeterID)
		            .then()
		                    .extract().response();

		    Assert.assertEquals(response.getStatusCode(), 200);
		    Assert.assertTrue(response.getTime() < 5000);
		    test.info("Response Body: " + response.asPrettyString());
		    
		    MeterID = response.jsonPath().getString("data[0].id");
		    
		    String actualMessage = response.jsonPath().getString("message");
		    Assert.assertEquals(actualMessage, "Meter deleted successfully");
		}
	 @Test(priority=3)
	  public void USMSMeterCreate() {
		  Map<String, Object> requestCreateMeter =
		            USMSPayload.USMSMeterCreatePayload(
		            		"55004884",
		            		"Alex Lee",
		            		"ELECTRICITY"
		            		 
		                    
		            );

		    Response response =
		    		 given()
	               .header("Authorization", 
	                       "Bearer " + ConfigManager.getUserToken())
		                    .contentType("application/json")
		                    .body(requestCreateMeter)
		            .when()
		                    .post("/ding/usms/meters")
		            .then()
		                    .extract().response();

		    Assert.assertEquals(response.getStatusCode(), 200);
		    Assert.assertTrue(response.getTime() < 5000);
		    
		    test.info("Response Body: " + response.asPrettyString());
		    
		    CreatedMeterID = response.jsonPath().getString("data.id");
		    
		    System.out.println("Created Meter ID Is : "+CreatedMeterID);
		    
	  }
	 
	 @Test(priority=4)
	  public void USMSMeterUpdate() {
		  Map<String, Object> requestUpdateMeter =
		            USMSPayload.USMSMeterUpdatePayload(
		            		"55004884",
		            		"Max Lee",
		            		"Electricity"      
		            );

		    Response response =
		    		 given()
	               .header("Authorization", 
	                       "Bearer " + ConfigManager.getUserToken())
		                    .contentType("application/json")
		                    .body(requestUpdateMeter)
		            .when()
		                    .put("/ding/usms/meters/"+CreatedMeterID)
		            .then()
		                    .extract().response();

		    Assert.assertEquals(response.getStatusCode(), 200);
		    Assert.assertTrue(response.getTime() < 5000);
		    
		    test.info("Response Body: " + response.asPrettyString());
		    
	  }
	 
}
