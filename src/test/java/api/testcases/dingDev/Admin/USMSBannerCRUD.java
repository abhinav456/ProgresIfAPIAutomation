package api.testcases.dingDev.Admin;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Properties;

import api.utilities.ConfigManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import api.payload.Banner;

import api.Base.BaseTest;
import api.payload.CompanyAmend;

public class USMSBannerCRUD extends BaseTest{
	
  @Test(priority = 1)
  public void GetAllBanner() {
	  
	  Response response =
			  given()
              .header("Authorization", 
                      "Bearer " + ConfigManager.getProperty("access_token"))
	                    .queryParam("limit", 100)
	            .when()
	                    .get("ding/usms/banners")
	            .then()
	                    .extract().response();

	    Assert.assertEquals(response.getStatusCode(), 200);
	    Assert.assertTrue(response.getTime() < 5000);
	}
  
  
  @Test(priority=2)
  public void CreateBanner() throws Exception {

      Map<String, Object> requestBodyCreate =
              Banner.createBannerPayload(
                      "Holiday Special Offer - Limited Time",
                      "https://example.com/images/holiday-banner.png",
                      "https://example.com/holiday-sale",
                      13,
                      "2024-12-01T00:00:00Z"
              );

      Response response =
    		  given()
              .header("Authorization", 
                      "Bearer " + ConfigManager.getProperty("access_token"))
                      .contentType("application/json")
                      .body(requestBodyCreate)
              .when()
                      .post("/ding/usms/banners")
              .then()
                      .extract().response();

      Assert.assertEquals(response.getStatusCode(), 200);
      Assert.assertTrue(response.getTime() < 5000);

      test.info("Response Body: " + response.asPrettyString());

      //Extract Banner ID as String
      String bannerId = response.jsonPath().getString("data.id");

      System.out.println("Banner ID Created: " + bannerId);

      //Save into globalVariables.properties
      Properties prop = new Properties();

      FileInputStream fis =
              new FileInputStream("src/test/resources/globalVariables.properties");
      prop.load(fis);

      prop.setProperty("usms_banner_id", bannerId);

      FileOutputStream fos =
              new FileOutputStream("src/test/resources/globalVariables.properties");
      prop.store(fos, "Updated Banner ID");

      fos.close();
  }
  

  @Test(priority=3)
public void UpdateBanner() {
	  String bannerId = ConfigManager.getProperty("usms_banner_id");
	  Map<String, Object> requestBodyUpdate =
	            Banner.updateBannerPayload(
	                    "Holiday Special Offer - Limited Time",
	                    "https://example.com/images/holiday-banner.png",
	                    "https://example.com/holiday-sale",
	                    13,
	                    "2024-12-01T00:00:00Z",
	                    "2024-12-01T00:00:00Z"
	            );

	    Response response =
	    		 given()
                 .header("Authorization", 
                         "Bearer " + ConfigManager.getProperty("access_token"))
	                    .contentType("application/json")
	                    .body(requestBodyUpdate)
	            .when()
	                    .put("/ding/usms/banners/"+bannerId)
	            .then()
	                    .extract().response();

	    Assert.assertEquals(response.getStatusCode(), 200);
	    Assert.assertTrue(response.getTime() < 5000);

	    test.info("Response Body: " + response.asPrettyString());
	}
  
  @Test(priority=4)
  public void DeleteBanner() {
  	  String bannerId = ConfigManager.getProperty("usms_banner_id");
  	  Map<String, Object> requestBodyUpdate =
  	            Banner.updateBannerPayload(
  	                    "Holiday Special Offer - Limited Time",
  	                    "https://example.com/images/holiday-banner.png",
  	                    "https://example.com/holiday-sale",
  	                    13,
  	                    "2024-12-01T00:00:00Z",
  	                    "2024-12-01T00:00:00Z"
  	            );

  	    Response response =
  	    		 given()
                 .header("Authorization", 
                         "Bearer " + ConfigManager.getProperty("access_token"))
  	                    .contentType("application/json")
  	                    .body(requestBodyUpdate)
  	            .when()
  	                    .delete("/ding/usms/banners/"+bannerId)
  	            .then()
  	                    .extract().response();

  	    Assert.assertEquals(response.getStatusCode(), 200);
  	    Assert.assertTrue(response.getTime() < 5000);

  	    test.info("Response Body: " + response.asPrettyString());
  	}
}
