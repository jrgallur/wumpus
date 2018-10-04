package es.wumpus.actions;

/**
 * Defines when the user throws an arrow. The arrow would kill the wumpus if it's in the next cell in the direction the user is.
 * 
 * @author jrgallur
 *
 */
public class ThrowArrow extends Action {

	public ThrowArrow() {
		super();
		sentences.add("DISPARAR");
		sentences.add("DISPARAR FLECHA");
		sentences.add("FLECHA");
		sentences.add("DISPARA");
		sentences.add("DISPARA FLECHA");
		sentences.add("LANZAR FLECHA");
		sentences.add("LANZA FLECHA");
		sentences.add("LANZAR");
	}

	@Override
	public ActionType getAction() {
		return ActionType.THROW_ARROW;
	}

}
