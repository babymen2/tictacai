package de.paul.Neural;

import java.util.HashMap;

public class Neuron {

    private double value;
    private HashMap<Neuron, Double> outgoing;

    public Neuron(){
        this.value = 0;
        this.outgoing = new HashMap<Neuron, Double>();
    }

    public void addOutgoing(Neuron target, double weight){
        outgoing.put(target, weight);
    }

    public void updateOutgoing(Neuron target, double weight){
        outgoing.remove(target);
        outgoing.put(target, weight);
    }

    public double getOutputValue(Neuron target, double inputValue){
        return sigmoid(outgoing.get(target)*inputValue);
    }

    //Used to keep the Result between 0 and 1
    public double sigmoid(double x)
    {
        return 1 / (1 + Math.exp(-x));
    }

    public HashMap<Neuron, Double> getOutgoing() {
        return outgoing;
    }

    public void setOutgoing(HashMap<Neuron, Double> outgoing) {
        this.outgoing = outgoing;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
