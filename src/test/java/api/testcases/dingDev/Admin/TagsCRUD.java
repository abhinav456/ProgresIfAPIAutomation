package api.testcases.dingDev.Admin;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.util.Map;
import java.util.List;
import org.testng.Assert;

import api.Base.BaseTest;
import api.payload.*;
import api.utilities.ConfigManager;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import api.utilities.*;
public class TagsCRUD extends BaseTest{
  @Test(priority=1)
  public void CreateTags() {
	  
	  List<Map<String, String>> testData =
	            ExcelUtils.readExcel("DataFiles/ProgresifCarePlus_Tags.xlsx", "Sheet1");

	    for (Map<String, String> row : testData) {

	        Map<String, Object> payload =
	                Tags.createTagPayload(
	                        row.get("tag_name"),
	                        row.get("tag_description"),
	                        Boolean.parseBoolean(row.get("is_banner_tag")),
	                        row.get("banner_image"),
	                        row.get("banner_start_date"),
	                        row.get("banner_end_date"),
	                        row.get("tag_start_date"),
	                        row.get("tag_end_date"),
	                        (int) Double.parseDouble(row.get("display_order"))
	                );

	        Response response =
	                given()
	                        .header("Authorization",
	                                "Bearer " + ConfigManager.getProperty("access_token"))
	                        .contentType("application/json")
	                        .body(payload)
	                .when()
	                        .post("/ding/tags")
	                .then()
	                        .extract().response();

	        System.out.println("Response: " + response.asPrettyString());

	        Assert.assertEquals(response.getStatusCode(), 200);
		    Assert.assertTrue(response.getTime() < 10000);
	        
	        test.info("Response Body: " + response.asPrettyString());
	    }
	  
  }
}
