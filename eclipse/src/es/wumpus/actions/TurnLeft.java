package es.wumpus.actions;

/**
 * Defines when the user rotates 90º to the left
 * 
 * @author jrgallur
 *
 */
public class TurnLeft extends Action {

	public TurnLeft() {
		super();

		sentences.add("GIRAR IZQUIERDA");
		sentences.add("IZQUIERDA");
		sentences.add("IZQ");
		sentences.add("GIRAR IZQ");
		sentences.add("GIRAR 90 IZQ");
		sentences.add("GIRAR 90 IZQUIERDA");
	}

	@Override
	public ActionType getAction() {
		return ActionType.TURN_LEFT;
	}
}
