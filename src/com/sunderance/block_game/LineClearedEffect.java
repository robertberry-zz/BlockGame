/**
 * 
 */
package com.sunderance.block_game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * Highlight effect for when a bunch of lines are cleared
 * 
 * @author Robert Berry
 */
public class LineClearedEffect implements Effect {
	private float x;
	private float y;
	private float width;
	private float height;
	
	private BlockGrid grid;
	private int line;
	
	private Image overlay;

	public LineClearedEffect(BlockGrid grid, int line, Image overlay) {
		super();
		
		this.grid = grid;
		this.line = line;
		
		this.x = grid.translateX(0);
		this.y = grid.translateY(line);
		this.width = grid.getLineWidth();
		this.height = grid.getLineHeight();
		
		this.overlay = overlay;
	}
	
	/**
	 * @see com.sunderance.block_game.Effect#render()
	 */
	@Override
	public void render(Graphics graphics) {
		graphics.setColor(Color.decode("#006600"));
		graphics.fillRect(x, y, width, height, overlay, 0, 0);
	}

	/**
	 * @see com.sunderance.block_game.Effect#duration()
	 */
	@Override
	public int duration() {
		return 20;
	}

	@Override
	public void onFinish() {
		grid.clearLine(line);
	}
}
