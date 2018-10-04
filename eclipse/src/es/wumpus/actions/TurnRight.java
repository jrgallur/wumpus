package es.wumpus.actions;

/**
 * Defines when the user rotates 90º to the right
 * 
 * @author jrgallur
 *
 */
public class TurnRight extends Action {

	public TurnRight() {
		sentences.add("GIRAR DERECHA");
		sentences.add("DERECHA");
		sentences.add("DRCHA");
		sentences.add("GIRAR DRCHA");
		sentences.add("GIRAR 90 DRCHA");
		sentences.add("GIRAR 90 DERECHA");
	}

	@Override
	public ActionType getAction() {
		return ActionType.TURN_RIGHT;
	}

}
