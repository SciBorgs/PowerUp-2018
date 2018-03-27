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
	
	public AutonomousRoutine configurePath(String gameInfo, int pos){
		try {	
			//File myPath = new File("/home/lvuser/AutoRoutines/star.auto");
			//System.out.println(myPath.getAbsolutePath());
//			AutonomousRoutine path = new AutonomousRoutine(new File("/home/lvuser/AutoRoutines/Test-Square.auto"));
			AutonomousRoutine path;
			if(gameInfo.charAt(0) == 'L') {
				path = new AutonomousRoutine(new File("/home/lvuser/AutoRoutines/Left-Left-Switch.auto"));
			} else {
				path = new AutonomousRoutine(new File("/home/lvuser/AutoRoutines/Left-Right-Switch.auto"));
			}
			
			System.out.println(path);
			return path;
		} catch (IOException e1) {
			System.out.println("e2");
			e1.printStackTrace();
			return null;
		}
//		try {
//			return new AutonomousRoutine(new File("/home/lvuser/AutoRoutines/" + pos + "/" + gameInfo + ".auto"));
//		} catch (IOException e) {
//			System.out.println("e1");
//			e.printStackTrace();
//			
//		}
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

