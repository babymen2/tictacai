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

    //This method was heavily inspired by https://github.com/vivin/DigitRecognizingNeuralNetwork  (I'm just to dump to come up with this by myself ;) )
    public double backpropagate(){
        //Iterating through all training sets
        for(int i = 0; i < trainingDataIn.size();i++){
            double[] input = trainingDataIn.get(i);
            double[] expectedOutput = trainingDataOut.get(i);

            network.setInputs(input);
            double[] output = network.getOutput();

            //iterating through all layers (except the input layer, bc why would you?!)
            for(int layer = network.getLayerCount(); layer > 0; layer--){
                //iterating through all neurons of this layer
                for(int neuronCounter = 0; neuronCounter < network.getNetworkStructure()[layer]; neuronCounter ++){
                    Neuron neuron = network.getLayers().get(layer).get(neuronCounter);
                    double neuronError = 0;
                    //check if neuron is an output neuron
                    if(layer==network.getLayerCount()){
                        //calculating the error of the output neuron;
                        neuronError = neuron.derivate(neuron.getValue())*expectedOutput[neuronCounter]-output[neuronCounter];
                    }else{
                        //We are dealing with a hidden layer! Yeay

                        double sum = 0;
                        neuronError = neuron.derivate(neuron.getValue());

                        //Iterate through Neurons of the next Layer (next meaning closer to output), this is needed for calculating
                        //the error of our currently viewed neuron (neuron) in regard to the complete output error
                        for(Neuron upperNeuron : network.getLayers().get(layer++)){

                        }
                    }

                    //setting the neuron error
                    neuron.setError(neuronError);

                }



            }
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
