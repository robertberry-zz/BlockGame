/**
 * 
 */
package com.sunderance.block_game;

/**
 * Given a block, which knows its own integer coordinates in block space,
 * maps those coordinates to x, y coordinates on the display
 * 
 * @author Robert Berry
 */
public interface BlockCoordinateMapper {
	/**
	 * Translate the y co-ordinate into pixels
	 * 
	 * @param x The x co-ordinate in grid space
	 * @return The x co-ordinate in pixels
	 */
	public float translateX(int x);
	
	/**
	 * Translate the y co-ordinate into pixels
	 * 
	 * @param y The y co-ordinate in grid space
	 * @return The y co-ordinate in pixels
	 */
	public float translateY(int y);
}
