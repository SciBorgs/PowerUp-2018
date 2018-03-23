package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.OI;
import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.commands.autoCommands.AutoLiftCommand;
import org.usfirst.frc.team1155.robot.subsystems.LiftSubsystem.LiftTarget;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;

public class ChangeLiftHeightCommand extends Command {

	private GenericHID controller;
	
    public ChangeLiftHeightCommand(GenericHID controller) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.controller = controller;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	LiftTarget newTarget = LiftTarget.Bottom;
    	
    	if(OI.increaseLiftHeight.get()) {
    		switch(Robot.liftSubsystem.liftTarget) {
    		case Bottom:
    			newTarget = LiftTarget.SwitchHeight;
    			break;
    		case SwitchHeight:
    			newTarget = LiftTarget.ScaleHeight;
    			break;
    		case ScaleHeight:
    			newTarget = LiftTarget.Bottom;
    			break;
    		}
    	}
    	
    	if(OI.decreaseLiftHeight.get()) {
    		switch(Robot.liftSubsystem.liftTarget) {
    		case Bottom:
    			newTarget = LiftTarget.ScaleHeight;
    			break;
    		case SwitchHeight:
    			newTarget = LiftTarget.Bottom;
    			break;
    		case ScaleHeight:
    			newTarget = LiftTarget.SwitchHeight;
    			break;
    		}
    	}
    	
    	(new AutoLiftCommand(newTarget)).start();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
