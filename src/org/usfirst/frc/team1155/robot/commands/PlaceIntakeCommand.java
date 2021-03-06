package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;

public class PlaceIntakeCommand extends Command{

	private GenericHID controller;
	private double speed;
	
	public PlaceIntakeCommand(GenericHID controller, double speed) {
		requires(Robot.intakeSubsystem);
		this.controller = controller;
		this.speed = speed;
	}
	public PlaceIntakeCommand() {
		
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.intakeSubsystem.setCounter(0);
//		Robot.intakeSubsystem.retractTiltPiston();
//		Robot.intakeSubsystem.retractArmPiston();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.intakeSubsystem.setLeftSpeed(-speed);
		Robot.intakeSubsystem.setRightSpeed(-speed);
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
		end();
	}
}