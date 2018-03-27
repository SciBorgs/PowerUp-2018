package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.OI;
import org.usfirst.frc.team1155.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbCommand extends Command {
	public ClimbCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.climbSubsystem);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

		Robot.climbSubsystem.setExtensionSpeed(0);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

		if (OI.windClimber.get()) {
			System.out.println("extending");
			Robot.climbSubsystem.setExtensionSpeed(-.5);
		} else if (OI.stopClimbing.get()) {
			System.out.println("stopping");
			Robot.climbSubsystem.setExtensionSpeed(0);

		} else if (OI.unwindClimber.get()) {
			System.out.println("stopping");
			Robot.climbSubsystem.setExtensionSpeed(.5);

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
		Robot.climbSubsystem.setExtensionSpeed(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
