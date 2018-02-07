package org.usfirst.frc.team1155.robot.commands.autoCommands;

import org.usfirst.frc.team1155.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoShootCubeCommand extends Command {

    public AutoShootCubeCommand() {
        requires(Robot.intakeSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		Robot.intakeSubsystem.setCounter(0);
		Robot.intakeSubsystem.extendTiltPiston();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		if (Robot.intakeSubsystem.counter >= Robot.intakeSubsystem.REV_AMOUNT) {
			Robot.intakeSubsystem.retractArmPiston();
			Robot.intakeSubsystem.setSpeed(1);
		} else {
			Robot.intakeSubsystem.setArmSpeed(1);
			Robot.intakeSubsystem.incrementCounter(1);
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.intakeSubsystem.ultrasonic.getRangeInches() >= Robot.intakeSubsystem.MAX_INCHES_FROM_ULTRA_TO_BOX;
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.intakeSubsystem.setCounter(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
