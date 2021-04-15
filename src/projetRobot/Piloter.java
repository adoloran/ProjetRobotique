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
	private int rotation;
    	
    // Initialisation du pilot
	public Piloter() {
		pilot = new DifferentialPilot(roue, roue, entraxe, mLeftMotor, mRightMotor, false);
	}
	
	// Permet de tourner le robot � une vitesse de rotation donnee � un angle donnee  
	public void tourner(double vitesse, double angle) {
		// Vitesse de rotation (<20 = lent)
		pilot.setAngularSpeed(vitesse);
		// Rotation < 0 vers la droite, > 0 vers la gauche
		pilot.rotate(angle);
	} 
	
	public void tournerAsynch(double vitesse, double angle, boolean bool) {
		pilot.setAngularSpeed(vitesse);
		pilot.rotate(angle, true);
	}
	
	// Permet de faire avancer le robot a une certaine vitesse � une distance
	public void avancer(double vitesse, double distance) {
		pilot.setLinearSpeed(vitesse);
		pilot.travel(distance, false);
		// Il faudra faire des conditions pour qu'il s'arr�te
		// Quand il rencontre un mur
		// Quand il rencontre une ligne blanche
	}
	
	// Permet d'avancer vers
	
	// Permet de reculer le robot, il ne doit pas heurter les murs!!
	public void reculer(double vitesse, double distance) {
		pilot.setLinearSpeed(vitesse);
		pilot.travel(-distance, false);
	}
		
	// Permet de d�placer le robot de mani�re courb�e.
	public void courbe(double degre, double angle) {
		pilot.steer(degre, angle);
		pilot.travel(20, false);
	}
	
	// M�thode ouvrir les pinces
	public void ouvrir() {
		pince.rotate(360*2);
	}
	
	// M�thode fermer les pinces
	public void fermer() {
		pince.rotate(-360*2);
	}
	
	// M�thode r�cup�ration de palet en avan�ant
	public void recuperer() {
		this.ouvrir();
		pilot.travel(10, false);
		this.fermer();
		this.tourner(50, 50);
		this.avancer(20, 30);
    	this.tourner(50, -50);
	}
	
	// M�thode qui permet de l�cher le palet
	public void lacher() {
		this.ouvrir();
		this.reculer(20, 15);
		this.fermer();
		this.tourner(30, 90);
	}
	
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
    {
    	Piloter ori = new Piloter();
    	ori.fermer();
    	ori.avancer(30, 50);
    	ori.recuperer();
    	ori.avancer(30, 50);
    	ori.lacher();
//    	ori.tourner(20, 90);
//    	ori.courbe(60, -30);
//    	ori.courbe(60, 20);
//    	ori.avancer(20, 30);
    	//ori.reculer(10, 20);
    }
}
