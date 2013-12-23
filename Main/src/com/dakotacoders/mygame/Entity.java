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
	public Texture texture;
	public boolean flipped = false;
	public boolean flippable = false;
	public boolean draw = true;
	// public final boolean isControlled = false; **Removed until we need it.

	public Entity(Texture t)
	{
		texture = t;
		commonConstructor();
		sizeToTexture();
	}

	public Entity()
	{
		texture = new Texture(Gdx.files.internal("white.png"));
		commonConstructor();
	}

	public void sizeToTexture()
	{
		width = texture.getWidth();
		height = texture.getHeight();
	}

	public void centerOnScreen()
	{
		x = MyGame.width / 2 - width / 2;
		y = MyGame.height / 2 - height / 2;
	}

	protected void commonConstructor()
	{
		frameReset();
		x = 80;
		y = 460;
		width = 20;
		height = 20;
	}

	public void draw(SpriteBatch batch)
	{
		if (draw)
		{
			batch.draw(texture, x, y, width, height);
		}
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
		if (flippable)
		{
			if (dx > 0.0001)
			{
				if (flipped)
				{
					x += width;
					width *= -1;
					flipped = false;
				}
			}
			else if (dx < -0.0001)
			{
				if (!flipped)
				{
					x += width;
					width *= -1;
					flipped = true;
				}
			}
		}
	}
}
