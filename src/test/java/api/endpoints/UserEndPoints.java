package api.endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;
import api.endpoints.*;

import static io.restassured.RestAssured.given;

public class UserEndPoints {

    // ==============================
    // GET
    // ==============================
    public static Response get(String endpoint,
                               Map<String, String> headers,
                               Map<String, Object> queryParams) {

        RequestSpecification request = given();

        if (headers != null)
            request.headers(headers);

        if (queryParams != null)
            request.queryParams(queryParams);

        return request.when()
                      .get(endpoint);
    }

    // ==============================
    // POST
    // ==============================
    public static Response post(String endpoint,
                                Object payload,
                                Map<String, String> headers) {

        RequestSpecification request = given();

        if (headers != null)
            request.headers(headers);

        if (payload != null)
            request.body(payload);

        return request.when()
                      .post(endpoint);
    }

    // ==============================
    // PUT
    // ==============================
    public static Response put(String endpoint,
                               Object payload,
                               Map<String, String> headers) {

        RequestSpecification request = given();

        if (headers != null)
            request.headers(headers);

        if (payload != null)
            request.body(payload);

        return request.when()
                      .put(endpoint);
    }

    // ==============================
    // DELETE
    // ==============================
    public static Response delete(String endpoint,
                                  Map<String, String> headers) {

        RequestSpecification request = given();

        if (headers != null)
            request.headers(headers);

        return request.when()
                      .delete(endpoint);
    }
}