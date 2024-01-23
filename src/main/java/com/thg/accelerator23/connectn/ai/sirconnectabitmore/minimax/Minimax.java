package com.thg.accelerator23.connectn.ai.sirconnectabitmore.minimax;

import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thg.accelerator23.connectn.ai.sirconnectabitmore.connectn.TranspositionEntry;

import java.util.Map;

public final class Minimax<M, S, P> {
  private StateGenerator<M, S, P> stateGenerator;
  private StateAnalyser<S, P> stateAnalyser;

  public Minimax(StateGenerator<M, S, P> stateGenerator,
                 StateAnalyser<S, P> stateAnalyser) {
    this.stateGenerator = stateGenerator;
    this.stateAnalyser = stateAnalyser;
  }

  public M minimaxDecision(S state, P activePlayer, P passivePlayer, int max,
                           Map<Counter[][], TranspositionEntry> table, long startTime) {
    ScoredType<M> negamax =
        negamax(state, activePlayer, activePlayer, passivePlayer, max, -Double.MAX_VALUE,
            Double.MAX_VALUE, table, startTime);

        return negamax.getT();
    }

  private ScoredType<M> negamax(S state, P utilityPlayer, P player, P passivePlayer,
                                int maxDepth, double alpha, double beta,
                                Map<Counter[][], TranspositionEntry> table, long startTime) {
    if ((System.currentTimeMillis() - startTime > 8500)) {
      throw new RuntimeException("Out of time");
    }
    StateInfo stateInfo = stateAnalyser.analyseState(state, utilityPlayer);
    if (stateInfo.isEndState() || maxDepth == 0) {
      return new ScoredType(null,
          player == utilityPlayer ? stateInfo.getUtility() : -stateInfo.getUtility());
    }
    var value = -Double.MAX_VALUE;
    M move = null;
    for (var nextState : stateGenerator.getActionStates(state, player)) {
      double thisValue =
          -negamax(nextState.getState(), utilityPlayer, passivePlayer, player, maxDepth - 1, -beta,
              -alpha, table, startTime).getScore();
      if (thisValue > value) {
        value = thisValue;
        move = nextState.getAction();
      }

      alpha = Math.max(alpha, value);

      if (alpha >= beta) {
        break;
      }

    }
    return new ScoredType<>(move, value);
  }
}