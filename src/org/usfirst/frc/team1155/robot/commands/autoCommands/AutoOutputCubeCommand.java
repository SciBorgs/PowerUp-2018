package org.usfirst.frc.team1155.robot.commands.autoCommands;

import org.usfirst.frc.team1155.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutoOutputCubeCommand extends Command {

	int counter = 0;
	Timer timer;

	public AutoOutputCubeCommand() {
		requires(Robot.intakeSubsystem);
		setInterruptible(false);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		System.out.println("-- Auto Output Cube -- Starting");

		timer = new Timer();

		Robot.intakeSubsystem.setCounter(0);
		Robot.intakeSubsystem.setSpeed(-.8);
//		Robot.intakeSubsystem.retractArmPiston();


	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		// counter += 1;
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return timer.get() > 2;// counter > 100;//false;//Robot.intakeSubsystem.ultrasonic.getRangeInches() >=
						// Robot.intakeSubsystem.MAX_INCHES_FROM_ULTRA_TO_BOX;
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("<< Auto Output Cube >> Ending");

		Robot.intakeSubsystem.stop();

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		System.out.println("** Auto Output Cube ** INTERRUPTED");
		end();

	}
}
