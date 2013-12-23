package com.dakotacoders.mygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class Entity extends Rectangle
{
	public float dx;
	public float dy;
	public boolean gravityAffected = true;
	public float gravitySpeed = 4;
	public final boolean isControlled = false;
	public Texture texture;

	public Entity(Texture t)
	{
		texture = t;
		commonConstructor();
	}

	public Entity()
	{
		texture = new Texture(Gdx.files.internal("white.png"));
		commonConstructor();
	}

	private void commonConstructor()
	{
		frameReset();
		x = 80;
		y = 460;
		width = 20;
		height = 20;
	}

	public void draw(SpriteBatch batch)
	{
		batch.draw(texture, x, y, width, height);
	}

	public void dispose()
	{
		texture.dispose();
	}

	public void frameReset()
	{
		dx = 0;
		dy = 0;
	}

	public void frameLogic()
	{
		x += (dx * MyGame.delta);
		y += (dy * MyGame.delta);
	}
}
