package de.paul.Genetic;

import de.paul.Player.GeneticPlayer;
import de.paul.TicTacGame.State;

public class Algorithm
{

    /* Hyperparams */
    private static final double mutationRate = 0.05;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;
    private static final State type = State.O;
    
    // Evolves a population over one generation
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.populationSize(), true, type);
    	FitnessCalculator.calculateFitness(pop);
        // Keep best
        int elitismOffset = 0;
        if (elitism) {
            newPopulation.savePlayer(0, pop.getFittest());
            elitismOffset = 1;
        }

        // Crossover population
        // Loop over the new population's size and create individuals from
        // Current population
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            // Select parents
            GeneticPlayer parent1 = tournamentSelection(pop);
            GeneticPlayer parent2 = tournamentSelection(pop);
            // Crossover parents
            GeneticPlayer child = crossover(parent1, parent2);
            // Add child to new population
            newPopulation.savePlayer(i, child);
        }

        // Mutation for wider genpool
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getPlayer(i));
        }

        return newPopulation;
    }

    // Applies crossover to a set of parents and creates offspring
    public static GeneticPlayer crossover(GeneticPlayer parent1, GeneticPlayer parent2) {
        // Create new child tour
    	GeneticPlayer child = new GeneticPlayer(type);

        // Get start and end sub tour positions for parent1's responses
        int startPos = (int) (Math.random() * parent1.getResponses().size());
        int endPos = (int) (Math.random() * parent1.getResponses().size());

        // Loop and add the sub tour from parent1 to our child
        for (int i = 0; i < endPos; i++) {
            // If our start position is less than the end position
            if (startPos < endPos && i > startPos && i < endPos) {
                child.addResponse(parent1.getResponse(i));
            } // If our start position is larger
       
        }

        // Loop through parent2's city tour
        for (int i = 0; i < parent2.getResponses().size(); i++) {
            // If child doesn't have the city add it
            if (!child.containsResponse(parent2.getResponse(i))) {
                // Loop to find a spare position in the child's array
                for (int ii = 0; ii < child.getResponses().size(); ii++) {
                    // Spare position found, add Response
                    if (child.getResponse(ii) == null) {
                        child.addResponse(parent2.getResponse(i));
                        break;
                    }
                }
            }
        }
        return child;
    }

    // Mutate a tour using swap mutation
    private static void mutate(GeneticPlayer player) {
        // Loop through tour cities
        for(int i=0; i<player.getResponses().size();i++){
            // Apply mutation rate
            if(Math.random() < mutationRate){
                Response newResponse = new Response(player.getResponse(i).getBoardState());
                player.setResponse(i, newResponse);
            }
        }
    }

    // Selects candidate player for crossover
    private static GeneticPlayer tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false, type);
        // For each place in the tournament get a random candidate player
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            tournament.savePlayer(i, pop.getPlayer(randomId));
        }
        // Get the fittest player
        GeneticPlayer fittest = tournament.getFittest();
        return fittest;
    }
}