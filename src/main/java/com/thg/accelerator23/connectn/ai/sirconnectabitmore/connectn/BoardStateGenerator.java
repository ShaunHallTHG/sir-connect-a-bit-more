package com.thg.accelerator23.connectn.ai.sirconnectabitmore.connectn;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.BoardHacker;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.GameConfig;
import com.thehutgroup.accelerator.connectn.player.InvalidMoveException;
import com.thg.accelerator23.connectn.ai.sirconnectabitmore.minimax.ActionState;
import com.thg.accelerator23.connectn.ai.sirconnectabitmore.minimax.StateGenerator;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardStateGenerator implements StateGenerator<Integer, Board, Counter> {

    private List<Integer> order;
    private int top;

    public BoardStateGenerator(GameConfig config) {
        order = IntStream.range(0, config.getWidth()).mapToObj(Integer::valueOf).collect(Collectors.toList());
        int half = config.getWidth() / 2;
        order.sort(Comparator.comparing(move -> Math.abs(move - half)));
        top = config.getHeight() - 1;
    }

    @Override
    public List<ActionState<Integer, Board>> getActionStates(Board state, Counter counter) {
        List<ActionState<Integer, Board>> moves = new LinkedList<>();
        for (int move : order) {
            if (BoardHacker.getCounterQuickly(state, move, top) == null) {
                try {
                    moves.add(new BoardActionState(move, new Board(state, move, counter)));
                } catch (InvalidMoveException e) {
                    throw new RuntimeException("Shaun's AI has a bug -- tried to insert a counter in a full column. lol.");
                }
            }
        }
        return moves;
    }
}
