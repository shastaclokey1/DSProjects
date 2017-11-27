package tinder;

public class Link 
{
	// Two users per link
	User v1;
	User v2; 
	Link nextLink;
	
	public Link(User v1, User v2)
	{
		this.v1 = v1;
		this.v2 = v2;
		nextLink = null;
	}
	
	public User getNeighbor(User current)
	{
		if (!current.equals(v1) || !current.equals(v2))
			return null;
		else if (current.equals(v1))
			return v2;
		else
			return v1;
	}
	
	// get the first User of the Link
	public User getFirst()
	{
		return this.v1;
	}
	
	// get the second User of the Link
	public User getSecond()
	{
		return this.v2;
	}
	
	//can identify user within the link either by id number or by email
	public boolean contains(long idTarget)
	{
		if (v1.id == idTarget || v2.id == idTarget)
			return true;
		else
			return false;
	}
	
	public boolean contains(String email)
	{
		if (v1.email == email || v2.email == email)
			return true;
		else
			return false;
	}
	
	public String toString()
	{
		String result ="";
		result += "Link: [" + getFirst().toString() + ", " + getSecond().toString() + "]";
		return result;
	}
}
