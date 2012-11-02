package com.sunderance.block_game.states;

import org.newdawn.slick.state.BasicGameState;

/**
 * Base class for game states - implements the getID method, as we are storing
 * this ID through the constructor
 * 
 * @author Robert Berry
 * @version 0.1
 */
public abstract class GameState extends BasicGameState {
	private int stateID;

	/**
	 * Creates a GameState with the given state ID
	 * 
	 * @param stateID The state ID
	 */
	public GameState(int stateID) {
		super();
		this.stateID = stateID;
	}

	@Override
	public int getID() {
		return stateID;
	}
}
