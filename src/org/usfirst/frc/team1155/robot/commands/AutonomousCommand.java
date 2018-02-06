package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.AutoActionType;
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
    			DriveDistanceCommand driveDist = new DriveDistanceCommand(Robot.autonomousSubsystem.currentPath.getDistance(counter, counter + 1));
    			driveDist.start();
    			movingType = MovingType.MOVING;
    		}
    	}else if(movingType == MovingType.MOVING){
    		if(Robot.driveSubsystem.getPIDController().onTarget()){
    			if(counter == Robot.autonomousSubsystem.currentPath.size() - 1){
    				finished = true;
    			}else if (Robot.autonomousSubsystem.currentPath.hasAction(counter)){
    				switch(Robot.autonomousSubsystem.currentPath.actionAt(counter)){
    				case AutoActionType.PICKUP_CUBE:
    					break;
    				case AutoActionType.PLACE_CUBE_ON_SCALE:
    					break;
    				case AutoActionType.PLACE_CUBE_ON_SWITCH:
    					break;
    				}
    				movingType = MovingType.PERFORMING_ACTION;
    			}else{
    				counter++;
    				TurnToDegreeCommand turn = new TurnToDegreeCommand(Robot.driveSubsystem.calculatesAngleToTurnTo(Robot.autonomousSubsystem.currentPath.get(counter)));
    				turn.start();
    				movingType = MovingType.TURNING;
    			}
    		}
    	}else if(movingType == MovingType.PERFORMING_ACTION){
    		 if(Robot.intakeSubsystem.isStopped){
    			 if(counter == Robot.autonomousSubsystem.currentPath.size() - 1){
    				 finished = true;
    			 }else{
        			 counter++;
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

