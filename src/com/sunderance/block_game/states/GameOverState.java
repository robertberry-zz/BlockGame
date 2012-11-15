/**
 * 
 */
package com.sunderance.block_game.states;

import java.util.TreeSet;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.StateBasedGame;

import com.google.common.collect.Lists;
import com.sunderance.block_game.BlockGame;
import com.sunderance.block_game.ScoreTable;
import com.sunderance.block_game.TextField;

/**
 * Game over state
 * 
 * @author Robert Berry
 */
public class GameOverState extends GameState {
	private int GAME_OVER_TEXT_X;
	
	private static final int GAME_OVER_TEXT_Y = 100;
	
	private static final String GAME_OVER_TEXT = "Game over!";
	
	private static final String HIGH_SCORE_TEXT = "High score!";
	
	private static final String ENTER_NAME_TEXT = "Enter name:";
	
	private int ENTER_NAME_TEXT_X;
	
	private static final int ENTER_NAME_TEXT_Y = 260;
	
	private int HIGH_SCORE_TEXT_X;
	
	private static final int HIGH_SCORE_TEXT_Y = 200;
	
	private int NAME_FIELD_X;
	
	private static final int NAME_FIELD_Y = 320;
	
	private TextField nameField;
	
	private org.newdawn.slick.state.GameState gamePlay;
	
	private UnicodeFont font;
	
	private UnicodeFont smallFont;
	
	private boolean highScore;
	
	private int score;
	
	/**
	 * Constructs the state with the given state ID
	 * 
	 * @param stateID
	 */
	public GameOverState(int stateID) {
		super(stateID);
	}

	/**
	 * @see org.newdawn.slick.state.GameState#init(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		gamePlay = game.getState(BlockGame.State.GAME_PLAY.ordinal());
		font = ((BlockGame) game).getMediumFont();
		smallFont = ((BlockGame) game).getSmallFont();
		
		TreeSet<Character> allowedChars =
				new TreeSet<Character>(Lists.charactersOf(ScoreTable.ALLOWED_CHARS));
		
		nameField = new TextField(font, allowedChars,
				ScoreTable.MAX_NAME_LENGTH, "", '_');
		
		Input input = gc.getInput();
		input.addKeyListener(nameField);
		
		int width = gc.getWidth();
		
		NAME_FIELD_X = (width - nameField.getWidth()) / 2;
		GAME_OVER_TEXT_X = (width - font.getWidth(GAME_OVER_TEXT)) / 2;
		HIGH_SCORE_TEXT_X = (width - font.getWidth(HIGH_SCORE_TEXT)) / 2;
		ENTER_NAME_TEXT_X = (width - smallFont.getWidth(ENTER_NAME_TEXT)) / 2;
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame game) {
		nameField.setValue("");
				
		score = ((GamePlayState) 
				game.getState(BlockGame.State.GAME_PLAY.ordinal())).getScore();
		
		highScore = ((BlockGame) game).isHighScore(score);

		if (highScore) {
			nameField.enable();
		} else {
			nameField.disable();
		}
	}

	/**
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics graphics)
			throws SlickException {
		gamePlay.render(gc, game, graphics);
		
		graphics.setColor(Color.black);
		graphics.fillRect(0, GAME_OVER_TEXT_Y - 10, gc.getWidth(), 
				font.getHeight(GAME_OVER_TEXT) + 30);

		font.drawString(GAME_OVER_TEXT_X, GAME_OVER_TEXT_Y, GAME_OVER_TEXT);
		
		if (highScore) {
			graphics.fillRect(0, HIGH_SCORE_TEXT_Y - 10, gc.getWidth(), 
					NAME_FIELD_Y - HIGH_SCORE_TEXT_Y + 60);
			
			font.drawString(HIGH_SCORE_TEXT_X, HIGH_SCORE_TEXT_Y,
					HIGH_SCORE_TEXT);
			smallFont.drawString(ENTER_NAME_TEXT_X, ENTER_NAME_TEXT_Y, 
					ENTER_NAME_TEXT);
			
			nameField.render(NAME_FIELD_X, NAME_FIELD_Y);
		}
	}

	/**
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			if (highScore) {
				nameField.disable();
				String name = nameField.getValue();
				
				if (name == "") {
					name = "Anon";
				}
				
				((BlockGame) game).addHighScore(name, score);
			}
			
			game.enterState(BlockGame.State.HIGH_SCORES.ordinal());
		}
	}
}
