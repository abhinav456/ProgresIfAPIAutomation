package api.testcases.dingDev.Admin;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.Base.BaseTest;
import api.payload.MerchantCategories;
import api.utilities.ConfigManager;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class MerchantDiscoveryCategories extends BaseTest {
	String categoryCode;
	String categoryName;
	int categoryId;
	
	@Test(priority=1)
	  public void CreateCategory() {

		  Map<String, Object> requestBody =
				  MerchantCategories.createCategoryPayload(
					        "https://example.com/icons/electronics.png",
					        2,
					        10,
					        true,
					        "2026-01-01T00:00:00Z",
					        "2026-12-31T23:59:59Z"
		            );

		    Response response =
		    		 given()
	               .header("Authorization", 
	                       "Bearer " + ConfigManager.getAdminToken())
		                    .contentType("application/json")
		                    .body(requestBody)
		            .when()
		                    .post("/ding/categories")
		            .then()
		                    .extract().response();

		    Assert.assertEquals(response.getStatusCode(), 200);
		    Assert.assertTrue(response.getTime() < 5000);
		    
		 // Extract response JSON
		    JsonPath jsonPath = response.jsonPath();

		    // Store values
		    categoryId = jsonPath.getInt("categoryCreated.id");
		    categoryName = jsonPath.getString("categoryCreated.category_name");

		    // Print values
		    System.out.println("Saved category_id: " + categoryId);
		    System.out.println("Saved category_name: " + categoryName);

		    test.info("Response Body: " + response.asPrettyString());
		}
	
	@Test(priority=2)
	  public void UpdateCategory() {

		  Map<String, Object> requestBody =
				  MerchantCategories.UpdateCategoryPayload(
						    categoryName,
					        "https://example.com/icons/electronics.png",
					        2,
					        10,
					        true,
					        "2026-01-01T00:00:00Z",
					        "2026-12-31T23:59:59Z"
		            );

		    Response response =
		    		 given()
	               .header("Authorization", 
	                       "Bearer " + ConfigManager.getAdminToken())
		                    .contentType("application/json")
		                    .body(requestBody)
		            .when()
		                    .put("/ding/categories/"+categoryId)
		            .then()
		                    .extract().response();

		    Assert.assertEquals(response.getStatusCode(), 200);
		    Assert.assertTrue(response.getTime() < 5000);
		    
		    test.info("Response Body: " + response.asPrettyString());
		}
	@Test(priority=3)
	  public void DeleteCategory() {
		    Response response =
		    		 given()
	               .header("Authorization", 
	                       "Bearer " + ConfigManager.getAdminToken())
		                    .contentType("application/json")
		                    
		            .when()
		                    .delete("/ding/categories/"+categoryId)
		            .then()
		                    .extract().response();

		    Assert.assertEquals(response.getStatusCode(), 200);
		    Assert.assertTrue(response.getTime() < 5000);
		    
		    test.info("Response Body: " + response.asPrettyString());
		}
}
