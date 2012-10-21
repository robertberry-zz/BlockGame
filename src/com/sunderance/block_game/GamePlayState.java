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
	
	private static final int FRAMES_PER_DROP = 30;
	
	private static final int GRID_TOP_LEFT_X = 20;
	private static final int GRID_TOP_LEFT_Y = 20;
	private static final int BLOCK_SIZE = 32;
	private static final int COLUMNS = 12;
	private static final int ROWS = 18;
	
	private int framesSinceDrop;
	
	public GamePlayState(int stateID) {
		super(stateID);
		framesSinceDrop = FRAMES_PER_DROP;
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
		// pointless and inefficient to render grid every time
		// todo: add logic to only render grid when it changes
		grid.render();
		currentBlock.render();
	}
	
	/**
	 * Given the user's input, returns a movement for the block, or null if
	 * no movement is made
	 * 
	 * @param input The input object
	 * @return The movement
	 */
	public Block getMovement(Input input) {
		Block movement = null;
		
		if (input.isKeyPressed(Input.KEY_UP)) {
			movement = currentBlock.getLeftRotation();
		} else if (input.isKeyPressed(Input.KEY_DOWN)) {
			movement = currentBlock.getRightRotation();
		} else if (input.isKeyPressed(Input.KEY_LEFT)) {
			movement = currentBlock.getLeftMovement();
		} else if (input.isKeyPressed(Input.KEY_RIGHT)) {
			movement = currentBlock.getRightMovement();
		}
		
		return movement;
	}

	private void newBlock() {
		currentBlock = blockFactory.random();
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		Input input = gc.getInput();
		
		// just quit for now
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			//gc.exit();
		}
		
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			newBlock();
		}
		
		Block movement = getMovement(input);
		
		if (movement != null && grid.hasSpaceForBlock(movement)) {
			currentBlock = movement;
		}
		
		if (framesSinceDrop == FRAMES_PER_DROP) {
			Block drop = currentBlock.getDownMovement();
			
			if (grid.hasSpaceForBlock(drop)) {
				currentBlock = drop;
			} else {
				grid.consume(currentBlock);
				newBlock();
			}
			
			framesSinceDrop = 0;
		} else {
			framesSinceDrop += 1;
		}
	}
}
