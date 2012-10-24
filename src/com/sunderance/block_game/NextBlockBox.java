/**
 * 
 */
package com.sunderance.block_game;

/**
 * Box displaying the next block to come into play
 * 
 * @author Robert Berry
 */
public class NextBlockBox implements BlockCoordinateMapper {
	Block block;
	
	float x;
	
	float y;
	
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
		return this.x + x * blockSize;
	}

	/**
	 * @see com.sunderance.block_game.BlockCoordinateMapper#translateY(int)
	 */
	@Override
	public float translateY(int y) {
		return this.y + y * blockSize;
	}

	public void render() {
		// render a box?
		
		this.block.render(this);
	}
}
