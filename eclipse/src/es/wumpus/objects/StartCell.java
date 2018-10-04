package es.wumpus.objects;

/**
 * Defines de start cell of the game. The player can only exit the game by the start cell
 * 
 * @author jrgallur
 *
 */
public class StartCell implements PlayObject {
	public PlayObjectType getType() {
		return PlayObjectType.STARTCELL;
	}

	public String toString() {
		return "^";
	}

}
