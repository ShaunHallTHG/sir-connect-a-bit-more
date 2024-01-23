package com.thg.accelerator23.connectn.ai.sirconnectabitmore.minimax;

public interface StateAnalyser<S, P> {
  StateInfo analyseState(S state, P player);
}
