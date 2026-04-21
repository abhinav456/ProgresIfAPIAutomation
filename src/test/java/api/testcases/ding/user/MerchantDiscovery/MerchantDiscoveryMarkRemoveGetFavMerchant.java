package api.testcases.ding.user.MerchantDiscovery;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.Base.BaseTest;
import api.utilities.ConfigManager;
import io.restassured.response.Response;

public class MerchantDiscoveryMarkRemoveGetFavMerchant extends BaseTest {
  String FavID;
  @Test(priority=1)
  public void GetAllFavorites() {
	  
	  Response response =
			  given()
              .header("Authorization", 
                      "Bearer " + ConfigManager.getUserToken())
                       .queryParam("lat", 89.421998333333335)
                       .queryParam("lng", -122.08)
                       .queryParam("offset", 0)
                       .queryParam("limit", 40)
	            .when()
	                    .get("/ding/favourites")
	            .then()
	                    .extract().response();

	    Assert.assertEquals(response.getStatusCode(), 200);
	    Assert.assertTrue(response.getTime() < 5000);
	    test.info("Response Body: " + response.asPrettyString());
	    FavID = response.jsonPath().getString("data.categories[0].merchants[0].id");
	    System.out.println("Category ID Is : "+FavID);
	}
  
  @Test(priority=2)
  public void RemoveFavorites() {
	  
	  Response response =
			  given()
              .header("Authorization", 
                      "Bearer " + ConfigManager.getUserToken())
                       
	            .when()
	                    .delete("/ding/"+FavID+"/favourites")
	            .then()
	                    .extract().response();

	    Assert.assertEquals(response.getStatusCode(), 200);
	    Assert.assertTrue(response.getTime() < 5000);
	    test.info("Response Body: " + response.asPrettyString());
	    
	    String companyId = response.jsonPath().getString("data.ding_company_id");

	    Assert.assertEquals(FavID, companyId);
	    
	}
  
  @Test(priority=3)
  public void SetFavorites() {
	  
	  Response response =
			  given()
              .header("Authorization", 
                      "Bearer " + ConfigManager.getUserToken())
                       
	            .when()
	                    .post("/ding/"+FavID+"/favourites")
	            .then()
	                    .extract().response();

	    Assert.assertEquals(response.getStatusCode(), 200);
	    Assert.assertTrue(response.getTime() < 5000);
	    test.info("Response Body: " + response.asPrettyString());
	    
	}
}
