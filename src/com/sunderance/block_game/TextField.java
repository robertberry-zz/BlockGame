/**
 * 
 */
package com.sunderance.block_game;

import java.util.Set;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.UnicodeFont;

import com.google.common.base.Strings;

/**
 * Text field component
 * 
 * @author Robert Berry
 */
public class TextField implements KeyListener {
	private UnicodeFont font;
	
	private String value;
	
	private int maxLength;
	
	private Set<Character> allowedChars;

	private boolean enabled = true;
	
	private Character emptyChar;
	
	public TextField(UnicodeFont font, Set<Character> allowedChars, 
			int maxLength, String initialValue, Character emptyChar) {
		super();
		this.font = font;
		this.value = initialValue;
		this.allowedChars = allowedChars;
		this.maxLength = maxLength;
		this.emptyChar = emptyChar;
	}

	public String getValue() {
		return value;
	}

	public void render(float x, float y) {
		font.drawString(x, y, Strings.padEnd(value, maxLength, emptyChar));
	}
	
	public int getWidth() {
		return font.getWidth(Strings.repeat(" ", maxLength));
	}
	
	public int getHeight() {
		return font.getHeight(" ");
	}

	@Override
	public void inputEnded() {
		// do nothing
	}

	@Override
	public void inputStarted() {
		// do nothing
	}


	@Override
	public boolean isAcceptingInput() {
		return enabled;
	}

	@Override
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_BACK && !value.isEmpty()) {
			value = value.substring(0, value.length() - 1);
		} else if (value.length() < maxLength && 
				allowedChars.contains(new Character(c))) {
			value += c;
		}
	}

	@Override
	public void keyReleased(int key, char c) {
		// do nothing
	}


	@Override
	public void setInput(Input input) {
		// do nothing
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void enable() {
		this.enabled  = true;
	}
	
	public void disable() {
		this.enabled = false;
	}
}
