package userTestClasses;
import static io.restassured.RestAssured.*;
import java.sql.ResultSet;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import users.api.BaseAPIClass.BaseAPIClass;
import users.api.pojoclass.ProjectPOJO;
import users.api.endpoints.IEndPoint;

public class ProjectTest extends BaseAPIClass {

    String projectName;
    ProjectPOJO pObj;

    @Test
    public void addSingleProjectWithCreated() throws Throwable {
        test = extent.createTest("API Test - Add Single Project with Status 'Created'");
        test.log(Status.INFO, "Starting test: Add project with 'Created' status");

        String BASEURI = fLib.getDtaFromPropertiesFile("BASEUri");
        String actSuccessMsg = "Successfully Added";
        projectName = "Asurion_" + jLib.getRandomNumber();

        pObj = new ProjectPOJO(projectName, "Created", "Rahul", 0);

        test.log(Status.INFO, "Sending POST request to create project: " + projectName);

        // API Request
        Response resp = given()
                .spec(specReqObj)
                .body(pObj)
                .when()
                .post(IEndPoint.AddProj);

        // Log response in Extent Report
        String responseBody = resp.asPrettyString();
        test.log(Status.INFO, "Response Body:\n" + responseBody);

        // Capture response as "screenshot" equivalent text file
        String responsePath = captureApiResponse(responseBody);
        test.addScreenCaptureFromPath(responsePath);

        // Validate response
        int statusCode = resp.getStatusCode();
        test.log(Status.INFO, "Status Code: " + statusCode);
        Assert.assertEquals(statusCode, 201, "Expected status code 201");

        resp.then()
                .assertThat().time(Matchers.lessThan(3000L))
                .assertThat().spec(specRespObj)
                .log().all();

        // Verify response message
        String actualMSG = resp.jsonPath().get("msg");
        Assert.assertEquals(actualMSG, actSuccessMsg);
        test.log(Status.PASS, "Verified API layer message: " + actualMSG);

        // Verify in DB Layer
        ResultSet flag = dbLib.executeSelectQuery("select * from project where project_name='" + projectName + "'");
        Assert.assertTrue(flag.next(), "Project not found in DB");
        test.log(Status.PASS, "Verified project record in DB: " + projectName);
    }

    @Test(dependsOnMethods = "addSingleProjectWithCreated")
    public void createDuplicateProjectTest() throws Throwable {
        test = extent.createTest("API Test - Duplicate Project Creation");
        test.log(Status.INFO, "Starting test: Attempt to create duplicate project: " + projectName);

        Response resp = given()
                .spec(specReqObj)
                .body(pObj)
                .when()
                .post(IEndPoint.AddProj);

        String responseBody = resp.asPrettyString();
        test.log(Status.INFO, "Response Body:\n" + responseBody);

        String responsePath = captureApiResponse(responseBody);
        test.addScreenCaptureFromPath(responsePath);

        int statusCode = resp.getStatusCode();
        Assert.assertEquals(statusCode, 409, "Expected status code 409 for duplicate project");
        test.log(Status.PASS, "Duplicate project validation passed with status code 409");

        resp.then().assertThat().spec(specRespObj).log().all();
    }
}
