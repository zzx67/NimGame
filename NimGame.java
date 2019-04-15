/*Author: Zixuan Zhang
 *Student Number: 846305
 *User Name: ZIXUANZ6
 *
 *Project Name:	project C
 *Date: 22/May/2018
 *Function: A game that asks players to remove stones. Who remove the last stone is the loser.
 * */
public class NimGame 
{
	int currentStone;
	int upperBound;
	NimPlayer player1;
	NimPlayer player2;
	
	
	public NimGame(int currentStone,int upperBound,NimPlayer player1,NimPlayer player2)
	{
		this.currentStone = currentStone;
		this.upperBound = upperBound;
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public void processGame()
	{	
		System.out.println("");
		System.out.println("Initial stone count: "+currentStone);
		System.out.println("Maximum stone removal: "+upperBound);
		System.out.println("Player 1: "+player1.givenName+" "+player1.familyName);
		System.out.println("Player 2: "+player2.givenName+" "+player2.familyName);
		
		boolean p1 = true;/*Determine player order. p1 means player1*/
		while(currentStone>0) 
			{
				if(p1) /*player1's turn*/
				{
					int removeStone = 0;
					do
					{
						removeStone = player1.removeStone(currentStone,upperBound);
					}while(removeStone==-1);
					currentStone = currentStone - removeStone;
				}else /*player2's turn*/
				{
					int removeStone = 0;
					do
					{
						removeStone = player2.removeStone(currentStone,upperBound);
					}while(removeStone==-1);
					currentStone = currentStone - removeStone;
				}
				p1 = !p1;/*switch players*/
			}
		System.out.println("");
		System.out.println("Game Over");
		player1.setPlayTimes(player1.getPlayTimes() + 1);
		player2.setPlayTimes(player2.getPlayTimes() + 1);
		if(p1) /*Judging the outcome*/
		{
			System.out.println(player1.givenName+" "+player1.familyName+" wins!");
			player1.setWonTimes(player1.getWonTimes() + 1);
		}else 
		{
			System.out.println(player2.givenName+" "+player2.familyName+" wins!");
			player2.setWonTimes(player2.getWonTimes() + 1);
		}
	}
}