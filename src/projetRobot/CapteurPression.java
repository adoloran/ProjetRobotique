package projetRobot;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.EV3TouchSensor;

public class CapteurPression {
	
	private EV3TouchSensor CapteurPression;

	public CapteurPression(String nomPort) {
		// TODO Auto-generated constructor stub
		CapteurPression = new EV3TouchSensor(LocalEV3.get().getPort(nomPort));
	}
	

	 public boolean isPressed()
	    {
	        float[] sample = new float[1];
	        CapteurPression.fetchSample(sample, 0);

	        return sample[0] != 0;
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
