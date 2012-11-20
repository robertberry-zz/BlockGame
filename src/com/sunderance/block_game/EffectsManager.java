/**
 * 
 */
package com.sunderance.block_game;

import java.util.LinkedList;
import java.util.TreeMap;

import org.newdawn.slick.Graphics;

/**
 * Manages the lifetime of effects that implement a 'duration' method
 * 
 * @author Robert Berry
 */
public class EffectsManager {

	private TreeMap<Integer, LinkedList<Effect>> effects =
		new TreeMap<Integer, LinkedList<Effect>>();
	
	private int framesAlive = 0;
	
	/**
	 * Adds an effect to the manager
	 * 
	 * @param effect
	 */
	public void add(Effect effect) {
		int diesAt = framesAlive + effect.duration();
		
		if (!effects.containsKey(diesAt)) {
			effects.put(diesAt, new LinkedList<Effect>());
		}
		
		effects.get(diesAt).add(effect);
	}
	
	/**
	 * Renders all of the living effects being managed
	 * 
	 * @param graphics The graphics manager
	 */
	public void render(Graphics graphics) {
		for (LinkedList<Effect> effectsList : effects.values()) {
			for (Effect effect : effectsList) {
				effect.render(graphics);
			}
		}
	}
	
	/**
	 * Increments the frame count, removing any now dead effects
	 */
	public void frame() {
		framesAlive += 1;
		
		if (effects.containsKey(framesAlive)) {
			for (Effect effect : effects.get(framesAlive)) {
				effect.onFinish();
			}
			
			effects.remove(framesAlive);
		}
	}
}
