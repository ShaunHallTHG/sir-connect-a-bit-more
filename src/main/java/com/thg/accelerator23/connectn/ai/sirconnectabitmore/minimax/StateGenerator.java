package com.thg.accelerator23.connectn.ai.sirconnectabitmore.minimax;

import java.util.List;

public interface StateGenerator<T, S, P> {
  List<ActionState<T, S>> getActionStates(S state, P player);
}
