package org.usfirst.frc.team1155.robot.subsystems;

import java.io.File;
import java.io.IOException;

import api.AutonomousRoutine;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AutonomousSubsystem extends Subsystem {

	
	public final double DIST_TO_BASELINE = 12.41;
//	public final double DIST_PAST_SWITCH = 18.8;
//	public final double DIST_TO_SCALE = 18;
	
	public AutonomousRoutine configurePath(String gameInfo, int pos, String priority){
		gameInfo = gameInfo.substring(0, 2);
		
		//0 = left, 1 = middle, 2 = right
		String name = "";
		switch(pos){
		case 0:
			name += "Left-";
			break;
		case 1:
			name += "Middle-";
			break;
		case 2:
			name += "Right-";
			break;
		}
		
		name += gameInfo + "-";
		name += priority;
		AutonomousRoutine path = getPathWithName(name);
		return path;
	}
	
	public AutonomousRoutine getPathWithName(String name){
		AutonomousRoutine path = new AutonomousRoutine();

		switch(name){
		case "Left-LL-Switch":
			
		}
		return path;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

