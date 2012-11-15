/**
 * 
 */
package com.sunderance.block_game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Box displaying the next block to come into play
 * 
 * @author Robert Berry
 */
public class NextBlockBox implements BlockCoordinateMapper {
	Block block;
	
	float x;
	
	float y;
	
	private static final int MAX_BLOCKS_WIDE = 4;

	private static final int MAX_BLOCKS_HIGH = 4;
	
	int blockSize;

	public NextBlockBox(float x, float y, int blockSize, Block block) {
		super();
		this.x = x;
		this.y = y;
		this.blockSize = blockSize;
		this.block = block;
	}

	/**
	 * Sets the next block
	 * 
	 * @param block The block
	 */
	public void setBlock(Block block) {
		this.block = block;
	}

	/**
	 * @see com.sunderance.block_game.BlockCoordinateMapper#translateX(int)
	 */
	@Override
	public float translateX(int x) {
		// not proud of this horrid code. it's a workaround. in retrospect,
		// I should have defined the block components as the co-ordinates of the
		// block items relative to the centre.
		return this.getCentreX() + x * blockSize - 
				block.getCentreX() * blockSize +
				(block.getWidth() % 2 == 0 ? blockSize / 2 : 0);
	}

	/**
	 * @see com.sunderance.block_game.BlockCoordinateMapper#translateY(int)
	 */
	@Override
	public float translateY(int y) {
		return this.getCentreY() - y * blockSize +
				(block.getHeight() % 2 == 0 ? + blockSize / 2 : 0);
	}

	public void render(Graphics graphics) {
		graphics.setColor(Color.darkGray);
		graphics.fillRect(x, y, getWidth() + blockSize, 
				getHeight() + blockSize);
		this.block.render(this);
	}

	private float getWidth() {
		return blockSize * MAX_BLOCKS_WIDE;
	}

	private float getHeight() {
		return blockSize * MAX_BLOCKS_HIGH;
	}
	
	public float getCentreX() {
		return x + getWidth() / 2;
	}
	
	public float getCentreY() {
		return y + getHeight() / 2;
	}
}
