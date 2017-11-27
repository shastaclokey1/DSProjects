package tinder;

public class User 
{
	// Information about each user. id will be used as the main identifier
	long id;
	double longitude, latitude, radius;
	String firstName, lastName, email, gender, interestedGender;
	
	boolean visited;

	// construct a user with all of its information
	public User (long id, double longitude, double latitude, double radius, String fN, String lN, String email, String gender, String inGender)
	{
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.radius = radius;
		this.firstName = fN;
		this.lastName = lN;
		this.email = email;
	    this.gender = gender;
	    this.interestedGender = inGender;
		visited = false;
	}
	
	// getters and setters for all the information fields
	public long getid()
	{
		return this.id;
	}

	public String getInterestedGender()
	{
		return interestedGender;
	}
	
	public String getGender()
	{
		return gender;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public double getRadius()
	{
		return radius;
	}
	
	public double getLongitude()
	{
		return longitude;
	}
	
	public double getLatitude()
	{
		return latitude;
	}
	
	public void setInterestedGender(String intGen)
	{
		this.interestedGender = intGen;
	}
	
	public void setGender(String gen)
	{
		this.gender = gen;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public void setLastName(String lN)
	{
		this.lastName = lN;
	}
	
	public void setFirstName(String fN)
	{
		this.firstName = fN;
	}
	
	public void setRadius(double rad)
	{
		this.radius = rad;
	}
	
	public void setLocation(double longitude, double latitude)
	{
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public boolean hasVisited()
	{
		return visited;
	}
	
	public String toString()
	{
		String result = "";
		result += "User: [" + id + "] \"" + firstName + " " + lastName + "\"";
		return result;
	}

}
