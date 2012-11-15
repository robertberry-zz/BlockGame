/**
 * 
 */
package com.sunderance.block_game.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.StateBasedGame;

import com.sunderance.block_game.BlockGame;
import com.sunderance.block_game.ScoreTableView;

/**
 * High scores / game over screen
 * 
 * @author Robert Berry
 */
public class HighScoresState extends GameState {
	private ScoreTableView scoresView;
	
	private UnicodeFont font;
	
	private UnicodeFont smallFont;
	
	private static final String TITLE = "High Scores";
	
	private static final int TITLE_Y = 30;
	
	private int SCORES_TABLE_X;
	
	private static final int SCORES_TABLE_Y = 130;
	
	private int TITLE_X;
	
	private static final String CONTINUE_TEXT = "Press enter to return to menu";
	
	private static final int CONTINUE_TEXT_Y = 580;
	
	private int CONTINUE_TEXT_X;
	
	public HighScoresState(int stateID) {
		super(stateID);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		int width = gc.getWidth();
		
		font = ((BlockGame) game).getMediumFont();
		smallFont = ((BlockGame) game).getSmallFont();
		TITLE_X = (width - font.getWidth(TITLE)) / 2;
		CONTINUE_TEXT_X = (width - smallFont.getWidth(CONTINUE_TEXT)) / 2;
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame game) {
		// sometimes still has 'enter' from inputting high score name, which
		// makes this immediately quit
		// todo: figure out better way of dealing with this
		gc.getInput().clearKeyPressedRecord();
		
		scoresView = new ScoreTableView(((BlockGame) game).getScores(), 
				smallFont, (float) 1.4);
		
		SCORES_TABLE_X = (gc.getWidth() - scoresView.getWidth()) / 2;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics graphics)
			throws SlickException {	
		font.drawString(TITLE_X, TITLE_Y, TITLE);
		scoresView.render(SCORES_TABLE_X, SCORES_TABLE_Y);
		smallFont.drawString(CONTINUE_TEXT_X, CONTINUE_TEXT_Y, CONTINUE_TEXT);
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
