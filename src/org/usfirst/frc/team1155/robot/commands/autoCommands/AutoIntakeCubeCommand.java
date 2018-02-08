package org.usfirst.frc.team1155.robot.commands.autoCommands;

import org.usfirst.frc.team1155.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoIntakeCubeCommand extends Command {

    public AutoIntakeCubeCommand() {
        requires(Robot.intakeSubsystem);
    }

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.intakeSubsystem.setCounter(0);
		Robot.intakeSubsystem.retractTiltPiston();
		Robot.intakeSubsystem.retractArmPiston();
		Robot.intakeSubsystem.setSpeed(-.3);

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;//Robot.intakeSubsystem.ultrasonic.getRangeInches() <= Robot.intakeSubsystem.MIN_INCHES_FROM_ULTRA_TO_BOX;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
