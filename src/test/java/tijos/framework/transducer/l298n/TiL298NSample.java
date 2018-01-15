package tijos.sample.transducer.l298n;

import java.io.IOException;

import tijos.framework.devicecenter.TiGPIO;
import tijos.framework.devicecenter.TiPWM;
import tijos.framework.transducer.l298n.TiL298N;
import tijos.util.Delay;

public class TiL298NSample {

	public static void main(String[] args) {
		try {
		
		/*
		 * 定义使用的TiGPIO port
		 */
		int gpioPort0 = 0;
		
		/*
		 * 定义所使用的gpioPin
		 */
		int gpioPin0 = 0;
		int gpioPin1 = 1;

		int gpioPin2 = 6;
		int gpioPin3 = 7;
		

		TiGPIO gpio0 = TiGPIO.open(gpioPort0, gpioPin0, gpioPin1, gpioPin2, gpioPin3);
		
		
		int pwmPortId = 0;
		int pwmChn0 = 0;
		int pwmChn1 = 1;
		
		TiPWM pwmPort = TiPWM.open(pwmPortId, pwmChn0, pwmChn1);

		TiL298N l298n = new TiL298N(gpio0,gpioPin0, gpioPin1, gpioPin2, gpioPin3, pwmPort, pwmChn0, pwmChn1);
		
		l298n.initialize();
				
		while(true){
			
			//tell the motor to go forward (may depend by your wiring)
			  l298n.forward(100);

			  System.out.print("forward ");

			  Delay.msDelay(3000);

			  //stop running
			  l298n.stop();

			  Delay.msDelay(3000);

			  //tell the motor to go back (may depend by your wiring)
			  l298n.backward(100);

			  System.out.print("backward ");

			  Delay.msDelay(3000);

			  //stop running
			  l298n.stop();

			  System.out.print("stop ");
	
			  Delay.msDelay(3000);
			  
			  l298n.turnLeft(100);

			  System.out.print("left ");
				
			  Delay.msDelay(3000);
			  
			  l298n.stop();

			  System.out.print("stop ");
	
			  Delay.msDelay(3000);
			  
			  l298n.turnRight(100);

			  System.out.print("right ");
				
			  Delay.msDelay(3000);
			  
			  l298n.stop();

			  System.out.print("stop ");
	
			  Delay.msDelay(3000);
			  
		}
			
			
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
		
		
		
	}

}
