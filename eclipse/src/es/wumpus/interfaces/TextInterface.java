package es.wumpus.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import es.wumpus.actions.Action;
import es.wumpus.actions.ActionType;
import es.wumpus.actions.Exit;
import es.wumpus.actions.Help;
import es.wumpus.actions.Move;
import es.wumpus.actions.PickGold;
import es.wumpus.actions.Quit;
import es.wumpus.actions.ThrowArrow;
import es.wumpus.actions.TurnLeft;
import es.wumpus.actions.TurnRight;
import es.wumpus.structure.DirectionType;

public class TextInterface {
	public static Scanner reader = new Scanner(System.in);

	private static boolean finished = false;
	private static List<Action> actionList = new ArrayList<Action>();

	public static boolean isFinished() {
		return finished;
	}

	public static void userFallInAPit() {
		System.out.println("Has caido en un pozo!!");
	}

	public static void userKilledByWumpus() {
		System.out.println("El Wumpus te ha comido!!");
	}

	public static void feelsGold() {
		System.out.println("Sientes el brillo del oro!!");
	}

	public static void feelsWumpusOdor() {
		System.out.println("Sientes el olor del Wumpus... Parece estar cerca!!");
	}

	public static void feelsPitBreeze() {
		System.out.println("Sientes la brisa de un pozo cercano. Ten cuidado!!");
	}

	public static void playerInfo(DirectionType direction, int arrowNumber) {
		String directionString = "";
		switch (direction) {
		case UP:
			directionString = "Norte";
			break;
		case DOWN:
			directionString = "Sur";
			break;
		case LEFT:
			directionString = "Oeste";
			break;
		case RIGHT:
			directionString = "Este";
			break;
		}
		System.out.println("Estás mirando hacia el: " + directionString);
		System.out.println("Tienes " + Integer.toString(arrowNumber) + " flechas");
	}

	public static void feelsTheWall() {
		System.out.println("Paf!! Te has tropezado con un muro");
	}

	public static void feelsTheWumpusDeath() {
		System.out.println("Has oído el último suspiro del Wumpus!");
	}

	public static void cantExit() {
		System.out.println("No puedes salir porque no estás en la celda de inicio");
	}

	public static void goldIsNotHere() {
		System.out.println("No puedes coger el oro, porque el oro no está aquí");
	}

	public static void whatDoYouWant() {
		System.out.println("Qué quieres hacer ahora?");
	}

	public static void writeHelp(int arrowNumber) {
		System.out.println("Eres un cazador que busca oro. ");
		System.out.println("En el laberinto tienes el oro que debes buscar, pero cuidado! Puedes encontrarte con un pozo o incluso con el terrible Wumpus.");
		System.out.println("Si te aproximas al pozo, notarás la brisa. Si te aproximas al Wumpus notarás su olor");
		System.out.println("Dispones de " + Integer.toString(arrowNumber) + " flechas para matar al Wuntus, pero si disparas una y no le das, se moverá hacia cualquier sitio y podría matarte");
		System.out.println("Puedes [GIRAR DERECHA], [GIRAR IZQUIERDA], [AVANZAR], [SALIR (si estás en la casilla de salida)] y [DISPARAR FLECHA]");
	}

	public static void writeMessage(String error) {
		System.out.println(error);
	}

	public static void wrongCommand() {
		System.out.println("No entiendo lo que dices. Pulsa ? para pedir ayuda");
	}

	public static void arrowLost() {
		System.out.println("Has desperdiciado una flecha");
	}

	public static void goldMissed() {
		System.out.println("Te falta el oro");
	}

	public static void cantExitHere() {
		System.out.println("No puedes salir por aquí");
	}

	public static void goldGotten() {
		System.out.println("Enhorabuena! Ya tienes el oro!");
	}

	public static void commandsNumber(int number, int max) {
		System.out.println("Llevas " + Integer.toString(number) + " comandos ejecutados. Te quedan " + Integer.toString(max - number));
	}

	public static ActionType readString(String actionText) {
		if (actionList.size() == 0) {
			actionList.add(new Exit());
			actionList.add(new Help());
			actionList.add(new Move());
			actionList.add(new Quit());
			actionList.add(new PickGold());
			actionList.add(new ThrowArrow());
			actionList.add(new TurnLeft());
			actionList.add(new TurnRight());
		}

		for (Action action : actionList) {
			if (action.matches(actionText)) {
				return action.getAction();
			}
		}
		return ActionType.UNKNOWN;
	}
}
