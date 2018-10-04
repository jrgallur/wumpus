package es.wumpus.structure;

import es.wumpus.objects.GoldIngot;
import es.wumpus.objects.Pit;
import es.wumpus.objects.PitBreeze;
import es.wumpus.objects.PlayObject;
import es.wumpus.objects.PlayObjectType;
import es.wumpus.objects.Player;
import es.wumpus.objects.StartCell;
import es.wumpus.objects.Wumpus;
import es.wumpus.objects.WumpusOdor;

/**
 * Class with utils to the main class board. It has the random fill method
 * 
 * @author jrgallur
 *
 */
public class BoardUtilities {
	private static final int ATTEMPTS_FACTOR = 5;
	private static Matrix matrix;
	private static int numRows;
	private static int numColumns;
	private static int numPits;
	private static int numArrows;

	/**
	 * Get a random position of the board
	 * 
	 * @return
	 */
	private static Coordinates getRandomPosition() {
		Coordinates coordinates = new Coordinates((int) (Math.random() * numRows), (int) (Math.random() * numColumns));
		return coordinates;
	}

	/**
	 * Find the first cell without pit
	 * 
	 * @return
	 */
	private static Coordinates getFirstCellAccessible() {
		Coordinates coordinates = new Coordinates(0, 0);
		Coordinates coordTemp = new Coordinates(0, 0);
		for (coordTemp.row = 0; coordTemp.row < numRows; coordTemp.row++) {
			for (coordTemp.column = 0; coordTemp.column < numColumns; coordTemp.column++) {
				// If the cell hasn't pit, then return the coordinates
				if (!matrix.hasObjectIn(coordTemp, PlayObjectType.PIT)) {
					coordinates.row = coordTemp.row;
					coordinates.column = coordTemp.column;
					return coordinates;
				}
			}
		}
		return coordinates;
	}

	/**
	 * Return the number of accessibles cells starting from one
	 * 
	 * @param row
	 * @param column
	 * @param visited
	 * @return
	 */
	private static int countAccesibleCells(Coordinates coordinates, Matrix visited) {
		// Mark the cell as visited
		visited.putObject(coordinates, new Player(0));

		// This cell is accessible
		int numAccessibleCells = 1;

		// if the row before exists and wasn't visited and it isn't a pit, search there
		if (coordinates.row > 0 && !visited.hasObjectIn(coordinates.upper(), PlayObjectType.PLAYER) && !matrix.hasObjectIn(coordinates.upper(), PlayObjectType.PIT)) {
			numAccessibleCells = numAccessibleCells + countAccesibleCells(coordinates.upper(), visited);
		}

		// if the column before exists and wasn't visited and it isn't a pit, search there
		if (coordinates.column > 0 && !visited.hasObjectIn(coordinates.left(), PlayObjectType.PLAYER) && !matrix.hasObjectIn(coordinates.left(), PlayObjectType.PIT)) {
			numAccessibleCells = numAccessibleCells + countAccesibleCells(coordinates.left(), visited);
		}

		// if the row after exists and wasn't visited and it isn't a pit, search there
		if (coordinates.row < numRows - 1 && !visited.hasObjectIn(coordinates.lower(), PlayObjectType.PLAYER) && !matrix.hasObjectIn(coordinates.lower(), PlayObjectType.PIT)) {
			numAccessibleCells = numAccessibleCells + countAccesibleCells(coordinates.lower(), visited);
		}

		// if the column before exists and wasn't visited and it isn't a pit, search there
		if (coordinates.column < numColumns - 1 && !visited.hasObjectIn(coordinates.right(), PlayObjectType.PLAYER) && !matrix.hasObjectIn(coordinates.right(), PlayObjectType.PIT)) {
			numAccessibleCells = numAccessibleCells + countAccesibleCells(coordinates.right(), visited);
		}
		return numAccessibleCells;
	}

