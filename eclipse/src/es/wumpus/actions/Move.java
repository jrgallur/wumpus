package es.wumpus.actions;

/**
 * Defines the Move action. The player will try to move one cell to the direction he is
 * 
 * @author jrgallur
 *
 */
public class Move extends Action {

	public Move() {
		super();
		sentences.add("AVANZAR");
		sentences.add("ANDAR");
		sentences.add("AVANZA");
		sentences.add("ANDA");
	}

	@Override
	public ActionType getAction() {
		return ActionType.MOVE;
	}

}
