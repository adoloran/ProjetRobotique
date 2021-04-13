package projetRobot;

public class testCapteur {

	public testCapteur() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		Capteur test = new Capteur();
			
		System.out.println(test.isPressed());
		System.out.println(test.getDistance());
		System.out.println(test.isWhite());

			
		}

	}


