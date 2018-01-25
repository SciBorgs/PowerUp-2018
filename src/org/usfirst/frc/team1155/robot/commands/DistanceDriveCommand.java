package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DistanceDriveCommand extends Command {

	public double distance;
	
    public DistanceDriveCommand(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.resetEncoders();
    	
    	Robot.driveSubsystem.startAdjustment(Robot.driveSubsystem.getEncDistance(), distance);
    	Robot.driveSubsystem.getPIDController().setPID(0.1, 0, 0.1);
    	Robot.driveSubsystem.getPIDController().setAbsoluteTolerance(2);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Encoder Value", Robot.driveSubsystem.getEncDistance());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveSubsystem.getPIDController().onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Gaussian Quadrature");
    	Robot.driveSubsystem.endAdjustment();
    	Robot.driveSubsystem.setSpeed(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
