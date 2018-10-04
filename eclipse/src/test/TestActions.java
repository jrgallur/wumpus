package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import es.wumpus.actions.Action;
import es.wumpus.actions.Exit;
import es.wumpus.actions.Help;
import es.wumpus.actions.Move;
import es.wumpus.actions.PickGold;
import es.wumpus.actions.Quit;
import es.wumpus.actions.ThrowArrow;
import es.wumpus.actions.TurnLeft;
import es.wumpus.actions.TurnRight;

public class TestActions {

	private boolean isCommandValidInClassAndInvalidInOtherClasses(Action action, String command) {
		if (!action.matches(command)) {
			return false;
		}

		if (!(action instanceof Exit)) {
			Action test = new Exit();
			if (test.matches(command)) {
				return false;
			}
		}
		if (!(action instanceof Help)) {
			Action test = new Help();
			if (test.matches(command)) {
				return false;
			}
		}
		if (!(action instanceof Move)) {
			Action test = new Move();
			if (test.matches(command)) {
				return false;
			}
		}
		if (!(action instanceof PickGold)) {
			Action test = new PickGold();
			if (test.matches(command)) {
				return false;
			}
		}
		if (!(action instanceof Quit)) {
			Action test = new Quit();
			if (test.matches(command)) {
				return false;
			}
		}
		if (!(action instanceof ThrowArrow)) {
			Action test = new ThrowArrow();
			if (test.matches(command)) {
				return false;
			}
		}
		if (!(action instanceof TurnLeft)) {
			Action test = new TurnLeft();
			if (test.matches(command)) {
				return false;
			}
		}
		if (!(action instanceof TurnRight)) {
			Action test = new TurnRight();
			if (test.matches(command)) {
				return false;
			}
		}
		return true;
	}

	@Test
	public void testCommandsOkOnExit() {
		Action action = new Exit();
		assertTrue("SALIR es un comando correcto y pertenece a Exit", isCommandValidInClassAndInvalidInOtherClasses(action, "SALIR"));
		assertTrue("salir es un comando correcto y pertenece a Exit", isCommandValidInClassAndInvalidInOtherClasses(action, "salir"));
		assertTrue("FIN es un comando correcto y pertenece a Exit", isCommandValidInClassAndInvalidInOtherClasses(action, "FIN"));
		assertTrue("FINALIZAR es un comando correcto y pertenece a Exit", isCommandValidInClassAndInvalidInOtherClasses(action, "FINALIZAR"));
		assertTrue("EXIT es un comando correcto y pertenece a Exit", isCommandValidInClassAndInvalidInOtherClasses(action, "EXIT"));
	}

	@Test
	public void testCommandsNOkOnExit() {
		Action action = new Exit();
		assertTrue("a NO es un comando correcto", !action.matches("a"));
		assertTrue("SALIRE NO es un comando correcto", !action.matches("SALIRE"));
		assertTrue("SAL IR NO es un comando correcto", !action.matches("SAL IR"));
	}

	@Test
	public void testCommandsOkOnHelp() {
		Action action = new Help();
		assertTrue("? es un comando correcto y pertenece a Help", isCommandValidInClassAndInvalidInOtherClasses(action, "?"));
		assertTrue("H es un comando correcto y pertenece a Help", isCommandValidInClassAndInvalidInOtherClasses(action, "H"));
		assertTrue("h es un comando correcto y pertenece a Help", isCommandValidInClassAndInvalidInOtherClasses(action, "h"));
		assertTrue("HELP es un comando correcto y pertenece a Help", isCommandValidInClassAndInvalidInOtherClasses(action, "HELP"));
		assertTrue("AYUDA es un comando correcto y pertenece a Help", isCommandValidInClassAndInvalidInOtherClasses(action, "AYUDA"));
		assertTrue("SOCORRO es un comando correcto y pertenece a Help", isCommandValidInClassAndInvalidInOtherClasses(action, "SOCORRO"));
	}

	@Test
	public void testCommandsNOkOnHelp() {
		Action action = new Help();
		assertTrue("* NO es un comando correcto", !action.matches("*"));
		assertTrue("i NO es un comando correcto", !action.matches("i"));
		assertTrue("AY UDA NO es un comando correcto", !action.matches("AY UDA"));
	}

