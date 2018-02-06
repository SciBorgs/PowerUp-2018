package org.usfirst.frc.team1155.robot.commands;

import api.Path;

import org.usfirst.frc.team1155.robot.AutoActionType;
import org.usfirst.frc.team1155.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommandGroup extends CommandGroup {

    public AutonomousCommandGroup(String gameInfo, int pos) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	Path path = Robot.autonomousSubsystem.configurePath(gameInfo, pos);
    	for(int i = 0; i < path.size(); i++){
    		if(i != path.size() - 1){
	    		double angle = Robot.driveSubsystem.calculatesAngleToTurnTo(path.get(i));
	    		addSequential(new TurnToDegreeCommand(angle));
	    		double distance = path.getDistance(i, i+1);
	    		addSequential(new DriveDistanceCommand(distance));
    		}
    		if(path.hasAction(i)){
    			switch(path.actionAt(i)){
    			case AutoActionType.PLACE_CUBE_ON_SWITCH:
    				//addSequential();
    				break;
    			case AutoActionType.PLACE_CUBE_ON_SCALE:
    				break;
    			case AutoActionType.PICKUP_CUBE:
    				addSequential(new PlaceIntakeCommand());
    				break;
    			}
    		}

    	}
    }
}
