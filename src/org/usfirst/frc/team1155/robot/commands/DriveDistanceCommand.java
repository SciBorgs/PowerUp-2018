package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.DriveSubsystem.PIDMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveDistanceCommand extends Command {

	double distanceToDrive;
	
    public DriveDistanceCommand(double dist) {
    	//requires(Robot.driveSubsystem);
    	setInterruptible(true);
    	System.out.println("dist: " + dist);
    	distanceToDrive = dist;
    	//distanceToDrive = SmartDashboard.getNumber("Dist To Drive", 0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.resetEncoders();

    	//distanceToDrive = SmartDashboard.getNumber("Dist To Drive", 0);

    	System.out.println("drivedistance starting");
    	Robot.driveSubsystem.pidMode = PIDMode.DriveDistance;
    	double[] drivePids = Robot.driveSubsystem.DRIVE_PID;
    	Robot.driveSubsystem.getPIDController().setPID(drivePids[0], drivePids[1], drivePids[2]);
//    	double ticksToDrive = Robot.driveSubsystem.feetToEncTicks(distanceToDrive);
    	Robot.driveSubsystem.currentAngle = Robot.driveSubsystem.getPigeonAngle();
    	Robot.driveSubsystem.getPIDController().setInputRange(distanceToDrive - 10, distanceToDrive + 10);
    	Robot.driveSubsystem.getPIDController().setOutputRange(-1, 1);
    	Robot.driveSubsystem.startAdjustment(Robot.driveSubsystem.getEncPosition(), Robot.driveSubsystem.getEncPosition() + distanceToDrive);
    	System.out.println("Get Enc Pos: " + Robot.driveSubsystem.getEncPosition());
    	System.out.println("dist + enc pos" + distanceToDrive + Robot.driveSubsystem.getEncPosition());

    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.out.println("PID delta setpoint: " + Robot.driveSubsystem.getPIDController().getDeltaSetpoint());
    	//SmartDashboard.putNumber("EncoderValue", Robot.driveSubsystem.getEncPosition());
    	//System.out.println("PID Error: " + Robot.driveSubsystem.getPIDController().getError());
    	SmartDashboard.putNumber("Pid error", Robot.driveSubsystem.getPIDController().getError());
    	SmartDashboard.putNumber("Talon Current", Robot.driveSubsystem.frontLeftMotor.getOutputCurrent());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.driveSubsystem.getPIDController().onTarget();
//    	return (Robot.driveSubsystem.getPIDController().getError() / distanceToDrive) < 0.03;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Drive distance end");
    	Robot.driveSubsystem.endAdjustment();

    	Robot.driveSubsystem.resetEncoders();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("Drive distance interrupt");
    	end();
    }
}
