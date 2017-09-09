package de.paul.Neural;

import java.util.Random;

public class NeuralTest {

    public static void main(String[] args){

        int[] structure = new int[]{2,3,1};

        Network n = new Network(structure);
        Training t = new Training(n, 0.05, 0.03);

        Neuron testN = new Neuron();
        double siggi = testN.sigmoid(0.9);
        double deriv = siggi * (1.0 - siggi);
        System.out.println(siggi);

        /*
        t.addTrainingtSet(new double[]{0,1}, new double[]{1});


        System.out.println(t.calculateAverageError());
        System.out.println(n.getLayers().get(n.getLayerCount()).get(0).getValue());
        */
    }

}
