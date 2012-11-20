package com.sunderance.block_game.states;

import java.util.Observable;
import java.util.Observer;
import java.util.SortedSet;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.StateBasedGame;

import com.sunderance.block_game.Block;
import com.sunderance.block_game.BlockFactory;
import com.sunderance.block_game.BlockGame;
import com.sunderance.block_game.BlockGrid;
import com.sunderance.block_game.EffectsManager;
import com.sunderance.block_game.LevelLabel;
import com.sunderance.block_game.LineClearedEffect;
import com.sunderance.block_game.NextBlockBox;
import com.sunderance.block_game.ScoreCounter;
import com.sunderance.block_game.ScoreEffect;
import com.sunderance.block_game.events.LevelUpEvent;
import com.sunderance.block_game.events.LinesClearedEvent;
import com.sunderance.block_game.events.NoLinesClearedEvent;

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
	
	private UnicodeFont smallFont;
	
	private Image linesClearedOverlay;
	
	private static final int BASE_FRAMES_PER_DROP = 30;
	
	private static final int GRID_TOP_LEFT_X = 20;
	private static final int GRID_TOP_LEFT_Y = -44;
	
	private static final String NEXT_TEXT = "Next block";
	
	private static final int NEXT_TEXT_X = 380;
	private static final int NEXT_TEXT_Y = 115;
	
	private static final int NEXT_BOX_X = 380;
	private static final int NEXT_BOX_Y = 155;
	
	private static final int BLOCK_SIZE = 32;
	private static final int COLUMNS = 10;
	private static final int ROWS = 22;
	
	private static final int SCORE_X = 540;
	private static final int SCORE_Y = 52;
	
	private static final String SCORE_TEXT = "Score";
	
	private static final int SCORE_TEXT_X = 380;
	private static final int SCORE_TEXT_Y = 20;
	
	private static final int LEVEL_X = 380;
	private static final int LEVEL_Y = 340;
	
	private static final int BLOCK_DROP_SCORE = 10;
	private static final int SCORE_PER_LINE = 100;
	private static final int FOUR_LINE_BONUS = 400;
	private static final int DOUBLE_FOUR_LINE_BONUS = 800;
	
	private static final String OVERLAY_PATH = "res/lines-cleared-overlay.png";
	
	private boolean fourLineBonus = false;
	
	private int framesSinceDrop;
	
	private boolean softDrop = false;
	
	private EffectsManager effects;
	
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
		// todo: refactor following so it all happens in 'reset', which is 
		// called every time a game begins anyway. Currently generates blocks
		// that are then thrown away when reset is called.
		nextBlock = blockFactory.random();
		nextBox = new NextBlockBox(NEXT_BOX_X, NEXT_BOX_Y, BLOCK_SIZE, 
				nextBlock);
		score = new ScoreCounter(SCORE_X, SCORE_Y, 
				((BlockGame) game).getMediumFont());
		score.addObserver(this);
		smallFont = ((BlockGame) game).getSmallFont();
		level = new LevelLabel(1, LEVEL_X, LEVEL_Y, smallFont);
		linesClearedOverlay = new Image(OVERLAY_PATH);
		effects = new EffectsManager();
	}
	
	/**
	 * Reset game
	 */
	public void reset() {
		effects = new EffectsManager();
		grid.clear();
		score.reset();
		level.reset();
		blockFactory.reset();
		setCurrentBlock(blockFactory.random());
		setNextBlock(blockFactory.random());
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
		smallFont.drawString(NEXT_TEXT_X, NEXT_TEXT_Y, NEXT_TEXT);
		smallFont.drawString(SCORE_TEXT_X, SCORE_TEXT_Y, SCORE_TEXT);
		effects.render(graphics);
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
		effects.frame();
		
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_RETURN)) {
			currentBlock = currentBlock.getGhostBlock();
			framesSinceDrop = BASE_FRAMES_PER_DROP;
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
			} else if (!nextBlock()) {
				game.enterState(BlockGame.State.GAME_OVER.ordinal());
			}
			
			framesSinceDrop = 0;
		} else {
			framesSinceDrop += 1;
		}
		
		if (input.isKeyPressed(Input.KEY_P)) {
			game.enterState(BlockGame.State.PAUSE.ordinal());
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
	 * 
	 * @return boolean True if block is consumed, false if there is no space in
	 *   grid.
	 */
	private boolean nextBlock() {
		if (grid.hasSpaceForBlock(currentBlock)) {
			grid.consume(currentBlock);
			this.setCurrentBlock(nextBlock);
			this.setNextBlock(blockFactory.random());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void update(Observable obs, Object event) {
		if (event instanceof LinesClearedEvent) {
			SortedSet<Integer> lines = ((LinesClearedEvent) event).getLines();

			for (Integer line : lines) {
				effects.add(new LineClearedEffect(grid, line, 
						linesClearedOverlay));
			}
			
			int numberLines = lines.size();
			int scoreValue = numberLines * SCORE_PER_LINE;
			
			if (numberLines == 4) {
				if (fourLineBonus) {
					scoreValue += DOUBLE_FOUR_LINE_BONUS;
				} else {
					scoreValue += FOUR_LINE_BONUS;
				}
				fourLineBonus = true;
			} else {
				fourLineBonus = false;
			}
			
			float scoreX = grid.getWidth() - 
					smallFont.getWidth(Integer.toString(scoreValue));
			
			float scoreY = grid.translateY(lines.last()) - BLOCK_SIZE;
			
			effects.add(new ScoreEffect(scoreValue, scoreX, scoreY,
					-2, smallFont, score));
			
		} else if (event instanceof NoLinesClearedEvent) {
			score.add(BLOCK_DROP_SCORE);
		} else if (event instanceof LevelUpEvent) {
			level.nextLevel();
		}
	}

	/**
	 * Current score
	 * 
	 * @return The score
	 */
	public int getScore() {
		return score.getValue();
	}
}
