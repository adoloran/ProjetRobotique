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



	public void lancerJeu() {
		
		int Etat = 0 ;
		double distance = 0;
		while(true) {
			System.out.println("Nous sommes dans l'etat :"+ Etat);
			switch (Etat) {
			case 0 :
				System.out.println(robot.getNom()+ " va commencer a jouer ! Appuyer sur le bouton central pour commencer.");
				Button.ENTER.waitForPress();
				Etat = 1 ;
				break;
			case 1 : 
				robot.getPiloter().Palet1(57);
				Etat = 2 ;
				break ;
			case 2 : 
				distance = robot.scannerZone();
				// calibrage
				// 
				if(robot.getCapteurU().getDistance() == distance) {
					Etat = 3;
				} else {
					Etat = 4 ;
				}
				break;
			case 3 :
				robot.getPiloter().attraperPalet(distance);
				Etat = 5 ;
				break;
			case 4 : 
				System.out.println("Calibrage necessaire");
				robot.calibrage();
				Etat = 3;
				break ; 
			case 5 :
				robot.getPiloter().marquer() ; 
				nbJetons++ ; 
				System.out.println(score());
				Etat = 2 ; 
				break;
			}
		}
//		System.out.println("Fin de la partie");
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
