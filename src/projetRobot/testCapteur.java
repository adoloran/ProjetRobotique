package projetRobot;

import lejos.hardware.*;

public class testCapteur {

	public testCapteur() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		Capteur test = new Capteur();
		
		System.out.println("DEBUT DU TEST");
		
			
		System.out.println("Le capteur pression est en mode : " + test.isPressed()
							+ "\n Le capteur ultrason mesure : " + test.getDistance()); 
		//+ "Le capteur couleur mesure : " + test.isWhite());
		Button.ENTER.waitForPress();

		System.out.println("La mesure du capteur couleur est : " + test.mesureColorID());
		
		Button.ENTER.waitForPress();

		System.out.println("FIN DU TEST");

		
	}

}


