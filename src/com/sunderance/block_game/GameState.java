package com.sunderance.block_game;

import org.newdawn.slick.state.BasicGameState;

public abstract class GameState extends BasicGameState {
	private int stateID;

	public GameState(int stateID) {
		super();
		this.stateID = stateID;
	}
	
	@Override
	public int getID() {
		return stateID;
	}
}
