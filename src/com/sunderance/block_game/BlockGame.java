package com.sunderance.block_game;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

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
	
	private ScoreTable scores;
	
	public enum State {
		MAIN_MENU, GAME_PLAY, PAUSE, HIGH_SCORES, GAME_OVER
	}
	
	public BlockGame() {
		super("BlockGame");
	}
	
	private static final String SCORES_FILE_PATH = "data/scores.dat";
	
	public ScoreTable getScores() {
		if (scores == null) {
			if (hasScoresFile()) {
				loadScoresFile();
			} else {
				generateScoresFile();
			}
		}
		return scores;
	}
	
	/**
	 * Whether the given score is a high score
	 * 
	 * @param score The score
	 * @return Whether high
	 */
	public boolean isHighScore(int score) {
		return getScores().incorporates(score);
	}
	
	/**
	 * Adds the given name and score as a high score
	 * 
	 * @param name The name
	 * @param score The score
	 */
	public void addHighScore(String name, int score) {
		scores = scores.withScore(new ScoreTableEntry(name, score));
		saveScoresFile();
	}
	
	/**
	 * Whether the high scores file exists
	 * 
	 * @return Whether scores file exists
	 */
	private boolean hasScoresFile() {
		File file = new File(SCORES_FILE_PATH);
		
		return file.exists();
	}
	
	/**
	 * Generate a default scores file
	 */
	private void generateScoresFile() {
		scores = ScoreTable.fromDefaults();
		saveScoresFile();
	}
	
	private void saveScoresFile() {
		try {
			FileOutputStream output = new FileOutputStream(SCORES_FILE_PATH);
			scores.writeToFile(output);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void loadScoresFile() {
		try {
			FileInputStream input = new FileInputStream(SCORES_FILE_PATH);
			scores = ScoreTable.fromFile(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
