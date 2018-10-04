package es.wumpus.structure;

import java.util.ArrayList;

import es.wumpus.objects.PlayObject;
import es.wumpus.objects.PlayObjectType;

public class Column extends ArrayList<Cell> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7836865192668581365L;

	private int numCells;

	/**
	 * Constructor Column. Create a column creating each single cell
	 * 
	 * @param numCells
	 */
	public Column(int numCells) {
		this.numCells = numCells;
		for (int contCells = 0; contCells < numCells; contCells++) {
			this.add(new Cell());
		}
	}

	public void putObject(int column, PlayObject object) {
		this.get(column).addObject(object);
	}

	/**
	 * Delete one object from the cell
	 * 
	 * @param column
	 * @param type
	 */
	public void deleteObject(int column, PlayObjectType type) {
		this.get(column).deleteObject(type);
	}

	/**
	 * Converts the object to an understanding text
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("(");
		for (int contCells = 0; contCells < numCells; contCells++) {
			result.append(this.get(contCells).toString());
		}
		result.append(")");

		return result.toString();
	}

}
