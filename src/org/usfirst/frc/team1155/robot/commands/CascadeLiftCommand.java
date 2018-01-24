package org.usfirst.frc.team1155.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1155.robot.OI;
import org.usfirst.frc.team1155.robot.Robot;

/**
 *
 */
public class CascadeLiftCommand extends Command {
	public CascadeLiftCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.liftSubsystem);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (OI.leftJoystick.getPOV() == 0 && Robot.liftSubsystem.liftEncoder.get() <= Robot.liftSubsystem.tickToTop) {
			Robot.liftSubsystem.setSpeed(1);
		}
		if (OI.leftJoystick.getPOV() == 180) {
			Robot.liftSubsystem.setSpeed(-1);			
		}
		if (OI.leftJoystick.getPOV() == -1) {
			Robot.liftSubsystem.stop();			
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
		Robot.liftSubsystem.setSpeed(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		Robot.liftSubsystem.setSpeed(0);
	}
}
