package org.usfirst.frc.team1155.robot.commands.autoCommands;

import org.usfirst.frc.team1155.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoPlaceOutputCubeCommand extends Command {

    public AutoPlaceOutputCubeCommand() {
        requires(Robot.intakeSubsystem);
    }

    // Called just before this Command runs the first time
    @Override
	protected void initialize() {
		Robot.intakeSubsystem.setCounter(0);
//		Robot.intakeSubsystem.retractTiltPiston();
		Robot.intakeSubsystem.retractArmPiston();
		Robot.intakeSubsystem.setSpeed(.3);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	}


    // Make this return true when this Command no longer needs to run execute()
	@Override
    protected boolean isFinished() {
        return true;//Robot.intakeSubsystem.ultrasonic.getRangeInches() >= Robot.intakeSubsystem.MAX_INCHES_FROM_ULTRA_TO_BOX;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
