
package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.OI;
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
    	double rawLeft = -xbox.getRawAxis(1);
    	double rawRight = -xbox.getRawAxis(5);
    	double leftSpeed = Robot.driveSubsystem.applyDriveCurve(rawLeft);
    	double rightSpeed = Robot.driveSubsystem.applyDriveCurve(rawRight);
    	
    	System.out.println(leftSpeed);
    	System.out.println(rightSpeed);
    	Robot.driveSubsystem.setSpeed(-xbox.getRawAxis(1), -xbox.getRawAxis(5));
    	//Robot.driveSubsystem.setSpeed(leftSpeed, rightSpeed);

    	if(OI.driveStraightButton.get() && !Robot.driveSubsystem.getPIDController().isEnabled())
    		Robot.driveSubsystem.startAdjustment(0, Robot.driveSubsystem.getPigeonRoll());
    	else if (!OI.driveStraightButton.get() && Robot.driveSubsystem.getPIDController().isEnabled())
    		Robot.driveSubsystem.endAdjustment();
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
