package googleGearsProject;

public class gearSizeCalculator 
{

	public static int[] answer(int[] pegs) 
    {
        // Your code goes here.
        
        //I, being a curious henchman, notice that the radius of each
        //gear needed in the chain is equal to the distance to the
        //next gear minus the radius of the next gear. 
        //After working with a couple gears of different sizes, I also
        //notice that if a gear is half as large as another and they
        //are interconnected, the smaller gear revolves twice as fast.
        //I put these two facts together to define the size of the
        //first gear by the alternating sum of the distance from one
        //gear to the next all divided by a half if there are an odd
        //number of gears and three halves if there are an even
        //number of gears.
        //Man am I glad I paid attention as a lowly minion when 
        //my evil organization professor was talking about summations 
        //and implicit equations.
        //I have to say, this is way too fun! hahaha
        
		/////////////////calculate num and denom then simplify the fraction////////
		int[] rtn = calcAndSimp(pegs);
        
		///////////////////Eliminate configurations which do not bear a possible solution//////
		
		//if the first gear is negative then the system obviously doesn't have a solution
		//this is the base case of the error check below
		if ((double)rtn[0]/rtn[1] < 1.0)
		{
			rtn[0] = -1;
			rtn[1] = -1;
			return rtn;
		}
		
        //calculate the size of each gear which needs to be fit on its corresponding peg
		//if any of the gears are negative, recognize that the system does not have a solution
		double[] gearSizes = new double[pegs.length];
		gearSizes[0] = rtn[0]/rtn[1];
		for (int i = 1; i < pegs.length; i++)
		{
			gearSizes[i] = pegs[i] - pegs[i-1] - gearSizes[i-1];
			if (gearSizes[i] < 1)
			{
				rtn[0] = -1;
	        	rtn[1] = -1;
	        	return rtn;
			}
		}
		printArray(gearSizes);
        
        
        return rtn;

    } 
	
	public static int[] calcAndSimp(int[] pegs)
	{
		////////////////Calculation////////////////////////////
		//takes care of pegs 0 and last
		int lastPegIndi = pegs.length - 1;
		int numerator = -pegs[0] + ((int)Math.pow(-1, pegs.length)*pegs[lastPegIndi]);
		int denominator = 2 + (int)Math.pow(-1, pegs.length);
		//takes care of the rest of the pegs
		for (int i = 1; i < lastPegIndi; i++)
		    numerator = numerator + 2*((int)Math.pow(-1,i+1)*pegs[i]);
		
		numerator = numerator * 2;
		
		
		////////////////////Simplification//////////////////////////
		int gcdOfNumDenom = gcd(numerator, denominator);
		numerator = numerator/gcdOfNumDenom;
		denominator = denominator/gcdOfNumDenom;
		
		///////////////////Preparing Return Values///////////////////
		int[] rtn = {numerator,denominator};
		return rtn;
		
	}
    
    public static int gcd(int a, int b) 
    {
        while (b != 0) 
        {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }  
    
    public static void printArray(int[] target)
    {
    	for (int i = 0; i < target.length; i++)
    	{
    		System.out.print(target[i]);
    		System.out.print(",");
    	}
    	System.out.println();
    }
    
    public static void printArray(double[] target)
    {
    	for (int i = 0; i < target.length; i++)
    	{
    		System.out.print(target[i]);
    		System.out.print(",");
    	}
    	System.out.println();
    }
	
	public static void main(String[] args) 
	{
		int[] ans = new int[2];
		int[] test = {4,30,50,70};
		ans = answer(test);
		System.out.println(ans[0]);
		System.out.println(ans[1]);
	}
	
}
