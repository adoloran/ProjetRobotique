package projetRobot;

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

	public void scannerZone() {

	}

	public void lancerJeu() {
		System.out.println(robot.getNom()+ " va commencer à jouer ! Appuyer sur le bouton central pour commencer.");
		// Boutton START
		Button.ENTER.waitForPress();
		System.out.println(score());
		robot.Palet1(57) ; 
		while (nbJetons <9) {
			float distance = robot.scannerZone();
			robot.attraperPalet(distance);
			if (robot.getPression().isPressed()) {
				robot.marquer();
				nbJetons++; 
			}
		}

		System.out.println("Fin de la partie");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Robot Ori = new Robot("S4", "S4","S3");
		Robot Toby = new Robot("Toby","S2", "S4", "S3");
		Jouer premierJeu = new Jouer(Toby);
		premierJeu.lancerJeu();
		//Toby.ouvrir();
	}

}
