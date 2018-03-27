package org.usfirst.frc.team1155.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DelayCommand extends Command {

	double duration = 1;
	Timer timer;
	
    public DelayCommand(double d) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	setInterruptible(false);
    	timer = new Timer();
    	duration = d;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	System.out.println("Delay start with " + duration);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() >= duration;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Delay end");

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("Delay interrupted");
    }
}
