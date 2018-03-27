package org.usfirst.frc.team1155.robot.commands.autoCommands;

import org.usfirst.frc.team1155.robot.Robot;
import org.usfirst.frc.team1155.robot.subsystems.DriveSubsystem.PIDMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class TurnToDegreeCommand extends Command{

	double angleToTurn;
	Timer timer;
	
	public TurnToDegreeCommand(double angle) {
		requires(Robot.driveSubsystem);
		setInterruptible(false);
		angleToTurn = angle;
		Robot.driveSubsystem.currentAngle = angle;
		
		timer = new Timer();
	}
	@Override
	protected void initialize() {
		timer.start();
		
    	Robot.driveSubsystem.resetEncoders();
		
		// Calibrates the turn angle
		//angleToTurn = SmartDashboard.getNumber("AngleToTurn", 0);
    	Robot.driveSubsystem.pidMode = PIDMode.TurnDegree;
    	double[] turnPids = Robot.driveSubsystem.TURN_PID;
    	Robot.driveSubsystem.getPIDController().setPID(turnPids[0], turnPids[1], turnPids[2]);
    	Robot.driveSubsystem.getPIDController().setInputRange(-360, 360);
    	Robot.driveSubsystem.getPIDController().setOutputRange(-1, 1);

    	
		//Robot.driveSubsystem.startAdjustment(Robot.driveSubsystem.getPigeonRoll(), angleToTurn);
    	Robot.driveSubsystem.startAdjustment(Robot.driveSubsystem.getPigeonAngle(), Robot.driveSubsystem.getPigeonAngle() + angleToTurn);
    	
		System.out.println("-- Angle Turn -- Starting");
		System.out.println("Current Angle: " + Robot.driveSubsystem.getPigeonAngle());
		System.out.println("Angle to Turn to: " + (Robot.driveSubsystem.getPigeonAngle() + angleToTurn));

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
		return Robot.driveSubsystem.getPIDController().onTarget() || timer.get() > 3; 
    	//return Math.abs(Robot.driveSubsystem.getPigeonAngle() - angleToTurn ) < 1;//Robot.driveSubsystem.getPIDController().getError() < 1;//(Robot.driveSubsystem.getPIDController().getError() / angleToTurn) < 0.03;
	}

	@Override
	protected void end() {
		Robot.driveSubsystem.endAdjustment();
		System.out.println("<< Angle Turn >> Ending");

		
	}

	@Override
	protected void interrupted() {
		end();
		System.out.println("** Angle Turn ** INTERRUPTED");
	}

}
