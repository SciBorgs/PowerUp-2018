package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.DriveSubsystem.PIDMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveDistanceCommand extends Command {

	double distanceToDrive;
	
    public DriveDistanceCommand(double dist) {
    	requires(Robot.driveSubsystem);
    	setInterruptible(true);
    	distanceToDrive = dist;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.pidMode = PIDMode.DriveDistance;
    	double ticksToDrive = Robot.driveSubsystem.feetToEncTicks(distanceToDrive);
    	Robot.driveSubsystem.startAdjustment(Robot.driveSubsystem.getEncPosition(), ticksToDrive);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("EncoderValue", Robot.driveSubsystem.getEncPosition());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.driveSubsystem.getPIDController().onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.endAdjustment();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
