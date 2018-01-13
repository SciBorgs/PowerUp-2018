package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.PortMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimbSubsystem extends Subsystem{

	public TalonSRX leftClimbMotor, rightClimbMotor;
	
	public void initDefaultCommand() {

		leftClimbMotor = new TalonSRX(PortMap.CLIMB_LEFT_TALON);
		rightClimbMotor = new TalonSRX(PortMap.CLIMB_RIGHT_TALON);

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		stop();
	}	
	
	public void stop() {
		leftClimbMotor.set(ControlMode.PercentOutput, 0);
		rightClimbMotor.set(ControlMode.PercentOutput, 0);
	}
	
	public void setSpeed(double speed){
		leftClimbMotor.set(ControlMode.PercentOutput, speed);
		rightClimbMotor.set(ControlMode.PercentOutput, speed);
	}
}
