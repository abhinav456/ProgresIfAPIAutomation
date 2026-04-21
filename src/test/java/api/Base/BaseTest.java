package api.Base;

import api.endpoints.UserEndPoints;
import api.payload.UserLogins;
import api.utilities.ConfigManager;
import api.utilities.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BaseTest {

    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeSuite
    public void setupReport() throws IOException {
    	 extent = ExtentManager.getInstance();

    	    // Set Base URL
    	    RestAssured.baseURI = ConfigManager.getProperty("baseURL");

    	    // Payload
    	    Map<String, String> payload = new HashMap<>();
    	    payload.put("grant_type", "password");
    	    payload.put("username", UserLogins.getUsername());
    	    payload.put("password", UserLogins.getPassword());
    	    payload.put("version", "1.0.0");

    	    // Headers
    	    Map<String, String> headers = new HashMap<>();
    	    headers.put("Content-Type", "application/json");

    	    // Login API
    	    Response response = UserEndPoints.post(
    	            "/user/login",
    	            payload,
    	            headers
    	    );

    	    String accessToken = response.jsonPath().getString("access_token");

    	    Properties tokenProp = new Properties();
    	    tokenProp.setProperty("access_token", accessToken);

    	    FileOutputStream fos =
    	            new FileOutputStream("src/test/resources/token.properties");
    	    tokenProp.store(fos, "Token Saved");
    	    fos.close();

    	    System.out.println("Token saved successfully");
    	    
    	    //Admin Payload
    	    Map<String, String> adminpayload = new HashMap<>();
    	    adminpayload.put("grant_type", "password");
    	    adminpayload.put("username", UserLogins.getAdminUsername());
    	    adminpayload.put("password", UserLogins.getAdminPassword());
    	    adminpayload.put("version", "1.0.0");

    	    // Headers
    	    Map<String, String> adminheaders = new HashMap<>();
    	    adminheaders.put("Content-Type", "application/json");

    	    // Login API
    	    Response adminresponse = UserEndPoints.post(
    	            "/ding/company/login",
    	            adminpayload,
    	            adminheaders
    	    );

    	    String adminaccessToken = adminresponse.jsonPath().getString("access_token");

    	    Properties admintokenProp = new Properties();
    	    admintokenProp.setProperty("access_token", adminaccessToken);

    	    FileOutputStream adminfos =
    	            new FileOutputStream("src/test/resources/admintoken.properties");
    	    admintokenProp.store(adminfos, "Admin Token Saved");
    	    adminfos.close();

    	    System.out.println("Admin Token saved successfully");
    	    
    	}

    @BeforeClass
    public void setupBaseURLAndLogin() throws IOException, InterruptedException {

        // Set Base URL using ConfigManager
        RestAssured.baseURI =
                ConfigManager.getProperty("baseURL");

    }
    @BeforeMethod
    public void createTest(java.lang.reflect.Method method) {
        test = extent.createTest(method.getName());
    }

    @AfterMethod
    public void getResult(ITestResult result) {
    	
    	 String testName = result.getMethod().getMethodName();

        if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass(testName +" API Testcase Passed");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
        } else {
            test.skip("Test Skipped");
        }
    }

    @AfterSuite
    public void tearDownReport() throws IOException {
        extent.flush();
        // Path of report
        String reportPath = System.getProperty("user.dir") 
                + "/Reports/ProgresIfAPIReport.html";

        // Open report automatically in default browser
        java.awt.Desktop.getDesktop().browse(new java.io.File(reportPath).toURI());
        
    }
}
