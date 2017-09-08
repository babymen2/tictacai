package de.paul.Player;

import de.paul.TicTacGame.State;

public class Player
{
	
	private State type;
	private int wins, games;
	
	public Player(State type){
		this.setType(type);
		this.wins = 0;
		this.games = 0;
	}

	public State getType()
	{
		return type;
	}

	public void setType(State type)
	{
		this.type = type;
	}

	public int getWins()
	{
		return wins;
	}

	public void setWins(int wins)
	{
		this.wins = wins;
	}

	public int getGames()
	{
		return games;
	}

	public void setGames(int games)
	{
		this.games = games;
	}
	
}
