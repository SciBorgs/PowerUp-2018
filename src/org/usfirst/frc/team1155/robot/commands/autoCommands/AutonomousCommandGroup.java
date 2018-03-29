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

    public AutonomousCommandGroup(String gameInfo, int pos, String priority) {
		System.out.println("~~ AUTONOMOUS STARTING ~~");
		
		AutonomousRoutine path = Robot.autonomousSubsystem.configurePath(gameInfo, pos, priority);
		System.out.println("Chosen path: " + path.toString());
		int pathLength = path.size();
		double prevAngle = 0;
		for(int i = 1; i < pathLength; i++){
			double[] p1 = path.getCoordinate(i - 1);
			double[] p2 = path.getCoordinate(i);
			
			double[] relativePoint = {p2[0] - p1[0], p2[1] - p1[1]};
		    double absoluteAngle = Math.atan2(relativePoint[1], relativePoint[0]);
		    double relativeAngle = absoluteAngle - prevAngle;
		    prevAngle = absoluteAngle;
		    
		    System.out.println("Adding relative angle of " + relativeAngle);
		    addSequential(new TurnToDegreeCommand(relativeAngle));
		    
		    if(i != pathLength - 1){
		    	double distance = distanceFromOrigin(relativePoint[0], relativePoint[1]);
		    	System.out.println("Adding distance of " + distance);
		    	addSequential(new DriveDistanceCommand(distance));
		    }
		    
		    if(path.hasAutonomousAction(i)){
		    	switch(path.getAutonomousAction(i)){
		    	case PICKUP_CUBE:
		    		addSequential(new AutoIntakeCubeCommand());
		    		break;
		    	case PLACE_CUBE_ON_SCALE:
		    		addSequential(new AutoLiftCommand(LiftTarget.ScaleHeight, true));
		    		addSequential(new AutoOutputCubeCommand());
		    		break;
		    	case PLACE_CUBE_ON_SWITCH:
		    		addSequential(new AutoLiftCommand(LiftTarget.SwitchHeight, true));
		    		addSequential(new AutoOutputCubeCommand());
		    		break;
		    	}
		    	System.out.println("Adding Autonomous Action of " + path.getAutonomousAction(i));
		    }
		    
		    
		}
    }
    
    public double distanceFromOrigin(double x, double y){
    	return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}
