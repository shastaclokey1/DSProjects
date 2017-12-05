package tinder;
import java.util.ArrayList;

public class hashWorldMap
{
	ArrayList<ArrayList<StoredULPair>> array;
	public hashWorldMap() 
	{
		array = new ArrayList<>();
		for (int i = 0; i < 16; i++) //16 pre-designated world-zones
		{
			array.add(new ArrayList<>());
		}
	}
	
	public void put(User U, Link L) 
	{
		StoredULPair curULPair = new StoredULPair(U,L);
		ArrayList<StoredULPair> innerArray = array.get(hashUserLocation(curULPair.getUser().getLatitude(),curULPair.getUser().getLongitude()));
		innerArray.add(curULPair);
	}
	
	public StoredULPair get(int id, double lon, double lat) 
	{
		int hashedValue = hashUserLocation(lat,lon);
		ArrayList<StoredULPair> innerArray = array.get(hashedValue);
		for (int i = 0; i < innerArray.size(); i++) 
		{
			StoredULPair curULPair = innerArray.get(i);
			if (curULPair.getUser().getid() == id) 
			{
				return curULPair;
			}
		}
		return null;
	}
	
	public StoredULPair get(String FN, String LN, double lon, double lat) 
	{
		int hashedValue = hashUserLocation(lat,lon);
		ArrayList<StoredULPair> innerArray = array.get(hashedValue);
		for (int i = 0; i < innerArray.size(); i++) 
		{
			StoredULPair curULPair = innerArray.get(i);
			if ((curULPair.getUser().getFirstName().equals(FN)) && (curULPair.getUser().getLastName().equals(LN))) 
			{
				return curULPair;
			}
		}
		return null;
	}

	public static int hashUserLocation(double lat, double lon)
	{
		//Separating the world into 4 main quadrants
			//Each quadrant is broken into sections along latitude boundary lines
		if (lat >= 0 && lon <= -30)
		{
			if (lat >= 60)//far North
			{
				return 0;
			}
			else if (lat >= 45 && lat < 60)//Canada + UK
			{
				return 1;
			}
			else if (lat >= 30 && lat < 45)//US + Spain + Maroco
			{
				return 2;
			}
			else if (lat >= 15 && lat < 30)//Latin America + West Africa
			{
				return 3;
			}
			else//Central America + South West Africa
			{
				return 4;
			}
		}
		else if (lat < 0 && lon < -30)
		{
			if (lat <= -30)//Far South
			{
				return 7;
			}
			else if (lat <= -15 && lat > -30)//Mid South America
			{
				return 6;
			}
			else//Northern South America
			{
				return 5;
			}
		}
		else if (lat > 0 && lon > -30)
		{
			if (lat >= 60)//Far North
			{
				return 8;
			}
			else if (lat >= 45 && lat < 60)//Mid Europe-Asia
			{
				return 9;
			}
			else if (lat >= 30 && lat < 45)//Mediterranean + East Asia
			{
				return 10;
			}
			else if (lat >= 15 && lat < 30)//North Africa + Middle East + India
			{
				return 11;
			}
			else//Central Africa + North Indies
			{
				return 12;
			}
		}
		else
		{
			if (lat <= -30)//Far South
			{
				return 15;
			}
			else if (lat <= -15 && lat > -30)//Really South Africa + Australia
			{
				return 14;
			}
			else//Kind of South Africa + Indonesia
			{
				return 13;
			}
		}
	}
	
