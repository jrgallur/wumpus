package es.wumpus.actions;

import java.util.ArrayList;
import java.util.List;

/**
 * Define the generic Action. The action has a list of sentences to match
 * 
 * @author jrgallur
 *
 */
public abstract class Action {
	List<String> sentences = new ArrayList<String>();

	public abstract ActionType getAction();

	public List<String> getWords() {
		return sentences;
	}

	public boolean matches(String actionText) {
		String actionToMatch = actionText.toUpperCase();
		for (String sentence : sentences) {
			if (sentence.toUpperCase().equals(actionToMatch)) {
				return true;
			}
		}
		return false;
	}
}
