/**
 * 
 */
package com.sunderance.block_game;

import org.newdawn.slick.UnicodeFont;

import com.google.common.base.Strings;

/**
 * View for score table class
 * 
 * @author Robert Berry
 */
public class ScoreTableView {
	private ScoreTable scores;
	
	private UnicodeFont font;
	
	private float lineHeight;

	/**
	 * Constructs a ScoreTableView from the given scores table, with the given
	 * font, and with the given line height. The line height should be a 
	 * fraction of the font height. E.g., 1.5 would mean that for every line
	 * there is a half-line margin before the next.
	 * 
	 * @param scores The score table
	 * @param font The font
	 * @param lineHeight The line height
	 */
	public ScoreTableView(ScoreTable scores, UnicodeFont font, 
			float lineHeight) {
		super();
		this.scores = scores;
		this.font = font;
		this.lineHeight = lineHeight;
	}
	
	/**
	 * Renders the high scores at the given x and y co-ordinates
	 * 
	 * @param x The x co-ordinate
	 * @param y The y co-ordinate
	 */
	public void render(float x, float y) {
		float skipY = font.getLineHeight() * lineHeight;
		
		for (ScoreTableEntry entry : scores) {
			String line = String.format("%-" + ScoreTable.MAX_NAME_LENGTH + 
					"s %10d", entry.getName(), 
					entry.getScore());
			font.drawString(x, y, line);
			font.getHeight(line);
			y += skipY;
		}
	}
	
	/**
	 * Width of the score table
	 * 
	 * @return The width
	 */
	public int getWidth() {
		// 10 for the size of the number and 1 for the space
		String blankLine = Strings.repeat(" ", ScoreTable.MAX_NAME_LENGTH + 11);
		// todo, cache this value
		return font.getWidth(blankLine);
	}
}
