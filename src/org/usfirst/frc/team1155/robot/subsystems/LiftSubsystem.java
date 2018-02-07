package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.PortMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSubsystem extends Subsystem{

	public TalonSRX leftLiftMotor, rightLiftMotor, leftReverseLiftMotor, rightReverseLiftMotor;
	public Encoder liftEncoder;
	public final double TICKS_TO_TOP = 100;
	public final double TICKS_TO_SWITCH_HEIGHT = 20;
	public final double TICKS_AT_BOTTOM = 0;

	public void initDefaultCommand() {

		leftLiftMotor = new TalonSRX(PortMap.LIFT_LEFT_TALON);
		rightLiftMotor = new TalonSRX(PortMap.LIFT_RIGHT_TALON);
		leftReverseLiftMotor = new TalonSRX(PortMap.LIFT_REVERSE_LEFT_TALON);
		rightReverseLiftMotor = new TalonSRX(PortMap.LIFT_REVERSE_RIGHT_TALON);
		liftEncoder = new Encoder(1,2);
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		stop();
	}	
	
	public void stop() {
		leftLiftMotor.set(ControlMode.PercentOutput, 0);
		rightLiftMotor.set(ControlMode.PercentOutput, 0);
		leftReverseLiftMotor.set(ControlMode.PercentOutput, 0);
		rightReverseLiftMotor.set(ControlMode.PercentOutput, 0);
	}
	
	public void setSpeed(double speed){
		leftLiftMotor.set(ControlMode.PercentOutput, speed);
		rightLiftMotor.set(ControlMode.PercentOutput, speed);
		leftReverseLiftMotor.set(ControlMode.PercentOutput, -speed);
		rightReverseLiftMotor.set(ControlMode.PercentOutput, -speed);
	}
}
