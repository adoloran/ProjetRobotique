package projetRobot;

import java.awt.Color;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.*;
import lejos.robotics.SampleProvider;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.*;
import lejos.robotics.SampleProvider;


public class Capteur {
	
	private EV3TouchSensor CapteurPression;
	private EV3UltrasonicSensor CapteurUltrason;
	private EV3ColorSensor CapteurCouleur;
	
	
	

	public Capteur() {
		CapteurCouleur = new EV3ColorSensor(LocalEV3.get().getPort("S2"));
		CapteurPression = new EV3TouchSensor(LocalEV3.get().getPort("S3"));
		CapteurUltrason = new EV3UltrasonicSensor(LocalEV3.get().getPort("S4"));

	}
	
	public boolean isWhite() {
		// attention tester si le blanc est bien détecté, selon la luminosité il peut ne pas fonctionner. Dans ce cas prévoir un calibrage.
		return(CapteurCouleur.getName()=="white");
		
	}
	
	public SensorMode isPressed() {
		
		return(CapteurPression.getTouchMode());
	}
	
	public SampleProvider getDistance() {
		return CapteurUltrason.getDistanceMode();
		
	}
	
	
	
	
	
	
	

}
