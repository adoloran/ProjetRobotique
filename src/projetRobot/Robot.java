package projetRobot;

import java.util.ArrayList;
import java.util.TreeMap;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

import lejos.hardware.Button;

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
	
	public CapteurUltrason getCapteurU() {
		return CapteurUltrason;
	}
	
	public CapteurPression getCapteurP() {
		return CapteurPression;
	}
	
	public CapteurCouleur getCapteurC() {
		return CapteurCouleur;
	}
	
	@SuppressWarnings("deprecation")
	public float scannerZone() {
		int i = 0;
		int indiceMin = 0;
		float distanceMin = 4 ;
		
		tournerAsynch(30, 345);
		
		while(getPilot().isMoving()) {
			
			float distance = CapteurUltrason.getDistance();
			i++;
			indiceMin = distance < distanceMin ? i : indiceMin; 
			distanceMin = distance < distanceMin ? distance : distanceMin ; 
		}
		System.out.println("I  " + indiceMin + "  distancemin "+ distanceMin+ " angle departs "+ (360.0/i)*indiceMin);
		
//	
		getPilot().setLinearSpeed(50);
		getPilot().rotate((360.0/i)*indiceMin/2);
		return distanceMin ; 
//		getPilot().travel(distanceMin*100+30, true);
//		
//		while (getPilot().isMoving()) {
//			getPince().rotate(360*2);
//			getPince().rotate(-360*2);
//		}

		
//		Button.ENTER.waitForPress();
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
