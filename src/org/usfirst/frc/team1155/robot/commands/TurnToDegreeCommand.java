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
		// Calibrates the turn angle
		System.out.println("turning...");
    	Robot.driveSubsystem.pidMode = PIDMode.TurnDegree;
    	
		//Robot.driveSubsystem.startAdjustment(Robot.driveSubsystem.getPigeonRoll(), angleToTurn);
    	Robot.driveSubsystem.startAdjustment(Robot.driveSubsystem.getPigeonAngle(), SmartDashboard.getNumber("angleToTurn", 0));
	}

	@Override
	protected void execute() {
		SmartDashboard.putNumber("GyroValue", Robot.driveSubsystem.getPigeonAngle());
	}

	@Override
	protected boolean isFinished() {
		return Robot.driveSubsystem.getPIDController().onTarget();
	}

	@Override
	protected void end() {
		Robot.driveSubsystem.endAdjustment();
		
	}

	@Override
	protected void interrupted() {
		end();
	}

}
