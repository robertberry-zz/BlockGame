package com.sunderance.block_game;

import java.awt.Font;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.StateBasedGame;

import com.sunderance.block_game.states.GamePlayState;
import com.sunderance.block_game.states.HighScoresState;
import com.sunderance.block_game.states.MainMenuState;
import com.sunderance.block_game.states.PauseState;
import com.sunderance.block_game.states.GameOverState;

public class BlockGame extends StateBasedGame {
	private static final int WIDTH = 600;
	private static final int HEIGHT = 680;
	private static final boolean FULL_SCREEN = false;
	private static final int FRAMES_PER_SECOND = 60;
	
	private static final String FONT_FAMILY = "Courier";
	private static final int FONT_MEDIUM_SIZE = 36;
	private static final int FONT_SMALL_SIZE = 24;
	
	private UnicodeFont smallFont;
	private UnicodeFont mediumFont;
	
	public enum State {
		MAIN_MENU, GAME_PLAY, PAUSE, HIGH_SCORES, GAME_OVER
	}
	
	public BlockGame() {
		super("BlockGame");
	}
	
	private UnicodeFont loadFont(int fontSize) {
		UnicodeFont font = new UnicodeFont(new Font(FONT_FAMILY, Font.PLAIN, 
					fontSize));
		font.addAsciiGlyphs();
		font.getEffects().add(new ColorEffect(java.awt.Color.white));
		try {
			font.loadGlyphs();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return font;
	}
	
	/**
	 * Small font used in game
	 * 
	 * @return The font
	 */
	public UnicodeFont getSmallFont() {
		if (smallFont == null) {
			smallFont = loadFont(FONT_SMALL_SIZE);
		}
		return smallFont;
	}
	
	/**
	 * Medium font used in game
	 * 
	 * @return The font
	 */
	public UnicodeFont getMediumFont() {
		if (mediumFont == null) {
			mediumFont = loadFont(FONT_MEDIUM_SIZE);
		}
		return mediumFont;
	}

	/**
	 * Runs the game
	 * 
	 * @param args Command-line arguments
	 * @throws SlickException
	 */
	public static void main(String[] args) throws SlickException {
         AppGameContainer app = new AppGameContainer(new BlockGame());
         
         app.setDisplayMode(WIDTH, HEIGHT, FULL_SCREEN);
         app.setTargetFrameRate(FRAMES_PER_SECOND);
         app.start();
    }
	
	/**
	 * Sets up the states
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.addState(new MainMenuState(State.MAIN_MENU.ordinal()));
		this.addState(new GamePlayState(State.GAME_PLAY.ordinal()));
		this.addState(new PauseState(State.PAUSE.ordinal()));
		this.addState(new HighScoresState(State.HIGH_SCORES.ordinal()));
		this.addState(new GameOverState(State.GAME_OVER.ordinal()));
	}
}
