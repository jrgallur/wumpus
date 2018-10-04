package es.wumpus.actions;

/**
 * The user quits the game
 * 
 * @author jrgallur
 *
 */
public class Quit extends Action {

	public Quit() {
		super();
		sentences.add("QUIT");
		sentences.add("ABANDONAR");
		sentences.add("ABANDONA");
	}

	@Override
	public ActionType getAction() {
		return ActionType.QUIT;
	}

}
