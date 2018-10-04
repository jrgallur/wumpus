package es.wumpus.objects;

/**
 * The pit class.
 * 
 * @author jrgallur
 *
 */
public class Pit implements PlayObject {
	public PlayObjectType getType() {
		return PlayObjectType.PIT;
	}

	public String toString() {
		return "O";
	}

}
