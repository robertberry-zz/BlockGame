/**
 * 
 */
package com.sunderance.block_game;

import org.newdawn.slick.Image;

/**
 * The MenuItem
 * 
 * @author Robert Berry
 */
public class MenuItem {
	private Image background;
	private Image foreground;
	
	private boolean selected = false;
	
	/**
	 * Creates a menu item with the given background (for when not selected)
	 * and foreground (for when selected) images.
	 * 
	 * @param background The background image
	 * @param foreground The foreground image
	 */
	public MenuItem(Image background, Image foreground) {
		super();
		this.background = background;
		this.foreground = foreground;
	}
	
	/**
	 * Selects the menu item
	 */
	public void select() {
		selected = true;
	}
	
	/**
	 * Deselects the menu item
	 */
	public void deselect() {
		selected = false;
	}

	/**
	 * The image to render for the menu item
	 * 
	 * @return The image
	 */
	private Image getImage() {
		return selected ? foreground : background;
	}
	
	/**
	 * Renders the item at the given x and y co-ordinates
	 * 
	 * @param x The x co-ordinate
	 * @param y The y co-ordinate
	 */
	public void render(float x, float y) {
		getImage().draw(x, y);
	}

	/**
	 * The height of the item
	 * 
	 * @return The height
	 */
	public int getHeight() {
		return getImage().getHeight();
	}
	
	/**
	 * The width of the item
	 * 
	 * @return The width
	 */
	public int getWidth() {
		return getImage().getWidth();
	}
}
