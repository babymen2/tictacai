package de.paul.Genetic;

import de.paul.Player.GeneticPlayer;
import de.paul.TicTacGame.State;

public class Population
{
	   // Holds population of players
    GeneticPlayer[] players;

    // Construct a population
    public Population(int populationSize, State type) {
        players = new GeneticPlayer[populationSize];
        for(int i = 0; i<populationSize; i++){
        	GeneticPlayer p = new GeneticPlayer(type);
        	players[i] = p;
        }
    }
    public Population(int populationSize, boolean init, State type) {
        players = new GeneticPlayer[populationSize];
        
    }
    
    public void savePlayer(int index, GeneticPlayer player) {
        players[index] = player;
    }
    
    public GeneticPlayer getPlayer(int index) {
        return players[index];
    }
    
    public double getAverageWins(){
    	double counter = 0;
    	for(int i=0;i<populationSize();i++){
    		counter+=players[i].getWins();
    	}
    	return counter/populationSize()*200;
    }

    public double getAverageFittnes(){
        double counter = 0;
        for(int i=0;i<populationSize();i++){
            counter+=players[i].getFitness();
        }
        return counter/populationSize();
    }
    
    // Gets the best tour in the population
    public GeneticPlayer getFittest() {
        GeneticPlayer fittest = players[0];
        // Loop through individuals to find fittest
        for (int i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() <= getPlayer(i).getFitness()) {
                fittest = getPlayer(i);
            }
        }
        return fittest;
    }

    // Gets population size
    public int populationSize() {
        return players.length;
    }

}