	public ArrayList<User> returnMatches(User targetUser)
	{
		/*
		final double toKM = 6371;
		final double kmToMiles = 0.621371;
		double lat1 = v1.getLatitude(), lat2 = v2.getLatitude(), long1 = v1.getLongitude(), long2 = v2.getLongitude();
		double latRad1 = Math.toRadians(lat1), latRad2 = Math.toRadians(lat2);
		double deltaLat = Math.toRadians(lat1 - lat2), deltaLong = Math.toRadians(long1 - long2);
		double a = Math.sin(deltaLat/2) * Math.sin(deltaLat/2) + Math.cos(latRad1) * Math.cos(latRad2) *Math.sin(deltaLong/2) * Math.sin(deltaLong/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double dist = toKM * c * kmToMiles;
		*/
		final double toKM = 6371;
		final double kmToMiles = 0.621371;
		
		
		ArrayList<User> rtn = new ArrayList<User>();
		System.out.println("Printing matches for " + targetUser.toString() + ":");
		double lat1 = targetUser.getLatitude(), long1 = targetUser.getLongitude();
		double latRad1 = Math.toRadians(lat1);
		int hashValue = hashUserLocation(lat1,long1);
		ArrayList<StoredULPair> targetRegion = array.get(hashValue);
		for (int i = 0; i < targetRegion.size(); i++)
		{
			double lat2 = targetRegion.get(i).uMain.getLatitude(), long2 = targetRegion.get(i).uMain.getLongitude();
			double latRad2 = Math.toRadians(lat2);
			double deltaLat = Math.toRadians(lat1 - lat2), deltaLong = Math.toRadians(long1 - long2);
			double a = Math.sin(deltaLat/2) * Math.sin(deltaLat/2) + Math.cos(latRad1) * Math.cos(latRad2) *Math.sin(deltaLong/2) * Math.sin(deltaLong/2);
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
			double dist = toKM * c * kmToMiles;
			if (dist < targetUser.getRadius() && dist < targetRegion.get(i).uMain.getRadius() && targetUser.interestedGender.equals(targetRegion.get(i).uMain.gender) && targetRegion.get(i).uMain.interestedGender.equals(targetUser.gender))
			{
				rtn.add(targetRegion.get(i).uMain);
				//System.out.println("\tLink: [" + targetUser.toString() + ", " + targetRegion.get(i).uMain.toString() + "]");
			}
		}
		if (hashValue != 0)
		{
			targetRegion = array.get(hashValue - 1);
			for (int i = 0; i < targetRegion.size(); i++)
			{
				double lat2 = targetRegion.get(i).uMain.getLatitude(), long2 = targetRegion.get(i).uMain.getLongitude();
				double deltaLat = lat1 - lat2, deltaLong = long1 - long2;
				double dist = Math.sqrt(Math.pow(deltaLat, 2) + Math.pow(deltaLong, 2));
				if (dist < targetUser.getRadius() && dist < targetRegion.get(i).uMain.getRadius() && targetUser.interestedGender.equals(targetRegion.get(i).uMain.gender) && targetRegion.get(i).uMain.interestedGender.equals(targetUser.gender))
				{
					rtn.add(targetRegion.get(i).uMain);
					//System.out.println("\tLink: [" + targetUser.toString() + ", " + targetRegion.get(i).uMain.toString() + "]");
				}
			}
		}
		if (hashValue < this.array.size()-1)
		{
			targetRegion = array.get(hashValue + 1);
			for (int i = 0; i > targetRegion.size(); i++)
			{
				double lat2 = targetRegion.get(i).uMain.getLatitude(), long2 = targetRegion.get(i).uMain.getLongitude();
				double deltaLat = lat1 - lat2, deltaLong = long1 - long2;
				double dist = Math.sqrt(Math.pow(deltaLat, 2) + Math.pow(deltaLong, 2));
				if (dist < targetUser.getRadius() && dist < targetRegion.get(i).uMain.getRadius() && targetUser.interestedGender.equals(targetRegion.get(i).uMain.gender) && targetRegion.get(i).uMain.interestedGender.equals(targetUser.gender))
				{
					rtn.add(targetRegion.get(i).uMain);
					//System.out.println("\tLink: [" + targetUser.toString() + ", " + targetRegion.get(i).uMain.toString() + "]");
				}
			}
		}
		return rtn;
	}
}