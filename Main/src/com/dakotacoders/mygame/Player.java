package com.dakotacoders.mygame;

public class Player extends Entity
{
	public final boolean isControlled = true;
	public boolean isCrouching = false;
	public float jumpHeight = 50;
	public float jumpSpeed = 10;
	public float speed = 10;
	private boolean isFalling = false;
	private boolean isJumping = false;
	private float beforeJumpHeight;

	@Override
	public void frameReset()
	{
		// Don't reset the dx and dy here, as we move ourselves.
	}

	@Override
	public void frameLogic()
	{
		if (isCrouching)
		{

		}
		if (isJumping && !isFalling)
		{
			if (y > beforeJumpHeight + jumpHeight)
			{
				gravityAffected = true;
				isFalling = true;
				isJumping = false;
			}
			else if (!isFalling)
			{
				dy += jumpSpeed;
			}
		}
		isFalling = dy < 0;
		super.frameLogic();
	}

	public boolean isJumping()
	{
		return isJumping;
	}

	public boolean isFalling() { return isFalling; }

	public boolean jump()
	{
		if (!(isJumping() || isFalling))
		{
			if (isCrouching)
			{
				isCrouching = false;
				jumpSpeed = 5;
			}
			beforeJumpHeight = y;
			gravityAffected = false;
			isJumping = true;
			isFalling = false;
			return true;
		}
		return false;
	}
}
