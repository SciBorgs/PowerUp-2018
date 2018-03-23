package org.usfirst.frc.team1155.robot.commands.autoCommands;

import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.LiftSubsystem.LiftTarget;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoLiftCommand extends Command {

	
	LiftTarget target;
	
	
    public AutoLiftCommand(LiftTarget t) {
        requires(Robot.liftSubsystem);
        target = t;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.liftSubsystem.liftTarget = target;
    	Robot.liftSubsystem.startAdjustment();
    	System.out.println("Starting..");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.liftSubsystem.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.liftSubsystem.stop();
    	Robot.liftSubsystem.endAdjustment();

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
