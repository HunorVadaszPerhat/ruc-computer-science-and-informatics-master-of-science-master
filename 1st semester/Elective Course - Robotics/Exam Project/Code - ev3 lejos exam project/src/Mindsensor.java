import lejos.hardware.lcd.LCD;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.MindsensorsAbsoluteIMU;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
public class Mindsensor {
	
	MindsensorsAbsoluteIMU mindSensor;
	SampleProvider sp;
	TextLCD lcddisplay;
	
	public Mindsensor(MindsensorsAbsoluteIMU mindSensor, SampleProvider sp, TextLCD lcddisplay) 
	{
		this.mindSensor = mindSensor;
		this.sp = sp;
		this.lcddisplay = lcddisplay;

	}
	
    //Control loop

	public void mindSensor(final SampleProvider sp){
	   // for(int i = 0; i <= 2; i++) 
	    //{
	    	float [] sample = new float[sp.sampleSize()];
	        sp.fetchSample(sample, 0);
	        int value;
	        value = (int)sample[0];
	
			System.out.println("Gyro angle: " + value);
			lcddisplay.drawString("Gyro angle: " + value, 2, 2);
			lcddisplay.clear();
	        Delay.msDelay(2000);	        
	    		}
	
		//}
}
	
	
	


