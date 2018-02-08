package org.usfirst.frc.team1155.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionSubsystem extends Subsystem {
    
    public boolean canSeeCube(){
    	return SmartDashboard.getBoolean("canSeeCube", false);
    }
    
    public double getAngleToCube(){
    	return SmartDashboard.getNumber("angleToCube", 0);
    }
    
    public double getDistanceToCube(){
    	return SmartDashboard.getNumber("distanceToCube", 0);
    }
    
    public double getAngleOfCube(){
    	return SmartDashboard.getNumber("pnpAngle", 0);
    }

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
    
}

