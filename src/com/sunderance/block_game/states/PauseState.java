package com.sunderance.block_game.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.StateBasedGame;

import com.sunderance.block_game.BlockGame;

/**
 * State when the game is paused
 * 
 * @author Robert Berry
 */
public class PauseState extends GameState {
	private float textX;
	private float textY;
	
	private static final String PAUSE_TEXT = "Paused";
	
	org.newdawn.slick.state.GameState gamePlay;
	UnicodeFont font;

	public PauseState(int stateID) {
		super(stateID);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		gamePlay = game.getState(BlockGame.State.GAME_PLAY.ordinal());
		font = ((BlockGame) game).getMediumFont();
		
		textX = (gc.getWidth() - font.getWidth(PAUSE_TEXT)) / 2;
		textY = (gc.getHeight() - font.getHeight(PAUSE_TEXT)) / 2;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics graphics)
			throws SlickException {
		gamePlay.render(gc, game, graphics);
		
		font.drawString(textX, textY, PAUSE_TEXT);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_P)) {
			game.enterState(BlockGame.State.GAME_PLAY.ordinal());
		}
		
		input.clearKeyPressedRecord();
	}
}
