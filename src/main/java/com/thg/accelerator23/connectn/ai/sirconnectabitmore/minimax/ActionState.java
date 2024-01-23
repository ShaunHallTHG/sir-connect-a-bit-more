package com.thg.accelerator23.connectn.ai.sirconnectabitmore.minimax;

public interface ActionState<T, S> {
  T getAction();
  S getState();
}
