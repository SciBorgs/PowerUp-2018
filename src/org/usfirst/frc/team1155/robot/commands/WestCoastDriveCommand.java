
package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.PortMap;
import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.DriveSubsystem.PIDMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class WestCoastDriveCommand extends Command {
	
	private Joystick left, right;
	private XboxController xbox;
	private boolean joystick;
	private double rawLeft, rawRight;
	
    public WestCoastDriveCommand(Joystick leftStick, Joystick rightStick) {
        requires(Robot.driveSubsystem);
        left = leftStick;
        right = rightStick;
        setInterruptible(true);
        joystick = true;
    }
    
    public WestCoastDriveCommand(XboxController xbox) {
        requires(Robot.driveSubsystem);
        this.xbox = xbox;
        setInterruptible(true);
        joystick = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.pidMode = PIDMode.DriveStraight;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(joystick) {
    		rawLeft = -left.getY();
    		rawRight = -right.getY();
    	} else {
    		rawLeft = -xbox.getRawAxis(PortMap.XBOX_LEFT_JOY_Y);
    		rawRight = -xbox.getRawAxis(PortMap.XBOX_RIGHT_JOY_Y);
    	}

    	double leftSpeed = Robot.driveSubsystem.applyDriveCurve(rawLeft);
    	double rightSpeed = Robot.driveSubsystem.applyDriveCurve(rawRight);
    	
    	Robot.driveSubsystem.setSpeed(leftSpeed, rightSpeed);
    	
    	double leftVel = Robot.driveSubsystem.getEncVelocity("Left");
    	double rightVel = Robot.driveSubsystem.getEncVelocity("Right");

    	SmartDashboard.putNumber("Left Velocity", leftVel);
    	SmartDashboard.putNumber("Right Velocity", rightVel);
    	
    	//Automatic gear shifting
    	if(leftVel > Robot.driveSubsystem.SHIFT_SPEED && rightVel > Robot.driveSubsystem.SHIFT_SPEED){ //if both wheels are more than the shift speed
    		if(Robot.driveSubsystem.gearShifter.get() != Robot.driveSubsystem.lowGearValue){		   //Check if the gear is wrong
    			Robot.driveSubsystem.gearShifter.set(Robot.driveSubsystem.lowGearValue);			   //if it is, change the gear
    		}
    	}else if(leftVel < Robot.driveSubsystem.SHIFT_SPEED && rightVel < Robot.driveSubsystem.SHIFT_SPEED){ //if both wheels are less than the shift speed
    		if(Robot.driveSubsystem.gearShifter.get() != Robot.driveSubsystem.highGearValue){				 //Check if the gear is wrong
    			Robot.driveSubsystem.gearShifter.set(Robot.driveSubsystem.highGearValue);					 //if it is, change the gear
    		}
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.setSpeed(0, 0);
    	Robot.driveSubsystem.endAdjustment();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
}
