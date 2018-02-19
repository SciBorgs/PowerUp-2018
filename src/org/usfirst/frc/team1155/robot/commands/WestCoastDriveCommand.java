
package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.OI;
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
	
    public WestCoastDriveCommand(Joystick leftStick, Joystick rightStick) {
        requires(Robot.driveSubsystem);
        left = leftStick;
        right = rightStick;
        setInterruptible(true);
    }
    
    public WestCoastDriveCommand(XboxController xbox) {
        requires(Robot.driveSubsystem);
        this.xbox = xbox;
        setInterruptible(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.pidMode = PIDMode.DriveStraight;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
/*    	SmartDashboard.putNumber("GyroValue", Robot.driveSubsystem.getPigeonRoll());
    	Robot.driveSubsystem.setSpeed(-left.getY(), -right.getY());
    	if(OI.driveStraightButton.get() && !Robot.driveSubsystem.getPIDController().isEnabled())
    		Robot.driveSubsystem.startAdjustment(0, Robot.driveSubsystem.getPigeonRoll());
    	else if (!OI.driveStraightButton.get() && Robot.driveSubsystem.getPIDController().isEnabled())
    		Robot.driveSubsystem.endAdjustment(); */
    	double rawLeft = -xbox.getRawAxis(PortMap.XBOX_LEFT_JOY_Y);
    	double rawRight = -xbox.getRawAxis(PortMap.XBOX_RIGHT_JOY_Y);
    	SmartDashboard.putNumber("Left Y-Axis", rawLeft);
    	SmartDashboard.putNumber("Right Y-Axis", rawRight);
    	
    	double leftSpeed = Robot.driveSubsystem.applyDriveCurve(rawLeft);
    	double rightSpeed = Robot.driveSubsystem.applyDriveCurve(rawRight);
    	
    	leftSpeed /= 2;
    	rightSpeed /= 2;
    	//Robot.driveSubsystem.setSpeed(rawLeft, rawRight);
    	Robot.driveSubsystem.setSpeed(leftSpeed, rightSpeed);
   /* 	if(OI.driveStraightButton.get())
    		Robot.driveSubsystem.setSpeed(rawLeft, rawLeft);
    	if(OI.driveStraightButton.get() && !Robot.driveSubsystem.getPIDController().isEnabled()) {
    		Robot.driveSubsystem.startAdjustment(Robot.driveSubsystem.getPigeonAngle(), Robot.driveSubsystem.getPigeonAngle());
    	
    	}else if (!OI.driveStraightButton.get() && Robot.driveSubsystem.getPIDController().isEnabled()) {
    		Robot.driveSubsystem.endAdjustment();
    	} */
    	SmartDashboard.putNumber("Talon Current", Robot.driveSubsystem.frontRightMotor.getOutputCurrent());

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
