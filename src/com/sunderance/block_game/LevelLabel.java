/**
 * 
 */
package com.sunderance.block_game;


import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;

/**
 * Label for the current level
 * 
 * @author Robert Berry
 */
public class LevelLabel {
	private static final int MAX_LEVEL = 6;
	
	private int level;
	
	private float x;
	
	private float y;
	
	private UnicodeFont font;
	
	private String label;
	
	/**
	 * Creates a level label for the given level at the given x and y
	 * co-ordinates
	 * 
	 * @param level The level
	 * @param x The x co-ordinate
	 * @param y The y co-ordinate
	 * @throws SlickException If the font cannot be loaded
	 */
	public LevelLabel(int level, float x, float y, UnicodeFont font) {
		super();
		
		if (level > MAX_LEVEL) {
			throw new IllegalStateException(
					String.format("%d is the maximum allowed level", MAX_LEVEL)
					);
		}
		
		this.level = level;
		this.x = x;
		this.y = y;
		this.font = font;
		
		updateLabel();
	}

	/**
	 * Updates the text to match the current level
	 */
	private void updateLabel() {
		label = String.format("Level %d", level);
	}
	
	/**
	 * The level being displayed
	 * 
	 * @return The level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Increments the level
	 */
	public void nextLevel() {
		if (level == MAX_LEVEL) {
			throw new IllegalStateException(
					String.format("Already at maximum level (%d).", MAX_LEVEL));
		}
		
		this.level += 1;
		updateLabel();
	}

	/**
	 * Renders the label on the screen
	 */
	public void render() {
		font.drawString(x, y, label);
	}

	/**
	 * Resets level to 1.
	 */
	public void reset() {
		this.level = 1;
	}
}
