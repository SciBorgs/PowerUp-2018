package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

public class ClimbCommand extends Command {
	
	private XboxController xbox;
	
	public ClimbCommand(XboxController xbox) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.climbSubsystem);
		this.xbox = xbox;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.climbSubsystem.setExtensionSpeed(0);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if(xbox.getXButton()) {
			Robot.climbSubsystem.setExtensionSpeed(-0.75);
		} else if (xbox.getBButton()) {
			Robot.climbSubsystem.setExtensionSpeed(0.75);
		} else {
			Robot.climbSubsystem.setExtensionSpeed(0);
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
