package com.EREMEI.Recursion;

public class BagComb {
    private int[] weights;
    private int targetWeight;

    private int[][] combinations;

    public void setWeights(int[] weights) {
        this.weights = weights;
    }

    public void setTargetWeight(int targetWeight) {
        this.targetWeight = targetWeight;
    }

    public void proceed() {
        knapsack(targetWeight, 0);
    }

    private void knapsack(int targetWeight, int startIndex) {
        for (int i = startIndex + 1; i < weights.length; i++) {

        }

        knapsack(targetWeight - weights[startIndex], startIndex + 1);
    }

}
