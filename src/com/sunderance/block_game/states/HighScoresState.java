/**
 * 
 */
package com.sunderance.block_game.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
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
	
	public HighScoresState(int stateID) {
		super(stateID);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		// do nothing for now
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame game) {
		scoresView = new ScoreTableView(((BlockGame) game).getScores(), 
		((BlockGame) game).getSmallFont(), (float) 1.4);	
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
