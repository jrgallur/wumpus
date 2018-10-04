package es.wumpus;

import es.wumpus.interfaces.TextInterface;
import es.wumpus.structure.Board;

public class Main {
	private static final int DEFAULT_ROWS_NUMBER = 4;
	private static final int DEFAULT_COLUMNS_NUMBER = 4;
	private static final int DEFAULT_ARROWS_NUMBER = 3;
	private static final int DEFAULT_PITS_NUMBER = 3;

	private static final int MAX_ROWS_NUMBER = 50;
	private static final int MAX_COLUMNS_NUMBER = 50;

	private static final int MIN_ROWS_NUMBER = 4;
	private static final int MIN_COLUMNS_NUMBER = 4;

	private static int rowsNumber = DEFAULT_ROWS_NUMBER;
	private static int columnsNumber = DEFAULT_COLUMNS_NUMBER;
	private static int arrowsNumber = DEFAULT_ARROWS_NUMBER;
	private static int pitsNumber = DEFAULT_PITS_NUMBER;

	private static void getParameters() {
		System.out.println("Introduzca el número de filas [" + Integer.toString(DEFAULT_ROWS_NUMBER) + "]");
		// Scans the next token of the input as a String
		String text = TextInterface.reader.nextLine();
		if (text.equals("")) {
			text = Integer.toString(DEFAULT_ROWS_NUMBER);
		}
		try {
			rowsNumber = Integer.parseInt(text);
		} catch (NumberFormatException nfexcp) {
			System.out.println("Entrada incorrecta. Se cogerá el valor por defecto");
		}
		if (rowsNumber < MIN_ROWS_NUMBER || rowsNumber > MAX_ROWS_NUMBER) {
			rowsNumber = DEFAULT_ROWS_NUMBER;
		}

		System.out.println("Introduzca el número de columnas [" + Integer.toString(DEFAULT_COLUMNS_NUMBER) + "]");
		// Scans the next token of the input as a String
		text = TextInterface.reader.nextLine();
		if (text.equals("")) {
			text = Integer.toString(DEFAULT_COLUMNS_NUMBER);
		}
		try {
			columnsNumber = Integer.parseInt(text);
		} catch (NumberFormatException nfexcp) {
			System.out.println("Entrada incorrecta. Se cogerá el valor por defecto");
		}
		if (columnsNumber < MIN_COLUMNS_NUMBER || columnsNumber > MAX_COLUMNS_NUMBER) {
			columnsNumber = DEFAULT_COLUMNS_NUMBER;
		}

		System.out.println("Introduzca el número de flechas [" + Integer.toString(DEFAULT_ARROWS_NUMBER) + "]");
		// Scans the next token of the input as a String
		text = TextInterface.reader.nextLine();
		if (text.equals("")) {
			text = Integer.toString(DEFAULT_ARROWS_NUMBER);
		}
		try {
			arrowsNumber = Integer.parseInt(text);
		} catch (NumberFormatException nfexcp) {
			System.out.println("Entrada incorrecta. Se cogerá el valor por defecto");
		}

		System.out.println("Introduzca el número de pozos [" + Integer.toString(DEFAULT_PITS_NUMBER) + "]");
		// Scans the next token of the input as a String
		text = TextInterface.reader.nextLine();
		if (text.equals("")) {
			text = Integer.toString(DEFAULT_PITS_NUMBER);
		}
		try {
			pitsNumber = Integer.parseInt(text);
		} catch (NumberFormatException nfexcp) {
			System.out.println("Entrada incorrecta. Se cogerá el valor por defecto");
		}
	}

	private static void playGame() {
		getParameters();
		Board board = null;

		try {
			try {
				board = new Board(rowsNumber, columnsNumber, arrowsNumber, pitsNumber);
			} catch (Exception e) {
				System.out.println("ERROR generating the Game:");
				e.printStackTrace();
				System.out.println();
				board = null;
			}
			if (board != null) {
				board.play();
			}
		} finally {
			TextInterface.reader.close();
		}
	}

	public static void main(String[] args) {
		playGame();
	}

}
