package api.testcases.ding.user.USMS;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.Base.BaseTest;
import api.Base.BaseTest.*;
import api.payload.Banner;
import api.utilities.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import api.payload.USMSPayload;

public class USMSPayment extends BaseTest{
	
	String SessionID;
	String OrderId;

	@Test(priority=1)
	  public void GetUSMSPaymentWallet() {
		  Map<String, Object> requestBodyPaymentWallet =
		            USMSPayload.getUSMSPaymentWallet(
		            		 "2.00",
		            		"55004884",
		            		"ELECTRICITY"	    
		            );

		    Response response =
		    		 given()
	               .header("Authorization", 
	                       "Bearer " + ConfigManager.getProperty("access_token"))
		                    .contentType("application/json")
		                    .body(requestBodyPaymentWallet)
		            .when()
		                    .post("/ding/pay/usms/wallet")
		            .then()
		            .log().all()
		            .extract().response();

		    Assert.assertEquals(response.getStatusCode(), 200);
		    Assert.assertTrue(response.getTime() < 20000);
		    
		    test.info("Response Body: " + response.asPrettyString());
		    
	  }
	
  @Test(priority=2)
  public void GetSessionForUSMSPayment() {
	  Map<String, Object> requestBodySessionPayment =
	            USMSPayload.GetSessionUSMSPayment(
	            		   "1.00",
	            		   "test",
	            		   "5123450000000008",
	            		   "01",
	            		   "39",
	            		  "100"
	                    
	            );

	    Response response =
	    		 given()
               .header("Authorization", 
                       "Bearer " + ConfigManager.getProperty("access_token"))
	                    .contentType("application/json")
	                    .body(requestBodySessionPayment)
	            .when()
	                    .post("/ding/usms/createsessionusms")
	            .then()
	                    .extract().response();

	    Assert.assertEquals(response.getStatusCode(), 200);
	    Assert.assertTrue(response.getTime() < 5000);
	    
	   SessionID = response.jsonPath().getString("sessionId");
	    
	   OrderId = response.jsonPath().getString("orderId");
	    
	    test.info("Response Body: " + response.asPrettyString());
	    
  }
  @Test(priority=3)
  public void GetUSMSPaymentNewCard() {
	  Map<String, Object> requestBodyCardPayment =
	            USMSPayload.getUSMSCardPayment(
	            		SessionID,
	            		OrderId,
	            		"55004884",
	            		"ELECTRICITY",
	            		 "0.1"
	            		 
	                    
	            );

	    Response response =
	    		 given()
               .header("Authorization", 
                       "Bearer " + ConfigManager.getProperty("access_token"))
	                    .contentType("application/json")
	                    .body(requestBodyCardPayment)
	            .when()
	                    .post("/ding/pay/usms/cardpayment")
	            .then()
	                    .extract().response();

	    Assert.assertEquals(response.getStatusCode(), 200);
	    Assert.assertTrue(response.getTime() < 20000);
	    
	    test.info("Response Body: " + response.asPrettyString());
	    
  }
  
  @Test(priority=4)
  public void GetUSMSPaymentExitCard() {
	  Map<String, Object> requestBodyExitCardPayment =
	            USMSPayload.getUSMSPaymentExitCard(
	            		"1.00",
	            		"55004884",
	            		"ELECTRICITY",
	            		"test 3"
	            		 
	                    
	            );

	    Response response =
	    		 given()
               .header("Authorization", 
                       "Bearer " + ConfigManager.getProperty("access_token"))
	                    .contentType("application/json")
	                    .body(requestBodyExitCardPayment)
	            .when()
	                    .post("/ding/pay/usms/exitpayment")
	            .then()
	                    .extract().response();

	    Assert.assertEquals(response.getStatusCode(), 200);
	    Assert.assertTrue(response.getTime() < 20000);
	    
	    test.info("Response Body: " + response.asPrettyString());
	    
  }
  
  
}
