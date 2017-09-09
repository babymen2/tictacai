package de.paul.Neural;

public class Synapse {
    private Neuron source;
    private Neuron target;
    private double weight;

    public Synapse(Neuron source, Neuron target) {
        this.source = source;
        this.target = target;
    }


    public Neuron getTarget() {
        return target;
    }

    public void setTarget(Neuron target) {
        this.target = target;
    }

    public Neuron getSource() {
        return source;
    }

    public void setSource(Neuron source) {
        this.source = source;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}