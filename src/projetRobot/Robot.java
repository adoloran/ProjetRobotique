package projetRobot;

import java.util.ArrayList;
import java.util.TreeMap;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Robot {
	private CapteurPression CapteurPression;
	private CapteurUltrason CapteurUltrason;
	private CapteurCouleur CapteurCouleur;
	private String nom ;
	private final EV3LargeRegulatedMotor mLeftMotor ;
	private final EV3LargeRegulatedMotor mRightMotor ;
	private final EV3MediumRegulatedMotor pince ;
	private Piloter piloter ; 
	

	public Robot(String nomRobot, String portPression, String portUltrason, String portCouleur) {
		// TODO Auto-generated constructor stub
		nom = nomRobot;
		CapteurPression = new CapteurPression(portPression);
		CapteurUltrason = new CapteurUltrason(portUltrason);
		CapteurCouleur = new CapteurCouleur(portCouleur);
		mLeftMotor = new EV3LargeRegulatedMotor(MotorPort.B);
		mRightMotor = new EV3LargeRegulatedMotor(MotorPort.A);
		pince = new EV3MediumRegulatedMotor(MotorPort.C);
		piloter = new Piloter(this);
	
	}

	public String getNom() {
		return nom;
	}
	public Piloter getPiloter() {
		return piloter ;
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
	public EV3LargeRegulatedMotor getMotorG() {
		return mLeftMotor ; 
	}
	
   public EV3LargeRegulatedMotor getMotorD() {
		return mRightMotor ; 
   }
   
   public EV3MediumRegulatedMotor getPince() {
	   return pince ;
   }
	
	@SuppressWarnings("deprecation")
	public float scannerZone() {
		int i = 0;
		int indiceMin = 0;
		float distanceMin = 4 ;
		
		piloter.tournerAsynch(30, 345);
		
		while(piloter.getPilot().isMoving()) {
			
			float distance = CapteurUltrason.getDistance();
			i++;
			if (distance > 0.30) {
			indiceMin = distance < distanceMin ? i : indiceMin; 
			distanceMin = distance < distanceMin ? distance : distanceMin ; 
			}
		}
		System.out.println("I  " + indiceMin + "  distancemin "+ distanceMin+ " angle departs "+ (345.0/i)*indiceMin);
		
//	
		piloter.getPilot().setLinearSpeed(50);
		piloter.getPilot().rotate((345.0/i)*indiceMin/2);
		piloter.setRotation((345.0/i)*indiceMin/2);
		return distanceMin *100 ; 
//		getPilot().travel(distanceMin*100+30, true);
//		
//		while (getPilot().isMoving()) {
//			getPince().rotate(360*2);
//			getPince().rotate(-360*2);
//		}

		
//		Button.ENTER.waitForPress();
		
		
	}
	
	public void calibrage() {
		int i = 0;
		int indiceMin = 0;
		float distanceMin = 4 ;
		
		piloter.tourner(30, -20);
		piloter.tournerAsynch(30, 40);
		
		while(piloter.getPilot().isMoving()) {
			
			float distance = CapteurUltrason.getDistance();
			i++;
			if (distance > 0.30) {
			indiceMin = distance < distanceMin ? i : indiceMin; 
			distanceMin = distance < distanceMin ? distance : distanceMin ; 
			}
		}
		System.out.println("I  " + indiceMin + "  distancemin "+ distanceMin+ " angle departs "+ (40.0/i)*indiceMin);
		
//	
		piloter.getPilot().setLinearSpeed(50);
		piloter.getPilot().rotate((40.0/i)*indiceMin/2);
		piloter.setRotation((40.0/i)*indiceMin/2);

		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
