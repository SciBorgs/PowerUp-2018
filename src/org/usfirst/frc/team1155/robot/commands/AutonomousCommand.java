package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.MovingType;
import org.usfirst.frc.team1155.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousCommand extends Command {

	int counter = 0;
	MovingType movingType;
	boolean finished = false;
	
    public AutonomousCommand(int pos) {
        requires(Robot.autonomousSubsystem);
        Robot.autonomousSubsystem.position = pos;
        Robot.autonomousSubsystem.configurePath();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	double angle = Robot.driveSubsystem.calculatesAngleToTurnTo(Robot.autonomousSubsystem.currentPath.get(0));
    	new TurnToDegreeCommand(angle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(movingType == MovingType.TURNING){
    		if(Robot.driveSubsystem.getPIDController().onTarget()){
    			new DriveDistance(Robot.autonomousSubsystem.currentPath.getDistance(counter, counter + 1));
    			movingType = MovingType.MOVING;
    		}
    	}else if(movingType == MovingType.MOVING){
    		if((Math.abs(Robot.position[0] - Robot.autonomousSubsystem.currentPath.get(counter)[0]) > 0.5) && (Math.abs(Robot.position[1] - Robot.autonomousSubsystem.currentPath.get(counter)[1]) > 0.5)){
    			if(counter == Robot.autonomousSubsystem.currentPath.size()){
    				finished = true;
    			}
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

