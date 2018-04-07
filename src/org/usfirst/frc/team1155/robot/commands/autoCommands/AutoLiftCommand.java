package org.usfirst.frc.team1155.robot.commands.autoCommands;

import org.usfirst.frc.team1155.robot.OI;
import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.commands.CascadeLiftCommand;
import org.usfirst.frc.team1155.robot.subsystems.LiftSubsystem.LiftTarget;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoLiftCommand extends Command {

	
	LiftTarget target;
	boolean isAuto;
	
    public AutoLiftCommand(LiftTarget t, boolean isAuto) {
        requires(Robot.liftSubsystem);
        target = t;
        this.isAuto = isAuto;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("-- Auto Lift -- Starting");
    	Robot.liftSubsystem.liftTarget = target;
    	Robot.liftSubsystem.startAdjustment();
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
    	Robot.liftSubsystem.stopMovement();
    	Robot.liftSubsystem.endAdjustment();
    	
    	if(!isAuto) {
    		System.out.println("Starting cascade lift command from auto lift end");
    		new CascadeLiftCommand(OI.rightJoystick).start();
    	}
    	
    	System.out.println("<< Auto Lift >> Ending");


    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("** Auto Lift ** INTERRUPTED");
    	end();
    }
}
