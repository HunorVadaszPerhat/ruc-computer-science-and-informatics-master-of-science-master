import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.*;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.MindsensorsAbsoluteIMU;

public class Main {

	 	static EV3LargeRegulatedMotor LEFT_MOTOR = new EV3LargeRegulatedMotor(MotorPort.A);
	 	static EV3LargeRegulatedMotor RIGHT_MOTOR = new EV3LargeRegulatedMotor(MotorPort.C);
	 	
	 	private static Music mThread;
	 	private static Counter cThread;
	 	
	 	public static void main(String[] args) {
	    
	    Wheel wheel1 = WheeledChassis.modelWheel(LEFT_MOTOR, 4.32).offset(-6.3); 
		Wheel wheel2 = WheeledChassis.modelWheel(RIGHT_MOTOR, 4.32).offset(6.3);
		
		Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, WheeledChassis.TYPE_DIFFERENTIAL);
		MovePilot ev3robot = new MovePilot(chassis);
		
		// navigator constructor
		Navigator nav = new Navigator(ev3robot);
		
		EV3TouchSensor touch = new EV3TouchSensor(SensorPort.S3);
		
		SampleProvider touched = touch.getTouchMode();
		
		MindsensorsAbsoluteIMU mindSensor = new MindsensorsAbsoluteIMU(SensorPort.S2);
		
		float[] sample = new float[touched.sampleSize()];
		
		final SampleProvider sp = mindSensor.getAngleMode();
	    
		Behavior b1 = new Move(LEFT_MOTOR, RIGHT_MOTOR, wheel1, wheel2, chassis, ev3robot, nav);
	    
	    InfraRed ir = new InfraRed();
	    EV3 ev3brick = (EV3) BrickFinder.getLocal();
		Keys buttons = ev3brick.getKeys();
	    TextLCD lcddisplay = ev3brick.getTextLCD();
	    
	    Mindsensor mindsensor = new Mindsensor(mindSensor, sp, lcddisplay);
	    
	    mThread = new Music();
	    cThread = new Counter();
		
	    
	    Behavior b2 = new SensorAvoid(LEFT_MOTOR, RIGHT_MOTOR, wheel1, wheel2, chassis,
	    				ev3robot, nav, ir, mindSensor, mindsensor, mThread, cThread);
	    
	    
	    Behavior b3 = new TouchAvoid(LEFT_MOTOR, RIGHT_MOTOR, wheel1, 
	    		wheel2, chassis, ev3robot, nav, touch, touched, sample);
	    
	    Behavior[] b1b2b3 = { b2};
	    
	    Arbitrator arby = new Arbitrator(b1b2b3);
	    arby.go();
	}

}