	/**
	 * Returns the number of pits from the matrix
	 * 
	 * @return
	 */
	private static int countPitNumber() {
		int result = 0;
		Coordinates coordinates = new Coordinates(0, 0);
		for (coordinates.row = 0; coordinates.row < numRows; coordinates.row++) {
			for (coordinates.column = 0; coordinates.column < numColumns; coordinates.column++) {
				// If the cell hasn't pit, then return the coordinates
				if (matrix.hasObjectIn(coordinates, PlayObjectType.PIT)) {
					result++;
				}
			}
		}
		return result;
	}

	/**
	 * Virifies if all the cells are visitables. Avoid to leave the Player, the wumpus or the gold closed between pits
	 * 
	 * @return True if all the cells are visitables
	 */
	private static boolean areAllCellsVisitables() {
		// Finds the first accessible cell
		Coordinates coordinates = getFirstCellAccessible();
		// Initialize the visited cells matrix
		Matrix visited = new Matrix(numRows, numColumns);
		// Count the number of accessibles cells
		int numAccessibleCells = countAccesibleCells(coordinates, visited);
		// Count the number of pits
		int numPits = countPitNumber();
		// The board will be accessible if the sum of pits and accessible cells are rows * columns
		return (numAccessibleCells + numPits == numRows * numColumns);
	}

	/**
	 * Finds one objecto of one defined type on the board and surround with objects
	 * 
	 * @param typeToFind
	 *            Type of object to find to surround it
	 * 
	 * @param object
	 *            Object to surround the found
	 */
	public static void surroundTypeWithObject(PlayObjectType typeToFind, PlayObject object) {
		Coordinates coordinates = new Coordinates(0, 0);
		for (coordinates.row = 0; coordinates.row < numRows; coordinates.row++) {
			for (coordinates.column = 0; coordinates.column < numColumns; coordinates.column++) {
				// If the cell hasn't pit, then return the coordinates
				if (matrix.hasObjectIn(coordinates, typeToFind)) {
					if (matrix.validCoordinates(coordinates.left())) {
						matrix.putObject(coordinates.left(), object);
					}
					if (matrix.validCoordinates(coordinates.right())) {
						matrix.putObject(coordinates.right(), object);
					}
					if (matrix.validCoordinates(coordinates.upper())) {
						matrix.putObject(coordinates.upper(), object);
					}
					if (matrix.validCoordinates(coordinates.lower())) {
						matrix.putObject(coordinates.lower(), object);
					}
				}
			}
		}
	}

