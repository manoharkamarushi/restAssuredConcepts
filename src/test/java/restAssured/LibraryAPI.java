package restAssured;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


public class LibraryAPI {

	
	@Test(dataProvider = "multipleBooks")
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
	
}
