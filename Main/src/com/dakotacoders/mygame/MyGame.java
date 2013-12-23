package com.dakotacoders.mygame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class MyGame extends Game
{
	public static float delta = 0.2f;
	public int width = 800;
	public int height = 480;

	public OrthographicCamera camera;
	public SpriteBatch batch;
	public Player player;

	public ArrayList<Entity> entities = new ArrayList<Entity>();

	@Override
	public void create()
	{
		// This method gets called when the game is started.
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);

		batch = new SpriteBatch();

		// After this is where you will be initializing all of your game's objects, textures, sounds, etc.

		Entity logo = new Entity(new Texture(Gdx.files.internal("logo.png")));
		logo.width = logo.texture.getWidth();
		logo.height = logo.texture.getHeight();
		logo.x = width / 2 - logo.width / 2;
		logo.y = height / 2 - logo.height / 2;
		entities.add(logo);

		player = new Player();
		entities.add(player);
	}

	public void controls()
	{
		// Your controller code should go here.
		player.dx = 0;
		player.dy = 0;
		player.isCrouching = false;
		if (Gdx.input.isKeyPressed(Input.Keys.A))
		{
			player.dx = -player.speed;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D))
		{
			player.dx = player.speed;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.D))
		{
			player.dx = 0;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S) && !player.isJumping())
		{
			player.isCrouching = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && !player.isJumping() && !player.isFalling())
		{
			player.jump();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
		{
			dispose();
			System.exit(0);
		}
	}

	public void logic()
	{
		// Your logic code should go here.
		for (Entity entity : entities.toArray(new Entity[entities.size()]))
		{
			entity.frameReset();
			if (entity.gravityAffected)
			{
				gravity(entity);
			}
			entity.frameLogic();
		}
	}

	public void draw()
	{
		// Your rendering code should go here.
		// Want help? Try here: https://github.com/libgdx/libgdx/wiki/A-simple-game
		for (Entity entity : entities.toArray(new Entity[entities.size()]))
		{
			entity.draw(batch);
		}
	}

	public void gravity(Entity entity)
	{
		if (entity.y > 0)
		{
			entity.dy = -entity.gravitySpeed;
		}
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
		for (Entity entity : entities.toArray(new Entity[entities.size()]))
		{
			entity.dispose();
		}
		batch.dispose();
		super.dispose();
	}

	@Override
	public void render()
	{
		// This method is called once every time the screen refreshes.
		// I've set it up so that generally you don't need to touch it.
		// It manages the controls, logic, and draw methods for you.

		// Removed the delta because it was causing problems. Will have to add back later.
		//delta = Gdx.graphics.getDeltaTime();

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
