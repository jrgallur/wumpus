package es.wumpus.objects;

import es.wumpus.structure.DirectionType;

/**
 * The player class
 * 
 * @author jrgallur
 *
 */
public class Player implements PlayObject {
	// Where is player pointing
	private DirectionType direction;
	// Number of arrows left
	private int arrowsNumber = 0;
	// Has the gold or not
	private boolean hasGold = false;

	public Player(int arrowsNumber) {
		int directionInt = (int) (Math.random() * 4);
		direction = DirectionType.getDirection(directionInt);
		this.arrowsNumber = arrowsNumber;
	}

	public PlayObjectType getType() {
		return PlayObjectType.PLAYER;
	}

	/**
	 * Converts the object to an understanding text
	 */
	public String toString() {
		return "D-";
	}

	public void turnLeft() {
		direction = DirectionType.turnLeft(direction);
	}

	public void turnRight() {
		direction = DirectionType.turnRight(direction);
	}

	public DirectionType getDirection() {
		return direction;
	}

	/**
	 * When the user throw an arrow, the number of arrows left decrease
	 * 
	 * @return
	 */
	public boolean throwArrow() {
		if (arrowsNumber > 0) {
			arrowsNumber--;
			return true;
		}
		return false;
	}

	public int getArrowsNumber() {
		return arrowsNumber;
	}

	public void pickUpGold() {
		hasGold = true;
	}

	public boolean hasGold() {
		return hasGold;
	}
}
