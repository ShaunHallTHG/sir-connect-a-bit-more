package com.thg.accelerator23.connectn.ai.sirconnectabitmore.minimax;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.BoardHacker;

import java.util.Objects;

public class StateInfo {
  private double utility;
  private boolean isEndState;

  public StateInfo(double utility, Board board) {
    this.utility = utility;
    this.isEndState = Math.abs(utility) > 10e8 || isDraw(board);
  }

  private boolean isDraw(Board board) {
    //TODO optimise by calling once at startup
    int top = board.getConfig().getHeight() - 1;
    for (int i = 0; i < board.getConfig().getWidth(); i++) {
      if (BoardHacker.getCounterQuickly(board, i, top) ==null) {
        return false;
      }
    }
    return true;
  }

  public double getUtility() {
    return utility;
  }

  public boolean isEndState() {
    return isEndState;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StateInfo stateInfo = (StateInfo) o;
    return Double.compare(stateInfo.utility, utility) == 0 &&
        isEndState == stateInfo.isEndState;
  }

  @Override
  public int hashCode() {
    return Objects.hash(utility, isEndState);
  }
}
