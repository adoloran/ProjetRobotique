package projetRobot;

import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.TextLCD;


public class ProjetRobot1 {
	
	/**
	 * @param args
	 */
	


	public ProjetRobot1() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		
			EV3 ev3 = (EV3) BrickFinder.getLocal();
			TextLCD lcd = ev3.getTextLCD();
			Keys keys = ev3.getKeys();
			
			lcd.drawString("Hello World", 4, 4);
			keys.waitForAnyPress();
	}

}
