package de.paul.Neural;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Training {

    private Network network;
    private ArrayList<double[]> trainingDataIn, trainingDataOut;
    private double minError, learningRate;
    public Training(Network network, double minError, double learningRate){
        this.network = network;
        this.minError = minError;
        this.learningRate = learningRate;
        this.trainingDataIn = new ArrayList<double[]>();
        this.trainingDataOut = new ArrayList<double[]>();

    }

    public void addTrainingtSet(double[] inputs, double[] expectedOutputs) {
        if(network.getNetworkStructure()[0]!=inputs.length || network.getNetworkStructure()[network.getLayerCount()] != expectedOutputs.length)return;
        this.trainingDataIn.add(inputs);
        this.trainingDataOut.add(expectedOutputs);
    }

    public double backpropagate(){
        for(int i = 0; i < trainingDataIn.size();i++){
            double[] input = trainingDataIn.get(i);
            double[] expectedOutput = trainingDataOut.get(i);

            network.setInputs(input);

        }
        return 1.0;
    }


    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public ArrayList<double[]> getTrainingDataIn() {
        return trainingDataIn;
    }

    public void setTrainingDataIn(ArrayList<double[]> trainingDataIn) {
        this.trainingDataIn = trainingDataIn;
    }

    public ArrayList<double[]> getTrainingDataOut() {
        return trainingDataOut;
    }

    public void setTrainingDataOut(ArrayList<double[]> traininigDataOut) {
        this.trainingDataOut = traininigDataOut;
    }
}
