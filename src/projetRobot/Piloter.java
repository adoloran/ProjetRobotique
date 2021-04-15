package projetRobot;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.navigation.DifferentialPilot;

public class Piloter{
	
	private final static double roue = 5.6;
	private final static double entraxe = 12.5;
	
	// ORI
//	private final static EV3LargeRegulatedMotor mLeftMotor = new EV3LargeRegulatedMotor(MotorPort.C);
//	private final static EV3LargeRegulatedMotor mRightMotor = new EV3LargeRegulatedMotor(MotorPort.B);
//	private final static EV3MediumRegulatedMotor pince = new EV3MediumRegulatedMotor(MotorPort.A);
    
	// Toby
	private final static EV3LargeRegulatedMotor mLeftMotor = new EV3LargeRegulatedMotor(MotorPort.B);
	private final static EV3LargeRegulatedMotor mRightMotor = new EV3LargeRegulatedMotor(MotorPort.A);
	private final static EV3MediumRegulatedMotor pince = new EV3MediumRegulatedMotor(MotorPort.C);
	private DifferentialPilot pilot;
<<<<<<< HEAD
	private final static CapteurCouleur couleur = new CapteurCouleur("S3");
	private final static CapteurUltrason ultrason = new CapteurUltrason("S4");
	
	private double rotation = 0;
	
//	private boolean stopper = false;
=======
	private int rotation;
>>>>>>> 63963c37567f6c86ecf1005d6fed2f29636482e4
    	
    // Initialisation du pilot
	public Piloter() {
		pilot = new DifferentialPilot(roue, roue, entraxe, mLeftMotor, mRightMotor, false);
	}
	
	// Permet de tourner le robot ï¿½ une vitesse de rotation donnee ï¿½ un angle donnee  
	public void tourner(double vitesse, double angle) {
		// Vitesse de rotation (<20 = lent)
		pilot.setAngularSpeed(vitesse);
		// Rotation < 0 vers la droite, > 0 vers la gauche
		pilot.rotate(angle);
		this.rotation += angle;
	} 
	
<<<<<<< HEAD
	// Permet de faire avancer le robot a une certaine vitesse à une distance
	public void avancer(double vitesse) {
		pilot.setLinearSpeed(vitesse);
		pilot.travel(300, true);
		double distance = ultrason.getDistance()*100;
		
		boolean couleurb = false;
		while (!couleurb && distance > 30) {
			couleurb = couleur.isWhite();
			System.out.println(couleurb);
			distance = ultrason.getDistance()*100;
			System.out.println(distance);
		}
		pilot.stop();
	}
	
=======
	public void tournerAsynch(double vitesse, double angle, boolean bool) {
		pilot.setAngularSpeed(vitesse);
		pilot.rotate(angle, true);
	}
	
	// Permet de faire avancer le robot a une certaine vitesse ï¿½ une distance
>>>>>>> 63963c37567f6c86ecf1005d6fed2f29636482e4
	public void avancer(double vitesse, double distance) {
		//on doit avancer d'une distance pour aller chercher le palet le plus proche (après scanner)
		pilot.setLinearSpeed(vitesse);
<<<<<<< HEAD
		pilot.travel(distance, true);
		
		distance = ultrason.getDistance()*100;
		boolean couleurb = false;

		while (!couleurb && distance > 30) {
			couleurb = couleur.isWhite();
			System.out.println(couleurb);
			distance = ultrason.getDistance()*100;
			System.out.println(distance);
		}
		pilot.stop();
	}
	
	public void attraperPalet(double distance) {
		// hors palet 1, attraper palet détecter lors scan (le plus proche)
		this.avancer(distance);
	}
	
	public void marquer() {
		//palet attrapé, seulement déposer le palet 
		this.repositionner();
		this.avancer(70);
		this.lacher();
=======
		pilot.travel(distance, false);
		// Il faudra faire des conditions pour qu'il s'arrï¿½te
		// Quand il rencontre un mur
		// Quand il rencontre une ligne blanche
>>>>>>> 63963c37567f6c86ecf1005d6fed2f29636482e4
	}
	
