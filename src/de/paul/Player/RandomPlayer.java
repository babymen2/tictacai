package de.paul.Player;

import java.util.Random;

import de.paul.TicTacGame.Board;
import de.paul.TicTacGame.State;

public class RandomPlayer extends Player
{
	
	private State type;
	
	public RandomPlayer(State type){
		super(type);
		
	}

	public int[] getRandomTile(Board b){
		Random r = new Random();
		int[] randomTile = new int[2];
		while(true){
			randomTile[0] = r.nextInt(3);
			randomTile[1] = r.nextInt(3);

			if(b.isEmpty(randomTile[0], randomTile[1])){
				return randomTile;
			}
		}
	}
	
}
