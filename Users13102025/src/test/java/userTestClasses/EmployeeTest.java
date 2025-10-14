	package userTestClasses;
	import static io.restassured.RestAssured.given;
	import java.io.IOException;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import org.hamcrest.Matchers;
	import org.testng.Assert;
	import org.testng.annotations.Test;
	import com.aventstack.extentreports.Status;
	import io.restassured.response.Response;
	import users.api.BaseAPIClass.BaseAPIClass;
	import users.api.pojoclass.EmployeePOJO;
	import users.api.pojoclass.ProjectPOJO;
	import users.api.endpoints.IEndPoint;

	public class EmployeeTest extends BaseAPIClass {

	    @Test
	    public void addEmployeeTest() throws SQLException, Throwable {
	        String BASEURI = fLib.getDtaFromPropertiesFile("BASEUri");
	        String projectName = "Airtel_" + jLib.getRandomNumber();

	        test = extent.createTest("API Test - Create Project & Add Employee");
	        test.log(Status.INFO, "Starting API Test for creating a project and employee");

	        // Step 1: Add a project
	        ProjectPOJO pObj = new ProjectPOJO(projectName, "Created", "Rahul", 0);
	        test.log(Status.INFO, "Sending POST request to create project: " + projectName);

	        Response projectResp = given().spec(specReqObj).body(pObj)
	                .when().post(IEndPoint.AddProj)
	                .then().log().all().extract().response();

	        test.log(Status.INFO, "Project creation response: " + projectResp.asPrettyString());
	        String projectResponsePath = captureApiResponse(projectResp.asPrettyString());
	        test.addScreenCaptureFromPath(projectResponsePath);

	        Assert.assertEquals(projectResp.statusCode(), 201, "Project creation failed");
	        test.log(Status.PASS, "Project created successfully with status code 201");

	        // Step 2: Add an employee
	        EmployeePOJO empObj = new EmployeePOJO(
	                "Architect", "20/08/1999", "swati80162345@gmail.com",
	                "user_" + jLib.getRandomNumber(), 6, "9928906544",
	                projectName, "ROLE_EMPLOYEE", "user_" + jLib.getRandomNumber()
	        );

	        Response empResp = given().spec(specReqObj).body(empObj)
	                .when().post(IEndPoint.AddEmp)
	                .then().log().all()
	                .extract().response();

	        test.log(Status.INFO, "Employee creation response: " + empResp.asPrettyString());
	        String empResponsePath = captureApiResponse(empResp.asPrettyString());
	        test.addScreenCaptureFromPath(empResponsePath);

	        Assert.assertEquals(empResp.statusCode(), 201, "Employee creation failed");
	        test.log(Status.PASS, "Employee created successfully with status code 201");

	        // Step 3: Verify in DB
	        ResultSet rs = dbLib.executeSelectQuery("SELECT * FROM employee WHERE project_name='" + projectName + "'");
	        Assert.assertTrue(rs.next(), "Project not found in DB");
	        test.log(Status.PASS, "Verified employee record in DB for project: " + projectName);
	    }

	    @Test
	    public void addEmployeeWithoutEmailTest() throws IOException {
	        test = extent.createTest("API Test - Add Employee Without Email");

	        String projectName = "Airtel_" + jLib.getRandomNumber();
	        ProjectPOJO pObj = new ProjectPOJO(projectName, "Created", "Rahul", 0);

	        Response projectResp = given().spec(specReqObj).body(pObj)
	                .when().post(IEndPoint.AddProj)
	                .then().extract().response();

	        test.log(Status.INFO, "Created project for negative test: " + projectName);

	        EmployeePOJO empObj = new EmployeePOJO(
	                "Architect", "20/08/1999", "", "user_" + jLib.getRandomNumber(),
	                6, "9928906544", projectName, "ROLE_EMPLOYEE", "user_" + jLib.getRandomNumber()
	        );

	        Response empResp = given().spec(specReqObj).body(empObj)
	                .when().post(IEndPoint.AddEmp)
	                .then().extract().response();

	        test.log(Status.INFO, "Negative test response: " + empResp.asPrettyString());
	        String negativePath = captureApiResponse(empResp.asPrettyString());
	        test.addScreenCaptureFromPath(negativePath);

	        Assert.assertEquals(empResp.statusCode(), 500, "Expected failure (500) not returned");
	        test.log(Status.PASS, "Verified negative scenario handled properly with status code 500");
	    }
	}
