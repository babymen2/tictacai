package de.paul.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import de.paul.Genetic.Response;
import de.paul.TicTacGame.Board;
import de.paul.TicTacGame.State;

public class GeneticPlayer extends Player
{

	private ArrayList<Response> responses;
	private double fitness;
	public GeneticPlayer(State type){
		super(type);
		this.responses = new ArrayList<Response>();
		setFitness(0);
	}
	
	public int[] getResponseMove(Board b){

		for(Response r : responses){
			if(r.getBoardState() == b.getStateArray()){
				return r.getResponseMove();
			}
		}
		
		Response nR = new Response(b);
		responses.add(nR);
	
		return nR.getResponseMove();
	}
	
	public boolean containsResponse(Response r){
		for(Response rL : responses){
			if(rL == r)return true;
		}
		return false;
	}
	
	public void setResponse(int index, Response r){
		responses.set(index, r);
	}
	public void addResponse(Response r){
		responses.add(r);
	}
	
	public Response getResponse(int index){
		return responses.get(index);
	}

	public ArrayList<Response> getResponses(){
		return responses;
	}
	
	public double getFitness()
	{
		return fitness;
	}

	public void setFitness(double fitness)
	{
		this.fitness = fitness;
	}
	
	
}
