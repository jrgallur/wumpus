package es.wumpus.structure;

import es.wumpus.actions.ActionType;
import es.wumpus.interfaces.TextInterface;
import es.wumpus.objects.PlayObjectType;
import es.wumpus.objects.Player;
import es.wumpus.objects.Wumpus;
import es.wumpus.objects.WumpusOdor;

/**
 * Defines the board of the game
 * 
 * @author jrgallur
 *
 */
public class Board {
	private int maxCommandsNumber;
	private Matrix matrix;
	private int numRows;
	private int numColumns;
	private boolean errorGeneratingGame = false;
	private int commandsNumber = 0;

	/**
	 * Constructor of the class. Gets the configuration and generate a new random game
	 * 
	 * @throws Exception
	 */
	public Board(int numRows, int numColumns, int numArrows, int numPits) throws Exception {
		this.numRows = numRows;
		this.numColumns = numColumns;
		// The maximun number of commands will be the number of cells times 10 (4 times 2 and a little more)
		maxCommandsNumber = numRows * numColumns * 10;
		try {
			matrix = BoardUtilities.generateMatrix(numRows, numColumns, numPits, numArrows);
		} catch (Exception excp) {
			TextInterface.writeMessage("ERROR generating Matrix:");
			excp.printStackTrace();
			errorGeneratingGame = true;
		}
	}

	/**
	 * Get the coordinates for a type of object in the matrix
	 * 
	 * @param type
	 *            Type of the object to find
	 * @return The coordinates of the first object of type "type" in the matrix, or null if it can't find it
	 */
	private Coordinates getObjectCoordinates(PlayObjectType type) {
		Coordinates result = new Coordinates(0, 0);
		Coordinates coordAux = new Coordinates(0, 0);
		for (coordAux.row = 0; coordAux.row < numRows; coordAux.row++) {
			for (coordAux.column = 0; coordAux.column < numColumns; coordAux.column++) {
				if (matrix.hasObjectIn(coordAux, type)) {
					result.row = coordAux.row;
					result.column = coordAux.column;
					return result;
				}
			}
		}
		return null;
	}

	/**
	 * Get the coordinates of the Player in the matrix
	 * 
	 * @return The coordinates of the Player in the matrix, or null if it can't find it
	 */
	private Coordinates getPlayerCoordinates() {
		return getObjectCoordinates(PlayObjectType.PLAYER);
	}

	/**
	 * Get the coordinates of the Wumpus in the matrix
	 * 
	 * @return The coordinates of the Wumpus in the matrix, or null if it can't find it
	 */
	private Coordinates getWumpusCoordinates() {
		return getObjectCoordinates(PlayObjectType.WUMPUS);
	}

	/**
	 * Get the coordinates of the Gold Ingote in the matrix
	 * 
	 * @return The coordinates of the Gold Ingote in the matrix, or null if it can't find it
	 */
	private Coordinates getGoldIngoteCoordinates() {
		return getObjectCoordinates(PlayObjectType.GOLDINGOT);
	}

	/**
	 * Get the coordinates of the Start Cell (and Exit cell) in the matrix
	 * 
	 * @return The coordinates of the Start Cell in the matrix, or null if it can't find it
	 */
	private Coordinates getStartCellCoordinates() {
		return getObjectCoordinates(PlayObjectType.STARTCELL);
	}

	private boolean printCurrentInfo(Coordinates playerCoordinates) {
		boolean finished = false;

		if (matrix.hasObjectIn(playerCoordinates, PlayObjectType.PIT)) {
			TextInterface.userFallInAPit();
			finished = true;
		}

		if (matrix.hasObjectIn(playerCoordinates, PlayObjectType.WUMPUS)) {
			TextInterface.userKilledByWumpus();
			finished = true;
		}

		if (!finished) {
			if (matrix.hasObjectIn(playerCoordinates, PlayObjectType.GOLDINGOT)) {
				TextInterface.feelsGold();
			}

			if (matrix.hasObjectIn(playerCoordinates, PlayObjectType.WUMPUSODOR)) {
				TextInterface.feelsWumpusOdor();
			}

			if (matrix.hasObjectIn(playerCoordinates, PlayObjectType.PITBREEZE)) {
				TextInterface.feelsPitBreeze();
			}

			Player player = (Player) matrix.getObjectAt(playerCoordinates, PlayObjectType.PLAYER);
			TextInterface.playerInfo(player.getDirection(), player.getArrowsNumber());
		}
		TextInterface.commandsNumber(commandsNumber, maxCommandsNumber);
		return finished;
	}