	@Test
	public void testCommandsOkOnMove() {
		Action action = new Move();
		assertTrue("AVANZAR es un comando correcto y pertenece a Move", isCommandValidInClassAndInvalidInOtherClasses(action, "AVANZAR"));
		assertTrue("avanzar es un comando correcto y pertenece a Move", isCommandValidInClassAndInvalidInOtherClasses(action, "avanzar"));
		assertTrue("ANDAR es un comando correcto y pertenece a Move", isCommandValidInClassAndInvalidInOtherClasses(action, "ANDAR"));
		assertTrue("AVANZA es un comando correcto y pertenece a Move", isCommandValidInClassAndInvalidInOtherClasses(action, "AVANZA"));
		assertTrue("ANDA es un comando correcto y pertenece a Move", isCommandValidInClassAndInvalidInOtherClasses(action, "ANDA"));
	}

	@Test
	public void testCommandsNOkOnMove() {
		Action action = new Move();
		assertTrue("sube NO es un comando correcto", !action.matches("sube"));
		assertTrue("baja NO es un comando correcto", !action.matches("baja"));
	}

	@Test
	public void testCommandsOkOnPickGold() {
		Action action = new PickGold();
		assertTrue("COGER ORO es un comando correcto y pertenece a PickGold", isCommandValidInClassAndInvalidInOtherClasses(action, "COGER ORO"));
		assertTrue("coger oro es un comando correcto y pertenece a PickGold", isCommandValidInClassAndInvalidInOtherClasses(action, "coger oro"));
		assertTrue("RECOGER ORO es un comando correcto y pertenece a PickGold", isCommandValidInClassAndInvalidInOtherClasses(action, "RECOGER ORO"));
		assertTrue("AGARRAR ORO es un comando correcto y pertenece a PickGold", isCommandValidInClassAndInvalidInOtherClasses(action, "AGARRAR ORO"));
		assertTrue("GUARDAR ORO es un comando correcto y pertenece a PickGold", isCommandValidInClassAndInvalidInOtherClasses(action, "GUARDAR ORO"));
	}

	@Test
	public void testCommandsNOkOnPickGold() {
		Action action = new PickGold();
		assertTrue("ORO NO es un comando correcto", !action.matches("ORO"));
		assertTrue("ROBAR ORO NO es un comando correcto", !action.matches("ROBAR ORO"));
	}

	@Test
	public void testCommandsOkOnQuit() {
		Action action = new Quit();
		assertTrue("QUIT es un comando correcto y pertenece a Quit", isCommandValidInClassAndInvalidInOtherClasses(action, "QUIT"));
		assertTrue("quit es un comando correcto y pertenece a Quit", isCommandValidInClassAndInvalidInOtherClasses(action, "quit"));
		assertTrue("ABANDONAR es un comando correcto y pertenece a Quit", isCommandValidInClassAndInvalidInOtherClasses(action, "ABANDONAR"));
		assertTrue("ABANDONA es un comando correcto y pertenece a Quit", isCommandValidInClassAndInvalidInOtherClasses(action, "ABANDONA"));
	}

	@Test
	public void testCommandsNOkOnQuit() {
		Action action = new Quit();
		assertTrue("QUITAR NO es un comando correcto", !action.matches("QUITAR"));
		assertTrue("SALIR NO es un comando correcto", !action.matches("SALIR"));
	}

	@Test
	public void testCommandsOkOnThrowArrow() {
		Action action = new ThrowArrow();
		assertTrue("DISPARAR es un comando correcto y pertenece a ThrowArrow", isCommandValidInClassAndInvalidInOtherClasses(action, "DISPARAR"));
		assertTrue("disparar es un comando correcto y pertenece a ThrowArrow", isCommandValidInClassAndInvalidInOtherClasses(action, "disparar"));
		assertTrue("DISPARAR FLECHA es un comando correcto y pertenece a ThrowArrow", isCommandValidInClassAndInvalidInOtherClasses(action, "DISPARAR FLECHA"));
		assertTrue("FLECHA es un comando correcto y pertenece a ThrowArrow", isCommandValidInClassAndInvalidInOtherClasses(action, "FLECHA"));
		assertTrue("DISPARA es un comando correcto y pertenece a ThrowArrow", isCommandValidInClassAndInvalidInOtherClasses(action, "DISPARA"));
		assertTrue("DISPARA FLECHA es un comando correcto y pertenece a ThrowArrow", isCommandValidInClassAndInvalidInOtherClasses(action, "DISPARA FLECHA"));
		assertTrue("LANZAR FLECHA es un comando correcto y pertenece a ThrowArrow", isCommandValidInClassAndInvalidInOtherClasses(action, "LANZAR FLECHA"));
		assertTrue("LANZA FLECHA es un comando correcto y pertenece a ThrowArrow", isCommandValidInClassAndInvalidInOtherClasses(action, "LANZA FLECHA"));
		assertTrue("FLECHA es un comando correcto y pertenece a ThrowArrow", isCommandValidInClassAndInvalidInOtherClasses(action, "FLECHA"));
	}

