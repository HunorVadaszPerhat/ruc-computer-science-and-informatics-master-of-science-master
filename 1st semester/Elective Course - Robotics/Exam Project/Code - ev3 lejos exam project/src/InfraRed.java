import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;

public class InfraRed extends Thread {
	
	int objectDistance = 1000;
	
	EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S4);
	
	SampleProvider sp = irSensor.getDistanceMode();
	
	public InfraRed() 
	{
		this.setDaemon(true);
		this.start();
	}
	
	public void run() 
	{
		while(true) 
		{
			float [] sample = new float[sp.sampleSize()];
		    sp.fetchSample(sample, 0);
		    if((int)sample[0]==0) objectDistance=1000;
		    else objectDistance=(int)sample[0];
		    Thread.yield();
		} 
		   
	}
		   
	public int getObjectDistance() 
	{
		return objectDistance;
	}
	   
}

