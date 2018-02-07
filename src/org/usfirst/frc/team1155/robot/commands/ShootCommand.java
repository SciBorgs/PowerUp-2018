package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class ShootCommand extends Command{
	public ShootCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.intakeSubsystem);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.intakeSubsystem.setCounter(0);
		Robot.intakeSubsystem.extendTiltPiston();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (Robot.intakeSubsystem.counter >= (1.0 / (20.0 / 1000.0))) {
			Robot.intakeSubsystem.retractArmPiston();
			Robot.intakeSubsystem.setSpeed(1);
		} else {
			Robot.intakeSubsystem.setArmSpeed(1);
			Robot.intakeSubsystem.incrementCounter(1);
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