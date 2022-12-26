package testData;

public class Payloads {

	public static String addPlace() {

		return "{\r\n"
				+ "    \"location\": {\r\n"
				+ "        \"lat\": -38.383494,\r\n"
				+ "        \"lng\": 33.427362\r\n"
				+ "    },\r\n"
				+ "    \"accuracy\": 50,\r\n"
				+ "    \"name\": \"Frontline house\",\r\n"
				+ "    \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "    \"address\": \"29 sidelayout cohen 09\",\r\n"
				+ "    \"types\": [\r\n"
				+ "        \"park\",\r\n"
				+ "        \"shop\"\r\n"
				+ "    ],\r\n"
				+ "    \"website\": \"http://google.com\",\r\n"
				+ "    \"language\": \"French-IN\"\r\n"
				+ "}";
	}
	
	public static String updatePlace(String place_id) {
		
		return "{\r\n"
				+ "\"place_id\":\""+place_id+"\",\r\n"
				+ "\"address\":\"70 winter walk, USA\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}";
		
	}
	
	public static String complexjson() {
		
		return "{\r\n"
				+ "  \"dashboard\" : {\r\n"
				+ "    \"purchaseAmount\" : 910,\r\n"
				+ "    \"website\" : \"rahulshettyacademy.com\"\r\n"
				+ "  },\r\n"
				+ "  \"courses\" : [ {\r\n"
				+ "    \"title\" : \"Selenium Python\",\r\n"
				+ "    \"price\" : 50,\r\n"
				+ "    \"copies\" : 6\r\n"
				+ "  }, {\r\n"
				+ "    \"title\" : \"Cypress\",\r\n"
				+ "    \"price\" : 40,\r\n"
				+ "    \"copies\" : 4\r\n"
				+ "  }, {\r\n"
				+ "    \"title\" : \"RPA\",\r\n"
				+ "    \"price\" : 45,\r\n"
				+ "    \"copies\" : 10\r\n"
				+ "  } ]\r\n"
				+ "}";
		
	}
    

}
