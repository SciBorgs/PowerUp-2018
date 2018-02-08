package org.usfirst.frc.team1155.robot.subsystems;

import java.io.File;
import java.io.IOException;

import api.AutonomousRoutine;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AutonomousSubsystem extends Subsystem {

	
	
	
	public AutonomousRoutine configurePath(String gameInfo, int pos){
		
		try {
			return new AutonomousRoutine(new File("/home/lvuser/AutoRoutines/" + pos + "/" + gameInfo + ".auto"));
		} catch (IOException e) {
			e.printStackTrace();
			try {
				return new AutonomousRoutine(new File("/home/lvuser/AutoRoutines/testRoutine.auto"))	;
			} catch (IOException e1) {
				e1.printStackTrace();
				return null;
			}
		}
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
}

