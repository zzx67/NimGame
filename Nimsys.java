/*Author: Zixuan Zhang
 *Student Number: 846305
 *User Name: ZIXUANZ6
 *
 *Project Name:	project C
 *Date: 22/May/2018
 *Function: A game that asks players to remove stones. Who remove the last stone is the loser.
 * */
import java.util.Scanner;
import java.util.regex.Pattern;
public class Nimsys 
{
	static Scanner kb = new Scanner(System.in);
	public static void main(String args[]) throws Exception 
	{
		System.out.println("Welcome to Nim");
		NimManager pm = new NimManager();
		pm.inputStatistics();

		boolean exit = false;
		while(exit==false)
		{
			System.out.println("");
			System.out.print("$");
			String operation = kb.nextLine();
			Pattern pattern = Pattern.compile("[, ]");
			String[] content = pattern.split(operation);
			exit=pm.command(operation, content);
		}
		System.exit(0);
	}
}