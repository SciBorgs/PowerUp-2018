package org.usfirst.frc.team1155.robot.commands.autoCommands;

import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.DriveSubsystem.PIDMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveDistanceCommand extends Command {

	double distanceToDrive;
	private Timer timer;
	private double timeTillStop = 4.5;
	private boolean considerTime = false;
	
    public DriveDistanceCommand(double dist) {
    	requires(Robot.driveSubsystem);
    	setInterruptible(true);
    	System.out.println("Distance Set: " + dist);
    	distanceToDrive = dist * .7;
    }
    
    public DriveDistanceCommand(double dist, double time) {
    	requires(Robot.driveSubsystem);
    	setInterruptible(true);
    	System.out.println("Distance Set: " + dist);
    	distanceToDrive = dist * .7;
    	timeTillStop = time;
    	considerTime = true;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("-- Distance Drive -- Starting");
    	System.out.println("Drive Enc Pos: " + Robot.driveSubsystem.getEncPosition());
    	System.out.println("Distance to Drive to: " + (distanceToDrive + Robot.driveSubsystem.getEncPosition()));
    	
    	timer = new Timer();
		timer.start();
//    	if(considerTime) {
//    		timer.start();
//    	}
    	
    	
    	Robot.driveSubsystem.resetEncoders();

    	Robot.driveSubsystem.pidMode = PIDMode.DriveDistance;
    	double[] drivePids = Robot.driveSubsystem.DRIVE_PID;
    	Robot.driveSubsystem.getPIDController().setPID(drivePids[0], drivePids[1], drivePids[2]);
//    	double ticksToDrive = Robot.driveSubsystem.feetToEncTicks(distanceToDrive);
    	Robot.driveSubsystem.currentAngle = Robot.driveSubsystem.getPigeonAngle();
    	Robot.driveSubsystem.getPIDController().setInputRange(distanceToDrive - 15, distanceToDrive + 15);
    	Robot.driveSubsystem.getPIDController().setOutputRange(-1, 1);
    	Robot.driveSubsystem.startAdjustment(Robot.driveSubsystem.getEncPosition(), Robot.driveSubsystem.getEncPosition() + distanceToDrive);
    	

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.out.println("PID delta setpoint: " + Robot.driveSubsystem.getPIDController().getDeltaSetpoint());
    	//SmartDashboard.putNumber("EncoderValue", Robot.driveSubsystem.getEncPosition());
    	//System.out.println("PID Error: " + Robot.driveSubsystem.getPIDController().getError());
    	SmartDashboard.putNumber("Pid Error: ", Robot.driveSubsystem.getPIDController().getError());
  
		System.out.println("Timer time: " + timer.get());
//    	if (considerTime) {
//    		System.out.println("Timer time: " + timer.get());
//    	}
    	
//    	System.out.println("PID error: " + Robot.driveSubsystem.getPIDController().getError());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	if(timer.get() > timeTillStop) {
        	System.out.println("** Distance Drive ** STOPPED DUE TO TIMER");
    	}
    	return Robot.driveSubsystem.getPIDController().onTarget() || (timer.get() > timeTillStop);
//    	if(considerTime) {
//	    	if(timer.get() > timeTillStop) {
//	        	System.out.println("** Distance Drive ** STOPPED DUE TO TIMER");
//	    	}
//	    	return Robot.driveSubsystem.getPIDController().onTarget() || (timer.get() > timeTillStop);
//    	} else {
//    		return Robot.driveSubsystem.getPIDController().onTarget();
//    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("<< Distance Drive >> Ending");

    	Robot.driveSubsystem.endAdjustment();
    	Robot.driveSubsystem.setSpeed(0, 0);
    	Robot.driveSubsystem.resetEncoders();

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("** Distance Drive ** INTERRUPTED");
    	end();

    }
}
