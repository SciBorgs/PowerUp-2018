package org.usfirst.frc.team1155.robot.subsystems;
import org.usfirst.frc.team1155.robot.OI;
import org.usfirst.frc.team1155.robot.PortMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimbSubsystem extends Subsystem {
	
	public TalonSRX leftClimbTalon, rightClimbTalon, angleClimbTalon;
	
	public double speedAngle = 1.0;
	
	public void initDefaultCommand() {
		leftClimbTalon = new TalonSRX(PortMap.LEFT_CLIMB_TALON);
		rightClimbTalon = new TalonSRX(PortMap.RIGHT_CLIMB_TALON);
		angleClimbTalon = new TalonSRX(PortMap.ANGLE_CLIMB_TALON);
	}
	
	public void setExtensionSpeed(double speed) {
		leftClimbTalon.set(ControlMode.PercentOutput,speed);
		rightClimbTalon.set(ControlMode.PercentOutput,speed);
	}
	
	public void turnClimber(double speed) {
		angleClimbTalon.set(ControlMode.PercentOutput, speed);
	}
	
	public void resetAngle() {
		angleClimbTalon.set(ControlMode.Position, 0);
	}
}
