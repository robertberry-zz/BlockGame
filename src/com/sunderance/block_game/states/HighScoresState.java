/**
 * 
 */
package com.sunderance.block_game.states;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.sunderance.block_game.BlockGame;
import com.sunderance.block_game.ScoreTable;
import com.sunderance.block_game.ScoreTableView;

/**
 * High scores / game over screen
 * 
 * @author Robert Berry
 */
public class HighScoresState extends GameState {
	private static final String SCORES_FILE_PATH = "data/scores.dat";

	private ScoreTable scores;
	private ScoreTableView scoresView;
	
	public HighScoresState(int stateID) {
		super(stateID);
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
		
		try {
			FileOutputStream output = new FileOutputStream(SCORES_FILE_PATH);
			scores.writeToFile(output);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		if (!hasScoresFile()) {
			generateScoresFile();
		} else {
			loadScoresFile();
		}

		scoresView = new ScoreTableView(scores, 
				((BlockGame) game).getSmallFont(), (float) 1.4);
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

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics graphics)
			throws SlickException {
		scoresView.render(20, 20);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_ENTER) ||
				input.isKeyPressed(Input.KEY_SPACE)) {
			game.enterState(BlockGame.State.MAIN_MENU.ordinal());
		}
	}
}
