package com.sunderance.block_game;

import java.util.Observable;
import java.util.Observer;
import java.util.SortedSet;

import org.newdawn.slick.Color;
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
	private BlockGrid grid;
	private NextBlockBox nextBox;
	
	private BlockFactory blockFactory;
	private Block currentBlock;
	private Block nextBlock;
	
	private ScoreCounter score;
	private LevelLabel level;
	
	private static final int BASE_FRAMES_PER_DROP = 30;
	
	private static final int GRID_TOP_LEFT_X = 20;
	private static final int GRID_TOP_LEFT_Y = -44;
	
	private static final int NEXT_BOX_X = 380;
	private static final int NEXT_BOX_Y = 100;
	
	private static final int BLOCK_SIZE = 32;
	private static final int COLUMNS = 10;
	private static final int ROWS = 22;
	
	private static final float SCORE_X = 500;
	private static final float SCORE_Y = 20;
	
	private static final float LEVEL_X = 380;
	private static final float LEVEL_Y = 600;
	
	private static final int SCORE_PER_LINE = 100;
	private static final int FOUR_LINE_BONUS = 400;
	
	private int framesSinceDrop;
	
	private boolean softDrop = false;
	
	public GamePlayState(int stateID) {
		super(stateID);
		framesSinceDrop = 0;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		grid = new BlockGrid(GRID_TOP_LEFT_X, GRID_TOP_LEFT_Y, BLOCK_SIZE,
				COLUMNS, ROWS);
		grid.addObserver(this);
		blockFactory = new BlockFactory(grid);
		setCurrentBlock(blockFactory.random());
		nextBlock = blockFactory.random();
		nextBox = new NextBlockBox(NEXT_BOX_X, NEXT_BOX_Y, BLOCK_SIZE, 
				nextBlock);
		score = new ScoreCounter(SCORE_X, SCORE_Y);
		score.addObserver(this);
		level = new LevelLabel(1, LEVEL_X, LEVEL_Y);
	}

	/**
	 * Sets the block as the next block to come into play
	 * 
	 * @param nextBlock The block
	 */
	private void setNextBlock(Block nextBlock) {
		this.nextBlock = nextBlock;
		nextBox.setBlock(this.nextBlock);
	}

	/**
	 * Brings the block into play as the current block
	 * 
	 * @param currentBlock The block
	 */
	private void setCurrentBlock(Block currentBlock) {
		this.currentBlock = currentBlock;
		currentBlock.setX(grid.getStartX());
		currentBlock.setY(grid.getStartY());
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics graphics)
			throws SlickException {
		// pointless and inefficient to render grid every time
		// todo: add logic to only render grid when it changes
		
		graphics.setColor(Color.darkGray);
		graphics.fillRect(GRID_TOP_LEFT_X, GRID_TOP_LEFT_Y, grid.getWidth(),
				grid.getHeight());
		
		grid.render();
		currentBlock.render(grid);
		score.render();
		nextBox.render(graphics);
		level.render();
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
		
		if (input.isKeyPressed(Input.KEY_RETURN)) {
			currentBlock = currentBlock.getGhostBlock();
		}
		
		if (input.isKeyDown(Input.KEY_SPACE)) {
			softDrop = true;
		} else {
			softDrop = false;
		}
		
		Block movement = getMovement(input);
		
		if (movement != null && grid.hasSpaceForBlock(movement)) {
			currentBlock = movement;
		}
		
		if (blockShouldDrop()) {
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
	
	/**
	 * Whether the block should drop in this frame
	 * 
	 * @return Should drop?
	 */
	private boolean blockShouldDrop() {
		return framesSinceDrop >= BASE_FRAMES_PER_DROP - level.getLevel() * 5 ||
				softDrop && framesSinceDrop >= 1;
	}

	/**
	 * Consume the current block into the grid and bring the next block into
	 * play
	 */
	private void nextBlock() {
		grid.consume(currentBlock);
		this.setCurrentBlock(nextBlock);
		this.setNextBlock(blockFactory.random());
	}

	@Override
	public void update(Observable obs, Object event) {
		if (event instanceof LinesClearedEvent) {
			SortedSet<Integer> lines = ((LinesClearedEvent) event).getLines();

			for (Integer line : lines) {
				grid.clearLine(line);
			}
			
			int numberLines = lines.size();
			
			score.add(numberLines * SCORE_PER_LINE);
			
			if (numberLines == 4) {
				score.add(FOUR_LINE_BONUS);
			}
		} else if (event instanceof LevelUpEvent) {
			level.nextLevel();
			System.out.println("Level up");
		}
	}
}
