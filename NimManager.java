/*Author: Zixuan Zhang
 *Student Number: 846305
 *User Name: ZIXUANZ6
 *
 *Project Name:	project C
 *Date: 22/May/2018
 *Function: A game that asks players to remove stones. Who remove the last stone is the loser.
 * */
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class NimManager 
{
	int count;
	NimPlayer[] player = new NimPlayer[count+1];

	public boolean command(String operation,String[] content) 
	{
		boolean exit = false;
		try 
		{
			if(content[0].equals("addplayer")||content[0].equals("addaiplayer"))
			{
				addPlayer(content[0],content[1], content[2], content[3],0,0,0);	
			}
			
			else if(content[0].equals("removeplayer"))
			{
				if(operation.equals("removeplayer")) 
				{
					removePlayer(null);/*remove all*/
				}else 
				{
					removePlayer(content[1]);	/*remove one user*/
				}
			}
			
			else if(content[0].equals("editplayer"))
			{
				editPlayer(content[1], content[2], content[3]);
			}
			
			else if(content[0].equals("resetstats"))
			{
				if(operation.equals("resetstats")) 
				{
					resetStats(null);/*reset all*/
				}else 
				{
					resetStats(content[1]);	/*reset one user*/
				}
			}
			
			else if(content[0].equals("displayplayer"))
			{
				if(operation.equals("displayplayer")) 
				{
					displayPlayer(null);/*display all*/
				}else 
				{
					displayPlayer(content[1]);	/*display one user*/
				}
			}
			
			else if(content[0].equals("rankings"))
			{
				if(operation.equals("rankings")) 
				{
					ranking(null);/*ranking in descending order*/
				}else 
				{
					ranking(content[1]);	/*ranking in descending order or in ascending order.depend the 'content[1]'*/
				}
			}
			
			else if(content[0].equals("startgame"))
			{
				int currentStone = Integer.parseInt(content[1]);/*change 'String' to 'int'*/
				int upperBound = Integer.parseInt(content[2]);
				startGame(currentStone, upperBound, content[3], content[4]);
			}
			
			else if(content[0].equals("exit"))
			{
				System.out.println("");
				outputStatistics();
				exit = true;
				
			}
			
			else 
			{
				throw new Exception();
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Incorrect number of arguments supplied to command.");           
		}
		catch(Exception e)
		{
			System.out.println("'"+content[0]+"' is not a valid command.");           
		}
		return exit;
	}
	
	private void addPlayer(String humanOrAi,String username,String familyName,String givenName,int playTimes,int wonTimes,double winRate) 
	{
		boolean exist = false;/*We assume the added user is not exist */
		if(player[0]==null) /*When array is empty(avoid empty point)*/
		{
			if(humanOrAi.equals("addplayer")) 
			{
				player[count] = new NimHumanPlayer(username,familyName,givenName,playTimes,wonTimes,winRate);
				count++;
				return;
			}
			else if(humanOrAi.equals("addaiplayer")) 
			{
				player[count] = new NimAIPlayer(username,familyName,givenName,playTimes,wonTimes,winRate);
				count++;
				return;
			}
		}
		for(int i=0;i<count;i++)
		{
			if(username.equals(player[i].username))/*If the user name already exists, We change 'exist' to true */
			{
				System.out.println("The player already exists.");
				exist = true;
				return;
			}
		}
		if(exist==false) /*add user*/
		{
			if(humanOrAi.equals("addplayer")) 
			{
				player[count] = new NimHumanPlayer(username,familyName,givenName,playTimes,wonTimes,winRate);
				count++;
				return;
			}
			else if(humanOrAi.equals("addaiplayer")) 
			{
				player[count] = new NimAIPlayer(username,familyName,givenName,playTimes,wonTimes,winRate);
				count++;
				return;
			}
		}	
	}
	
	private void removePlayer(String username)
	{
		boolean exist = false;/*We assume the removed user is not exist */
		if(username==null) /*remove all users*/
		{
			System.out.println("Are you sure you want to remove all players? (y/n)");
			String comfirm = Nimsys.kb.nextLine();
			if(comfirm.equals("y")) 
			{
				for(int i=0;i<count;i++) /*make array empty*/
				{
					player[i] = null;
				}
				count=0;
				return;
			}
			else 
			{
				return;
			}
		}
		else /*remove one user*/
		{
			if(player[0]==null) 
			{
				System.out.println("The player does not exist.");
				return;
			}
			for(int i=0;i<count;i++)
			{	
				if(username.equals(player[i].username))/*find the user*/
				{
					exist = true;/*the user exist*/
					for(int j=i;j<count-1;j++)/*Remove the current element, the rest of elements move forward*/
					{
						player[j] = player[j+1];
					}
					player[count-1] = null;	/*the last one is set to null*/
					count--;
					return;
				}
			}
		}
		if(exist==false) 
		{
			System.out.println("The player does not exist.");
		}
	}
		
	private void editPlayer(String username,String newFamilyName,String newGivenName) 
	{
		boolean exist = false;
		for(int i=0;i<count;i++) 
		{
			if(username.equals(player[i].username)) 
			{
				exist = true;
				player[i].familyName = newFamilyName;
				player[i].givenName = newGivenName;
				break;
			}
		}
		if(exist==false)
		{
			System.out.println("The player does not exist.");
		}
	}
	
	private void resetStats(String username)
	{
		boolean exist = false;
		if(player[0]==null) /*When array is empty(avoid empty point)*/
		{
			System.out.println("The player does not exist.");
			return;
		}
		if(username==null) /*reset all*/
		{
			System.out.println("Are you sure you want to reset all players statistics? (y/n)");
			String comfirm = Nimsys.kb.nextLine();
			if(comfirm.equals("y")) 
			{
				for(int i=0;i<count;i++) 
				{
					player[i].setWonTimes(0);
					player[i].setPlayTimes(0);
				}
			}
			else 
			{
				return;
			}
		}
		else /*reset one user*/
		{
			for(int i=0;i<count;i++)
			{
				if(username.equals(player[i].username))/*find the user*/
				{
					exist = true;
					player[i].setWonTimes(0);
					player[i].setPlayTimes(0);
				}
				break;
			}
			if(exist==false) 
			{
				System.out.println("The player does not exist.");
			}
		}
	}
		
	private void displayPlayer(String username) 
	{
		if(player[0]==null) 
		{
			System.out.println("The player does not exist.");
			return;
		}
		if(username==null) /*displayer all*/
		{
			String[] usernameArr = new String[count];/*create an array to sort usernames to alphabetical*/
			for(int i=0;i<count;i++) 
			{
				usernameArr[i] = player[i].username;
			}	
			Arrays.sort(usernameArr);
			
			for(int i=0;i<count;i++) /*Follow the order in array 'usernameArr' to display users*/
			{
				for(int j=0;j<count;j++) 
				{
					if(usernameArr[i].equals(player[j].username)) 
					{
						System.out.println(player[j].username+","+player[j].givenName+","+player[j].familyName+","+player[j].getPlayTimes()+" games,"+player[j].getWonTimes()+" wins");
					}
				}
			}
			return;
		}
		else/*display one user*/
		{
			boolean exist = false;
			for(int i=0;i<count;i++)
			{
				if(username.equals(player[i].username))
				{
					exist = true;
					System.out.println(player[i].username+","+player[i].givenName+","+player[i].familyName+","+player[i].getPlayTimes()+" games,"+player[i].getWonTimes()+" wins");
					break;
				}
			}
			if(exist==false) 
			{
				System.out.println("The player does not exist.");
			}
		}
		
	}
	
	private void ranking(String order) 
	{
		for(int i=0;i<count;i++) /*calculate winrate*/
		{
			if(player[i].getPlayTimes()==0) 
			{
				player[i].setWinRate(0);
			}
			else
			{
				double winRate = player[i].getWonTimes()*1.0/player[i].getPlayTimes();
				player[i].setWinRate(winRate*100);
			}
		}
		
		for(int i=0;i<count-1;i++) /*sort users by win rate*/
		{
			int min = i;
			for(int j=i+1;j<count;j++) 
			{
				if(player[j].getWinRate()<player[min].getWinRate()) 
				{
					min=j;
				}
				else if(player[j].getWinRate()==player[min].getWinRate()) 
				{
					if(order==null||order.equals("desc")) 
					{
						int compare = player[j].username.compareTo(player[min].username);
						if(compare>0) 
						{
							min=j;
						}
					}
					else if(order.equals("asc")) 
					{
						int compare = player[j].username.compareTo(player[min].username);
						if(compare<0) 
						{
							min=j;
						}
					}
				}
			}
			player[count] = new NimHumanPlayer(null,null,null,0,0,0);
			player[count] = player[i];
			player[i]=player[min];
			player[min]= player[count];
			player[count]=null;
		}
		
		if(order==null||order.equals("desc"))/*ranking in descending order*/
		{	
			if(count<=10) 
			{
				for(int i=count-1;i>=0;i--) 
				{
					displayRanking(player[i]);
				}
			}else if(count>10) 
			{
				for(int i=count-1;i>=count-10;i--) 
				{
					displayRanking(player[i]);
				}
			}
		}
		else if(order.equals("asc")) /*ranking in ascending order*/
		{
			if(count>0&&count<=10) 
			{
				for(int i=0;i<count;i++)
				{
					displayRanking(player[i]);
				}
			}else if(count>10) 
			{
				for(int i=0;i<10;i++)
				{
					displayRanking(player[i]);
				}
			}
		}
	}

	private void displayRanking(NimPlayer player) 
	{
		if(player.getWinRate()<10) 
		{
			System.out.printf("%.0f%-4s",player.getWinRate(),"%");
		}
		else if(player.getWinRate()>=10&&player.getWinRate()<100)
		{
			System.out.printf("%.0f%-3s",player.getWinRate(),"%");
		}
		else if(player.getWinRate()==100)
		{
			System.out.printf("%.0f%-2s",player.getWinRate(),"%");
		}
		System.out.print("| "+new DecimalFormat("00").format(player.getPlayTimes())+" "+"games"+" |");
		System.out.println(" "+player.givenName+" "+player.familyName);
	}
	
	private void startGame(int initialStones,int upperBound,String username1,String username2) 
	{
		boolean exist = false;
		if(player[0]==null) 
		{
			System.out.println("One of the players does not exist.");
			return;
		}
		for(int i=0;i<count;i++)/*find the users to play game*/
		{
			if(username1.equals(player[i].username))
			{
				for(int j=0;j<count;j++)
				{
					if(username2.equals(player[j].username))
					{
						exist = true;
						NimGame game = new NimGame(initialStones,upperBound,player[i],player[j]);	
						game.processGame();
						return;
					}
				}
			}
		}
		if(exist==false) 
		{
			System.out.println("One of the players does not exist.");
		}
	}

	public void inputStatistics() 
	{
		try 
		{
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("players.dat"));
			try 
			{
				NimPlayer[] inputPlayer = (NimPlayer[]) inputStream.readObject();
				player=inputPlayer;
				int i=0;
				while(player[i]!=null) 
				{
					count++;
					i++;
				}
			} 
			catch (ClassNotFoundException e) 
			{		
				
			}
			inputStream.close();
		} 
		catch (FileNotFoundException e) 
		{
			
		} 
		catch (IOException e)
		{
			
		}
	}
	
	private void outputStatistics() 
	{
		try 
		{
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("players.dat"));
			outputStream.writeObject(player);
			outputStream.close();
		} 
		catch (FileNotFoundException e) 
		{
			
		} 
		catch (IOException e)
		{
			
		}
	}

}
