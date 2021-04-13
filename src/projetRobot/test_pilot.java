import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.navigation.DifferentialPilot;

public class test_pilot
{
    
    public static void main(String[] args)
    {
    	// Roue gauche et droite
    	EV3LargeRegulatedMotor mLeftMotor = new EV3LargeRegulatedMotor(MotorPort.C);
    	EV3LargeRegulatedMotor mRightMotor = new EV3LargeRegulatedMotor(MotorPort.B);
    	int i = 10;
    	
    	// DifferentialPilot(roue gauche, roue droiten l'entracte entre les 2 roues, le moteur gauche, le moteur droit, reculer)
    	DifferentialPilot pilot = new DifferentialPilot(5.6, 5.6, 12.5, mLeftMotor, mRightMotor, false);
        pilot.setLinearSpeed(20.0);
        // Faire avancer le robot
        pilot.travel(50);
    	// Vitesse de rotation (<20 = lent)
        // pilot.setAngularSpeed(20.0);
        // Rotation < 0 vers la droite, > 0 vers la gauche
        // pilot.rotate(-90);
        // Test de stop
        if (i > 20)
        	pilot.stop();
        i++;
        // Tant que le robot bouge
        while (pilot.isMoving());
        
    }
}
