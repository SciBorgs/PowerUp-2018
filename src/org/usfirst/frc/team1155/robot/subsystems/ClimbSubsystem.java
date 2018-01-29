package org.usfirst.frc.team1155.robot.subsystems;
import org.usfirst.frc.team1155.robot.OI;
import org.usfirst.frc.team1155.robot.PortMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimbSubsystem extends Subsystem {
	
	public TalonSRX leftClimbTalon, rightClimbTalon, angleClimbTalon;
	public Servo servo;
	
	public double speedAngle = 1.0;
	
	public void initDefaultCommand() {
		leftClimbTalon = new TalonSRX(PortMap.DRIVE_BACK_LEFT_TALON);
		rightClimbTalon = new TalonSRX(PortMap.DRIVE_FRONT_LEFT_TALON);
		angleClimbTalon = new TalonSRX(PortMap.ANGLE_CLIMB_TALON);
		servo = new Servo(PortMap.TILT_CLIMB_SERVO);
	}
	
	public void setExtensionSpeed(double speed) {
		leftClimbTalon.set(ControlMode.PercentOutput, -speed);
		rightClimbTalon.set(ControlMode.PercentOutput, -speed);
		System.out.println("current 1 " +  rightClimbTalon.getOutputCurrent());
		System.out.println("current 2 "  + leftClimbTalon.getOutputCurrent());
		
	}
	
	public void turnClimber(double speed) {
		angleClimbTalon.set(ControlMode.PercentOutput, speed);
	}
	
	public void resetAngle() {
		angleClimbTalon.set(ControlMode.Position, 0);
	}
	
	public void fullLeft() {
		servo.set(0);
	}
	
	public void fullRight() {
		servo.set(1);
	}
}