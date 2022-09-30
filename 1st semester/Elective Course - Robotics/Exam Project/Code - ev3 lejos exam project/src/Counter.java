import lejos.hardware.lcd.LCD;

class Counter extends Thread {
	public void run() {
		for (int i = 0; i < 13; ++i) {
			LCD.drawInt(i, 6, 4);
			LCD.refresh();
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
	}
}
