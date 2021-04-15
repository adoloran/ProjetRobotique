import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.EV3ColorSensor;

public class CapteurCouleur {
	private EV3ColorSensor CapteurCouleur;
	private int RealIDWhite ;
	
	public CapteurCouleur(String nomPort) {
		// TODO Auto-generated constructor stub
		CapteurCouleur = new EV3ColorSensor(LocalEV3.get().getPort(nomPort));
		RealIDWhite = 6;
		CapteurCouleur.setCurrentMode("ColorID");
		CapteurCouleur.setFloodlight(6);
		CapteurCouleur.setFloodlight(true);

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
		sample = new float[CapteurCouleur.sampleSize()];		
		CapteurCouleur.fetchSample(sample, 0);
		int color = (int) sample[0];
	
		return color ;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CapteurCouleur toby = new CapteurCouleur("S3");
//		toby.CalibrerWhite();
		System.out.println("Est-ce blanc ? " + toby.isWhite());
//		System.out.println();
	}

}
