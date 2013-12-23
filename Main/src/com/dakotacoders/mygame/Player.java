package com.dakotacoders.mygame;

public class Player extends Entity
{
	public final boolean isControlled = true;
	public boolean isCrouching = false;
	public float jumpHeight = 50;
	public float jumpSpeed = 10;
	public float speed = 10;
	private boolean isJumping = false;
	private float beforeJumpHeight;

	@Override
	public void frameReset()
	{
		// Don't reset the dx and dy here, as we move.
	}

	@Override
	public void frameLogic()
	{
		if (isCrouching)
		{

		}
		if (isJumping)
		{
			if (y > beforeJumpHeight + jumpHeight)
			{
				gravityAffected = true;
			}
			else
			{
				dy += (jumpSpeed * MyGame.delta);
			}
		}
		super.frameLogic();
	}

	public boolean isJumping()
	{
		return isJumping;
	}

	public boolean jump()
	{
		if (!isJumping)
		{
			if (isCrouching)
			{
				isCrouching = false;
				jumpSpeed = 5;
			}
			beforeJumpHeight = y;
			gravityAffected = false;
			isJumping = true;
			return true;
		}
		else
		{
			return false;
		}
	}
}
