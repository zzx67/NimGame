import java.util.Random;
/*
	NimAIPlayer.java
	
	This class is provided as a skeleton code for the tasks of 
	Sections 2.4, 2.5 and 2.6 in Project C. Add code (do NOT delete any) to it
	to finish the tasks. 
*/

public class NimAIPlayer extends NimPlayer implements Testable 
{
	// you may further extend a class or implement an interface
	// to accomplish the tasks.	

	public NimAIPlayer(String username,String familyName,String givenName,int playTimes,int wonTimes,double winRate) 
	{
		super(username, familyName, givenName, playTimes, wonTimes, winRate);
	}
	
	public String advancedMove(boolean[] available, String lastMove) 
	{
		// the implementation of the victory
		// guaranteed strategy designed by you
		String move = "";
		
		return move;
	}

	public int removeStone(int currentStone, int upperBound) 
	{
		System.out.println("");
		System.out.print(currentStone+" stones left:");
		for(int i=0;i<currentStone;i++) 
		{
			System.out.print(" *");
		}
		System.out.println("");
		
		System.out.println(givenName +"'s turn - remove how many?");
		int gameState = (currentStone-1)%(upperBound+1);
		if(gameState!=0) 
		{
			int rate = 0;
			while((currentStone-1)-rate*(upperBound+1)>upperBound) 
			{	
				rate++;
			}
			return (currentStone-1)-rate*(upperBound+1);
		}
		else
		{
			Random rand = new Random();
			return rand.nextInt(upperBound)+1;
		}
	}
}