	/**
	 * Fills the board randomly with the objects<BR>
	 * Considerations<BR>
	 * All the board will be accessible (there won't be "circles" of pits)<BR>
	 * The Wumpus can be in any cell, including one used by a pit
	 * 
	 * @throws Exception
	 */
	private static void fillsRandomMatrix() throws Exception {
		Coordinates coordinates = new Coordinates(0, 0);
		int attempts;

		// Put numPits pits in the board
		int contPits = 0;
		attempts = 0;
		while (contPits < numPits && attempts < numPits * ATTEMPTS_FACTOR) {
			coordinates = getRandomPosition();
			// Avoid reuse one cell
			if (!matrix.hasObjectIn(coordinates, PlayObjectType.PIT)) {
				// If there are three corners busy and the coordinates are from another corner, avoid fill the cell with the pit
				if (!(getCornersBusy() > 2 && isCorner(coordinates))) {
					matrix.putObject(coordinates, new Pit());
					if (!areAllCellsVisitables()) {
						matrix.deleteObject(coordinates, PlayObjectType.PIT);
					} else {
						contPits++;
					}
				}
			}
			attempts++;
		}
		if (contPits < numPits) {
			throw new Exception("Cant put the pits... Maybe there are too much for the board dimensions");
		}
		// Surround the pits with the breeze
		surroundTypeWithObject(PlayObjectType.PIT, new PitBreeze());

		boolean wumpusPlaced = false;
		attempts = 0;
		while (attempts < 10 * ATTEMPTS_FACTOR && !wumpusPlaced) {
			// Put the Wumpus in any place
			coordinates = getRandomPosition();
			// If there are three corners busy and the coordinates are from another corner, avoid fill the cell with the pit
			if (!(getCornersBusy() == 3 && !matrix.hasObjectIn(coordinates, PlayObjectType.PIT) && isCorner(coordinates))) {
				matrix.putObject(coordinates, new Wumpus());
				wumpusPlaced = true;
			}
			attempts++;
		}
		// Surround the cell with the odor
		surroundTypeWithObject(PlayObjectType.WUMPUS, new WumpusOdor());

		// Put the gold ingot in any place
		attempts = 0;
		boolean goldIngotPlaced = false;
		while (attempts < numRows * numColumns * ATTEMPTS_FACTOR && !goldIngotPlaced) {
			coordinates = getRandomPosition();
			// The gold ingot can't be in a pit
			if (!matrix.hasObjectIn(coordinates, PlayObjectType.PIT)) {
				matrix.putObject(coordinates, new GoldIngot());
				goldIngotPlaced = true;
			}

			attempts++;
		}

		// Put the start cell in one corner
		attempts = 0;
		int corner = (int) (Math.random() * 4);
		boolean startCellSetted = false;
		while (attempts < 4 && !startCellSetted) {
			// Get the coordinates
			coordinates = getCoordinatesFromCorner(corner);
			// Assure the cell hasn't a pit, the Wumpus nor the gold
			if (!matrix.hasObjectIn(coordinates, PlayObjectType.PIT) && !matrix.hasObjectIn(coordinates, PlayObjectType.WUMPUS) && !matrix.hasObjectIn(coordinates, PlayObjectType.GOLDINGOT)) {
				matrix.putObject(coordinates, new StartCell());
				matrix.putObject(coordinates, new Player(numArrows));
				startCellSetted = true;
			} else {
				// Find the "next" corner
				corner = (corner + 1) % 4;
				attempts++;
			}
		}
		// If after ATTEMPTIONS_FACTOR is not accesible, repeat all
		if (!startCellSetted) {
			throw new Exception("Cant put the Player on board... May be there are too much pits");
		}
	}

	/**
	 * Transform one int used as a corner into x,y coordinates
	 * 
	 * @param corner
	 *            Corner to transform
	 * @return The coordinates object created from the corner
	 */
	private static Coordinates getCoordinatesFromCorner(int corner) {
		Coordinates coordinates = new Coordinates(0, 0);
		// Get the coordinates
		switch (corner) {
		case 0:
			coordinates.row = 0;
			coordinates.column = 0;

			break;
		case 1:
			coordinates.row = 0;
			coordinates.column = numColumns - 1;

			break;
		case 2:
			coordinates.row = numRows - 1;
			coordinates.column = 0;

			break;
		case 3:
			coordinates.row = numRows - 1;
			coordinates.column = numColumns - 1;

			break;
		}
		return coordinates;
	}

	/**
	 * Guess the number of corners busy by pits or the Wumpus, because it must be one left where the player can begin
	 * 
	 * @return The number of corners busy
	 */
	private static int getCornersBusy() {
		int numCornersBusy = 0;
		for (int corner = 0; corner < 4; corner++) {
			Coordinates coordinates = getCoordinatesFromCorner(corner);
			if (matrix.hasObjectIn(coordinates, PlayObjectType.PIT) || matrix.hasObjectIn(coordinates, PlayObjectType.WUMPUS)) {
				numCornersBusy++;
			}
		}

		return numCornersBusy;
	}

	private static boolean isCorner(Coordinates coordinates) {
		return ((coordinates.row == 0 || coordinates.row == numRows - 1) && (coordinates.column == 0 || coordinates.column == numColumns - 1));
	}

	/**
	 * Generates a new board
	 * 
	 * @throws Exception
	 */
	public static Matrix generateMatrix(int maxNumRows, int maxNumColumns, int maxNumPits, int maxNumArrows) throws Exception {
		numRows = maxNumRows;
		numColumns = maxNumColumns;
		numPits = maxNumPits;
		numArrows = maxNumArrows;

		// Generate a new Matrix
		matrix = new Matrix(numRows, numColumns);

		// Fills the Matrix
		fillsRandomMatrix();

		return matrix;
	}
}
