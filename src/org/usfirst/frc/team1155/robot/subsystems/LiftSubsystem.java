package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.PortMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSubsystem extends Subsystem{

	public TalonSRX leftLiftMotor, rightLiftMotor, leftLift2Motor, rightLift2Motor;
	public Encoder liftEncoder;
	public final double LIFT_SPEED = 1.;
	public final double TICKS_TO_TOP = 100;
	public final double TICKS_TO_SWITCH_HEIGHT = 20;
	public final double TICKS_AT_BOTTOM = 0;

	
	
	
	public void initDefaultCommand() {

		leftLiftMotor = new TalonSRX(PortMap.LIFT_LEFT_TALON);
		rightLiftMotor = new TalonSRX(PortMap.LIFT_RIGHT_TALON);
		leftLift2Motor = new TalonSRX(PortMap.LIFT_LEFT_2_TALON);
		rightLift2Motor = new TalonSRX(PortMap.LIFT_RIGHT_2_TALON);
		liftEncoder = new Encoder(1,2);
		

		leftLiftMotor.setNeutralMode(NeutralMode.Brake);
		rightLiftMotor.setNeutralMode(NeutralMode.Brake);
		leftLift2Motor.setNeutralMode(NeutralMode.Brake);
		rightLift2Motor.setNeutralMode(NeutralMode.Brake);
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		stop();
	}	
	
	public void stop() {
		leftLiftMotor.set(ControlMode.PercentOutput, 0);
		rightLiftMotor.set(ControlMode.PercentOutput, 0);
		leftLift2Motor.set(ControlMode.PercentOutput, 0);
		rightLift2Motor.set(ControlMode.PercentOutput, 0);
	}
	
	public void setSpeed(double speed){
		leftLiftMotor.set(ControlMode.PercentOutput, -speed);
		leftLift2Motor.set(ControlMode.PercentOutput, -speed);
		rightLiftMotor.set(ControlMode.PercentOutput, speed);
		rightLift2Motor.set(ControlMode.PercentOutput, speed);
	}
	
	public void setLeftSideSpeed(double speed) {
		leftLiftMotor.set(ControlMode.PercentOutput, -speed);
		leftLift2Motor.set(ControlMode.PercentOutput, -speed);
	}
	
	public void setRightSideSpeed(double speed) {
		rightLiftMotor.set(ControlMode.PercentOutput, speed);
		rightLift2Motor.set(ControlMode.PercentOutput, speed);
	}
}
