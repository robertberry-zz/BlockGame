package com.sunderance.block_game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.UnicodeFont;

public class ScoreEffect implements Effect {
	private final int score;
	
	private String scoreString;
	
	private float x;
	
	private float y;
	
	private float y_velocity;
	
	private UnicodeFont font;
	
	private ScoreCounter scoreCounter;

	public ScoreEffect(int score, float x, float y, float y_velocity,
			UnicodeFont font, ScoreCounter scoreCounter) {
		super();
		this.score = score;
		this.scoreString = Integer.toString(score);
		this.x = x;
		this.y = y;
		this.y_velocity = y_velocity;
		this.font = font;
		this.scoreCounter = scoreCounter;
	}

	@Override
	public void render(Graphics graphics) {
		y += y_velocity;
		
		font.drawString(x, y, scoreString);
	}

	@Override
	public int duration() {
		return 40;
	}

	@Override
	public void onFinish() {
		scoreCounter.add(score);
	}
}
