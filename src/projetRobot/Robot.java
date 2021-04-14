package projetRobot;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

public class Robot extends Piloter {
	private CapteurPression CapteurPression;
	private CapteurUltrason CapteurUltrason;
	private CapteurCouleur CapteurCouleur;
	private String nom ;
	

	public Robot(String nomRobot, String portPression, String portUltrason, String portCouleur) {
		// TODO Auto-generated constructor stub
		nom = nomRobot;
		CapteurPression = new CapteurPression(portPression);
		CapteurUltrason = new CapteurUltrason(portUltrason);
		CapteurCouleur = new CapteurCouleur(portCouleur);
	
	}

	public String getNom() {
		return nom;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
