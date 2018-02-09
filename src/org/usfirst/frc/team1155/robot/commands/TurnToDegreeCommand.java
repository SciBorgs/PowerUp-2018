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
	}
	@Override
	protected void initialize() {
		// Calibrates the turn angle
    	Robot.driveSubsystem.pidMode = PIDMode.TurnDegree;
    	
		Robot.driveSubsystem.startAdjustment(Robot.driveSubsystem.getPigeonRoll(), angleToTurn);
	}

	@Override
	protected void execute() {
		SmartDashboard.putNumber("GyroValue", Robot.driveSubsystem.getPigeonRoll());
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
