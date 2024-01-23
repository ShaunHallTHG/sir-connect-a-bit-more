package com.thg.accelerator23.connectn.ai.sirconnectabitmore.connectn;

public class TranspositionEntry {
    public static enum Flag {
        EXACT,
        LOWERBOUND,
        UPPERBOUND
    }
    private int depth;
    private Flag flag;
    private double value;
    private int move;

    public TranspositionEntry(int depth, Flag flag, double value, int move) {
        this.depth = depth;
        this.flag = flag;
        this.value = value;
        this.move = move;
    }

    public Integer getMove() {
        return move;
    }

    public int getDepth() {
        return depth;
    }

    public Flag getFlag() {
        return flag;
    }

    public double getValue() {
        return value;
    }
}
