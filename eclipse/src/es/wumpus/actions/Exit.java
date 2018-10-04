package es.wumpus.actions;

/**
 * Define the Exit action. The user try to exit from the maze
 * 
 * @author jrgallur
 *
 */
public class Exit extends Action {
	public Exit() {
		super();
		sentences.add("SALIR");
		sentences.add("FIN");
		sentences.add("FINALIZAR");
		sentences.add("EXIT");
	}

	@Override
	public ActionType getAction() {
		return ActionType.EXIT;
	}

}
