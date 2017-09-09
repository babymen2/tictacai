package de.paul.Neural;

import java.util.ArrayList;
import java.util.HashMap;

public class Neuron {

    private double value;
    private HashMap<Neuron, Synapse> outgoingSynapses;
    private ArrayList<Synapse> incomingSynapses;

    public Neuron(){
        this.value = 0;
        this.incomingSynapses = new ArrayList<Synapse>();
        this.outgoingSynapses = new HashMap<Neuron, Synapse>();

    }

    public void addOutgoing(Synapse syn){
       outgoingSynapses.put(syn.getTarget(),syn);
    }

    public void addIncoming(Synapse syn){
        incomingSynapses.add(syn);
    }

    public void updateOutgoing(Neuron target, double weight){
        outgoingSynapses.get(target).setWeight(weight);
    }

    public double getWeightedSum(){
        double weightedSum = 0;
        for(Synapse syn : incomingSynapses){
            weightedSum+=syn.getSource().getValue()*syn.getWeight();
        }
        return weightedSum;
    }


    public double getOutputValue(Neuron target, double inputValue){
        return sigmoid(outgoingSynapses.get(target).getWeight()*inputValue);
    }

    //Used to keep the Result between 0 and 1
    public double sigmoid(double x)
    {
        return 1 / (1 + Math.exp(-x));
    }

    public double derivate(double x){
        return x*(1-x);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public ArrayList<Synapse> getIncomingSynapses() {
        return incomingSynapses;
    }

    public HashMap<Neuron, Synapse> getOutgoingSynapses() {
        return outgoingSynapses;
    }

    public void setOutgoingSynapses(HashMap<Neuron, Synapse> outgoingSynapses) {
        this.outgoingSynapses = outgoingSynapses;
    }

    public void setIncomingSynapses(ArrayList<Synapse> incomingSynapses) {
        this.incomingSynapses = incomingSynapses;
    }
}
