package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.OI;
import org.usfirst.frc.team1155.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CarriageCommand extends Command{
	public CarriageCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.carriageSubsystem);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (OI.extendCarriage.get()) {
			Robot.carriageSubsystem.extendPiston();
		}
		if (OI.retractCarriage.get()) {
			Robot.carriageSubsystem.retractPiston();			
		}
		if (OI.activateCarriageMotor.get()) {
			Robot.carriageSubsystem.setSpeed(1);
		}
		if (OI.deactivateCarriageMotor.get()) {
			Robot.carriageSubsystem.setSpeed(-1);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.carriageSubsystem.setSpeed(0);
		Robot.carriageSubsystem.retractPiston();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		Robot.carriageSubsystem.setSpeed(0);
		Robot.carriageSubsystem.retractPiston();
	}
}