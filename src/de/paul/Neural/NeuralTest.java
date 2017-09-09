package de.paul.Neural;

import java.util.Random;

public class NeuralTest {

    public static void main(String[] args){

        int[] structure = new int[]{1,1};

        Network n = new Network(structure);
        n.setInputs(new double[]{0.5});
        System.out.println(n.getOutput()[0]);

        for(Synapse s : n.getLayers().get(1).get(0).getIncomingSynapses()){
            System.out.println("Snyapse Weight " + s.getWeight());
        }
        double startOutput = n.getOutput()[0];
        for(int i = 0; i<100000; i++){
            double error = 0-n.getOutput()[0];
            if(i%100==0)System.out.println("Epoch " + i + " :" + error);
            double delta = error*n.getLayers().get(1).get(0).derivate(n.getOutput()[0]);
            n.getLayers().get(1).get(0).getIncomingSynapses().get(0).setWeight(n.getLayers().get(1).get(0).getIncomingSynapses().get(0).getWeight()+delta);

        }
        System.out.println("Neural Network Number one");
        System.out.println("Start Output: " + startOutput);
        System.out.println("Expected Output: 0");
        System.out.println("Output after Learning :" + n.getOutput()[0]);



        /*
        Training t = new Training(n, 0.05, 0.03);


        t.addTrainingtSet(new double[]{0,1}, new double[]{1});
        t.addTrainingtSet(new double[]{1,1}, new double[]{0});
        t.addTrainingtSet(new double[]{0,0}, new double[]{0});
        t.addTrainingtSet(new double[]{1,0}, new double[]{1});


        System.out.println(n.getLayers().get(n.getLayerCount()).get(0).getValue());
        */

    }

}
