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
	Image title;
	
	Menu menu;
	
	private static final int TITLE_Y = 80;
	
	private static final int MENU_Y = 180;
	
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
		title = new Image("res/main_menu/title.png");
		
		MenuItem newGame = new MenuItem(new Image("res/main_menu/new-game.png"),
				new Image("res/main_menu/new-game-selected.png"));
		MenuItem highScores = new MenuItem(
				new Image("res/main_menu/high-scores.png"),
				new Image("res/main_menu/high-scores-selected.png"));
		MenuItem quit = new MenuItem(
				new Image("res/main_menu/quit.png"),
				new Image("res/main_menu/quit-selected.png"));
		
		MenuItem[] items = {newGame, highScores, quit};
		
		menu = new Menu(items);
	}
	
	/**
	 * The x co-ordinate for the title graphic
	 * 
	 * @param gc The game container
	 * @return The x co-ordinate
	 */
	private float getTitleX(GameContainer gc) {
		return (gc.getWidth() - title.getWidth()) / 2;
	}
	
	/**
	 * The x co-ordinate for the menu
	 * 
	 * @param gc The game container
	 * @return The x co-ordinate
	 */
	private float getMenuX(GameContainer gc) {
		return (gc.getWidth() - menu.getWidth()) / 2;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics graphics)
			throws SlickException {
		title.draw(getTitleX(gc), TITLE_Y);
		
		menu.render(getMenuX(gc), MENU_Y);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			menu.next();
		} else if (input.isKeyPressed(Input.KEY_UP)) {
			menu.previous();
		} else if (input.isKeyPressed(Input.KEY_RETURN)) {
			switch (menu.getSelectedIndex()) {
			case 0:
				game.enterState(BlockGame.State.GAME_PLAY.ordinal());
				break;
			case 1:
				game.enterState(BlockGame.State.HIGH_SCORES.ordinal());
				break;
			case 2:
				gc.exit();
				break;
			}
		}
	}
}
