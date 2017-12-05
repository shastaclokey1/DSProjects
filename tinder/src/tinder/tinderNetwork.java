package tinder;

import java.util.ArrayList;

public class tinderNetwork 
{
	// for each Vertex
	// there is a list of edges
	
	// Definition of Graph is G = (U, L)
	// so we need to keep track of a list of Users and a list of Links
	
	// for each User there is a list of links(array list of links which are each individually linked lists)
	ArrayList<User> users;
	ArrayList<Link> links; 
	
	public tinderNetwork()
	{
		this.users = new ArrayList<User>();
		this.links = new ArrayList<Link>();
	}
	
	// add vertex into the graph if it doesn't already exist, then add necessary edges to the graph
	public void addUser(User v1)
	{
		// if v1 already exists in the graph don't add else, add to the graph
		User temp = this.returnVertex(v1);
		if (temp != null)
			System.out.println(v1.getFirstName() + " " + v1.getLastName() + " already has the following profile: " + temp.toString());
		else
		{
			users.add(v1);
			Link initLoopBackLink = new Link(v1,v1);
			links.add(initLoopBackLink);
		}
	}
	
	public void updateLinks()
	{
		for (int i = 0; i < users.size(); i++)
			for (int j = 0; j < users.size(); j++)
			{
				if (i != j)
				{
					this.addLink(users.get(i), users.get(j), links.get(i));
				}
			}
	}
	
	public User returnVertex(User v1)
	{
		User rtn = null;
		for (int i = 0; i < users.size(); i++)
			if (v1.id == users.get(i).id)
			{
				rtn = users.get(i);
				break;
			}
		return rtn;
	}
	
	public void addLink(User v1, User v2, Link initialLink)
	{
		final double toKM = 6371;
		final double kmToMiles = 0.621371;
		double lat1 = v1.getLatitude(), lat2 = v2.getLatitude(), long1 = v1.getLongitude(), long2 = v2.getLongitude();
		double latRad1 = Math.toRadians(lat1), latRad2 = Math.toRadians(lat2);
		double deltaLat = Math.toRadians(lat1 - lat2), deltaLong = Math.toRadians(long1 - long2);
		double a = Math.sin(deltaLat/2) * Math.sin(deltaLat/2) + Math.cos(latRad1) * Math.cos(latRad2) *Math.sin(deltaLong/2) * Math.sin(deltaLong/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double dist = toKM * c * kmToMiles;
		if (dist < v1.getRadius() && dist < v2.getRadius() && v1.interestedGender.equals(v2.gender) && v2.interestedGender.equals(v1.gender))
		{
			//create temporary link and see if it already exists for that user
			Link tempLink = new Link(v1,v2);
			Link currentLink = initialLink;
			boolean linkExists = false;
			if (currentLink.contains(v1.id) && currentLink.contains(v2.id))
				linkExists = true;
			while (linkExists == false && currentLink.nextLink != null)
			{
				if (currentLink.contains(v1.id) && currentLink.contains(v2.id))
					linkExists = true;
				
				currentLink = currentLink.nextLink;
			}
			//if it doesn't already exist, add it
			if (linkExists == false)
			{
				currentLink.nextLink = tempLink;
			}
		}
	}
	
	public String toString()
	{
		String result ="";
		result += "Current Tinder Proximity Network: ";
		for (int i = 0; i < this.users.size(); i++)
		{
			result += "\n";
			result += this.users.get(i).toString();
			Link currentLink = this.links.get(i);
			currentLink = currentLink.nextLink;
			while (currentLink != null)
			{
				result += "\n\t";
				result += currentLink.toString();
				currentLink = currentLink.nextLink;
			}
		}
		return result;
	}
	
	public void printNetwork()
	{
		System.out.println("Current Tinder Proximity Network: ");
		for (int i = 0; i < this.users.size(); i++)
		{
			System.out.println(this.users.get(i).toString());
			Link currentLink = this.links.get(i);
			currentLink = currentLink.nextLink;
			while (currentLink != null)
			{
				System.out.println("\t" + currentLink.toString());
				currentLink = currentLink.nextLink;
			}
		}
	}

	
}
