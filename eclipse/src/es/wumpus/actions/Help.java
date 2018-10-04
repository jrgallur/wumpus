package es.wumpus.actions;

/**
 * Defines the Help action. It'll show help information
 * 
 * @author jrgallur
 *
 */
public class Help extends Action {

	public Help() {
		super();
		sentences.add("?");
		sentences.add("H");
		sentences.add("HELP");
		sentences.add("AYUDA");
		sentences.add("SOCORRO");
	}

	@Override
	public ActionType getAction() {
		return ActionType.HELP;
	}

}
