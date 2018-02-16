package org.usfirst.frc.team1155.robot.commands.autoCommands;

import api.AutonomousRoutine;

import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.commands.DriveDistanceCommand;
import org.usfirst.frc.team1155.robot.commands.TurnToDegreeCommand;

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
    	
    	AutonomousRoutine path = Robot.autonomousSubsystem.configurePath(gameInfo, pos);
    	int pathSize = path.size();
    	for(int i = 1; i < pathSize; i++){
    		if(i != pathSize - 1){
	    		double angle = Robot.driveSubsystem.calculatesAngleToTurnTo(path.getCoordinate(i-1), path.getCoordinate(i));
	    		addSequential(new TurnToDegreeCommand(angle));
	    		double distance = path.getDistance(i-1, i);
	    		addSequential(new DriveDistanceCommand(distance));
    		}
    		if(path.hasAutonomousAction(i)){
    			switch(path.getAutonomousAction(i)){
    			case PLACE_CUBE_ON_SWITCH:
    				addSequential(new AutoRaiseLiftToSwitchCommand());
    				addSequential(new AutoPlaceOutputCubeCommand());
    				addSequential(new AutoLowerLiftCommand());
    				break;
    			case PLACE_CUBE_ON_SCALE:
    				addSequential(new AutoRaiseLiftToScaleCommand());
    				addSequential(new AutoShootCubeCommand());
    				addSequential(new AutoLowerLiftCommand());
    				break;
    			case PICKUP_CUBE:
    				addSequential(new AlignToCubeCommand());
    				addSequential(new AutoIntakeCubeCommand());
    				break;
    			}
    		}

    	}
    }
}
