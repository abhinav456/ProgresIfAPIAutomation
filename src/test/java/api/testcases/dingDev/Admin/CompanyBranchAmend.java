package api.testcases.dingDev.Admin;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.Base.BaseTest;
import api.payload.CompanyAmend;
import api.utilities.ConfigManager;
import io.restassured.response.Response;

public class CompanyBranchAmend extends BaseTest {
  @Test(priority=1)
  public void createCompanyBranchAmend() {

	  Map<String, Object> requestBody =
	            CompanyAmend.CreateUpdateBranchPayload(
	            		 456,
	            	     "https://branch.example.com",
	            	     "main",
	            	     3.1392,
	            	     101.6869
	            );

	    Response response =
	    		 given()
               .header("Authorization", 
                       "Bearer " + ConfigManager.getAdminToken())
	                    .contentType("application/json")
	                    .body(requestBody)
	            .when()
	                    .post("/ding/company-branch-amends")
	            .then()
	                    .extract().response();

	    Assert.assertEquals(response.getStatusCode(), 200);
	    Assert.assertTrue(response.getTime() < 5000);

	    test.info("Response Body: " + response.asPrettyString());
	}
  @Test(priority = 2)
	public void getAllCompanyBranchAmends() {

	    Response response =
	    		 given()
               .header("Authorization", 
                       "Bearer " + ConfigManager.getAdminToken())
	                    .queryParam("branch_id", 456)
	            .when()
	                    .get("/ding/company-branch-amends")
	            .then()
	                    .extract().response();

	    Assert.assertEquals(response.getStatusCode(), 200);
	    Assert.assertTrue(response.getTime() < 5000);
	    test.info("Response Body: " + response.asPrettyString());
	}
}
