package com.thg.accelerator23.connectn.ai.sirconnectabitmore;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.GameConfig;
import com.thehutgroup.accelerator.connectn.player.Player;
import com.thg.accelerator23.connectn.ai.sirconnectabitmore.connectn.BoardAnalyser;
import com.thg.accelerator23.connectn.ai.sirconnectabitmore.connectn.BoardStateGenerator;
import com.thg.accelerator23.connectn.ai.sirconnectabitmore.connectn.TranspositionEntry;
import com.thg.accelerator23.connectn.ai.sirconnectabitmore.minimax.Minimax;

import java.util.HashMap;

public class ConnectFourEgoRestore extends Player {
  private Minimax<Integer, Board, Counter> minimax;
  private int minDepth;
  private int maxDepth;

  public ConnectFourEgoRestore(Counter counter, GameConfig gameConfig, int minDepth, int maxDepth) {
    super(counter, ConnectFourEgoRestore.class.getName());

    minimax = new Minimax<>(new BoardStateGenerator(gameConfig), new BoardAnalyser(gameConfig));
    this.minDepth = minDepth;
    this.maxDepth = maxDepth;
  }

  public ConnectFourEgoRestore(Counter counter) {
    this(counter, new GameConfig(10, 8, 4), 7, 40);
  }

  @Override
  public int makeMove(Board board) {
    int bestMove = 0;
    long startTime = System.currentTimeMillis();
    HashMap<Counter[][], TranspositionEntry> table = new HashMap<>();
    for (int i = minDepth; i <= maxDepth; i++) {
      System.out.println("Starting with depth " + i);
      try {
        Integer latestMove = minimax
            .minimaxDecision(board, getCounter(), getCounter().getOther(), i, table, startTime);
        if (latestMove != null) {
          bestMove = latestMove;
          System.out.println("best move " + bestMove);
        } else {
          System.out.println("Didn't complete iteration");
          return bestMove;
        }
      } catch (RuntimeException e) {
        System.out.println("stopped part-way through");
        return bestMove;
      }
    }
    return bestMove;
  }
}
