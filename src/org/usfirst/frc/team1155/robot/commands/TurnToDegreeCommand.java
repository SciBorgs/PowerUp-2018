package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.DriveSubsystem.PIDMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnToDegreeCommand extends Command{

	double angleToTurn;
	
	public TurnToDegreeCommand(double angle) {
		requires(Robot.driveSubsystem);
		setInterruptible(true);
		angleToTurn = angle;
		Robot.driveSubsystem.currentAngle = angle;
	}
	@Override
	protected void initialize() {
    	Robot.driveSubsystem.resetEncoders();
		
		// Calibrates the turn angle
		System.out.println("turning...");
		//angleToTurn = SmartDashboard.getNumber("AngleToTurn", 0);
    	Robot.driveSubsystem.pidMode = PIDMode.TurnDegree;
    	double[] turnPids = Robot.driveSubsystem.TURN_PID;
    	Robot.driveSubsystem.getPIDController().setPID(turnPids[0], turnPids[1], turnPids[2]);
    	Robot.driveSubsystem.getPIDController().setInputRange(-360, 360);
    	Robot.driveSubsystem.getPIDController().setOutputRange(-1, 1);

    	
		//Robot.driveSubsystem.startAdjustment(Robot.driveSubsystem.getPigeonRoll(), angleToTurn);
    	Robot.driveSubsystem.startAdjustment(Robot.driveSubsystem.getPigeonAngle(), angleToTurn);
	}

	@Override
	protected void execute() {
		//System.out.println("Angle error: " + Robot.driveSubsystem.getPIDController().getError());
		
//		if(Robot.driveSubsystem.getPIDController().getError() <= 0.2)
//			end();
		
		//SmartDashboard.putNumber("GyroValue", Robot.driveSubsystem.getPigeonAngle());
	}

	@Override
	protected boolean isFinished() {
		return Robot.driveSubsystem.getPIDController().onTarget();
    	//return Math.abs(Robot.driveSubsystem.getPigeonAngle() - angleToTurn ) < 1;//Robot.driveSubsystem.getPIDController().getError() < 1;//(Robot.driveSubsystem.getPIDController().getError() / angleToTurn) < 0.03;
	}

	@Override
	protected void end() {
		System.out.println("Turning ended");
		Robot.driveSubsystem.endAdjustment();
		
	}

	@Override
	protected void interrupted() {
		System.out.println("Turning interrupted");
		end();
	}

}
