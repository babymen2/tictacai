package de.paul.Neural;

import java.util.ArrayList;
import java.util.Random;

public class Network {

    private int[] networkStructure;
    private ArrayList<ArrayList<Neuron>> layers;


    public Network(int[] networkStructure){
        this.networkStructure = networkStructure;
        layers = new ArrayList<ArrayList<Neuron>>();

        for(int layer = 0; layer<networkStructure.length;layer++){
            ArrayList<Neuron> layerList = new ArrayList<Neuron>();
            for(int neuron = 0; neuron<networkStructure[layer];neuron++){
                layerList.add(new Neuron());
            }
            layers.add(layerList);
        }
        initWeights();
    }

    public void calculateOutputs(double[] inputValues){
        if(inputValues.length!=networkStructure[0])return;
        //Setting Values for Inputs
        for(int i = 0; i<layers.get(0).size();i++){
            layers.get(0).get(i).setValue(inputValues[i]);
        }

        for(int layer = 0; layer<networkStructure.length-1;layer++){
            for(Neuron n : layers.get(layer)){
                for(Neuron nNextLayer : layers.get(layer+1)){
                    nNextLayer.setValue(n.getOutputValue(nNextLayer, n.getValue()));
                }
            }
        }
    }

    public void initWeights(){
        Random r = new Random();
        for(int layer = 0; layer<networkStructure.length-1;layer++){
            for(Neuron n : layers.get(layer)){
                for(Neuron nNextLayer : layers.get(layer+1)){
                    n.addOutgoing(nNextLayer, r.nextDouble());
                }
            }
        }
    }
    //Count of Layers, starting with 0
    public int getLayerCount(){
        return networkStructure.length-1;
    }

    public ArrayList<ArrayList<Neuron>> getLayers(){
        return layers;
    }

    public void setLayers(ArrayList<ArrayList<Neuron>> layers){
        this.layers = layers;
    }

    public int[] getNetworkStructure() {
        return networkStructure;
    }

    public void setNetworkStructure(int[] networkStructure) {
        this.networkStructure = networkStructure;
    }


}
