import lejos.hardware.Sound;

public class Music extends Thread{
	
	public void run() {

    short Low_G = 392, B_Flat = 470, D = 588, C = 523, E_Flat = 627, F = 697, G = 784, A_Flat = 830;
    short[] note = 
  	  { 
  		  600, G, 100, F, 100, G, 400, C, 400, 0, 600,
  		  A_Flat, 100, G, 100, A_Flat, 200, G, 200, F, 400 

  	  };
  		  
    for (int i = 0; i < note.length; i += 2) 
  		  { 
  			  	short w = note[i];
  		  		Sound.playTone(note[i], w); 
  		  		Sound.pause(w);
  		  }
	}
}
