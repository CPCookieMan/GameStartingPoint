package com.dakotacoders.mygame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MyGame extends Game
{
	public int width = 800;
	public int height = 480;

	public OrthographicCamera camera;
	public SpriteBatch batch;
	public Texture logoImage;
	public Rectangle logo;

	@Override
	public void create()
	{
		// This method gets called when the game is started.
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);

		batch = new SpriteBatch();

		// After this is where you will be initializing all of your game's objects, textures, sounds, etc.

		logoImage = new Texture(Gdx.files.internal("logo.png"));

		logo = new Rectangle();
		logo.width = logoImage.getWidth();
		logo.height = logoImage.getHeight();
		logo.x = width / 2 - logo.width / 2;
		logo.y = height / 2 - logo.height / 2;
	}

	public void controls()
	{
		// Your controller code should go here.
	}

	public void logic()
	{
		// Your logic code should go here.
	}

	public void draw()
	{
		// Your rendering code should go here.
		// Want help? Try here: https://github.com/libgdx/libgdx/wiki/A-simple-game
		batch.draw(logoImage, logo.x, logo.y, logo.width, logo.height);
	}

	@Override
	public void pause()
	{
		// This is called when Android pauses the game.
		// For instance, if you go into multitasking and choose another app.
	}

	@Override
	public void resume()
	{
		// This is called when Android resumes the game.
		// You might want to make the pause screen pop up so things don't
		// start happening immediately upon getting focus again.
	}

	@Override
	public void dispose()
	{
		// Clean up after ourselves when the game exits. Dirty, dirty objects!
		logoImage.dispose();
		batch.dispose();
	}

	@Override
	public void render()
	{
		// This method is called once every time the screen refreshes.
		// I've set it up so that generally you don't need to touch it.
		// It manages the controls, logic, and draw methods for you.

		controls();
		logic();

		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		draw();
		batch.end();
	}
}
