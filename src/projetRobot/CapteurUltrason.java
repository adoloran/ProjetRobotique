package projetRobot;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.EV3UltrasonicSensor;

public class CapteurUltrason {
	private EV3UltrasonicSensor CapteurUltrason;

	public CapteurUltrason(String nomPort) {
		// TODO Auto-generated constructor stub
		CapteurUltrason = new EV3UltrasonicSensor(LocalEV3.get().getPort(nomPort));
	}

	public float getDistance() {

		float[] sample = new float[1];
		CapteurUltrason.fetchSample(sample, 0);

		return sample[0];

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
