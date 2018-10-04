package es.wumpus.structure;

import java.util.HashMap;
import java.util.Map;

import es.wumpus.objects.PlayObject;
import es.wumpus.objects.PlayObjectType;

public class Cell {
	// Object set contained in the cell
	private Map<PlayObject, PlayObject> contents;

	/**
	 * Constructor. Initialize the Map of objects contained
	 */
	public Cell() {
		contents = new HashMap<PlayObject, PlayObject>();
	}

	/**
	 * Insert a object in the cell
	 * 
	 * @param object
	 */
	public void addObject(PlayObject object) {
		contents.put(object, object);
	}

	/**
	 * Get an object of one type in the contents list
	 * 
	 * @param type
	 * @return
	 */
	private PlayObject findObjectOfType(PlayObjectType type) {
		for (Map.Entry<PlayObject, PlayObject> entry : contents.entrySet()) {
			if (entry.getValue().getType().equals(type)) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * Delete one object from the contents, if it exists
	 * 
	 * @param type
	 */
	public void deleteObject(PlayObjectType type) {
		PlayObject object = findObjectOfType(type);
		if (object != null) {
			contents.remove(object);
		}
	}

	/**
	 * Find out if the cell contains an object of some kind
	 * 
	 * @param object
	 * @return
	 */
	public boolean hasPlayObject(PlayObjectType type) {
		PlayObject object = findObjectOfType(type);
		return (object != null);
	}

	/**
	 * Return the object of one type (if exists)
	 * 
	 * @param type
	 * @return
	 */
	public PlayObject getPlayObject(PlayObjectType type) {
		PlayObject object = findObjectOfType(type);
		return object;
	}

	/**
	 * Return the whole objects contained
	 * 
	 * @return
	 */
	public Map<PlayObject, PlayObject> getObjects() {
		return contents;
	}

	/**
	 * Converts the object to an understanding text
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("{");
		String coma = "";
		for (Map.Entry<PlayObject, PlayObject> entry : contents.entrySet()) {
			result.append(coma);
			result.append(entry.getValue().toString());
			coma = ",";
		}
		result.append("}");
		return result.toString();
	}
}
