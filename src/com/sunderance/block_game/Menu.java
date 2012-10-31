/**
 * 
 */
package com.sunderance.block_game;

import java.util.Arrays;
import java.util.List;

/**
 * A graphical menu
 * 
 * @author Robert Berry
 */
public class Menu {
	List<MenuItem> items;
	
	private static final float ITEM_MARGIN = 30;
	
	private int selected = 0;
	
	private int width = 0;
	
	/**
	 * Creates a menu at the given x and y co-ordinates with the given first
	 * menu item (all menus must contain at least one item).
	 * 
	 * @param x The x co-ordinate
	 * @param y The y co-ordinate
	 * @param firstItem The first item
	 */
	public Menu(MenuItem[] items) {
		super();

		assert(items.length > 0);
		
		this.items = Arrays.asList(items);
		this.items.get(0).select();
	}
	
	/**
	 * The currently selected menu item
	 * 
	 * @return The item
	 */
	private MenuItem getSelectedItem() {
		return items.get(selected);
	}
	
	/**
	 * Number of items in the menu
	 * 
	 * @return The number of items
	 */
	public int size() {
		return items.size();
	}
	
	/**
	 * Select the next menu item
	 */
	public void next() {
		getSelectedItem().deselect();
		selected = (selected + 1) % size();
		getSelectedItem().select();
	}
	
	/**
	 * Select the previous menu item
	 */
	public void previous() {
		getSelectedItem().deselect();
		if (selected == 0) {
			selected = size() - 1;
		} else {
			selected--;
		}
		getSelectedItem().select();
	}
	
	/**
	 * The index of the currently selected menu item
	 * 
	 * @return The index
	 */
	public int getSelectedIndex() {
		return selected;
	}
	
	/**
	 * The width of the menu
	 * 
	 * @return The width
	 */
	public int getWidth() {
		if (width == 0) {
			for (MenuItem item : items) {
				int itemWidth = item.getWidth();
				
				if (itemWidth > width) {
					width = itemWidth;
				}
			}
		}
		return width;
	}
	
	/**
	 * Renders the menu and its items
	 */
	public void render(float x, float y) {
		for (MenuItem item : items) {
			item.render(x, y);
			
			y += item.getHeight() + ITEM_MARGIN;
		}
	}
}