	// Permet d'avancer et de récupérer le premier palet
	public void Palet1(double vitesse, double distance) {
		pilot.setLinearSpeed(vitesse);
		pilot.travel(distance, true);
		
		while (pilot.isMoving()) {
			this.ouvrir();
			this.fermer();
		}
		
		this.tourner(70, -50);
		pilot.travel(30);
    	this.tourner(70, 50);
    	distance = ultrason.getDistance()*100;
    	this.avancer(70);
    	this.lacher();
	}
	
	// Permet de reculer le robot, il ne doit pas heurter les murs!!
	public void reculer(double vitesse, double distance) {
		pilot.setLinearSpeed(vitesse);
		pilot.travel(-distance, false);
	}
<<<<<<< HEAD
=======
		
	// Permet de dï¿½placer le robot de maniï¿½re courbï¿½e.
	public void courbe(double degre, double angle) {
		pilot.steer(degre, angle);
		pilot.travel(20, false);
	}
>>>>>>> 63963c37567f6c86ecf1005d6fed2f29636482e4
	
	// Mï¿½thode ouvrir les pinces
	public void ouvrir() {
		pince.rotate(360*2);
	}
	
	// Mï¿½thode fermer les pinces
	public void fermer() {
		pince.rotate(-360*2);
	}
	
<<<<<<< HEAD
	// Méthode récupération de palet
//	public void recuperer() {
//		this.ouvrir();
//		this.avancer(30, 10);
//		this.fermer();
//		this.tourner(70, -50);
//		this.avancer(30, 30);
//    	this.tourner(70, 50);
//	}
=======
	// Mï¿½thode rï¿½cupï¿½ration de palet en avanï¿½ant
	public void recuperer() {
		this.ouvrir();
		pilot.travel(10, false);
		this.fermer();
		this.tourner(50, 50);
		this.avancer(20, 30);
    	this.tourner(50, -50);
	}
>>>>>>> 63963c37567f6c86ecf1005d6fed2f29636482e4
	
	// Mï¿½thode qui permet de lï¿½cher le palet
	public void lacher() {
		this.ouvrir();
		pilot.setLinearSpeed(30);
		pilot.travel(-15, true);
		while (pilot.isMoving()) {
			this.fermer();
		}
	}
	
	public void repositionner() {
		if (rotation != 0) {
			this.tourner(50, -rotation);
		}
	}
	
	public void demiTour() {
		this.tourner(60, -180);
	}
	
<<<<<<< HEAD
	public static void main(String[] args)
=======
	public void repositionner() {
		if (rotation != 0) {
			this.tourner(50, -rotation);
		}
	}
	
	public void demiTour() {
		this.tourner(60, -180);
	}
	
	public DifferentialPilot getPilot() {
		return pilot;
	}
	
	public EV3MediumRegulatedMotor getPince() {
		return pince;
	}
	
	
	
    public static void main(String[] args)
>>>>>>> 63963c37567f6c86ecf1005d6fed2f29636482e4
    {
    	Piloter toby = new Piloter();
//    	toby.estMur();
//    	toby.fermer(); toby.fermer(); 
//    	couleur.CalibrerWhite();
//    	double distance = ultrason.getDistance()*100;
//    	toby.Palet1(35, distance);
    	toby.tourner(50, -90);
    	toby.marquer();
//    	System.out.println(distance);
//    	toby.fermer();
////    	toby.reculer(10, 10);
//    	toby.avancer(10, 60);
//    	toby.recuperer();
//    	distance = ultrason.getDistance()*100;
//    	toby.avancer(30, distance);
//    	toby.lacher();
//    	toby.tourner(50, -90);
//    	toby.avancer(10, 10);
//    	toby.tourner(50, 20);
//    	toby.avancer(10, 10);
//    	toby.demiTour();
////    	toby.repositionner();
////    	toby.courbe(90, 100);
////    	toby.courbe(60, 20);
////    	toby.avancer(20, 30);
////    	toby.reculer(10, 20);
    }
}
