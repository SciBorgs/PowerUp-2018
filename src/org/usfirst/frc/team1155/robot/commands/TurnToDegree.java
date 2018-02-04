package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.DriveSubsystem.PIDMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnToDegree extends Command{

	public TurnToDegree() {
		requires(Robot.driveSubsystem);
		setInterruptible(true);
		
	}
	@Override
	protected void initialize() {
		// Calibrates the turn angle
    	Robot.driveSubsystem.pidMode = PIDMode.TurnDegree;
		Robot.driveSubsystem.startAdjustment(Robot.driveSubsystem.gyro.getAngle(), SmartDashboard.getNumber("TurnAngle", 0));
	}

	@Override
	protected void execute() {
		SmartDashboard.putNumber("GyroValue", Robot.driveSubsystem.gyro.getAngle());
	}

	@Override
	protected boolean isFinished() {
		return Robot.driveSubsystem.getPIDController().onTarget();
	}

	@Override
	protected void end() {
		Robot.driveSubsystem.endAdjustment();
		
	}

	@Override
	protected void interrupted() {
		end();
	}

}
