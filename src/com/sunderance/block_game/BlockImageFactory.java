/**
 * BlockImageFactory
 */
package com.sunderance.block_game;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Factory for generating component images of blocks
 * 
 * @author Robert Berry
 * @version 0.1
 */
public class BlockImageFactory {
	private Image greenBlock;
	private Image redBlock;
	private Image yellowBlock;
	private Image blueBlock;
	private Random colourGenerator;
	
	private static final int NUMBER_COLOURS = 4;
	
	public BlockImageFactory() throws SlickException {
		greenBlock = new Image("res/blocks/green.png");
		redBlock = new Image("res/blocks/red.png");
		yellowBlock = new Image("res/blocks/yellow.png");
		blueBlock = new Image("res/blocks/blue.png");
		colourGenerator = new Random();
	}
	
	/**
	 * Generates a random image
	 * 
	 * @return The image
	 */
	public Image random() {
		int colour = colourGenerator.nextInt(NUMBER_COLOURS);
		
		switch (colour) {
		case 0:
			return green();
		case 1:
			return red();
		case 2:
			return yellow();
		default:
			return blue();
		}
	}
	
	/**
	 * Generates n random images
	 * 
	 * @param n The number of images
	 * @return The images
	 */
	public ArrayList<Image> random(int n) {
		ArrayList<Image> images = new ArrayList<Image>();
		
		for (int i = 0; i < n; i++) {
			images.add(random());
		}
		
		return images;
	}
	
	/**
	 * Green block image
	 * 
	 * @return The image
	 */
	public Image green() {
		return greenBlock;
	}
	
	/**
	 * Red block image
	 * 
	 * @return The image
	 */
	public Image red() {
		return redBlock;
	}
	
	/**
	 * Yellow block image
	 * 
	 * @return The image
	 */
	public Image yellow() {
		return yellowBlock;
	}
	
	/**
	 * Blue block image
	 * 
	 * @return The image
	 */
	public Image blue() {
		return blueBlock;
	}
}
