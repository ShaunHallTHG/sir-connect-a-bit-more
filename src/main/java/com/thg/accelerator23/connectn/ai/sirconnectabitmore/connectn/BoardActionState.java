package com.thg.accelerator23.connectn.ai.sirconnectabitmore.connectn;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thg.accelerator23.connectn.ai.sirconnectabitmore.minimax.ActionState;

import java.util.Objects;

public class BoardActionState implements ActionState<Integer, Board> {
  private Integer action;
  private Board state;

  public BoardActionState(Integer action, Board state) {
    this.action = action;
    this.state = state;
  }

  @Override
  public Integer getAction() {
    return action;
  }

  @Override
  public Board getState() {
    return state;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BoardActionState that = (BoardActionState) o;
    return Objects.equals(action, that.action) &&
        Objects.equals(state, that.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(action, state);
  }
}
