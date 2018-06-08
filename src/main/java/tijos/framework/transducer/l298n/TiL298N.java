package tijos.framework.transducer.l298n;

import java.io.IOException;

import tijos.framework.devicecenter.TiGPIO;
import tijos.framework.devicecenter.TiPWM;

/**
 * L298N module DC Motor controller driver
 * 
 * @author TiJOS
 *
 */
public class TiL298N {

	/**
	 * GPIO port
	 */
	private TiGPIO gpioObj = null;
	
	/**
	 * PWM port
	 */
	TiPWM pwmObj = null;
	
	/**
	 * MotorA Pins 
	 */
	int motorApin1;
	int motorApin2;
	int motorAPWM;

	/**
	 * MotorB Pins 
	 */
	int motorBpin1;
	int motorBpin2;
	int motorBPWM;
	
	/**
	 * MotorA/B speed
	 */
	int motorSpeed;

	
	/** 
	 * Initialize with 2 motors  
	 * @param mA  Motor A, set null if not used
	 * @param mB  Motor B, set null if not used
	 */
	public TiL298N(TiGPIO gpio, int APin1, int APin2,
			int BPin1, int BPin2, TiPWM pwm, int APWMChn, int BPWMChn) {

		this.gpioObj = gpio;
		this.pwmObj = pwm;
		
		this.motorApin1 = APin1;
		this.motorApin2 = APin2;
		this.motorAPWM = APWMChn;
		
		this.motorBpin1 = BPin1;
		this.motorBpin2 = BPin2;
		this.motorBPWM = BPWMChn;
		
	}

	/**
	 * initialize the GPIO pin
	 * 
	 * @throws IOException
	 */
	public void initialize() throws IOException {

		gpioObj.setWorkMode(motorApin1, TiGPIO.OUTPUT_PP);
		gpioObj.setWorkMode(motorApin2, TiGPIO.OUTPUT_PP);
		
		gpioObj.setWorkMode(motorBpin1, TiGPIO.OUTPUT_PP);
		gpioObj.setWorkMode(motorBpin2, TiGPIO.OUTPUT_PP);
		
		pwmObj.setFrequency(500); //500hz
		
		this.motorSpeed = 0;

	}
	
	/**
	 * Used to set the pwm value used to determine the motor speed.
	 * 
	 * @param pwm
	 *            Value from 0 to 255.
	 */
	public void setSpeed(int speed) throws IOException {
		if(this.motorSpeed != speed) {
			pwmObj.setDutyCycle(motorAPWM, speed/255.0);
			pwmObj.setDutyCycle(motorBPWM, speed/255.0);
		
			pwmObj.updateFreqAndDuty();
			this.motorSpeed = speed;
		}
	}

	/**
	 * Run motor in forward direction (may depends by wires).
	 * 
	 * @throws IOException
	 */
	public void forward(int speed) throws IOException {

		gpioObj.writePin(this.motorApin1, 1);
		gpioObj.writePin(this.motorApin2, 0);

		gpioObj.writePin(this.motorBpin1, 1);
		gpioObj.writePin(this.motorBpin2, 0);
		
		setSpeed(speed);
	}

	/**
	 * Run motor in backward direction (may depends by wires).
	 * 
	 * @throws IOException
	 */
	public void backward(int speed) throws IOException {
		
		gpioObj.writePin(this.motorApin1, 0);
		gpioObj.writePin(this.motorApin2, 1);

		gpioObj.writePin(this.motorBpin1, 0);
		gpioObj.writePin(this.motorBpin2, 1);
		
		setSpeed(speed);
	}

	/**
	 * Stop the motor.
	 * 
	 * @throws IOException
	 */
	public void stop() throws IOException {
		
		gpioObj.writePin(this.motorApin1, 0);
		gpioObj.writePin(this.motorApin2, 0);

		gpioObj.writePin(this.motorBpin1, 0);
		gpioObj.writePin(this.motorBpin2, 0);
		
		setSpeed(0);
	}

	/**
	 * Turn right
	 * 
	 * @throws IOException
	 */
	public void turnRight(int speed) throws IOException {

		gpioObj.writePin(this.motorApin1, 0);
		gpioObj.writePin(this.motorApin2, 1);

		gpioObj.writePin(this.motorBpin1, 1);
		gpioObj.writePin(this.motorBpin2, 0);
		
		setSpeed(speed);
	}

	/**
	 * Turn left
	 * 
	 * @throws IOException
	 */
	public void turnLeft(int speed) throws IOException {

		gpioObj.writePin(this.motorApin1, 1);
		gpioObj.writePin(this.motorApin2, 0);
		
		gpioObj.writePin(this.motorBpin1, 0);
		gpioObj.writePin(this.motorBpin2, 1);
		
		setSpeed(speed);
	}

}
