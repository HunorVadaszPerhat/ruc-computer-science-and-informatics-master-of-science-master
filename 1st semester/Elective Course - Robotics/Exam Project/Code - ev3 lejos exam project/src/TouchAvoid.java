import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class TouchAvoid implements Behavior {
	
	EV3LargeRegulatedMotor leftMotor;
	EV3LargeRegulatedMotor rightMotor;
	
	Wheel wheel1; 
	Wheel wheel2;
	
	Chassis chassis;
	MovePilot ev3robot;
	
	InfraRed irAdapter;
	
	// navigator constructor
	Navigator nav;
	
	EV3TouchSensor touch;
	
	SampleProvider touched;
	
	float[] sample;
	
	public boolean suppressed = false;
	 
	public TouchAvoid (EV3LargeRegulatedMotor LEFT_MOTOR, EV3LargeRegulatedMotor RIGHT_MOTOR,Wheel wheel1, 
			Wheel wheel2, Chassis chassis, MovePilot ev3robot, Navigator nav, 
			EV3TouchSensor touch, SampleProvider touched, float[] sample) 
	{
	      this.leftMotor = LEFT_MOTOR; 
	      this.rightMotor = RIGHT_MOTOR;
	      
	      this.wheel1 = wheel1; 
	      this.wheel2 = wheel2;
	      
	      this.chassis = chassis; 
	      this.ev3robot = ev3robot;
	      this.nav = nav;
	      
	      this.touch = touch;
	      this.touched = touched;
	      this.sample = sample;
	      
	}
	
	 public boolean takeControl() {
		 touched.fetchSample(sample,0);
	     int t = (int) sample[0];
	     LCD.drawInt(t, 2, 2);
	     return t==1 || Button.ESCAPE.isDown();
	   }
	 
	   public void action() 
	   { 

		   suppressed = true;
		   	
			// execute 90 degree turn to navigate around object
		      ev3robot.rotate(90);
		      ev3robot.travel(20);
		      ev3robot.rotate(-90);
		      ev3robot.travel(40);
		      ev3robot.rotate(-90);
		      ev3robot.travel(20);
		      ev3robot.rotate(90);
		      ev3robot.travel(20);
		      
		      suppressed = false;
	   }
	   
	   public void suppress() 
	   {
		   while(suppressed) 
	    	  	Thread.yield();
	   }
}
