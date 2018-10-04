package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import es.wumpus.objects.Player;
import es.wumpus.structure.DirectionType;

public class TestObjects {

	@Test
	public void playerThrowsArrowOk() {
		int arrowNum = 3;
		Player player = new Player(arrowNum);
		player.throwArrow();
		assertEquals("Debería tener " + Integer.toString(arrowNum - 1) + " flechas", arrowNum - 1, player.getArrowsNumber());
	}

	@Test
	public void playerThrowsArrowNoOk() {
		int arrowNum = 0;
		Player player = new Player(arrowNum);
		assertEquals("No debería poder lanzar flecha", false, player.throwArrow());
		assertEquals("Debería tener 0 flechas", 0, player.getArrowsNumber());
	}

	@Test
	public void playerTurnLeft() {
		Player player = new Player(1);
		DirectionType direction = player.getDirection();
		player.turnLeft();
		direction = DirectionType.turnLeft(direction);
		assertEquals("Debería apuntar a " + direction, direction, player.getDirection());
		player.turnLeft();
		direction = DirectionType.turnLeft(direction);
		assertEquals("Debería apuntar a " + direction, direction, player.getDirection());
		player.turnLeft();
		direction = DirectionType.turnLeft(direction);
		assertEquals("Debería apuntar a " + direction, direction, player.getDirection());
		player.turnLeft();
		direction = DirectionType.turnLeft(direction);
		assertEquals("Debería apuntar a " + direction, direction, player.getDirection());
		player.turnLeft();
		direction = DirectionType.turnLeft(direction);
		assertEquals("Debería apuntar a " + direction, direction, player.getDirection());
	}

	@Test
	public void playerTurnRight() {
		Player player = new Player(1);
		DirectionType direction = player.getDirection();
		player.turnRight();
		direction = DirectionType.turnRight(direction);
		assertEquals("Debería apuntar a " + direction, direction, player.getDirection());
		player.turnRight();
		direction = DirectionType.turnRight(direction);
		assertEquals("Debería apuntar a " + direction, direction, player.getDirection());
		player.turnRight();
		direction = DirectionType.turnRight(direction);
		assertEquals("Debería apuntar a " + direction, direction, player.getDirection());
		player.turnRight();
		direction = DirectionType.turnRight(direction);
		assertEquals("Debería apuntar a " + direction, direction, player.getDirection());
		player.turnRight();
		direction = DirectionType.turnRight(direction);
		assertEquals("Debería apuntar a " + direction, direction, player.getDirection());
	}

	@Test
	public void playerPickTheGold() {
		Player player = new Player(1);
		assertTrue("El jugador no debe tener el oro", !player.hasGold());
		player.pickUpGold();
		assertTrue("El jugador no debe tener el oro", player.hasGold());
	}

}
