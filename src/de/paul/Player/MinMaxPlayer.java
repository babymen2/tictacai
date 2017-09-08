package de.paul.Player;

import java.util.ArrayList;
import java.util.Arrays;

import de.paul.Genetic.Response;
import de.paul.TicTacGame.Board;
import de.paul.TicTacGame.GameState;
import de.paul.TicTacGame.State;

public class MinMaxPlayer extends Player
{

	public MinMaxPlayer(State type)
	{
		super(type);
	}

	public int score(Board b)
	{
		if (b.getCurrenState() == GameState.Win)
		{
			if (b.getWinner() == getType())
			{
				return 10;
			} else if (b.getWinner() != getType())
			{
				return -10;
			}
		} else if (b.getCurrenState() == GameState.Tie)
		{
			return 0;
		}
		return Integer.MIN_VALUE;
	}
	public int score(Board b, int depth){
		if(b.getWinner()==getType()){
			return 10-depth;
		}else if(b.getWinner()==null){
			return 0;
		}else {
			return depth-10;
		}
	}
	public static int[] choice;
	
	public int minMax(Board b, int depth){
		if(b.getCurrenState()!=GameState.Running)return score(b, depth);
		
		depth+=1;
		ArrayList<Integer> scores = new ArrayList<Integer>();
		ArrayList<int[]> moves = new ArrayList<int[]>();

		for(int[] move : b.getPossibleMoves()){
			Board possibleBoard = b.copy();
			possibleBoard.move(possibleBoard.getNextPlayer(), move[0], move[1]);
			scores.add(minMax(possibleBoard, depth));
			moves.add(move);
		}
		
		if(b.getNextPlayer()==getType()){
			int maxIndex = -1;
			int maxScore = Integer.MIN_VALUE;
			for(int i = 0; i<scores.size();i++){
				if(scores.get(i)>maxScore){
					maxIndex = i;
					maxScore = scores.get(i);
				}
			}
			
			choice = moves.get(maxIndex);
			return scores.get(maxIndex);
		}else{
			int minIndex = -1;
			int minScore = Integer.MAX_VALUE;
			for(int i = 0; i<scores.size();i++){
				if(scores.get(i)<minScore){
					minIndex = i;
					minScore = scores.get(i);
				}
			}
			
			choice = moves.get(minIndex);
			return scores.get(minIndex);
		}
	}
	
	
	
	public static int count = 0;
	
	public int[] bestMove(Board b){
		ArrayList<int[]> moves = b.getPossibleMoves();
		int[] values = new int[moves.size()];
		int[] depthes = new int[moves.size()];
		for(int i = 0; i<moves.size();i++){
			Board copied = b.copy();
			copied.move(getType(), moves.get(i)[0], moves.get(i)[1]);
			int[] vd =  calcValue(copied, 0);
			values[i] = vd[0];
			depthes[i] = vd[1];
		}
		
		int max = Integer.MIN_VALUE;
		int[] current = new int[2];
		for(int i = 0; i<moves.size();i++){
			System.out.println(moves.get(i)[0]+", "+moves.get(i)[1]+": "+values[i] + " (D: " + depthes[i] + " )");
			 if(values[i]>=max){
				 max = values[i];
				 current = moves.get(i);
			 }
		}
		
		return current;
	}
	
	public int[] calcValue(Board b, int depth){
		depth++;
		count++;
		if(b.getWinner() == getType())
			return new int[]{10-depth, depth};
		else if(b.getCurrenState() == GameState.Tie)
			return new int[]{0, depth};
		else if(b.getCurrenState() == GameState.Win){
			return new int[]{depth-10, depth};
			
		}
		int value = 0;
		for(int[] move : b.getPossibleMoves()){
			Board copied = b.copy();
			copied.move(copied.getNextPlayer(), move[0], move[1]);
			//System.out.println(copied.getNextPlayer()+"--");
			value += calcValue(copied, depth)[0];
		}
		return new int[]{value, depth};
	}
/*
	public ArrayList<ArrayList<Response>> evaluateBestMove(Board b)
	{
		for (int[] move : b.getPossibleMoves())
		{
			Board nB = b.copy();
			nB.move(getType(), move[0], move[1]);
			if (nB.getCurrenState() != GameState.Running)
				score(nB);
			else
				evaluateMoveOtherPlayer(nB);
		}
		return null;
	}

	private int[] evaluateMoveOtherPlayer(Board b)
	{
		State pType = null;
		if(getType()==State.X)pType=State.O;
		else pType=State.X;
		for (int[] move : b.getPossibleMoves())
		{
			Board nB = b.copy();
			nB.move(pType, move[0], move[1]);
			
			if (nB.getCurrenState() != GameState.Running)
				score(nB);
			else
				evaluateBestMove(nB);
		}
		return null;
	}
*/
}
