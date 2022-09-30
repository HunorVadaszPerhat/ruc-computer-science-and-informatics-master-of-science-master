import lejos.hardware.BrickFinder;
import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.Sound;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.TextMenu;

public class Move implements Behavior {
	
	EV3LargeRegulatedMotor leftMotor;
	EV3LargeRegulatedMotor rightMotor;
	
	Wheel wheel1; 
	Wheel wheel2;
	
	Chassis chassis;
	MovePilot ev3robot;
	
	// navigator constructor
	Navigator nav;
	
	// navigate()
    // demo navigating using way points
	public static void navigate(Navigator nav) {
		
    		// get EV3 brick
		EV3 ev3brick = (EV3) BrickFinder.getLocal();
		
		// instantiated LCD class for displaying and Keys class for buttons
		Keys buttons = ev3brick.getKeys();
        TextLCD lcddisplay = ev3brick.getTextLCD();
		
        
        // set up way points
        Waypoint wp1 = new Waypoint(20, 20); 
        Waypoint wp2 = new Waypoint(0, 0); 
        Waypoint wp3 = new Waypoint(0, 30);
  
        // clear menu
        lcddisplay.clear();
        
        // display current location
        lcddisplay.drawString("At 0, 0", 0, 2);
        lcddisplay.drawString("Press ENTER", 0, 3);
        lcddisplay.drawString("to continue", 0, 4);
        
        // wait until ENTER key is pressed
        while (buttons.waitForAnyPress() != Keys.ID_ENTER)
        		Thread.yield();
        
        // navigate to way point one, display new location
        lcddisplay.clear();
        nav.goTo(wp1);
        lcddisplay.drawString("At 20, 20", 0, 2);
        lcddisplay.drawString("Press ENTER", 0, 3);
        lcddisplay.drawString("to continue", 0, 4);
        
        // wait until ENTER key is pressed
        while (buttons.waitForAnyPress() != Keys.ID_ENTER)
        		Thread.yield();
        
        // navigate to way point two, display new location
        lcddisplay.clear();
        nav.goTo(wp2);
        lcddisplay.drawString("At 0, 0", 0, 2);
        lcddisplay.drawString("Press ENTER", 0, 3);
        lcddisplay.drawString("to continue", 0, 4);
        
        // wait until ENTER key is pressed
        while (buttons.waitForAnyPress() != Keys.ID_ENTER)
        		Thread.yield();
        
        // navigate to way point 3, display new location
        lcddisplay.clear();
        nav.goTo(wp3);
        lcddisplay.drawString("At 0, 30", 0, 2);
        lcddisplay.drawString("Press ENTER", 0, 3);
        lcddisplay.drawString("to continue", 0, 4);
        
        while(nav.isMoving())
    	  		Thread.yield();
         
    }
	
	public Move (EV3LargeRegulatedMotor LEFT_MOTOR, EV3LargeRegulatedMotor RIGHT_MOTOR,Wheel wheel1, Wheel wheel2, Chassis chassis, MovePilot ev3robot, Navigator nav) 
	{
	      this.leftMotor = LEFT_MOTOR; 
	      this.rightMotor = RIGHT_MOTOR;
	      
	      this.wheel1 = wheel1; 
	      this.wheel2 = wheel2;
	      
	      this.chassis = chassis; 
	      this.ev3robot = ev3robot;
	      this.nav = nav;
	}
	
	private boolean suppressed;
	
	public boolean takeControl() 
	{
	      return true;
	}
	
	public void action() 
	{	
			String[] menuItems = new String[1];
	        menuItems[0] = "Task: Navigator";
	        TextMenu menu = new TextMenu(menuItems);
			
			// control the Waypoints
	        switch (menu.select()) 
	        {
		        case 0:
		        navigate(nav);
		        break; 
	        } 
		}
	
	
	public void suppress() 
	{
		//suppressed = true;
	}
}