	/**
	 * Play the game
	 */
	public void play() {
		// If there isn't any error..
		if (!errorGeneratingGame) {
			// When finish the game
			boolean finished = false;
			// Player coordinates
			Coordinates playerCoordinates;
			String actionText;
			ActionType action;
			while (!finished) {
				// TODO: Show the map
				// System.out.println(this.toString());

				// Update player coordinates
				playerCoordinates = getPlayerCoordinates();
				// Show the info to the user
				finished = printCurrentInfo(playerCoordinates);
				if (!finished) {
					TextInterface.whatDoYouWant();
					// Scans the next token of the input as a String
					actionText = TextInterface.reader.nextLine();

					// Get the asociated action to the user's input
					action = TextInterface.readString(actionText);
					finished = executeAction(action);
				}
			}
			TextInterface.writeMessage("Fin del juego");
		}
	}

	/**
	 * Action to do when the user wants to Exit
	 * 
	 * @return true if the game finish. False otherwise
	 */
	private boolean actionExit() {
		Coordinates playerCoordinates = getPlayerCoordinates();
		Coordinates startCellcoordinates = getStartCellCoordinates();
		// Is the player in the same coordinates than the start cell?
		if (playerCoordinates.equals(startCellcoordinates)) {
			Player player = (Player) matrix.getObjectAt(playerCoordinates, PlayObjectType.PLAYER);
			if (player.hasGold()) {
				return true;
			} else {
				TextInterface.goldMissed();
			}
		} else {
			TextInterface.cantExitHere();
		}
		return false;
	}

	/**
	 * Move the player on the board to the new coordinates
	 * 
	 * @param oldCoordinates
	 * @param newCoordinates
	 * @param player
	 */
	private void movePlayerToCoordinates(Coordinates oldCoordinates, Coordinates newCoordinates, Player player) {
		matrix.deleteObject(oldCoordinates, PlayObjectType.PLAYER);
		matrix.putObject(newCoordinates, player);
	}

	/**
	 * Action to do when the user wants to move
	 * 
	 */
	private void actionMove() {
		Coordinates playerCoordinates = getPlayerCoordinates();
		Player player = (Player) matrix.getObjectAt(playerCoordinates, PlayObjectType.PLAYER);
		DirectionType direction = player.getDirection();
		switch (direction) {
		case DOWN:
			if (playerCoordinates.row < numRows - 1) {
				movePlayerToCoordinates(playerCoordinates, playerCoordinates.lower(), player);
			} else {
				TextInterface.feelsTheWall();
			}
			break;
		case LEFT:
			if (playerCoordinates.column > 0) {
				movePlayerToCoordinates(playerCoordinates, playerCoordinates.left(), player);
			} else {
				TextInterface.feelsTheWall();
			}
			break;
		case RIGHT:
			if (playerCoordinates.column < numColumns - 1) {
				movePlayerToCoordinates(playerCoordinates, playerCoordinates.right(), player);
			} else {
				TextInterface.feelsTheWall();
			}
			break;
		case UP:
			if (playerCoordinates.row > 0) {
				movePlayerToCoordinates(playerCoordinates, playerCoordinates.upper(), player);
			} else {
				TextInterface.feelsTheWall();
			}
			break;
		default:
			break;
		}
	}

	/**
	 * Action to do when the user wants to rotate to the left
	 * 
	 */
	private void turnLeft() {
		Coordinates playerCoordinates = getPlayerCoordinates();
		Player player = (Player) matrix.getObjectAt(playerCoordinates, PlayObjectType.PLAYER);
		player.turnLeft();
	}

