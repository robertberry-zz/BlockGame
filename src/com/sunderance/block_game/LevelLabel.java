/**
 * 
 */
package com.sunderance.block_game;

import java.awt.Font;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

/**
 * Label for the current level
 * 
 * @author Robert Berry
 */
public class LevelLabel {
	private static final int MAX_LEVEL = 6;
	
	private static final String FONT_FAMILY = "Arial";
	
	private static final int FONT_SIZE = 36;
	
	private int level;
	
	private float x;
	
	private float y;
	
	private UnicodeFont font;
	
	private String label;
	
	public LevelLabel(int level, float x, float y) throws SlickException {
		super();
		
		if (level > MAX_LEVEL) {
			throw new IllegalStateException(
					String.format("%d is the maximum allowed level", MAX_LEVEL)
					);
		}
		
		this.level = level;
		this.x = x;
		this.y = y;
		font = new UnicodeFont(new Font(FONT_FAMILY, Font.PLAIN, FONT_SIZE));
		font.addAsciiGlyphs();
		font.getEffects().add(new ColorEffect(java.awt.Color.white));
		font.loadGlyphs();
		
		updateLabel();
	}

	private void updateLabel() {
		label = String.format("Level %d", level);
	}
	
	public int getLevel() {
		return level;
	}

	public void nextLevel() {
		if (level == MAX_LEVEL) {
			throw new IllegalStateException(
					String.format("Already at maximum level (%d).", MAX_LEVEL));
		}
		
		this.level += 1;
		updateLabel();
	}

	public void render() {
		font.drawString(x, y, label);
	}
}
