package de.paul.Genetic;

import de.paul.Player.GeneticPlayer;
import de.paul.Player.RandomPlayer;
import de.paul.TicTacGame.Board;
import de.paul.TicTacGame.GameState;
import de.paul.TicTacGame.State;

public class FitnessCalculator
{
	//Min 6
	private static int gamesPerPlayer = 1000;
	
	public static void calculateFitness(Population pop){
		
		for(int i = 0; i<pop.populationSize();i++){
			GeneticPlayer p = pop.getPlayer(i);
			double fitnessCalc = 0;
			for(int g=0; g<gamesPerPlayer;g++){
				fitnessCalc+=playVsRandom(p);
			}
			p.setFitness((p.getGames()*1.0-p.getDefeats()*1.0)/p.getGames()*1.0);
		}
		
	}
	
	public static double playVsRandom(GeneticPlayer p){
		RandomPlayer pX = new RandomPlayer(State.X);
		Board b = new Board(3);
		while(b.getCurrenState()==GameState.Running && b.getWinner()==null){
			int[] pxRT = pX.getRandomTile(b);
			b.move(pX.getType(), pxRT[0], pxRT[1]);
			if(b.getCurrenState()==GameState.Running && b.getWinner()==null){
				int[] poRT = p.getResponseMove(b);
				b.move(p.getType(), poRT[0], poRT[1]);
			}
		}
		p.setGames(p.getGames()+1);
		if(b.getCurrenState()!=GameState.Tie){
			p.setGames(p.getGames()+1);
			if(b.getWinner()==pX.getType()){
				p.setDefeats(p.getDefeats()+1);
				return 0;
			}
			if(b.getWinner()==p.getType()){
				p.setWins(p.getWins()+1);
				return 0;
			}
		}

		return 1.1;
		
	}
}