	/**
	 * Action to do when the user wants to rotate to the right
	 * 
	 */
	private void turnRight() {
		Coordinates playerCoordinates = getPlayerCoordinates();
		Player player = (Player) matrix.getObjectAt(playerCoordinates, PlayObjectType.PLAYER);
		player.turnRight();
	}

	/**
	 * Action to do when the user needs Help
	 * 
	 */
	private void writeHelp() {
		Coordinates playerCoordinates = getPlayerCoordinates();
		Player player = (Player) matrix.getObjectAt(playerCoordinates, PlayObjectType.PLAYER);
		TextInterface.writeHelp(player.getArrowsNumber());
	}

	/**
	 * Move the Wumpus one cell in a random direction
	 */
	private void moveWumpus() {
		Coordinates wumpusCoordinates = getWumpusCoordinates();
		Coordinates wumpusCoordinatesNew = wumpusCoordinates;
		boolean canMove = false;
		int directionInt = (int) (Math.random() * 4);
		DirectionType direction = DirectionType.getDirection(directionInt);
		while (!canMove) {
			switch (direction) {
			case LEFT:
				if (wumpusCoordinates.column > 0) {
					canMove = true;
					wumpusCoordinatesNew = wumpusCoordinates.left();
				}
				break;
			case DOWN:
				if (wumpusCoordinates.row < numRows - 1) {
					canMove = true;
					wumpusCoordinatesNew = wumpusCoordinates.lower();
				}
				break;
			case RIGHT:
				if (wumpusCoordinates.column < numColumns - 1) {
					canMove = true;
					wumpusCoordinatesNew = wumpusCoordinates.right();
				}
				break;
			case UP:
				if (wumpusCoordinates.row > 0) {
					canMove = true;
					wumpusCoordinatesNew = wumpusCoordinates.upper();
				}
				break;
			default:
				break;
			}
			// If the wumpus can't move in the previous direction, we choose the next one and repeat the process
			direction = DirectionType.turnRight(direction);
		}
		// Delete the Wumpus from its old position
		matrix.deleteObject(wumpusCoordinates, PlayObjectType.WUMPUS);
		// And its odor
		matrix.deleteObject(wumpusCoordinates.left(), PlayObjectType.WUMPUSODOR);
		matrix.deleteObject(wumpusCoordinates.right(), PlayObjectType.WUMPUSODOR);
		matrix.deleteObject(wumpusCoordinates.upper(), PlayObjectType.WUMPUSODOR);
		matrix.deleteObject(wumpusCoordinates.lower(), PlayObjectType.WUMPUSODOR);
		// Put the Wumpus in its new position
		matrix.putObject(wumpusCoordinatesNew, new Wumpus());
		// And its odor
		BoardUtilities.surroundTypeWithObject(PlayObjectType.WUMPUS, new WumpusOdor());
	}

	/**
	 * Return if the Wumpus is Killed by a thrown arrow
	 * 
	 * @return true if the Wumpus is killed. False otherwise
	 */
	private boolean isWumpusKilledAfterArrowThrown(DirectionType playerDirection, Coordinates playerCoordinates) {
		Coordinates wumpusCoordinates = getWumpusCoordinates();
		Coordinates auxCoordinates = new Coordinates(playerCoordinates.row, playerCoordinates.column);
		if (wumpusCoordinates == null) {
			// It was previously killed
			return false;
		}
		switch (playerDirection) {
		case DOWN:
			while (matrix.validCoordinates(auxCoordinates.lower())) {
				auxCoordinates = auxCoordinates.lower();
				if (wumpusCoordinates.equals(auxCoordinates)) {
					return true;
				}
			}
			break;
		case LEFT:
			while (matrix.validCoordinates(auxCoordinates.left())) {
				auxCoordinates = auxCoordinates.left();
				if (wumpusCoordinates.equals(auxCoordinates)) {
					return true;
				}
			}
			break;
		case RIGHT:
			while (matrix.validCoordinates(auxCoordinates.right())) {
				auxCoordinates = auxCoordinates.right();
				if (wumpusCoordinates.equals(auxCoordinates)) {
					return true;
				}
			}
			break;
		case UP:
			while (matrix.validCoordinates(auxCoordinates.upper())) {
				auxCoordinates = auxCoordinates.upper();
				if (wumpusCoordinates.equals(auxCoordinates)) {
					return true;
				}
			}
			break;
		default:
			break;
		}
		return false;
	}

