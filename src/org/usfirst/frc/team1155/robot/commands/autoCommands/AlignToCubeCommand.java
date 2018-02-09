package org.usfirst.frc.team1155.robot.commands.autoCommands;

import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.DriveSubsystem.PIDMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AlignToCubeCommand extends Command {

    public AlignToCubeCommand() {
        requires(Robot.visionSubsystem);
        requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.pidMode = PIDMode.TurnDegree;
    	double angleToCube = Robot.visionSubsystem.getAngleToCube();
		Robot.driveSubsystem.startAdjustment(Robot.driveSubsystem.getPigeonYaw(), angleToCube);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
