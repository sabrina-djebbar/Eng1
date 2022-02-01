package com.yorkpiratesgame.io;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import screens.mainMenuScreen;

/** This class acts as the window for our game.
 *  Where all screens are passed upon actions to call for combat or traversal. **/
public class YorkPirates extends Game {

	/** The SpriteBatch holds all that will be displayed
	 *  Acting as storage for images, tile-maps and more. **/
	public SpriteBatch batch;
	public Music music;
	/** As the primary platform for this game is desktop the default window size is as follows **/
	public static int HEIGHT = 960;
	public static int WIDTH = 1200;

	/** Initialise batch and set the screen to main menu on startup **/
	@Override
	public void create () {
		music = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.mp3"));
		music.setLooping(true);
		music.play();
		batch = new SpriteBatch();
		this.setScreen(new mainMenuScreen(this));
	}

	/** Renders the game **/
	@Override
	public void render () {
		music.play();
		super.render();
	}

	/** Disposes of all assets used when window is closed **/
	@Override
	public void dispose(){
		batch.dispose();
	}
}