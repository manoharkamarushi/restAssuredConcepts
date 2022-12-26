package restAssured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import testData.Payloads;

public class ComplexJsonParse {

	@Test()
	public void parseit() {
		
			JsonPath json= new JsonPath(Payloads.complexjson());
//		    1. Print No of courses returned by API
			int course_size=json.getInt("courses.size()");
			System.out.println("No.of courses available:"+course_size);			
		
//		    2.Print Purchase Amount
			int purchaseAmount= json.getInt("dashboard.purchaseAmount");
			System.out.println("Purchase Amount:"+purchaseAmount);	
			
//		    3. Print Title of the first course
			System.out.println("Title of course 1 is :"+json.get("courses[0].title"));
//		    4. Print All course titles and their respective Prices
			
			for(int i=0;i<course_size;i++) {
				
				String course = json.get("courses["+i+"].title");
				int price = json.getInt("courses["+i+"].price");
				
				System.out.println(course+" "+price);
				
			}
			
//		    5. Print no of copies sold by RPA Course
			
			System.out.println("no of copies sold by RPA Course:" + json.get("courses[2].copies"));
			
//		    6. Verify if Sum of all Course prices matches with Purchase Amount
			
			int totalPrice = 0,Price=0;
			for(int i=0;i<course_size;i++) {
				
				Price= json.getInt("courses["+i+"].price");
				totalPrice = totalPrice+Price;
				
			}
			System.out.println(totalPrice);
			Assert.assertEquals(totalPrice, purchaseAmount);
			
			
	}

}
