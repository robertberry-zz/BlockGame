package com.sunderance.block_game;

import java.util.Observable;
import java.util.Observer;
import java.util.SortedSet;

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
public class GamePlayState extends GameState implements Observer {
	BlockGrid grid;
	BlockFactory blockFactory;
	Block currentBlock;
	Block nextBlock;
	
	ScoreCounter score;
	
	private static final int FRAMES_PER_DROP = 30;
	
	private static final int GRID_TOP_LEFT_X = 20;
	private static final int GRID_TOP_LEFT_Y = -44;
	private static final int BLOCK_SIZE = 32;
	private static final int COLUMNS = 10;
	private static final int ROWS = 22;
	
	private static final float SCORE_X = 500;
	private static final float SCORE_Y = 20;
	
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
		grid.addObserver(this);
		blockFactory = new BlockFactory(grid);
		currentBlock = blockFactory.random();
		nextBlock = blockFactory.random();
		score = new ScoreCounter(SCORE_X, SCORE_Y);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics graphics)
			throws SlickException {
		// pointless and inefficient to render grid every time
		// todo: add logic to only render grid when it changes
		grid.render();
		currentBlock.render();
		score.render();
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
	
	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			currentBlock = currentBlock.getGhostBlock();
			nextBlock();
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
				nextBlock();
			}
			
			framesSinceDrop = 0;
		} else {
			framesSinceDrop += 1;
		}
	}

	private void nextBlock() {
		grid.consume(currentBlock);
		currentBlock = nextBlock;
		nextBlock = blockFactory.random();
	}

	@Override
	public void update(Observable obs, Object event) {
		if (event instanceof LinesClearedEvent) {
			SortedSet<Integer> lines = ((LinesClearedEvent) event).getLines();

			for (Integer line : lines) {
				grid.clearLine(line);
				score.add(10);
			}
		}
	}
}
