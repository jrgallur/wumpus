package es.wumpus.objects;

/**
 * Defines de Wumpus odor object
 * 
 * @author jrgallur
 *
 */
public class WumpusOdor implements PlayObject {
	public PlayObjectType getType() {
		return PlayObjectType.WUMPUSODOR;
	}

	public String toString() {
		return "~";
	}
}
