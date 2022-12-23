package restAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import files.Payloads;
import io.restassured.RestAssured;

public class Basics {

@Test()
public void verifyAddPlaceAPI(){
		
//		given() - tells about what all input details have given to the api
//		when() - how we are submitting the api
//		then()  - what all validations doing on the response generated

		// validate if Add Place API is workimg as expected 
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payloads.addPlace()).when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(201).body("scope",equalTo("APP")).header("Server", "Apache/2.4.41 (Ubuntu)");
		
		
	}

}
