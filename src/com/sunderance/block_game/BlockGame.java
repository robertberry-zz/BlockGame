package com.sunderance.block_game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class BlockGame extends StateBasedGame {
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final boolean FULL_SCREEN = false;
	
	enum State {
		MAIN_MENU, GAME_PLAY, PAUSE
	}
	
	public BlockGame() {
		super("BlockGame");
	}

	/**
	 * Runs the game
	 * 
	 * @param args Command-line arguments
	 * @throws SlickException
	 */
	public static void main(String[] args) throws SlickException {
         AppGameContainer app = new AppGameContainer(new BlockGame());
         
         app.setDisplayMode(WIDTH, HEIGHT, FULL_SCREEN);
         app.start();
    }
	
	/**
	 * Sets up the states
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.addState(new MainMenuState(State.MAIN_MENU.ordinal()));
		this.addState(new GamePlayState(State.GAME_PLAY.ordinal()));
	}
}
