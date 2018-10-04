package es.wumpus.objects;

/**
 * The gold ingot class
 * 
 * @author jrgallur
 *
 */
public class GoldIngot implements PlayObject {

	public PlayObjectType getType() {
		return PlayObjectType.GOLDINGOT;
	}

	public String toString() {
		return "€";
	}

}
