package com.thehutgroup.accelerator.connectn.player;

public class BoardHacker {
    public static Counter getCounterQuickly(Board board, int x, int y) {
        return board.getCounterPlacements()[x][y];
    }

    public static Counter[][] getInnerBoard(Board board) {
        return board.getCounterPlacements();
    }
}
