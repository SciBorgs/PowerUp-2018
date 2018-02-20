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
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (controller.getPOV() == 0) {
			if (controller.getRawButton(5)) {
				Robot.liftSubsystem.setLeftSideSpeed(-Robot.liftSubsystem.LIFT_SPEED);
			} else if (controller.getRawButton(6)) {
				Robot.liftSubsystem.setRightSideSpeed(-Robot.liftSubsystem.LIFT_SPEED);
			} else {
				Robot.liftSubsystem.setSpeed(-Robot.liftSubsystem.LIFT_SPEED);
			}
		}
		if (controller.getPOV() == 180) {
			if (controller.getRawButton(5)) {
				Robot.liftSubsystem.setLeftSideSpeed(Robot.liftSubsystem.LIFT_SPEED);
			} else if (controller.getRawButton(6)) {
				Robot.liftSubsystem.setRightSideSpeed(Robot.liftSubsystem.LIFT_SPEED);
			} else {
				Robot.liftSubsystem.setSpeed(Robot.liftSubsystem.LIFT_SPEED);
			}

		}
/*		if (controller.getPOV() == 0) {

			// If the difference between the encoders is too much
			// make the side that is higher go slower so the other one
			// can catch up

			if (Robot.liftSubsystem.getEncoderDifference() < -Robot.liftSubsystem.MAX_TICK_DIFFERENCE) {
				Robot.liftSubsystem.setLeftSideSpeed(-Robot.liftSubsystem.LIFT_SPEED);
				Robot.liftSubsystem.setRightSideSpeed(-Robot.liftSubsystem.LIFT_SPEED_ADJUST);
			} else if (Robot.liftSubsystem.getEncoderDifference() > Robot.liftSubsystem.MAX_TICK_DIFFERENCE) {
				Robot.liftSubsystem.setLeftSideSpeed(-Robot.liftSubsystem.LIFT_SPEED_ADJUST);
				Robot.liftSubsystem.setRightSideSpeed(-Robot.liftSubsystem.LIFT_SPEED);
			} else {
				Robot.liftSubsystem.setSpeed(-Robot.liftSubsystem.LIFT_SPEED);
			}

		}

		if (controller.getPOV() == 180) {
			if (Robot.liftSubsystem.getEncoderDifference() < -Robot.liftSubsystem.MAX_TICK_DIFFERENCE) {
				Robot.liftSubsystem.setLeftSideSpeed(Robot.liftSubsystem.LIFT_SPEED);
				Robot.liftSubsystem.setRightSideSpeed(Robot.liftSubsystem.LIFT_SPEED_ADJUST);
			} else if (Robot.liftSubsystem.getEncoderDifference() > Robot.liftSubsystem.MAX_TICK_DIFFERENCE) {
				Robot.liftSubsystem.setLeftSideSpeed(Robot.liftSubsystem.LIFT_SPEED_ADJUST);
				Robot.liftSubsystem.setRightSideSpeed(Robot.liftSubsystem.LIFT_SPEED);
			} else {
				Robot.liftSubsystem.setSpeed(Robot.liftSubsystem.LIFT_SPEED);
			}
		} */
		if (controller.getPOV() == -1) {
			Robot.liftSubsystem.stop();
		} 
		
		SmartDashboard.putNumber("Left lift enc val", Robot.liftSubsystem.getLeftEncPos());
		SmartDashboard.putNumber("Right lift enc val", Robot.liftSubsystem.getRightEncPos());

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
