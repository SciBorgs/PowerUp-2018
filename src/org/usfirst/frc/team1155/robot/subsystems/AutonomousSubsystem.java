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
		String info = "";
		switch(pos){
		case 0:
			info += "Left-";
			break;
		case 1:
			info += "Middle-";
			break;
		case 2:
			info += "Right-";
			break;
		}
		
		info += gameInfo + "-";
		info += priority;
		AutonomousRoutine path = getPathWithInfo(info);
		return path;
	}

	public AutonomousRoutine getPathWithInfo(String info){

		switch(info){
		case "Left-LL-Switch":
		case "Left-LR-Switch":
		case "Left-LL-Either":
		case "Left-LR-Either":
			return getPathFromName("LeftPos-LeftSwitch");
		case "Left-LL-Scale":
		case "Left-RL-Scale":
		case "Left-RL-Either":
			return getPathFromName("LeftPos-LeftScale");
		case "Left-RL-Switch":
		case "Left-RR-Switch":
		case "Left-RR-Either":
			return getPathFromName("LeftPos-RightSwitch");
		case "Left-LR-Scale":
		case "Left-RR-Scale":
			return getPathFromName("LeftPos-RightScale");
		case "Right-RR-Switch":
		case "Right-RL-Switch":
		case "Right-RR-Either":
		case "Right-RL-Either":
			return getPathFromName("RightPos-RightSwitch");
		case "Right-RR-Scale":
		case "Right-LR-Scale":
		case "Right-LR-Either":
			return getPathFromName("RightPos-RightScale");
		case "Right-LR-Switch":
		case "Right-LL-Switch":
		case "Right-LL-Either":
			return getPathFromName("RightPos-LeftSwitch");
		case "Right-RL-Scale":
		case "Right-LL-Scale":
			return getPathFromName("RightPos-LeftScale");
		case "Middle-LL-Switch":
		case "Middle-LR-Switch":
		case "Middle-LL-Either":
		case "Middle-LR-Either":
			return getPathFromName("MiddlePos-LeftSwitch");
		case "Middle-RL-Switch":
		case "Middle-RR-Switch":
		case "Middle-RL-Either":
		case "Middle-RR-Either":
			return getPathFromName("MiddlePos-RightSwitch");
		case "Middle-LL-Scale":
		case "Middle-RL-Scale":
			return getPathFromName("MiddlePos-LeftScale");
		case "Middle-LR-Scale":
		case "Middle-RR-Scale":
			return getPathFromName("MiddlePos-RightScale");
		default:
			return getPathFromName("Baseline");
		}
	}
	
	public AutonomousRoutine getPathFromName(String name){
		AutonomousRoutine path = new AutonomousRoutine();
		switch(name) {
			case "LeftPos-LeftSwitch":
				path.add(1.2083333333333333, 6.208333333333333, null);
				path.add(13.791666666666666, 6.166666666666667, null);
				path.add(13.791666666666666, 7.458333333333333, null);
				path.add(13.791666666666666, 8.333333333333334, AutonomousRoutine.AutonomousAction.PLACE_CUBE_ON_SWITCH);
				break;
			case "LeftPos-LeftScale":
				path.add(1.0416666666666667, 6.291666666666667, null);
				path.add(26.875, 6.208333333333333, null);
				path.add(26.875, 7.125, AutonomousRoutine.AutonomousAction.PLACE_CUBE_ON_SCALE);
				break;
			case "LeftPos-RightSwitch":
				path.add(1.0416666666666667, 6.291666666666667, null);
				path.add(19.291666666666668, 6.25, null);
				path.add(19.416666666666668, 20.083333333333332, null);
				path.add(17.791666666666668, 20.083333333333332, null);
				path.add(16.25, 20.083333333333332, AutonomousRoutine.AutonomousAction.PLACE_CUBE_ON_SWITCH);
				break;
			case "LeftPos-RightScale":
				path.add(1.0416666666666667, 6.291666666666667, null);
				path.add(19.333333333333332, 6.291666666666667, null);
				path.add(19.5, 24.0, null);
				path.add(26.875, 23.916666666666668, null);
				path.add(26.875, 23.208333333333332, null);
				path.add(26.875, 21.875, AutonomousRoutine.AutonomousAction.PLACE_CUBE_ON_SCALE);
				break;
			case "RightPos-RightSwitch":
				path.add(1.75, 23.5, null);
				path.add(13.958333333333334, 23.458333333333332, null);
				path.add(13.958333333333334, 22.25, null);
				path.add(13.958333333333334, 20.916666666666668, AutonomousRoutine.AutonomousAction.PLACE_CUBE_ON_SWITCH);
				break;
			case "RightPos-RightScale":
				path.add(1.7083333333333333, 23.541666666666668, null);
				path.add(27.041666666666668, 23.5, null);
				path.add(27.083333333333332, 21.875, AutonomousRoutine.AutonomousAction.PLACE_CUBE_ON_SCALE);
				break;
			case "RightPos-LeftSwitch":
				path.add(1.7083333333333333, 23.5, null);
				path.add(19.791666666666668, 23.5, null);
				path.add(19.833333333333332, 9.958333333333334, null);
				path.add(17.916666666666668, 9.958333333333334, null);
				path.add(15.791666666666666, 9.958333333333334, AutonomousRoutine.AutonomousAction.PLACE_CUBE_ON_SWITCH);
				break;
			case "RightPos-LeftScale":
				path.add(1.75, 23.541666666666668, null);
				path.add(19.75, 23.541666666666668, null);
				path.add(19.875, 5.166666666666667, null);
				path.add(26.916666666666668, 5.25, null);
				path.add(27.0, 7.958333333333333, AutonomousRoutine.AutonomousAction.PLACE_CUBE_ON_SCALE);
				break;
			case "MiddlePos-LeftSwitch":
				path.add(1.2916666666666667, 14.916666666666666, null);
				path.add(5.5, 14.916666666666666, null);
				path.add(5.958333333333333, 11.083333333333334, null);
				path.add(10.083333333333334, 11.041666666666666, null);
				path.add(12.208333333333334, 11.0, AutonomousRoutine.AutonomousAction.PLACE_CUBE_ON_SWITCH);
				break;
			case "MiddlePos-RightSwitch":
				path.add(1.2916666666666667, 14.958333333333334, null);
				path.add(5.5, 14.916666666666666, null);
				path.add(5.916666666666667, 18.5, null);
				path.add(10.166666666666666, 18.541666666666668, null);
				path.add(12.708333333333334, 18.458333333333332, AutonomousRoutine.AutonomousAction.PLACE_CUBE_ON_SWITCH);
				break;
			case "MiddlePos-LeftScale":
				path.add(1.4166666666666667, 14.791666666666666, null);
				path.add(5.541666666666667, 14.791666666666666, null);
				path.add(5.583333333333333, 5.208333333333333, null);
				path.add(26.875, 5.708333333333333, null);
				path.add(26.875, 8.375, AutonomousRoutine.AutonomousAction.PLACE_CUBE_ON_SCALE);
				break;
			case "MiddlePos-RightScale":
				path.add(1.375, 14.791666666666666, null);
				path.add(5.541666666666667, 14.791666666666666, null);
				path.add(5.75, 23.708333333333332, null);
				path.add(26.958333333333332, 23.75, null);
				path.add(26.791666666666668, 21.208333333333332, AutonomousRoutine.AutonomousAction.PLACE_CUBE_ON_SCALE);
				break;
			default:
				path.add(1.4166666666666667, 14.833333333333334, null);
				path.add(10.083333333333334, 14.791666666666666, null);
				break;
		}
		
		return path;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

