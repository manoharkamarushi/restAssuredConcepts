package restAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import files.Payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class CRUDAPI {

@Test()
public void verifyAddPlaceAPI(){
		
//		given() - tells about what all input details have given to the api
//		when() - how we are submitting the api
//		then()  - what all validations doing on the response generated

		// validate if Add Place API is workimg as expected 
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String addPlaceResponse=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payloads.addPlace()).when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server", "Apache/2.4.41 (Ubuntu)")
		.extract().response().asString(); //extracting response and save it as string
		
		//extract response and want to parse it use jsonPath class
		JsonPath json= new JsonPath(addPlaceResponse);
		String place_id = json.get("place_id");
		System.out.println(place_id);
		
		
		
		
	}

}
