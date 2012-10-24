/**
 * 
 */
package com.sunderance.block_game;

import java.awt.Font;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.font.effects.ColorEffect;

/**
 * ScoreCounter class
 * 
 * @author Robert Berry
 */
public class ScoreCounter {
	private static final int FONT_SIZE = 36;
	private static final String FONT_FAMILY = "Arial";
	private static final Color colour = Color.white;

	private int score = 0;
	
	private float rightX;
	private float y;
	
	private UnicodeFont font;

	/**
	 * Creates a score counter with its rightmost edge at rightX, and its top
	 * at y
	 * 
	 * @param rightX Rightmost edge
	 * @param y Top
	 * @throws SlickException If the font cannot be loaded
	 */
	public ScoreCounter(float rightX, float y) throws SlickException {
		super();
		this.rightX = rightX;
		this.y = y;
		font = new UnicodeFont(new Font(FONT_FAMILY, Font.PLAIN, FONT_SIZE));
		font.addAsciiGlyphs();
		font.getEffects().add(new ColorEffect(java.awt.Color.white));
		font.loadGlyphs();
	}
	
	/**
	 * Adds to the score
	 * 
	 * @param n How much to add
	 */
	public void add(int n) {
		score += n;
	}
	
	/**
	 * Renders the counter on the screen
	 */
	public void render() {
		String scoreStr = String.format("%06d", score);
		font.drawString(rightX - font.getWidth(scoreStr), y, scoreStr, colour);
	}
}
