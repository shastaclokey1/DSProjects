package tinder;

public class StoredULPair 
{
	User uMain;
	Link lMain;
	public StoredULPair(User U, Link L) 
	{
		this.uMain = U;
		this.lMain = L;
	}
	public User getUser() 
	{
		return this.uMain;
	}
	public Link getLink() 
	{
		return this.lMain;
	}
	
	public void printConnections()
	{
		System.out.println(uMain.toString());
		Link currentLink = lMain;
		currentLink = currentLink.nextLink;
		while (currentLink != null)
		{
			System.out.println("\t" + currentLink.toString());
			currentLink = currentLink.nextLink;
		}
	}
}
