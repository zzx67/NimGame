/*Author: Zixuan Zhang
 *Student Number: 846305
 *User Name: ZIXUANZ6
 *
 *Project Name:	project C
 *Date: 22/May/2018
 *Function: A game that asks players to remove stones. Who remove the last stone is the loser.
 * */
import java.io.Serializable;
public abstract class NimPlayer implements Serializable
{
	String username;
	String givenName;
	String familyName;
	private int playTimes;
	private int wonTimes;
	private double winRate;
	
	
	public NimPlayer(String username,String familyName,String givenName,int playTimes,int wonTimes,double winRate)
	{
		this.username = username;
		this.givenName = givenName;
		this.familyName = familyName;
		this.playTimes=playTimes;
		this.wonTimes=wonTimes;
		this.setWinRate(winRate);
	}
	
	public abstract int removeStone(int currentStone,int upperBound);

	public int getPlayTimes() {
		return playTimes;
	}
	
	public int getWonTimes() {
		return wonTimes;
	}
	
	public double getWinRate() {
		return winRate;
	}

	public void setPlayTimes(int playTimes) {
		this.playTimes = playTimes;
	}

	public void setWonTimes(int wonTimes) {
		this.wonTimes = wonTimes;
	}

	public void setWinRate(double winRate) {
		this.winRate = winRate;
	}
}