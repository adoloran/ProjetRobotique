package projetRobot;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import lejos.hardware.Button;

public class Jouer {
	private int rotation;
	private int nbJetons;
	private Robot robot;

	public Jouer(Robot robot) {
		// TODO Auto-generated constructor stub
		this.robot=robot;
		rotation = 90;
		nbJetons = 0;
	}

	public String score() {
		return "Vous avez " + nbJetons + " points ! ";
	}
	
	



	public void marquer() {
		if (nbJetons == 0) {
			
		} else {

		}

	}

	public void chercherPalet() {
		if (nbJetons == 0) {
			robot.fermer();
			robot.ouvrir();
			robot.avancer(30,16);
			robot.fermer();
			robot.tourner(15, 45);
			robot.avancer(40, 20);
			robot.tourner(15, -45);
			robot.avancer(40, 20);
			robot.ouvrir();
			robot.avancer(15, -30);
			robot.tourner(15, -45);
			robot.fermer();
		}
	}

	public void lancerJeu() {
		System.out.println(robot.getNom()+ " va commencer à jouer ! Appuyer sur le bouton central pour commencer.");
		// Boutton START
		Button.ENTER.waitForPress();
		System.out.println(score());

		// Premier palet
			chercherPalet();
		// Autres palets
//		while (nbJetons < 0) {
//			// scannerZone() ;
//					
//		}

		System.out.println("Fin de la partie");
	}
	
	public Robot getRobot() {
		return robot;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Robot Ori = new Robot("S4", "S4","S3");
		Robot Toby = new Robot("Toby","S2", "S4", "S3");
		Jouer premierJeu = new Jouer(Toby);
//		premierJeu.lancerJeu();
		//Toby.ouvrir();
		//Toby.scannerZone();
		premierJeu.getRobot().scannerZone();
	}

}
