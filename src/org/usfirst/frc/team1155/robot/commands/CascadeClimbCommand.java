package org.usfirst.frc.team1155.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1155.robot.OI;
import org.usfirst.frc.team1155.robot.Robot;

/**
 *
 */
public class CascadeClimbCommand extends Command {
	public CascadeClimbCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.climbSubsystem);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (OI.ascendClimb.get()) {
			Robot.climbSubsystem.setSpeed(1);
		} else if (OI.descendClimb.get()) {
			Robot.climbSubsystem.setSpeed(1);			
		} else {
			Robot.climbSubsystem.setSpeed(0);
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
		Robot.driveSubsystem.setSpeed(0,0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		Robot.driveSubsystem.setSpeed(0,0);
	}
}
