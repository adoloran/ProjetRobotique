package projetRobot;

import lejos.*;
import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.utility.Delay;

public class ProjetRobot1 {
	
	/**
	 * @param args
	 */
	


	public ProjetRobot1() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		GraphicsLCD g = BrickFinder.getDefault().getGraphicsLCD();
		
		g.drawString("Hello world", 0, 0, GraphicsLCD.VCENTER |
				GraphicsLCD.LEFT);
		Delay.msDelay(5000);
	}

}
