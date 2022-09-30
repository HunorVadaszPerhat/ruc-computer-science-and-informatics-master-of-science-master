import lejos.hardware.Button;
import lejos.hardware.Keys;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.sensor.MindsensorsAbsoluteIMU;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import lejos.utility.TextMenu;

public class SensorAvoid implements Behavior {
	
	EV3LargeRegulatedMotor leftMotor;
	EV3LargeRegulatedMotor rightMotor;
	
	Wheel wheel1; 
	Wheel wheel2;
	
	Chassis chassis;
	MovePilot ev3robot;
	
	InfraRed irAdapter;
	
	// navigator constructor
	Navigator nav;
	
	public boolean suppressed = false;
	
	MindsensorsAbsoluteIMU mindSensor;
	
	Mindsensor mindsensor;
	
    Music mThread;
	Counter cThread;
	
	Keys buttons;
	
	public SensorAvoid (EV3LargeRegulatedMotor LEFT_MOTOR, EV3LargeRegulatedMotor RIGHT_MOTOR,
			Wheel wheel1, Wheel wheel2, Chassis chassis, MovePilot ev3robot, 
			Navigator nav, InfraRed irA, MindsensorsAbsoluteIMU mindSensor, Mindsensor mindsensor,
			Music mThread, Counter cThread) 
	{
	      this.leftMotor = LEFT_MOTOR; 
	      this.rightMotor = RIGHT_MOTOR;
	      
	      this.wheel1 = wheel1; 
	      this.wheel2 = wheel2;
	      
	      this.chassis = chassis; 
	      this.ev3robot = ev3robot;
	      this.nav = nav;
	      
	      this.irAdapter=irA;
	      
	      this.mindSensor = mindSensor;
	      
	      this.mindsensor = mindsensor;
	      
	      this.mThread = mThread;
	      this.cThread = cThread;
	}
	
	   public boolean takeControl() 
	   {
	      return irAdapter.objectDistance < 30; // global variable
	   }
	   
	   public void action() 
	   {
	      suppressed = true;
	      
	      String[] menuItems = new String[1];
	      menuItems[0] = "Task: Sensor";
	      TextMenu menu = new TextMenu(menuItems);
	      
	      
	      ev3robot.stop();

	      ev3robot.rotate(90);
	      
	      ev3robot.travel(20);
	      ev3robot.rotate(-90);
	      
	      ev3robot.travel(50);
	      ev3robot.rotate(-90);
	      
	      ev3robot.travel(20);
	      ev3robot.rotate(90);

	      ev3robot.travel(20);
	      
	      mindsensor.mindSensor(mindSensor);
	      Thread.yield();
	      
	      ev3robot.travel(20);
	      ev3robot.rotate(90);
	      ev3robot.travel(20);
	      
	      mindsensor.mindSensor(mindSensor);
	      Thread.yield();
	      
	      mThread.start(); 
	      cThread.start(); 
	      try 
	      {
	    	  		while (buttons.getButtons() != Keys.ID_ESCAPE) 
	    	  		{ 
	    	  			Thread.yield();
	    	  		}
	    	  		System.exit(0);
	    	  		
	    	  } catch (Exception e) {
	    	  }
	      
	      suppressed = false;
	      
	      }
	   
	   public void suppress() 
	   {
	      while(suppressed) 
	    	  	Thread.yield();
	   }
}
