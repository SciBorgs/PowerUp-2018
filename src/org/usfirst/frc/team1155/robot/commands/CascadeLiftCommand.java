package org.usfirst.frc.team1155.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1155.robot.OI;
import org.usfirst.frc.team1155.robot.Robot;

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
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (controller.getPOV() == 0 /*&& (Robot.liftSubsystem.liftEncoder.get() <= Robot.liftSubsystem.TICKS_TO_TOP*/) {
			if(controller.getRawButton(5)) {
				Robot.liftSubsystem.setLeftSideSpeed(-Robot.liftSubsystem.LIFT_SPEED);
			}
			else if(controller.getRawButton(6)) {
				Robot.liftSubsystem.setRightSideSpeed(-Robot.liftSubsystem.LIFT_SPEED);
			}
			else {
				Robot.liftSubsystem.setSpeed(-Robot.liftSubsystem.LIFT_SPEED);
			}
		}
		if (controller.getPOV() == 180) {
			if(controller.getRawButton(5)) {
				Robot.liftSubsystem.setLeftSideSpeed(Robot.liftSubsystem.LIFT_SPEED);
			}
			else if(controller.getRawButton(6)) {
				Robot.liftSubsystem.setRightSideSpeed(Robot.liftSubsystem.LIFT_SPEED);
			}
			else {
				Robot.liftSubsystem.setSpeed(Robot.liftSubsystem.LIFT_SPEED);
			}
		
		}
		if (controller.getPOV() == -1) {
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
