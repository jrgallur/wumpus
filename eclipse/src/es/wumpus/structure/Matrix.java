package es.wumpus.structure;

import java.util.ArrayList;

import es.wumpus.objects.PlayObject;
import es.wumpus.objects.PlayObjectType;

public class Matrix extends ArrayList<Column> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2654139154442287048L;
	private int numRows;
	private int numColumns;

	@SuppressWarnings("unused")
	private Matrix() {
		// Dont use this
	}

	/**
	 * Constructor. Defines the size and the witdh of the matrix
	 * 
	 * @param numRows
	 * @param numColumns
	 */
	public Matrix(int numRows, int numColumns) {
		this.numRows = numRows;
		this.numColumns = numColumns;
		for (int contRow = 0; contRow < numRows; contRow++) {
			Column column = new Column(numColumns);
			this.add(column);
		}
	}

	/**
	 * Put a object in a defined coordinates inside the matrix
	 * 
	 * @param coordinates
	 *            Coordinates where to put the object in
	 * @param object
	 *            Object to put
	 */
	public void putObject(Coordinates coordinates, PlayObject object) {
		if (validCoordinates(coordinates)) {
			this.get(coordinates.row).putObject(coordinates.column, object);
		}
	}

	/**
	 * Guess if exists one object of one type in one defined coordinates
	 * 
	 * @param coordinates
	 *            Coordinates where search
	 * @param type
	 *            Type of the object to find
	 * @return true if this coordinates have that type of object. False in another case.
	 */
	public boolean hasObjectIn(Coordinates coordinates, PlayObjectType type) {
		if (validCoordinates(coordinates)) {
			return this.get(coordinates.row).get(coordinates.column).hasPlayObject(type);
		}
		return false;
	}

	/**
	 * Get one object of a defined type from a defined coordinates
	 * 
	 * @param coordinates
	 *            Coordinates where to find the object
	 * @param type
	 *            Type of the object to find
	 * @return The object found or null, if doesn't exist
	 */
	public PlayObject getObjectAt(Coordinates coordinates, PlayObjectType type) {
		if (validCoordinates(coordinates)) {
			return this.get(coordinates.row).get(coordinates.column).getPlayObject(type);
		}
		return null;
	}

	/**
	 * Delete one object of the type defined from the coordinates defined
	 * 
	 * @param coordinates
	 *            Coordinates where must delete de object
	 * @param type
	 *            Type of the object
	 */
	public void deleteObject(Coordinates coordinates, PlayObjectType type) {
		if (validCoordinates(coordinates)) {
			this.get(coordinates.row).deleteObject(coordinates.column, type);
		}
	}

	/**
	 * Returns if the coordinates are valid: the row must be between 0 and numRows and the column must be between 0 and numColumns
	 * 
	 * @param coordinates
	 *            Coordinates to validate
	 * @return true if the coordinates are valid. False if they don't
	 */
	public boolean validCoordinates(Coordinates coordinates) {
		return (coordinates.row >= 0 && coordinates.row < numRows && coordinates.column >= 0 && coordinates.column < numColumns);
	}

	/**
	 * Converts the object to an understanding text
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("[");
		String coma = "";
		for (int contRow = 0; contRow < numRows; contRow++) {
			result.append(coma);
			result.append(this.get(contRow).toString());
			coma = ",";
		}
		result.append("]");
		return result.toString();
	}

}
