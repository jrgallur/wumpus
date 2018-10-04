package es.wumpus.actions;

/**
 * Defines the "pick gold" action. If the gold is in the same place as the player, the player gets the gold
 * 
 * @author jrgallur
 *
 */
public class PickGold extends Action {
	public PickGold() {
		super();
		sentences.add("COGER ORO");
		sentences.add("RECOGER ORO");
		sentences.add("AGARRAR ORO");
		sentences.add("GUARDAR ORO");
	}

	@Override
	public ActionType getAction() {
		return ActionType.PICKGOLD;
	}
}
