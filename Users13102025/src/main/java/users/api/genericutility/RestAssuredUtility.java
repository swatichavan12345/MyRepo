package users.api.genericutility;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
import java.util.HashMap;

public class RestAssuredUtility {


    public static RequestSpecification given() {
        return RestAssured.given().contentType("application/json");
    }

    public static Response get(String url) {
        return given().get(url);
    }

    public static Response post(String url, Object requestBody) {
        return given().body(requestBody).post(url);
    }

    public static Response put(String url, Object requestBody) {
        return given().body(requestBody).put(url);
    }

    public static Response delete(String url) {
        return given().delete(url);
    }

    public static String getResponseBody(Response response) {
        return response.getBody().asString();
    }

    public static int getResponseStatusCode(Response response) {
        return response.getStatusCode();
    }

     public static Map<String, String> getResponseHeaders(Response response) {
        return new HashMap<>(response.getHeaders().asList().stream()
                .collect(java.util.stream.Collectors.toMap(
                        h -> h.getName(),
                        h -> h.getValue()
                )));
    }


    // Example method for validating status code
    public static boolean validateStatusCode(Response response, int expectedStatusCode) {
        return response.getStatusCode() == expectedStatusCode;
    }

    // Example method for validating response body contains a specific string
    public static boolean validateResponseBodyContains(Response response, String expectedString) {
        return response.getBody().asString().contains(expectedString);
    }
}