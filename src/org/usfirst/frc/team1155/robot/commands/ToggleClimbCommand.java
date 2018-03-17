package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.OI;
import org.usfirst.frc.team1155.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class ToggleClimbCommand extends Command{
	
	private final double E_MARGIN = 0.02;
	
	public ToggleClimbCommand() {
		// Use requires() here to declare subsystem dependencies
		//requires(Robot.intakeSubsystem);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
//		Robot.climbSubsystem.retract();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {		
		if(Robot.climbSubsystem.leftServo.getPosition() == 0.0 + E_MARGIN)
			Robot.climbSubsystem.extend();
		else {
			Robot.climbSubsystem.retract();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}