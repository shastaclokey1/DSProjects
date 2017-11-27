package googlePlatesChallenge;

public class platesChallenge 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		int[] list = {3,1,4,1,1,5,9};
		int ans = answer(list);
		System.out.println(ans);
		
	}
    
    public static int answer(int[] l) 
    { 
    	int rtn = 0;
        // Your code goes here.
        //first sort the list using the sweet sweet algorithm known as merge sort
        //dam, wish I was using c++ so I could use pass by reference... but it's all good here we go!
        l = selectionSort(l);
        
        
        
        //now we have a sorted list of ints from greatest to least
        //
        //woo! those prison guards
        //wont know what hit them!
        int[] ans = gettingTrickyWithThrees(l);
        
        for (int i = 0; i < ans.length; i++)
        {
        	rtn = (rtn + ((int)Math.pow(10, i) * ans[ans.length - 1 - i]));
        }
        
        return rtn;
        

    } 
    
    static int[] gettingTrickyWithThrees(int[] l)
    {
    	if (l.length == 1)
    	{
    		if (l[0] % 3 == 0)
    			return l;
    		else
    			return null;
    	}
    	
    	int sumElts = 0;
        int remainder = 0;
        int ans = 0;
        int[] oneSmaller = new int[l.length - 1];
        int[] twoSmaller = new int[l.length - 2];
        
        for (int i = 0; i < l.length; i++)
        {
        	sumElts = sumElts + l[i];
        }
        
        remainder = sumElts % 3;
        if (remainder == 0)
        {
        	return l;
        }
        else if (remainder == 1)
        {
        	boolean foundSingular = false;
        	boolean foundModSumOne = false;
        	boolean foundModSumTwo = false;
        	int indexOfFoundSingular = l.length - 1, indexOfModSumOne = l.length - 1, indexOfModSumTwo = l.length - 2;

        	for (int i = 0; i < l.length; i++)
        	{
        		if (l[l.length - 1 - i] % 3 == 1 && foundSingular == false)
        		{
        			foundSingular = true;
        			indexOfFoundSingular = l.length - 1 - i;
        			break;
        		}
        		if (l[l.length - 1 - i] % 3 == 2 && foundModSumTwo == false)
        		{
        			if (foundModSumOne = false)
        			{
        				foundModSumOne = true;
        				indexOfModSumOne = l.length - 1 - i;
        			}
        			else
        			{
        				if (l.length - 1 - i != indexOfModSumOne)
        				{
	        				foundModSumTwo = true;
	        				indexOfModSumTwo = l.length - 1 - i;
        				}
        			}
        		}
        	}
        	
        	int j = 0;
        	if (foundSingular == true)
        	{
        		for (int i = 0; i < l.length; i++)
	        	{
	        		if (i == indexOfFoundSingular)
	        		{
	        			//do nothing
	        		}
	        		else
	        		{
	        			oneSmaller[j] = l[i];
	        			j++;
	        		}
	        	}
        		
            	return oneSmaller;
        		
        	}
        	else
        	{
        		for (int i = 0; i < l.length; i++)
	        	{
        			if (i == indexOfModSumTwo || i == indexOfModSumOne)
	        		{
	        			//do nothing
	        		}
	        		else
	        		{
	        			twoSmaller[j] = l[i];
	        			j++;
	        		}
	        	}
        		
            	return twoSmaller;
        		
        	}
        	
        	
        }
        else
        {
        	boolean foundSingular = false;
        	boolean foundModSumOne = false;
        	boolean foundModSumTwo = false;
        	int indexOfFoundSingular = l.length - 1, indexOfModSumOne = l.length - 1, indexOfModSumTwo = l.length - 2;

        	for (int i = 0; i < l.length; i++)
        	{
        		if (l[l.length - 1 - i] % 3 == 2 && foundSingular == false)
        		{
        			foundSingular = true;
        			indexOfFoundSingular = l.length - 1 - i;
        			break;
        		}
        		if (l[l.length - 1 - i] % 3 == 1 && foundModSumTwo == false)
        		{
        			if (foundModSumOne = false)
        			{
        				foundModSumOne = true;
        				indexOfModSumOne = l.length - 1 - i;
        			}
        			else
        			{
        				if (l.length - 1 - i != indexOfModSumOne)
        				{
	        				foundModSumTwo = true;
	        				indexOfModSumTwo = l.length - 1 - i;
        				}
        			}
        		}
        	}
        	
        	int j = 0;
        	if (foundSingular == true)
        	{
        		for (int i = 0; i < l.length; i++)
	        	{
	        		if (i == indexOfFoundSingular)
	        		{
	        			//do nothing
	        		}
	        		else
	        		{
	        			oneSmaller[j] = l[i];
	        			j++;
	        		}
	        	}
        		
            	return oneSmaller;
        		
        	}
        	else
        	{
        		for (int i = 0; i < l.length; i++)
	        	{
        			if (i == indexOfModSumTwo || i == indexOfModSumOne)
	        		{
	        			//do nothing
	        		}
	        		else
	        		{
	        			twoSmaller[j] = l[i];
	        			j++;
	        		}
	        	}
        		
            	return twoSmaller;
        		
        	}
        }
    }
    
    //sorts an array into greatest to least format
    //I'm assuming that I wont have to be dealing with more than a couple dozen plates at a time
    //so selection sort works just fine and is the perfect light weight sorting algorithm for my
    //Sneaky, devious  requirements
	public static int[] selectionSort(int[] arr)
	{
		int indexOfMax = 0;
		for (int i = 0; i < arr.length; i++)
		{
			indexOfMax = i;
			for (int j = i+1; j < arr.length; j++)
				if (arr[j] > arr[indexOfMax])
					indexOfMax = j;
			if (indexOfMax != i)
			{
		        int temp = arr[i];
		        arr[i] = arr[indexOfMax];
		        arr[indexOfMax] = temp;
			}
		}
		return arr;
	}

}

