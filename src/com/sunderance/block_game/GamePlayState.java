package com.sunderance.block_game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * In game state
 * 
 * @author Robert Berry
 * @version 0.1
 */
public class GamePlayState extends GameState {
	BlockGrid grid;
	BlockFactory blockFactory;
	Block currentBlock;
	Block nextBlock;
	
	private static final int GRID_TOP_LEFT_X = 20;
	private static final int GRID_TOP_LEFT_Y = 20;
	private static final int BLOCK_SIZE = 32;
	private static final int COLUMNS = 20;
	private static final int ROWS = 80;
	
	public GamePlayState(int stateID) {
		super(stateID);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		grid = new BlockGrid(GRID_TOP_LEFT_X, GRID_TOP_LEFT_Y, BLOCK_SIZE,
				COLUMNS, ROWS);
		blockFactory = new BlockFactory(grid);
		currentBlock = blockFactory.random();
		nextBlock = blockFactory.random();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics graphics)
			throws SlickException {

		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		Input input = gc.getInput();
		
		// just quit for now
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			gc.exit();
		}
	}
}
