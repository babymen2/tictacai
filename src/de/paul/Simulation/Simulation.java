package de.paul.Simulation;

import de.paul.Genetic.Algorithm;
import de.paul.Genetic.FitnessCalculator;
import de.paul.Genetic.Population;
import de.paul.Player.MinMaxPlayer;
import de.paul.Player.RandomPlayer;
import de.paul.TicTacGame.Board;
import de.paul.TicTacGame.GameState;
import de.paul.TicTacGame.State;

public class Simulation
{
	
	
	public static void main(String[] args){
		//simulateRandomVsRandom(1000);
		//trainAndSimulateGenetic(500, 100);
		
		Board b = new Board(3);
		
		/*
		 * 
		 * b.move(State.O, 2, 0);
		b.move(State.X, 2, 1);
*/
		//System.out.println(b.getPossibleMoves().size());

		b.move(State.X, 0, 0);
		b.move(State.O, 0, 1);
		//b.move(State.X, 1, 2);

		MinMaxPlayer o = new MinMaxPlayer(State.O);
		MinMaxPlayer x = new MinMaxPlayer(State.X);
		//x.bestRespones(b);

		b.getPossibleRotations();

		for(Board b2 : b.getPossibleRotations())
			System.out.println(b2);

		//simulateMinMaxvsRandom(2000);
		
	}
	
	public static void simulateMinMaxvsMinMax(int amount){
		MinMaxPlayer pX = new MinMaxPlayer(State.X);
		MinMaxPlayer pO = new MinMaxPlayer(State.O);
		
		for(int i=0; i<amount; i++){
			Board b = new Board(3);
			while(b.getCurrenState()==GameState.Running && b.getWinner()==null){
				int[] pxRT = pO.bestMove(b);
				b.move(pX.getType(), pxRT[0], pxRT[1]);
				if(b.getCurrenState()==GameState.Running && b.getWinner()==null){
					pO.minMax(b, 0);
					int[] poRT = pO.choice;
					b.move(pO.getType(), poRT[0], poRT[1]);
				}
				System.out.println(b.getCurrenState().toString());
			}
			if(b.getCurrenState()!=GameState.Tie){
				if(b.getWinner()==pX.getType()){
					pX.setWins(pX.getWins()+1);
				}
				if(b.getWinner()==pO.getType()){
					pO.setWins(pO.getWins()+1);
				}
			}
			System.out.println("---- BOARD RESET-----");
		}
		System.out.println("Games Played: " + amount);
		System.out.println("Wins X:" + pX.getWins());
		System.out.println("Wins O: " + pO.getWins());
		System.out.println("Ties: " + (amount-(pX.getWins()+pO.getWins())));
	}
		
	
	public static void simulateMinMaxvsRandom(int amount){
		RandomPlayer pX = new RandomPlayer(State.X);
		MinMaxPlayer pO = new MinMaxPlayer(State.O);
		
		for(int i=0; i<amount; i++){
			Board b = new Board(3);
			while(b.getCurrenState()==GameState.Running && b.getWinner()==null){
				int[] pxRT = pX.getRandomTile(b);
				b.move(pX.getType(), pxRT[0], pxRT[1]);
				if(b.getCurrenState()==GameState.Running && b.getWinner()==null){
					pO.minMax(b, 0);
					int[] poRT = pO.choice;
					b.move(pO.getType(), poRT[0], poRT[1]);
				}
				//System.out.println(b.getCurrenState().toString());
			}
			if(b.getCurrenState()!=GameState.Tie){
				if(b.getWinner()==pX.getType()){
					pX.setWins(pX.getWins()+1);
					for(int x=0; x<3; x++){
						for(int y=0; y<3;y++){
							System.out.println("["+b.getState(x, y).toString()+"]");
						}
					}
					i=amount;
				}
				if(b.getWinner()==pO.getType()){
					pO.setWins(pO.getWins()+1);
				}
			}
			System.out.println("---- BOARD RESET-----");
		}
		System.out.println("Games Played: " + amount);
		System.out.println("Wins X:" + pX.getWins());
		System.out.println("Wins O: " + pO.getWins());
		System.out.println("Ties: " + (amount-(pX.getWins()+pO.getWins())));
		
	}
	public static void trainAndSimulateGenetic(int popSize,int generations){
		Population pop = new Population(popSize, State.O);
		FitnessCalculator.calculateFitness(pop);
		System.out.println("First Generation, Fittest Win Rate: ");
		System.out.println(((pop.getFittest().getWins()*1.0)/(pop.getFittest().getGames()*1.0))*100+"%");
		double[] avgWins = new double[generations];
		
		for(int i=0; i<generations;i++){
			pop.getFittest().setGames(0);
			pop.getFittest().setWins(0);
			pop = Algorithm.evolvePopulation(pop);
			avgWins[i] = pop.getAverageWins();
			System.out.println(i + " - " + avgWins[i]);
		}
		System.out.println("Last Generation, Fittest Win Rate: ");
		System.out.println(((pop.getFittest().getWins()*1.0)/(pop.getFittest().getGames()*1.0))*100+"%");
	}
	
	public static void simulateRandomVsRandom(int amount){
		RandomPlayer pX = new RandomPlayer(State.X);
		RandomPlayer pO = new RandomPlayer(State.O);
		
		for(int i=0; i<amount; i++){
			Board b = new Board(3);
			while(b.getCurrenState()==GameState.Running && b.getWinner()==null){
				int[] pxRT = pX.getRandomTile(b);
				b.move(pX.getType(), pxRT[0], pxRT[1]);
				if(b.getCurrenState()==GameState.Running && b.getWinner()==null){
					int[] poRT = pO.getRandomTile(b);
					b.move(pO.getType(), poRT[0], poRT[1]);
				}
				System.out.println(b.getCurrenState().toString());
			}
			if(b.getCurrenState()!=GameState.Tie){
				if(b.getWinner()==pX.getType()){
					pX.setWins(pX.getWins()+1);
				}
				if(b.getWinner()==pO.getType()){
					pO.setWins(pO.getWins()+1);
				}
			}
			System.out.println("---- BOARD RESET-----");
		}
		System.out.println("Games Played: " + amount);
		System.out.println("Wins X:" + pX.getWins());
		System.out.println("Wins O: " + pO.getWins());
		System.out.println("Ties: " + (amount-(pX.getWins()+pO.getWins())));
		
	}
	
}
