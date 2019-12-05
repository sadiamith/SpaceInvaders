package model;

import util.RandomNumberGenerator;

public class InvaderScore extends Invader implements Comparable<InvaderScore> {

	/* Score of the invidividual invader*/
	protected int score;
	
	/* Row of invader */
	protected int row;
	
	/* Column of invader */
	protected int column;
	
	
	
	/**
	 * Initialize this instance.
	 * @param x			the initial x-coordinate
	 * @param y			the initial y-coordinate
	 * @param killWorth	the value of killing this invader
	 * @param level		the current level in the game
	 * @param game		the game being played
	 * @param row     row of invader
	 * @param column  column of invader
	 */
	public InvaderScore(int x, int y, int killWorth, int level, Game game, int row, int column) {
		super(x, y, killWorth, level, game);
		// +1 so we aren't counting from 0.
		this.row = row+1;
		this.column = column+1;
		score = 0;
		
		if(this.row==1)
			this.row=5;
		else if(this.row==2)
			this.row=2;
		else if(this.row==4)
			this.row=2;
		else if(this.row==5)
			this.row=1;
		else
			this.row=3;
			
	}

	
	/**
	 * At each clock tick, decide whether to fire, and every
	 * changeFreq ticks move and change image.
	 */
	@Override
	protected void update()
	{
		float randomNum = RandomNumberGenerator.getInstance().getFloat();
		if (randomNum <= FIRE_PROBABILITY)
		{
			int missileX = x + (width - Missile.WIDTH)/2;
			int missileY = y + height;
			game.addMissile(new MissileScore(missileX, missileY, game, this));
		}

		int currentTick = game.getTicks();
		if (currentTick == changeDirectionTick)
		{
			moveDirection = - moveDirection;
			y = y + verticalMoveDistance;
		}

		if (currentTick % CHANGE_FREQ == 0)
		{
			x = x + (moveDirection * HORIZONTAL_MOVE_DIST);
			moveToNextImage();

			if (currentTick > changeDirectionTick)
			{
				// if next move would hit a side, switch direction and advance next move
				if (moveDirection == Invader.MOVE_RIGHT
						&& (x + width + HORIZONTAL_MOVE_DIST) > game.getWidth())
				{
					changeDirectionTick = currentTick + CHANGE_FREQ;
				}
				else if (moveDirection == Invader.MOVE_LEFT 
						&& x - HORIZONTAL_MOVE_DIST < 0)
				{
					changeDirectionTick = currentTick + CHANGE_FREQ;
				}
			}
		}
	}
	
	public String toString()
	{
		String dummy;
		if (score != 0)
		{
			dummy = ("Invader (" + row + "," + column + "):" + score + "]\n");
			return dummy;
		}
		else
			return "";
	}

	@Override
	public int compareTo(InvaderScore other) {
		if (this.score > other.score)
			return -1;
		else if (this.score < other.score)
			return 1;
		else
			return 0;
	}
}
