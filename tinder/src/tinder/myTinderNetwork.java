package tinder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class myTinderNetwork 
{

	public static void main(String[] args) 
	{
		tinderNetwork graph = new tinderNetwork();
		
		
		// TODO Auto-generated method stub
		Scanner s1 = new Scanner(System.in);
		JSONParser parser = new JSONParser();	
		String tempFN = "", tempLN = "", tempEmail = "", tempGen = "", tempIntGen = "";
		long tempId;
		double tempLong, tempLat, tempRad;
		System.out.print("Specify the match radius for all the users in the Tinderverse: ");
		double stdRad = s1.nextDouble();
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
	             }
	             catch(Exception e)
	             {
	            	 try
	            	 {
	            	 String tempLongStr = (String) jsonObject.get("longitude");
	            	 tempLong = Double.parseDouble(tempLongStr);
	            	 }
	            	 catch(Exception f)
	            	 {
	            		 tempLong = (long) jsonObject.get("longitude");
	            	 }
	             }
	             try
	             {
		             tempLat = (double) jsonObject.get("latitude");
	             }
	             catch(Exception e)
	             {
	            	 try
	            	 {
		            	 String tempLatStr = (String) jsonObject.get("latitude");
		            	 tempLat = Double.parseDouble(tempLatStr);
	            	 }
	            	 catch(Exception f)
	            	 {
	            		 tempLat = (long) jsonObject.get("latitude");
	            	 }
	             }
	             ////////////////////////Change radius of all users in Tinderverse here!!//////////////////
	             tempRad = stdRad;
	             
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
		hashWorldMap tinderNet = new hashWorldMap();
		for (int i = 0; i < graph.users.size(); i++)
			tinderNet.put(graph.users.get(i), graph.links.get(i));
		graph.printNetwork();
		char keepAskingForUsers = 'l';
		long startTime,endTime;
		StoredULPair foundUser;
		Scanner sc = new Scanner(System.in);
		while (keepAskingForUsers != 'n' && keepAskingForUsers != 'N')
		{
			System.out.print("Enter the latitude of the existing user you would like to search for: ");
			double lat = sc.nextDouble();
			System.out.print("Enter the longitude of the existing user you would like to search for: ");
			double lon = sc.nextDouble();
			
			System.out.print("Search by Name or id(n/N for name): ");
		    char nOrId = sc.next().charAt(0);
		    if (nOrId == 'n' || nOrId == 'N')
		    {
		    	System.out.print("Enter First Name: ");
			    String FN = sc.next();
			    System.out.print("Enter Last Name: ");
			    String LN = sc.next();
			    startTime = System.nanoTime();
			    foundUser = tinderNet.get(FN, LN, lon, lat);
			    endTime = System.nanoTime();
			    if (foundUser == null)
			    	System.out.println("User not found in Tinderverse.");
			    else
			    {
			    	System.out.println("It took " + (endTime - startTime) + " nanoseconds to find " + FN + " " + LN + " in the Tinderverse!");
			    	foundUser.printConnections();
			    }
			    
		    }
		    else
		    {
		    	System.out.print("Enter id: ");
			    int id = sc.nextInt();
			    startTime = System.nanoTime();
			    foundUser = tinderNet.get(id, lon, lat);
			    endTime = System.nanoTime();
			    if (foundUser == null)
			    	System.out.println("User not found in Tinderverse.");
			    else
			    {
			    	System.out.println("It took " + (endTime - startTime) + " nanoseconds to find user " + id + " in the Tinderverse!");
			    	foundUser.printConnections();
			    }
		    }
		    
		    System.out.print("Search for another user?(y/n): ");
		    keepAskingForUsers = sc.next().charAt(0);
		}
		
		keepAskingForUsers = 'l';
		while (keepAskingForUsers != 'n' && keepAskingForUsers != 'N')
		{
			System.out.println("Enter Informaiton for new user to find Matches in the Tinderverse: ");
			System.out.print("Enter the latitude: ");
			double lat = sc.nextDouble();
			System.out.print("Enter the longitude: ");
			double lon = sc.nextDouble();
			System.out.print("Enter First Name: ");
		    String FN = sc.next();
		    System.out.print("Enter Last Name: ");
		    String LN = sc.next();
		    System.out.print("Enter email: ");
		    String email = sc.next();
		    System.out.print("Enter gender(Male/Female): ");
		    String gen = sc.next();
		    System.out.print("Enter interested gender(Male/Female): ");
		    String intGen = sc.next();
		    System.out.print("Enter user radius: ");
		    double rad = sc.nextDouble();
		    int id = 1001;
		    User tempUser = new User(id, lon, lat, rad, FN, LN, email, gen, intGen);
		    
		    long start2 = System.nanoTime();
		    	ArrayList<User> matches = tinderNet.returnMatches(tempUser);
		    long end2 = System.nanoTime();
		    
		    System.out.println("It took " + (end2 - start2) + " nanoseconds to find matches for  " + tempUser.toString());
		    for (int i = 0; i < matches.size(); i++)
		    {
		    	System.out.println("\tLink: [" + tempUser.toString() + ", " + matches.get(i).toString() + "]");
		    }
		    if (matches.isEmpty())
		    	System.out.println("\tNo matches were found for " + tempUser.toString());
		    
		    
		    System.out.print("Create another user?(y/n): ");
		    keepAskingForUsers = sc.next().charAt(0);
		}
		sc.close();
	}
}
