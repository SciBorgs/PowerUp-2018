package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.OI;
import org.usfirst.frc.team1155.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DeployClimberCommand extends Command {

	public DeployClimberCommand() {
		requires(Robot.climbSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

		if (Robot.climbSubsystem.deploySolenoid.get() == DoubleSolenoid.Value.kForward) {
			Robot.climbSubsystem.deploySolenoid.set(DoubleSolenoid.Value.kReverse);
		} else {
			Robot.climbSubsystem.deploySolenoid.set(DoubleSolenoid.Value.kForward);
		}

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
    	System.out.println("<< Toggle Climb Pistons >> Ending");
		new ClimbCommand(OI.xbox).start();

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
