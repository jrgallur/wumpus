package es.wumpus.actions;

/**
 * Defines an unknow action
 * 
 * @author jrgallur
 *
 */
public class Unknow extends Action {
	@Override
	public ActionType getAction() {
		return ActionType.UNKNOWN;
	}

}