	/**
	 * Action to do when the user wants to Exit
	 * 
	 * @return true if the game finish. False otherwise
	 */
	private boolean throwArrow() {
		Coordinates playerCoordinates = getPlayerCoordinates();
		Player player = (Player) matrix.getObjectAt(playerCoordinates, PlayObjectType.PLAYER);
		if (player.getArrowsNumber() > 0) {
			player.throwArrow();
			if (!isWumpusKilledAfterArrowThrown(player.getDirection(), playerCoordinates)) {
				moveWumpus();
				Coordinates wumpusCoordinates = getWumpusCoordinates();
				if (playerCoordinates.equals(wumpusCoordinates)) {
					// printCurrentInfo(playerCoordinates);
					return true;
				}
				TextInterface.arrowLost();
			} else {
				Coordinates wumpusCoordinates = getWumpusCoordinates();
				// Delete the Wumpus from its old position
				matrix.deleteObject(wumpusCoordinates, PlayObjectType.WUMPUS);
				// And its odor
				matrix.deleteObject(wumpusCoordinates.left(), PlayObjectType.WUMPUSODOR);
				matrix.deleteObject(wumpusCoordinates.right(), PlayObjectType.WUMPUSODOR);
				matrix.deleteObject(wumpusCoordinates.upper(), PlayObjectType.WUMPUSODOR);
				matrix.deleteObject(wumpusCoordinates.lower(), PlayObjectType.WUMPUSODOR);
				TextInterface.feelsTheWumpusDeath();
			}
		}
		return false;
	}

	/**
	 * Action to do when the user wants to pick up the gold
	 */
	private void pickUpGold() {
		Coordinates playerCoordinates = getPlayerCoordinates();
		Coordinates goldCoordinates = getGoldIngoteCoordinates();
		if (playerCoordinates.equals(goldCoordinates)) {
			Player player = (Player) matrix.getObjectAt(playerCoordinates, PlayObjectType.PLAYER);
			player.pickUpGold();
			matrix.deleteObject(goldCoordinates, PlayObjectType.GOLDINGOT);
			TextInterface.goldGotten();
		} else {
			TextInterface.goldIsNotHere();
		}
	}

	/**
	 * Action to execute from the user command
	 * 
	 * @return true if the game finish. False otherwise
	 */
	private boolean executeAction(ActionType action) {
		boolean finish = false;
		switch (action) {
		case EXIT:
			finish = actionExit();
			finish = finish || incrementCommandsNumber();
			break;
		case HELP:
			writeHelp();
			break;
		case MOVE:
			actionMove();
			finish = finish || incrementCommandsNumber();
			break;
		case QUIT:
			finish = true;
			break;
		case THROW_ARROW:
			finish = throwArrow();
			finish = finish || incrementCommandsNumber();
			break;
		case TURN_LEFT:
			turnLeft();
			finish = finish || incrementCommandsNumber();
			break;
		case TURN_RIGHT:
			turnRight();
			finish = finish || incrementCommandsNumber();
			break;
		case PICKGOLD:
			pickUpGold();
			finish = finish || incrementCommandsNumber();
			break;
		case UNKNOWN:
			TextInterface.wrongCommand();
			break;
		default:
			TextInterface.wrongCommand();
			break;
		}

		return finish;
	}

	/**
	 * Increments the number of commands played and return true if they exceed the maximun
	 * 
	 * @return True if the user play more commands than the limit
	 */
	private boolean incrementCommandsNumber() {
		commandsNumber++;
		return (commandsNumber > maxCommandsNumber);
	}

	/**
	 * Converts the object to an understanding text
	 */
	public String toString() {
		if (matrix != null) {
			return matrix.toString();
		}
		return "";
	}

}
