package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.PortMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Ultrasonic;


public class IntakeSubsystem extends Subsystem{

	public TalonSRX leftIntakeMotor, rightIntakeMotor, leftArmMotor, rightArmMotor;
	public DoubleSolenoid armSolenoid, tiltSolenoid;
	public int counter;
	public boolean isStopped = true;
	//public Ultrasonic ultrasonic = new Ultrasonic(1,1);
	public final double MIN_INCHES_FROM_ULTRA_TO_BOX = 4.0;
	public final double MAX_INCHES_FROM_ULTRA_TO_BOX = 24.5;
	public final double REV_AMOUNT = 50.0;
	public final double ADJUST_SPEED = 0.6;
	public final double SECONDS_PER_EXECUTE = 0.02;
	
	public void initDefaultCommand() {

		leftIntakeMotor = new TalonSRX(PortMap.INTAKE_LEFT_TALON);
		rightIntakeMotor = new TalonSRX(PortMap.INTAKE_RIGHT_TALON);
		leftArmMotor = new TalonSRX(PortMap.INTAKE_ARM_LEFT_TALON);
		rightArmMotor = new TalonSRX(PortMap.INTAKE_ARM_RIGHT_TALON);

		armSolenoid = new DoubleSolenoid(PortMap.INTAKE_SOLENOID[0], PortMap.INTAKE_SOLENOID[1]);
		//ultrasonic.setAutomaticMode(true);
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		stop();
	}	
	
	public void stop() {
		isStopped = true;
		leftIntakeMotor.set(ControlMode.PercentOutput, 0);
		rightIntakeMotor.set(ControlMode.PercentOutput, 0);
		leftArmMotor.set(ControlMode.PercentOutput, 0);
		rightArmMotor.set(ControlMode.PercentOutput, 0);
	}
	
	public void setSpeed(double speed){
		if(speed != 0){
			isStopped = false;
		}
		leftIntakeMotor.set(ControlMode.PercentOutput, speed);
		rightIntakeMotor.set(ControlMode.PercentOutput, speed);
		leftArmMotor.set(ControlMode.PercentOutput, -speed);
		rightArmMotor.set(ControlMode.PercentOutput, speed);
	}
	
	public void setArmSpeed(double speed) {
		leftArmMotor.set(ControlMode.PercentOutput, -speed);
		rightArmMotor.set(ControlMode.PercentOutput, speed);
	}
	
	public void setCounter(int set) {
		counter = set;
	}
	
	public void incrementCounter(int incr) {
		counter += incr;
	}
	
	public void extendArmPiston() {
		armSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	public void retractArmPiston() {
		armSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void setLeftArmSpeed(double speed){
		leftArmMotor.set(ControlMode.PercentOutput, speed);
	}
	
	public void setLeftSpeed(double speed){
		leftArmMotor.set(ControlMode.PercentOutput, -speed);
		leftIntakeMotor.set(ControlMode.PercentOutput, speed);
	}

	public void setRightSpeed(double speed){
		rightArmMotor.set(ControlMode.PercentOutput, speed);
		rightIntakeMotor.set(ControlMode.PercentOutput, speed);
	}	
	
	//TODO: Find this value
	public double adjustTimeForAngle(double angle){
		return 0;
	}
	
}
