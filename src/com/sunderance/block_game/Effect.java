/**
 * 
 */
package com.sunderance.block_game;

import org.newdawn.slick.Graphics;

/**
 * Interface for a timed effect
 * 
 * @author Robert Berry
 */
public interface Effect {
	/**
	 * Renders the effect
	 */
	public void render(Graphics graphics);
	
	/**
	 * Duration of the effect in frames
	 * 
	 * @return Number of frames
	 */
	public int duration();
	
	/**
	 * Method invoked when the effect is complete
	 */
	public void onFinish();
}
