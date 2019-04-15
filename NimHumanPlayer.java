/*Author: Zixuan Zhang
 *Student Number: 846305
 *User Name: ZIXUANZ6
 *
 *Project Name:	project C
 *Date: 22/May/2018
 *Function: A game that asks players to remove stones. Who remove the last stone is the loser.
 * */
public class NimHumanPlayer extends NimPlayer
{
	public NimHumanPlayer(String username, String familyName, String givenName, int playTimes, int wonTimes,double winRate) 
	{
		super(username, familyName, givenName, playTimes, wonTimes, winRate);
	}
	
	public int removeStone(int currentStone,int upperBound) 
	{
		System.out.println("");
		System.out.print(currentStone+" stones left:");
		for(int i=0;i<currentStone;i++) 
		{
			System.out.print(" *");
		}
		System.out.println("");
		
		System.out.println(givenName +"'s turn - remove how many?");
		
		int removeStone = Nimsys.kb.nextInt();
		String junk= Nimsys.kb.nextLine();/*deal with '/n'*/
		if(removeStone == currentStone) /*avoid to print '0 stones left'*/
		{
			return removeStone;
		}
		try 
		{
			if(removeStone>upperBound||removeStone<1||removeStone>currentStone)
			{
				throw new Exception();
			}
		}
		catch(Exception e) 
		{
			if(currentStone<=upperBound)
			{
				System.out.println("");
				System.out.println("Invalid move. You must remove between 1 and "+currentStone+" stones.");
				return -1;
			}
			else if(currentStone>upperBound)
			{
				System.out.println("");
				System.out.println("Invalid move. You must remove between 1 and "+upperBound+" stones.");
				return -1;
			}
		}
		return removeStone;
	}
}