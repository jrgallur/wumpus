package es.wumpus.structure;

/**
 * Define the directions where the player points
 * 
 * @author jrgallur
 *
 */
public enum DirectionType {
	UP, DOWN, LEFT, RIGHT;

	public static DirectionType getDirection(int directionInt) {
		switch (directionInt) {
		case 0:
			return DirectionType.LEFT;
		case 1:
			return DirectionType.UP;
		case 2:
			return DirectionType.RIGHT;
		case 3:
			return DirectionType.DOWN;
		}
		return null;
	}

	public static int getInt(DirectionType direction) {
		switch (direction) {
		case DOWN:
			return 3;
		case LEFT:
			return 0;
		case RIGHT:
			return 2;
		case UP:
			return 1;
		default:
			return -1;
		}
	}

	public static DirectionType turnLeft(DirectionType direction) {
		int directionInt = getInt(direction);
		directionInt = (directionInt + 3) % 4;
		return DirectionType.getDirection(directionInt);
	}

	public static DirectionType turnRight(DirectionType direction) {
		int directionInt = DirectionType.getInt(direction);
		directionInt = (directionInt + 1) % 4;
		return DirectionType.getDirection(directionInt);
	}

	public String toString() {
		switch (this) {
		case DOWN:
			return "DOWN";
		case LEFT:
			return "LEFT";
		case RIGHT:
			return "RIGHT";
		case UP:
			return "UP";
		}
		return "";
	}

}
