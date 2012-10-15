package com.sunderance.block_game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Main-menu (start up screen) for the game
 * 
 * @author Robert Berry
 * @version 0.1
 */
public class MainMenuState extends GameState {
	Image background;
	
	/**
	 * Construct the state with the given ID
	 * 
	 * @param stateID
	 */
	public MainMenuState(int stateID) {
		super(stateID);
	}

	/**
	 * Initialise the state, load resources.
	 * 
	 * @param gc The game container
	 * @param game The game
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		background = new Image("res/main_menu/background.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics graphics)
			throws SlickException {
		background.draw(0, 0);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		Input input = gc.getInput();
		
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			game.enterState(BlockGame.State.GAME_PLAY.ordinal());
		}
	}
}
