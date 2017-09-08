package de.paul.Genetic;

import java.util.Random;

import de.paul.TicTacGame.Board;
import de.paul.TicTacGame.State;

public class Response
{

	private State[][] boardState; //Board setup that is responded to
	private int[] responseMove; //With which move to respond
	
	public Response(Board b){
		this.boardState = new State[3][3];
		for(int x = 0; x < 3; x++){
			for(int y = 0; y < 3; y++){
				boardState[x][y] = b.getState(x, y);
			}
		}
		Random r = new Random();
		int[] randomTile = new int[2];
		while(true){
			randomTile[0] = r.nextInt(3);
			randomTile[1] = r.nextInt(3);

			if(b.isEmpty(randomTile[0], randomTile[1])){
				this.responseMove = randomTile;
				return;
			}
		}
	}
	
	public Response(State[][] boardState){
		this.boardState = boardState;
		Random r = new Random();
		int[] randomTile = new int[2];
		while(true){
			randomTile[0] = r.nextInt(3);
			randomTile[1] = r.nextInt(3);

			if(boardState[randomTile[0]][randomTile[1]]==State.Blank){
				this.responseMove = randomTile;
				return;
			}
		}
	}
	
	
	public Response(Board b, int x, int y){
		this.boardState = b.getStateArray();
		this.responseMove = new int[2];
		this.responseMove[0] = x;
		this.responseMove[1] = y;
	}
	
	
	public State[][] getBoardState()
	{
		return boardState;
	}
	public void setBoardState(State[][] boardState)
	{
		this.boardState = boardState;
	}
	public int[] getResponseMove()
	{
		return responseMove;
	}
	public void setResponseMove(int[] responseMove)
	{
		this.responseMove = responseMove;
	}
	

}
