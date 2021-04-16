package projetRobot;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.navigation.DifferentialPilot;

public class Piloter  {
	
	private final static double roue = 5.6;
	private final static double entraxe = 12.5;
	
	// ORI
//	private final static EV3LargeRegulatedMotor mLeftMotor = new EV3LargeRegulatedMotor(MotorPort.C);
//	private final static EV3LargeRegulatedMotor mRightMotor = new EV3LargeRegulatedMotor(MotorPort.B);
//	private final static EV3MediumRegulatedMotor pince = new EV3MediumRegulatedMotor(MotorPort.A);
    
	// Toby
	private final EV3LargeRegulatedMotor mLeftMotor ;
	private final EV3LargeRegulatedMotor mRightMotor;
	private final EV3MediumRegulatedMotor pince ;
	private DifferentialPilot pilot;

	private final CapteurCouleur couleur ;
	private final CapteurUltrason ultrason ;
	private final CapteurPression pression ;
	
	private double rotation = 0;
	
	@SuppressWarnings("deprecation")
	
//	private boolean stopper = false;

    	
    // Initialisation du pilot
	public Piloter(Robot robot) {
		mLeftMotor = robot.getMotorG() ;
		mRightMotor = robot.getMotorD();
		pince = robot.getPince();
		couleur = robot.getCapteurC() ;
		ultrason = robot.getCapteurU();
		pression = robot.getCapteurP();
		
		pilot = new DifferentialPilot(roue, roue, entraxe, mLeftMotor, mRightMotor, false);
	}
	
	// Permet de tourner le robot � une vitesse de rotation donnee � un angle donnee  
	public void tourner(double vitesse, double angle) {
		// Vitesse de rotation (<20 = lent)
		pilot.setAngularSpeed(vitesse);
		// Rotation < 0 vers la droite, > 0 vers la gauche
		pilot.rotate(angle);
		this.rotation += angle;
	} 
	

	// Permet de faire avancer le robot a une certaine vitesse � une distance
	public void avancer(double vitesse) {
		pilot.setLinearSpeed(vitesse);
		pilot.travel(300, true);
		double distance = ultrason.getDistance()*100;
		
		boolean couleurb = false;
		while (!couleurb && distance > 30) {
			couleurb = couleur.isWhite();
			//System.out.println(couleurb);
			distance = ultrason.getDistance()*100;
			//System.out.println(distance);
		}
		pilot.stop();
	}
	
	public void tournerAsynch(double vitesse, double angle) {
		pilot.setAngularSpeed(vitesse);
		pilot.rotate(angle, true);
	}
	
	// Permet de faire avancer le robot a une certaine vitesse � une distance
	public void avancer(double vitesse, double distance) {
		//on doit avancer d'une distance pour aller chercher le palet le plus proche (apr�s scanner)
		pilot.setLinearSpeed(vitesse);
		pilot.travel(distance, true);
		
		distance = ultrason.getDistance()*100;
		boolean couleurb = false;

		while (!couleurb && distance > 30) {
			couleurb = couleur.isWhite();
			//System.out.println(couleurb);
			distance = ultrason.getDistance()*100;
			//System.out.println(distance);
		}
		pilot.stop();
	}
	
	public boolean attraperPalet(double distance) {
		// hors palet 1, attraper palet d�tecter lors scan (le plus proche)
		pilot.travel(distance,true);
		this.ouvrir();
		
		while (pilot.isMoving()) {
			if (couleur.isWhite() || distance < 30) {
				pilot.stop();
				this.fermer();
				return false;
			}
			
			if (pression.isPressed()) {
				this.fermer();
				return true;
			}
			
		}
		this.fermer();	
		return false;
	}
	
	public void marquer() {
		//palet attrap�, seulement d�poser le palet 
		this.repositionner();
		this.avancer(50);
		this.lacher();
		
	}
	
	// Permet d'avancer et de r�cup�rer le premier palet
	public void Palet1(double distance) {
		pilot.setLinearSpeed(30);
		pilot.travel(distance, true);
		
		while (pilot.isMoving()) {
			this.ouvrir();
			this.fermer();
		}
		
		this.tourner(70, -50);
		pilot.travel(30);
    	this.tourner(70, 50);
    	this.avancer(50);
    	this.lacher();
	}
	
	// Permet de reculer le robot, il ne doit pas heurter les murs!!
	public void reculer(double vitesse, double distance) {
		pilot.setLinearSpeed(vitesse);
		pilot.travel(-distance, false);
	}

	
	// M�thode ouvrir les pinces
	public void ouvrir() {
		pince.rotate(360*2);
	}
	
	// M�thode fermer les pinces
	public void fermer() {
		pince.rotate(-360*2);
	}
	

	
	// M�thode qui permet de l�cher le palet
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
	
	public DifferentialPilot getPilot() {
		
		return pilot;
	}
	
	public void setRotation(double rotation) {
		this.rotation += rotation;
	}
	
	
	
    public static void main(String[] args)
    {
//    	Piloter toby = new Piloter();
//    	toby.estMur();
//    	toby.fermer(); toby.fermer(); 
//    	couleur.CalibrerWhite();
//    	double distance = ultrason.getDistance()*100;
//    	toby.Palet1(35, distance);
//    	toby.tourner(50, -90);
//    	toby.marquer();
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