	@Test
	public void testCommandsNOkOnThrowArrow() {
		Action action = new ThrowArrow();
		assertTrue("SUELTA NO es un comando correcto", !action.matches("SUELTA"));
		assertTrue("MOVER NO es un comando correcto", !action.matches("MOVER"));
		assertTrue("SAL IR NO es un comando correcto", !action.matches("SAL IR"));
	}

	@Test
	public void testCommandsOkOnTurnLeft() {
		Action action = new TurnLeft();
		assertTrue("GIRAR IZQUIERDA es un comando correcto y pertenece a TurnLeft", isCommandValidInClassAndInvalidInOtherClasses(action, "GIRAR IZQUIERDA"));
		assertTrue("IZQUIERDA es un comando correcto y pertenece a TurnLeft", isCommandValidInClassAndInvalidInOtherClasses(action, "IZQUIERDA"));
		assertTrue("IZQ es un comando correcto y pertenece a TurnLeft", isCommandValidInClassAndInvalidInOtherClasses(action, "IZQ"));
		assertTrue("izq es un comando correcto y pertenece a TurnLeft", isCommandValidInClassAndInvalidInOtherClasses(action, "izq"));
		assertTrue("GIRAR 90 IZQ es un comando correcto y pertenece a TurnLeft", isCommandValidInClassAndInvalidInOtherClasses(action, "GIRAR 90 IZQ"));
		assertTrue("GIRAR 90 IZQUIERDA es un comando correcto y pertenece a TurnLeft", isCommandValidInClassAndInvalidInOtherClasses(action, "GIRAR 90 IZQUIERDA"));
	}

	@Test
	public void testCommandsNOkOnTurnLeft() {
		Action action = new TurnLeft();
		assertTrue("a NO es un comando correcto", !action.matches("a"));
		assertTrue("SALIRE NO es un comando correcto", !action.matches("SALIRE"));
		assertTrue("SAL IR NO es un comando correcto", !action.matches("SAL IR"));
	}

	@Test
	public void testCommandsOkOnTurnRight() {
		Action action = new TurnRight();
		assertTrue("GIRAR DERECHA es un comando correcto y pertenece a TurnRight", isCommandValidInClassAndInvalidInOtherClasses(action, "GIRAR DERECHA"));
		assertTrue("DERECHA es un comando correcto y pertenece a TurnRight", isCommandValidInClassAndInvalidInOtherClasses(action, "DERECHA"));
		assertTrue("DRCHA es un comando correcto y pertenece a TurnRight", isCommandValidInClassAndInvalidInOtherClasses(action, "DRCHA"));
		assertTrue("drcha es un comando correcto y pertenece a TurnRight", isCommandValidInClassAndInvalidInOtherClasses(action, "drcha"));
		assertTrue("GIRAR 90 DRCHA es un comando correcto y pertenece a TurnRight", isCommandValidInClassAndInvalidInOtherClasses(action, "GIRAR 90 DRCHA"));
		assertTrue("GIRAR 90 DERECHA es un comando correcto y pertenece a TurnRight", isCommandValidInClassAndInvalidInOtherClasses(action, "GIRAR 90 DERECHA"));
	}

	@Test
	public void testCommandsNOkOnTurnRight() {
		Action action = new TurnRight();
		assertTrue("a NO es un comando correcto", !action.matches("a"));
		assertTrue("SALIRE NO es un comando correcto", !action.matches("SALIRE"));
		assertTrue("SAL IR NO es un comando correcto", !action.matches("SAL IR"));
	}
}
