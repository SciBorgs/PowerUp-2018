package org.usfirst.frc.team1155.robot.commands.autoCommands;

import org.usfirst.frc.team1155.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTimeCommand extends Command {

	Timer timer;
	double timerEnd = 2.5;

    public DriveTimeCommand(double time) {
    	requires(Robot.driveSubsystem);
    	timerEnd = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer = new Timer();
    	Robot.driveSubsystem.setSpeed(0.5, 0.5);
    	timer.start();
    	
		System.out.println("-- Time Drive -- Starting");
		System.out.println("Time Drive Time: " + timerEnd);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() > timerEnd;
    }

    // Called once after isFinished returns true
    protected void end() {
		System.out.println("<< Time Drive >> Ending");
    	Robot.driveSubsystem.setSpeed(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		System.out.println("** Time Drive ** INTERRUPTED");
		end();
    }
}
