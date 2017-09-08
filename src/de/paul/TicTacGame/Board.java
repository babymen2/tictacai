package de.paul.TicTacGame;

import java.util.ArrayList;

public class Board
{
	
	
	private State[][] board;
	private int size;
	private GameState currentState;
	private State winner;
	
	private int moveCount;
	public Board(int size){
		this.size = size;
		this.board = new State[size][size];
		this.currentState = GameState.Running;
		moveCount = 0;
		for(int x=0; x<size; x++){
			for(int y=0; y<size; y++){
				board[x][y]=State.Blank;
			}
		}
	}
	
	
	
	public State getState(int x, int y){
		return board[x][y];
	}
	
	public boolean isEmpty(int x, int y){
		return board[x][y]==State.Blank;
	}
	
	public ArrayList<int[]> getPossibleMoves(){
		ArrayList<int[]> possibleMoves = new ArrayList<int[]>();
		for(int x=0; x<size; x++){
			for(int y=0; y<size; y++){
				if(isEmpty(x,y))possibleMoves.add(new int[]{x,y});
			}
		}
		return possibleMoves;
	}
	
	public Board copy(){
		Board copied = new Board(this.size);
		State[][] newS = new State[size][size];
		for(int x=0; x<size; x++){
			for(int y=0; y<size; y++){
				if(!isEmpty(x,y)){
					copied.move(this.getState(x, y), x, y);
				}
			}
		}
		return copied;
	}
	
	
	public void move(State p, int x, int y){
		if(isEmpty(x, y) && currentState == GameState.Running){
			board[x][y] = p;
			moveCount++;
		}
		
		//Check for Win
		//Check Column
		for(int yCheck = 0; yCheck < size; yCheck++){
			if(getState(x, yCheck)!=p){
				break;
			}
			if(yCheck == size-1){
				winner = p;
				currentState = GameState.Win;
				return;
			}		
		}
		//Check Row
		for(int xCheck = 0; xCheck < size; xCheck++){
			if(getState(xCheck, y)!=p){
				break;
			}
			if(xCheck == size-1){
				winner = p;
				currentState = GameState.Win;
				return;
			}
		}
		
		//Diagonal Check
		for(int digCheck = 0; digCheck < size; digCheck++){
			if(getState(digCheck, digCheck)!=p){
				break;
			}
			if(digCheck == size-1){
				winner = p;
				currentState = GameState.Win;
				return;
			}
		}
		
		//Anti Diagonal Check
		int counter = 0;
		for(int digCheck = size-1; digCheck >= 0; digCheck--){
			
			if(getState(digCheck, counter)!=p){
				break;
			}
			if(digCheck == 0){
				winner = p;
				currentState = GameState.Win;
				return;
			}
			counter++;
		}
		
		if(moveCount == Math.pow(size, 2)){
			currentState = GameState.Tie;
			winner = null;
			return;
		}
		
		currentState = GameState.Running;
		return;
	}
	
	public State getNextPlayer(){
		int countX = 0, countO = 0;
		for(int x=0; x<size; x++){
			for(int y=0; y<size; y++){
				if(getState(x,y)==State.X)countX++;
				if(getState(x,y)==State.O)countO++;
			}
		}
		if(countX>countO)return State.O;
		return State.X;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public GameState getCurrenState()
	{
		return currentState;
	}

	public void setCurrenState(GameState currentState)
	{
		this.currentState = currentState;
	}
	
	public State[][] getStateArray(){
		return board;
	}


	public State getWinner()
	{
		return winner;
	}

	public void setWinner(State winner)
	{
		this.winner = winner;
	}
}
