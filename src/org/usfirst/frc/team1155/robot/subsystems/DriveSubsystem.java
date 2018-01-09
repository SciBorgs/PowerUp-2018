package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.PortMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public TalonSRX frontLeftMotor, frontRightMotor, 
					backRightMotor, backLeftMotor,
					middleRightmotor, middleLeftMotor;

	public void initDefaultCommand() {

		frontLeftMotor = new TalonSRX(PortMap.DRIVE_FRONT_LEFT_TALON);
		frontRightMotor = new TalonSRX(PortMap.DRIVE_FRONT_RIGHT_TALON);

		middleLeftMotor = new TalonSRX(PortMap.DRIVE_MIDDLE_LEFT_TALON);
		middleRightmotor = new TalonSRX(PortMap.DRIVE_MIDDLE_RIGHT_TALON);

		backLeftMotor = new TalonSRX(PortMap.DRIVE_BACK_LEFT_TALON);
		backRightMotor = new TalonSRX(PortMap.DRIVE_BACK_RIGHT_TALON);
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setSpeed(double leftVal, double rightVal){
		frontRightMotor.set(ControlMode.PercentOutput, rightVal);
		frontLeftMotor.set(ControlMode.PercentOutput, -leftVal);

		middleRightmotor.set(ControlMode.PercentOutput, rightVal);
		middleLeftMotor.set(ControlMode.PercentOutput, -leftVal);
		
		backRightMotor.set(ControlMode.PercentOutput, rightVal);
		backLeftMotor.set(ControlMode.PercentOutput, -leftVal);
	}
}
