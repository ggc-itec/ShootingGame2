package edu.ggc.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.ggc.game.screens.Credits;
import edu.ggc.game.screens.GameScreen;
import edu.ggc.game.screens.MainMenu;

/**
 * Changed the generated class to extend Game instead of ApplicationAdapter.
 *
 */
public class ShootingGame extends Game {
    //receives drawing commands 
	SpriteBatch batch;
	//reference to an image
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		
		setScreen(new MainMenu());
	}
		
	
	/*
     * Remove this method to delegate rendering to another Screen.
     */
	
	/*	
    @Override
	public void render () {
	    //clear screen to RGBA
		Gdx.gl.glClearColor(0, 1, 0, 1);
		//Tells OpenGL which buffer to clear
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//(0, 0) is from the bottom left of the screen
		batch.draw(img, 100, 0);
		batch.end();
	}*/
	
	@Override
	public void dispose()
	{
	}
}
