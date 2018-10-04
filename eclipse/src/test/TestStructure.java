package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import es.wumpus.objects.Pit;
import es.wumpus.objects.PlayObject;
import es.wumpus.objects.PlayObjectType;
import es.wumpus.objects.Wumpus;
import es.wumpus.structure.Cell;
import es.wumpus.structure.Column;
import es.wumpus.structure.Coordinates;
import es.wumpus.structure.Matrix;

public class TestStructure {
	@Test
	public void testCoordinates() {
		int row = 1;
		int column = 1;
		Coordinates coordinates = new Coordinates(row, column);
		assertEquals("Debe ser el mismo valor", coordinates.row, row);
		assertEquals("Debe ser el mismo valor", coordinates.column, column);
		assertEquals("Debe ser el mismo valor", coordinates.left().row, row);
		assertEquals("Debe ser el mismo valor", coordinates.left().column, column - 1);
		assertEquals("Debe ser el mismo valor", coordinates.right().row, row);
		assertEquals("Debe ser el mismo valor", coordinates.right().column, column + 1);
		assertEquals("Debe ser el mismo valor", coordinates.upper().row, row - 1);
		assertEquals("Debe ser el mismo valor", coordinates.upper().column, column);
		assertEquals("Debe ser el mismo valor", coordinates.lower().row, row + 1);
		assertEquals("Debe ser el mismo valor", coordinates.lower().column, column);

		Coordinates coordinates2 = new Coordinates(row, column);
		assertTrue("Deben ser iguales", coordinates.equals(coordinates2));
	}

	@Test
	public void testCellEmptyContents() {
		Cell cell = new Cell();
		Map<PlayObject, PlayObject> contents = cell.getObjects();

		assertEquals("Debería tener un contenido vacío", contents.size(), 0);
	}

	@Test
	public void testCellPutDeleteContent() {
		Cell cell = new Cell();
		Wumpus wumpus = new Wumpus();
		cell.addObject(wumpus);
		assertEquals("Debería tener un wumpus", cell.getPlayObject(PlayObjectType.WUMPUS), wumpus);
		assertEquals("No debería tener ningún player", cell.getPlayObject(PlayObjectType.PLAYER), null);
		cell.addObject(new Pit());
		assertEquals("Debería tener 2 objetos", cell.getObjects().size(), 2);
		cell.deleteObject(PlayObjectType.WUMPUS);
		assertEquals("No debería tener ningún wumpus", cell.getPlayObject(PlayObjectType.WUMPUS), null);
		assertEquals("Debería tener 1 objeto", cell.getObjects().size(), 1);
		cell.addObject(wumpus);
		assertEquals("Debería tener un wumpus", cell.hasPlayObject(PlayObjectType.WUMPUS), true);
	}

	@Test
	public void testColumnCellPutAndDelete() {
		int index = 2;
		Column columnCells = new Column(index + 1);
		assertTrue("No debe tener un Wumpus", !columnCells.get(index).hasPlayObject(PlayObjectType.WUMPUS));
		columnCells.putObject(index, new Wumpus());
		assertTrue("Debe tener un Wumpus", columnCells.get(index).hasPlayObject(PlayObjectType.WUMPUS));
		columnCells.deleteObject(index, PlayObjectType.WUMPUS);
		assertTrue("No debe tener un Wumpus", !columnCells.get(index).hasPlayObject(PlayObjectType.WUMPUS));
	}

	@Test
	public void testMatrix() {
		int numRows = 10;
		int numCols = 10;
		Coordinates coordinates = new Coordinates(5, 5);
		Matrix matrix = new Matrix(numRows, numCols);
		assertTrue("No debe tener un Wumpus aquí", !matrix.hasObjectIn(coordinates, PlayObjectType.WUMPUS));
		Wumpus wumpus = new Wumpus();
		matrix.putObject(coordinates, wumpus);
		assertTrue("Debe tener un Wumpus aquí", matrix.hasObjectIn(coordinates, PlayObjectType.WUMPUS));
		assertTrue("No debe tener un pozo aquí", !matrix.hasObjectIn(coordinates, PlayObjectType.PIT));
		assertTrue("No debe tener un Wumpus aquí", !matrix.hasObjectIn(coordinates.left(), PlayObjectType.WUMPUS));
		assertTrue("No debe tener un Wumpus aquí", !matrix.hasObjectIn(coordinates.right(), PlayObjectType.WUMPUS));
		assertTrue("No debe tener un Wumpus aquí", !matrix.hasObjectIn(coordinates.upper(), PlayObjectType.WUMPUS));
		assertTrue("No debe tener un Wumpus aquí", !matrix.hasObjectIn(coordinates.lower(), PlayObjectType.WUMPUS));
		assertEquals("Los wumpus deben ser los mismos", matrix.getObjectAt(coordinates, PlayObjectType.WUMPUS), wumpus);
		assertEquals("El player debe ser null", matrix.getObjectAt(coordinates, PlayObjectType.PLAYER), null);
		matrix.deleteObject(coordinates, PlayObjectType.WUMPUS);
		assertTrue("No debe tener un Wumpus aquí", !matrix.hasObjectIn(coordinates, PlayObjectType.WUMPUS));
	}

	@Test
	public void testColumnCells() {
		int numCells = 5;
		int index = 2;
		Column column = new Column(numCells);
		assertTrue("No debe tener un Wumpus aquí", !column.get(index).hasPlayObject(PlayObjectType.WUMPUS));
		assertTrue("No debe tener un Player aquí", !column.get(index).hasPlayObject(PlayObjectType.PLAYER));
		Wumpus wumpus = new Wumpus();
		column.putObject(index, wumpus);
		assertTrue("Debe tener un Wumpus aquí", column.get(index).hasPlayObject(PlayObjectType.WUMPUS));
		assertTrue("No debe tener un Player aquí", !column.get(index).hasPlayObject(PlayObjectType.PLAYER));
		column.deleteObject(index, PlayObjectType.WUMPUS);
		assertTrue("No debe tener un Wumpus aquí", !column.get(index).hasPlayObject(PlayObjectType.WUMPUS));
		assertTrue("No debe tener un Player aquí", !column.get(index).hasPlayObject(PlayObjectType.PLAYER));

	}
}
