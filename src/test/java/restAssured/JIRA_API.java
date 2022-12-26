package restAssured;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class JIRA_API {
	
	SessionFilter session =null;
	String issueID=null;
	
	
	@BeforeTest
	public void getSessionID() {
		
			RestAssured.baseURI="http://localhost:8080/";
			session = new SessionFilter();
		
			given().header("Content-Type","application/json").body("{\r\n"
					+ "    \"username\":\"manoharautomation\",\r\n"
					+ "    \"password\":\"manoharautomation\"\r\n"
					+ "}").log().all().filter(session).
			when().post("rest/auth/1/session").
			then().log().all().assertThat().statusCode(200);
	
	}
	
	@Test
	public void createIssue() {
		
		String response=given().header("Content-Type","application/json").body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "        \"project\": {\r\n"
				+ "            \"key\": \"TP1\"\r\n"
				+ "        },\r\n"
				+ "        \"summary\": \"creating issue from API - something's wrong\",\r\n"
				+ "        \"description\": \"description\",\r\n"
				+ "        \"issuetype\": {\r\n"
				+ "            \"name\": \"Bug\"\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "}").log().all().filter(session).
		when().post("/rest/api/2/issue").
		then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath json = new JsonPath(response);
		issueID = json.get("id");
		
	
			
	}
	
	@Test(dependsOnMethods = "createIssue")
	public void addComment() {
		
		given().pathParam("Key", issueID).header("Content-Type","application/json").body("{\r\n"
				+ "    \"body\": \"Comment Added1\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").log().all().filter(session).
		when().post("rest/api/2/issue/{Key}/comment").
		then().log().all().assertThat().statusCode(201);
	}
	
	
	@Test(dependsOnMethods = "createIssue")
	public void addAttachment() {
		
		given().pathParam("Key", issueID).header("X-Atlassian-Token","no-check").log().all().filter(session).
		header("Content-Type","multipart/form-data").multiPart("file",new File("jira.txt")).
		when().post("/rest/api/2/issue/{Key}/attachments").
		then().log().all().assertThat().statusCode(200);
			
	}
	
}
