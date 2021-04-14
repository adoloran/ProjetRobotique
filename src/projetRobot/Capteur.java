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
	
	private int RealIDWhite ;
	
	

	public Capteur() {
		CapteurCouleur = new EV3ColorSensor(LocalEV3.get().getPort("S3"));
		CapteurPression = new EV3TouchSensor(LocalEV3.get().getPort("S2"));
		CapteurUltrason = new EV3UltrasonicSensor(LocalEV3.get().getPort("S4"));
		RealIDWhite = 6;

	}
	
	public void CalibrerWhite() {
		// appelle cette méthode lorsque le robot est placé sur la couleur blanche.
		// actualise la valeur ID associée à la couleur blanche
		RealIDWhite = mesureColorID();
		
	}
	
	public boolean isWhite() {
		
		//Renvoie true si le capteur couleur mesure un ID equivalent à Blanc.
		
		return (mesureColorID()==RealIDWhite);
		
	}
	
	public int mesureColorID() {
		
		float[] sample ;
		CapteurCouleur.setCurrentMode("ColorID");
		sample = new float[CapteurCouleur.sampleSize()];
		
		CapteurCouleur.setFloodlight(6);
		CapteurCouleur.setFloodlight(true);
		
		CapteurCouleur.fetchSample(sample, 0);
		int color = (int) sample[0];
		
		CapteurCouleur.setFloodlight(false);
		
		
		return color ;
	}
	
	
	 public boolean isPressed()
	    {
	        float[] sample = new float[1];
	        CapteurPression.fetchSample(sample, 0);

	        return sample[0] != 0;
	    }
	
	public float getDistance() {
		
			float[] sample = new float[1];
			CapteurUltrason.fetchSample(sample, 0);
			
			return sample[0];
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
