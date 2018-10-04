package es.wumpus.structure;

/**
 * Define (row, column) coordinates
 * 
 * @author jrgallur
 *
 */
public class Coordinates {
	public int row;
	public int column;

	/**
	 * Constructor. Define (row, column) coordinates
	 * 
	 * @param row
	 * @param column
	 */
	public Coordinates(int row, int column) {
		this.row = row;
		this.column = column;
	}

	/**
	 * Gets the next coordinates to this object, one position to the left
	 * 
	 * @return
	 */
	public Coordinates left() {
		return new Coordinates(row, column - 1);
	}

	/**
	 * Gets the next coordinates to this object, one position to the right
	 * 
	 * @return
	 */
	public Coordinates right() {
		return new Coordinates(row, column + 1);
	}

	/**
	 * Gets the next coordinates to this object, one position to the top
	 * 
	 * @return
	 */
	public Coordinates upper() {
		return new Coordinates(row - 1, column);
	}

	/**
	 * Gets the next coordinates to this object, one position to the bottom
	 * 
	 * @return
	 */
	public Coordinates lower() {
		return new Coordinates(row + 1, column);
	}

	/**
	 * Compares this object with another coordinates to guess if they are the same
	 * 
	 * @param otherCoordinates
	 *            Object of type Coordinates to compare with this
	 * @return True if rows and columns are equals in both objects
	 */
	public boolean equals(Coordinates otherCoordinates) {
		return (this.row == otherCoordinates.row && this.column == otherCoordinates.column);
	}

	/**
	 * Converts the object to an understanding text
	 */
	public String toString() {
		return Integer.toString(row) + "," + Integer.toString(column);
	}
}
