package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class AdjustCubeRotationCommand extends Command {

	int counter = 0;
	double angle;
	
    public AdjustCubeRotationCommand(double a) {
//        requires(Robot.intakeSubsystem);
//        requires(Robot.visionSubsystem);
        angle = a;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intakeSubsystem.stop();
    	Robot.intakeSubsystem.setLeftArmSpeed(Robot.intakeSubsystem.ADJUST_SPEED);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	counter += 1;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;//counter > Robot.intakeSubsystem.adjustTimeForAngle(angle) / Robot.intakeSubsystem.SECONDS_PER_EXECUTE;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
