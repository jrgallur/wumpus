package es.wumpus.objects;

/**
 * Defines the Wumpus object
 * 
 * @author jrgallur
 *
 */
public class Wumpus implements PlayObject {
	public PlayObjectType getType() {
		return PlayObjectType.WUMPUS;
	}

	public String toString() {
		return "*";
	}

}
