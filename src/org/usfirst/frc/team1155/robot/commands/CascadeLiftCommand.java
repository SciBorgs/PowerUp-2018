package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CascadeLiftCommand extends Command {

	private GenericHID controller;

	public CascadeLiftCommand(GenericHID controller) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.liftSubsystem);
		this.controller = controller;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		System.out.println("Starting cascade lift command");
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		System.out.println(controller.getY());
		Robot.liftSubsystem.setSpeed(controller.getY());
		if(controller.getY() < 0 && Robot.liftSubsystem.getLimit()) {
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
		end();
	}
}
