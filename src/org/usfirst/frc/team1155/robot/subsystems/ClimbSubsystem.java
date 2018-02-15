package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.PortMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimbSubsystem extends Subsystem {
	
	public TalonSRX leftClimbTalon, rightClimbTalon;
	public PWM leftServo, rightServo;
	
	public double speedAngle = 1.0;
	
	public void initDefaultCommand() {
		leftClimbTalon = new TalonSRX(PortMap.DRIVE_BACK_LEFT_TALON);
		rightClimbTalon = new TalonSRX(PortMap.DRIVE_FRONT_LEFT_TALON);
		leftServo = new PWM(PortMap.TILT_CLIMB_SERVO_LEFT);
		rightServo = new PWM(PortMap.TILT_CLIMB_SERVO_RIGHT);
		leftServo.setBounds(2, 2, 1,1, 1);
		rightServo.setBounds(2, 2, 1,1, 1);

	}
	
	public void setExtensionSpeed(double speed) {
		leftClimbTalon.set(ControlMode.PercentOutput, -speed);
		rightClimbTalon.set(ControlMode.PercentOutput, -speed);
		System.out.println("current 1 " +  rightClimbTalon.getOutputCurrent());
		System.out.println("current 2 "  + leftClimbTalon.getOutputCurrent());
		
	}
	
	public void retract() {
		leftServo.setPosition(0);
		rightServo.setPosition(0);

	}
	
	public void extend() {
		leftServo.setPosition(1);
		rightServo.setPosition(1);

	}
}