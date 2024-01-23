package com.thg.accelerator23.connectn.ai.sirconnectabitmore.minimax;

public class ScoredType<T> {
    private T t;
    private double score;

    public ScoredType(T t, double score) {
        this.t = t;
        this.score = score;
    }

    public T getT() {
        return t;
    }

    public double getScore() {
        return score;
    }
}
