/**
 * 
 */
package com.sunderance.block_game;

import java.util.Observable;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.Color;

import com.sunderance.block_game.events.LevelUpEvent;

/**
 * ScoreCounter class
 * 
 * @author Robert Berry
 */
public class ScoreCounter extends Observable {
	private static final Color colour = Color.white;

	private int score = 0;
	
	private float rightX;
	private float y;
	
	private static final int[] levelBoundaries = {
		1000, 3000, 6000, 10000, 15000, 21000
	};
	
	private UnicodeFont font;

	/**
	 * Creates a score counter with its rightmost edge at rightX, and its top
	 * at y
	 * 
	 * @param rightX Rightmost edge
	 * @param y Top
	 * @throws SlickException If the font cannot be loaded
	 */
	public ScoreCounter(float rightX, float y, UnicodeFont font) {
		super();
		this.rightX = rightX;
		this.y = y;
		this.font = font;
	}
	
	/**
	 * What level the score currently indicates (0-indexed)
	 * 
	 * @return The level
	 */
	private int getBoundaryIndex() {
		int i, max_i;
		
		for (i = 0, max_i = levelBoundaries.length; i < max_i; i++) {
			if (levelBoundaries[i] > score) {
				break;
			}
		}
		return i;
	}
	
	/**
	 * Adds to the score
	 * 
	 * @param n How much to add
	 */
	public void add(int n) {
		int oldBoundary = getBoundaryIndex();		
		score += n;
		if (getBoundaryIndex() != oldBoundary) {
			setChanged();
			notifyObservers(new LevelUpEvent());
		}
	}
	
	/**
	 * Renders the counter on the screen
	 */
	public void render() {
		String scoreStr = String.format("%06d", score);
		font.drawString(rightX - font.getWidth(scoreStr), y, scoreStr, colour);
	}

	/**
	 * Reset score to zero
	 */
	public void reset() {
		score = 0;
	}
}
