package restAssured;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


public class LibraryAPI {

	
	@Test(dataProvider = "multipleBooks",enabled=false)
	public void testAddLibraryAPI(String isbn, String asile) {
		
			RestAssured.baseURI="http://216.10.245.166";
			
			String response= given().log().all().header("Content-Type","application/json").body("{\r\n"
					+ "\r\n"
					+ "\"name\":\"API Automation with Java\",\r\n"
					+ "\"isbn\":\""+isbn+"\",\r\n"
					+ "\"aisle\":\""+asile+"\",\r\n"
					+ "\"author\":\"John foe\"\r\n"
					+ "}").
			when().post("/Library/Addbook.php").
			then().log().all().assertThat().statusCode(200).body("Msg",equalTo("successfully added")).extract().response().asString();
			
			JsonPath json = new JsonPath(response);
			String bookID= json.get("ID");
			System.out.println(bookID);
	}

	@DataProvider
	public Object[][] multipleBooks() {
		
		return new Object[][] {{"api2","261202"},{"api3","261203"},{"ap4","261204"}};
	}
	
	
	
	@Test
	public void readPayloadFromJsonFile() throws Exception {
		
		RestAssured.baseURI="http://216.10.245.166";
		
		String response= given().log().all().header("Content-Type","application/json").body(new String(Files.readAllBytes(Paths.get("./src/test/java/testData/addBook.json")))).
		when().post("/Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).body("Msg",equalTo("successfully added")).extract().response().asString();
		
		JsonPath json = new JsonPath(response);
		String bookID= json.get("ID");
		System.out.println(bookID);
		
		
	}
}
