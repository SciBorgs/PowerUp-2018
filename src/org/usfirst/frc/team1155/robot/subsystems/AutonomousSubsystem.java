package org.usfirst.frc.team1155.robot.subsystems;

import api.AutonomousRoutine;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AutonomousSubsystem extends Subsystem {

	
	
	
	public AutonomousRoutine configurePath(String gameInfo, int pos){
		switch(pos){
		case 1:
			switch(gameInfo){
			case "LLL":
				break;
			case "RRR":
				break;
			case "LRL":
				break;
			case "RLR":
				break;
			}
			break;
		case 2:
			switch(gameInfo){
			case "LLL":
				break;
			case "RRR":
				break;
			case "LRL":
				break;
			case "RLR":
				break;
			}
			break;
		case 3:
			switch(gameInfo){
			case "LLL":
				break;
			case "RRR":
				break;
			case "LRL":
				break;
			case "RLR":
				break;
			}
			break;
		default:
			break;
		}
		return new AutonomousRoutine();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
}

