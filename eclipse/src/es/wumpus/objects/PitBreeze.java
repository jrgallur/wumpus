package es.wumpus.objects;

/**
 * The breeze near the pits class
 * 
 * @author jrgallur
 *
 */
public class PitBreeze implements PlayObject {
	public PlayObjectType getType() {
		return PlayObjectType.PITBREEZE;
	}

	public String toString() {
		return ".";
	}

}
