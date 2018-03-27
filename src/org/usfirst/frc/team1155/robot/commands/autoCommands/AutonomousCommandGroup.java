package org.usfirst.frc.team1155.robot.commands.autoCommands;

import api.AutonomousRoutine;

import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.commands.DelayCommand;
import org.usfirst.frc.team1155.robot.subsystems.LiftSubsystem.LiftTarget;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommandGroup extends CommandGroup {

    public AutonomousCommandGroup(String gameInfo, int pos) {
		System.out.println("~~ AUTONOMOUS STARTING ~~");

		switch(pos) {
    	case 0: // Robot Starting on Left side of field
			System.out.println("!! SELECTED POSITION - Left !!");

    		
    		if(gameInfo.charAt(0) == 'L') {
    			System.out.println("!! ROUTINE - Switch - Left !!");
//    			addSequential(new DriveDistanceCommand(Robot.autonomousSubsystem.DIST_TO_BASELINE));
    			addSequential(new DriveTimeCommand(2));
    			addSequential(new TurnToDegreeCommand(270));
				addSequential(new AutoLiftCommand(LiftTarget.SwitchHeight, true));
				addSequential(new DriveTimeCommand(0.5));
		    	addSequential(new AutoOutputCubeCommand());

//    		}else if(gameInfo.charAt(0) == 'R' && gameInfo.charAt(1) == 'L') {
//    			addSequential(new DriveDistanceCommand(Robot.autonomousSubsystem.DIST_TO_SCALE));
//    			addSequential(new TurnToDegreeCommand(285));
//				addSequential(new AutoLiftCommand(LiftTarget.ScaleHeight));
//		    	addSequential(new AutoPlaceOutputCubeCommand());
    		}else {
    			System.out.println("!! ROUTINE - Baseline !!");
    			//addSequential(new DriveDistanceCommand(Robot.autonomousSubsystem.DIST_TO_BASELINE));
    			addSequential(new DriveTimeCommand(2));
//    			addSequential(new DriveDistanceCommand(Robot.autonomousSubsystem.DIST_PAST_SWITCH));
//    			addSequential(new TurnToDegreeCommand(90));
//    			addSequential(new DriveDistanceCommand(11));
//    			addSequential(new TurnToDegreeCommand(180));
//				addSequential(new AutoLiftCommand(LiftTarget.SwitchHeight));
//		    	addSequential(new AutoPlaceOutputCubeCommand());
    			

    		}
    		
    		break;
    	case 1: // Robot Starting on Right side of field
			System.out.println("!! SELECTED POSITION - Right !!");

    		if(gameInfo.charAt(0) == 'R') {
    			System.out.println("!! ROUTINE - Switch - Right !!");
    			// addSequential(new DriveDistanceCommand(Robot.autonomousSubsystem.DIST_TO_BASELINE));
    			addSequential(new DriveTimeCommand(2));
    			addSequential(new TurnToDegreeCommand(90));
				addSequential(new AutoLiftCommand(LiftTarget.SwitchHeight, true));
				addSequential(new DriveTimeCommand(0.5));
		    	addSequential(new AutoOutputCubeCommand());
//
//    		}else if(gameInfo.charAt(0) == 'L' && gameInfo.charAt(1) == 'R') {
//    			
//    			
//    			addSequential(new DriveDistanceCommand(Robot.autonomousSubsystem.DIST_TO_SCALE));
//    			addSequential(new TurnToDegreeCommand(75));
//				addSequential(new AutoLiftCommand(LiftTarget.ScaleHeight));
//		    	addSequential(new AutoPlaceOutputCubeCommand());
////
////    			
////    			
//////    			addSequential(new TurnToDegreeCommand(270));
//////    			addSequential(new DriveDistanceCommand(11));
//////    			addSequential(new TurnToDegreeCommand(180));
//////				addSequential(new AutoLiftCommand(LiftTarget.SwitchHeight));
//////		    	addSequential(new AutoPlaceOutputCubeCommand());
//
    		}else {
    			System.out.println("!! ROUTINE - Baseline !!");
    			addSequential(new DriveTimeCommand(2));
    			// addSequential(new DriveDistanceCommand(Robot.autonomousSubsystem.DIST_TO_BASELINE));

    		}
    		
    		break;
    	case 2: // test
    		
			addSequential(new AutoLiftCommand(LiftTarget.ScaleHeight, true));
			addSequential(new DelayCommand(.7));
	    	addSequential(new AutoOutputCubeCommand());
    		break;
    	default:
    		break;
    	}
    	
//    	AutonomousRoutine path = Robot.autonomousSubsystem.configurePath(gameInfo, pos);
//    	int pathSize = path.size();
//    	//double initialX = path.getCoordinate(0)[0];
//    	//double initialY = path.getCoordinate(0)[1];
//    	for(int i = 1; i < pathSize; i++){
//    		//if(i != pathSize - 1){
//    			System.out.println("Step:" + i);
//	    		double angle = Robot.driveSubsystem.calculatesAngleToTurnTo(path.getCoordinate(i-1), path.getCoordinate(i));
//	    		addSequential(new TurnToDegreeCommand(angle));
//	    		System.out.println("Adding angle of " + angle);
//	    		
//	    		if (i == pathSize - 1) {
//	    			double distance = (path.getDistance(i-1, i) / 24.);
//	    			addSequential(new DriveDistanceCommand(distance));
//	    			System.out.println("Adding distance of " + distance);
//	    		}
//
//    		//}
//    		if(path.hasAutonomousAction(i)){
//    			switch(path.getAutonomousAction(i)){
//    			case PLACE_CUBE_ON_SWITCH:
//    				addSequential(new AutoLiftCommand(LiftTarget.SwitchHeight));
//    				addSequential(new AutoPlaceOutputCubeCommand());
//    				addSequential(new AutoLiftCommand(LiftTarget.Bottom));
//    				break;
//    			case PLACE_CUBE_ON_SCALE:
//    				addSequential(new AutoLiftCommand(LiftTarget.ScaleHeight));
//    				addSequential(new AutoShootCubeCommand());
//    				addSequential(new AutoLiftCommand(LiftTarget.Bottom));
//    				break;
//    			case PICKUP_CUBE:
//    				addSequential(new AlignToCubeCommand());
//    				addSequential(new AutoIntakeCubeCommand());
//    				break;
//    			}
//    		}
//		}
//    	
//    	addSequential(new AutoPlaceOutputCubeCommand());

//    	addSequential(new DriveDistanceCommand(2));
//    	addSequential(new TurnToDegreeCommand(270));
//    	addSequential(new DriveDistanceCommand(2));
//    	addSequential(new TurnToDegreeCommand(180));
//    	addSequential(new DriveDistanceCommand(2));
//    	addSequential(new TurnToDegreeCommand(90));
//    	addSequential(new DriveDistanceCommand(2));
//    	addSequential(new TurnToDegreeCommand(0));
//    	addSequential(new DriveDistanceCommand(2));

    }
}
