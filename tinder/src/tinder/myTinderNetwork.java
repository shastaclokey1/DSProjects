package tinder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class myTinderNetwork 
{

	public static void main(String[] args) 
	{
		tinderNetwork graph = new tinderNetwork();
		
		//add vertices to the graph
		/*User v1 = new User(1, 500, 700, 10, "shasta", "clokey", "yay@gmail.com", "Male", "Female");
		User v2 = new User(2, 500, 700, 10, "fred", "bogs", "woo@gmail.com", "Male", "Female");
		User v3 = new User(3, 500, 700, 10, "emily", "franklin", "lol@gmail.com", "Female", "Female");
		User v4 = new User(4, 500, 700, 10, "lauren", "kenedy", "yolo@gmail.com", "Female", "Male");
		User v5 = new User(5, 500, 700, 10, "chloe", "williams", "yee@gmail.com", "Female", "Male");
		User v6 = new User(6, 500, 700, 10, "rachael", "clark", "yoyo@gmail.com", "Female", "Female");
		
		graph.addUser(v1);
		graph.addUser(v2);
		graph.addUser(v3);
		graph.addUser(v4);
		graph.addUser(v5);
		graph.addUser(v6);*/
		
		
		
		
		// TODO Auto-generated method stub
		JSONParser parser = new JSONParser();	
		String tempFN = "", tempLN = "", tempEmail = "", tempGen = "", tempIntGen = "";
		long tempId;
		double tempLong, tempLat, tempRad;
		try 
	    {
	   		Object obj = parser.parse(new FileReader("users.json"));
	   		//System.out.println("File exists.");
	   		JSONArray jsonArray = (JSONArray) obj;
	            
	         for (int i = 0; i < jsonArray.size(); i++) 
	         {
	             JSONObject jsonObject = (JSONObject) jsonArray.get(i);
	             //System.out.println(jsonObject);
	             
	             int id = i;
	             
	             tempFN = (String) jsonObject.get("first_name");
	             //System.out.println(tempFN);
	             
	             tempLN = (String) jsonObject.get("last_name");
	             //System.out.println(tempLN);
	             
	             tempEmail = (String) jsonObject.get("email");
	             //System.out.println(tempEmail);
	             
	             tempId = (long) jsonObject.get("id");
	             //System.out.println(tempId);
	             
	             try
	             {
		             tempLong = (double) jsonObject.get("longitude");
		             //System.out.println(tempLong);
		             
		             tempLat = (double) jsonObject.get("latitude");
		             //System.out.println(tempLat);
	             }
	             catch(Exception e)
	             {
	            	 continue;
	             }
	             tempRad = 10;
	             
	             tempGen = (String) jsonObject.get("gender");
	             //System.out.println(tempGen);
	             
	             tempIntGen = (String) jsonObject.get("interesed");
	             //System.out.println(tempIntGen);
	             
	             User tempUser = new User(tempId, tempLong, tempLat, tempRad, tempFN, tempLN, tempEmail, tempGen, tempIntGen);
	             graph.addUser(tempUser);
	             
	         }
	            
	            
	    } 
        catch (FileNotFoundException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        catch (IOException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        catch (ParseException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		graph.updateLinks();
		graph.printNetwork();

	}

}
