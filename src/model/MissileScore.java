package model;

import model.Missile;

public class MissileScore extends Missile 
{
	/** Hold's the invader*/
	InvaderScore invader;

	public MissileScore(int x, int y, Game game, InvaderScore invader) {
		super(x, y, game);
		this.invader = invader;
	}

	
	/**
	 * Check for a collision with the player or a block, 
	 * and if so, return the other object of the collision.
	 * Missiles do not hit invaders, explosions or other missiles.
	 * A laser detects a missile hit.
	 * @return the player or block that the missile hit 
	 */
	@Override
	protected GameObject objectHit()
	{
		if (intersects(game.player))
		{
			invader.score = invader.score+100;
			return game.player;
		}
		
		for (Block block : game.blocksList)
			if (intersects(block))
			{
				invader.score = invader.score+1;
				return block;
			}

		return null;
	}
	
	/**
	 * The method to handle the collision of this with the other object.
	 * @param other	the object that collided with this object
	 */
	@Override
	protected void collide(GameObject other)
	{
		if (other instanceof Laser)
		{
			invader.score = invader.score+20;
			isDead = true; // the laser found the collision and creates the explosion
		}
		else
		{
			isDead = true;	
			other.collide(this);
		}
	}
}

