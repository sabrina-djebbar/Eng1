package com.yorkpiratesgame.io;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import screens.mainMenuScreen;

public class YorkPirates extends Game {

	public SpriteBatch batch;
	public static int HEIGHT = 960;
	public static int WIDTH = 1200;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new mainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	}
