package de.paul.TicTacGame;

public enum GameState
{
	Running,
	Tie,
	Win;
	
	public String toString(){
		if(this==Running)return "Game still running";
		if(this==Tie)return "TIE!";
		if(this==Win)return "There is a winner";
		return null;
	}
}

