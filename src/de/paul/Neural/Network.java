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


    public double[] getOutput(){
        double[] outputs = new double[this.getNetworkStructure()[this.getLayerCount()]];
        for(int layer = 1; layer<networkStructure.length;layer++){
            for(Neuron n : layers.get(layer)){
                n.setValue(n.sigmoid(n.getWeightedSum()));
            }
        }
        for(int neuron = 0; neuron < this.getNetworkStructure()[this.getLayerCount()]; neuron ++){
            outputs[neuron] = this.getLayers().get(this.getLayerCount()).get(neuron).getValue();
        }
        return outputs;
    }

    public void setInputs(double[] input){
        if(input.length!=networkStructure[0])return;
        for(int i = 0; i<input.length;i++){
            layers.get(0).get(i).setValue(input[i]);
        }
    }

    public void initWeights(){
        Random r = new Random();
        for(int layer = 0; layer<networkStructure.length-1;layer++){
            for(Neuron n : layers.get(layer)){
                for(Neuron nNextLayer : layers.get(layer+1)){
                    Synapse syn = new Synapse(n, nNextLayer);
                    syn.setWeight(r.nextDouble());
                    n.addOutgoing(syn);
                    nNextLayer.addIncoming(syn);
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
