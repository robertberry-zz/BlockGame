/**
 * 
 */
package com.sunderance.block_game.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.StateBasedGame;

import com.sunderance.block_game.BlockGame;

/**
 * Game over state
 * 
 * @author Robert Berry
 */
public class GameOverState extends GameState {
	private static final int GAME_OVER_TEXT_X = 30;
	
	private static final int GAME_OVER_TEXT_Y = 100;
	
	private static final String GAME_OVER_TEXT = "Game over!";
	
	org.newdawn.slick.state.GameState gamePlay;
	UnicodeFont font;
	
	/**
	 * Constructs the state with the given state ID
	 * 
	 * @param stateID
	 */
	public GameOverState(int stateID) {
		super(stateID);
	}

	/**
	 * @see org.newdawn.slick.state.GameState#init(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		gamePlay = game.getState(BlockGame.State.GAME_PLAY.ordinal());
		font = ((BlockGame) game).getMediumFont();
	}

	/**
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics graphics)
			throws SlickException {
		gamePlay.render(gc, game, graphics);

		font.drawString(GAME_OVER_TEXT_X, GAME_OVER_TEXT_Y, GAME_OVER_TEXT);
	}

	/**
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			game.enterState(BlockGame.State.HIGH_SCORES.ordinal());
		}
	}

}
