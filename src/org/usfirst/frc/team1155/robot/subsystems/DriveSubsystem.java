package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.PortMap;
import org.usfirst.frc.team1155.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import api.Path;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public TalonSRX frontLeftMotor, frontRightMotor, 
					backRightMotor, backLeftMotor,
					middleRightmotor, middleLeftMotor;

	public void initDefaultCommand() {

		frontLeftMotor = new TalonSRX(PortMap.DRIVE_FRONT_LEFT_TALON);
		frontRightMotor = new TalonSRX(PortMap.DRIVE_FRONT_RIGHT_TALON);

		middleLeftMotor = new TalonSRX(PortMap.DRIVE_MIDDLE_LEFT_TALON);
		middleRightmotor = new TalonSRX(PortMap.DRIVE_MIDDLE_RIGHT_TALON);

		backLeftMotor = new TalonSRX(PortMap.DRIVE_BACK_LEFT_TALON);
		backRightMotor = new TalonSRX(PortMap.DRIVE_BACK_RIGHT_TALON);
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setSpeed(double leftVal, double rightVal){
		frontRightMotor.set(ControlMode.PercentOutput, rightVal);
		frontLeftMotor.set(ControlMode.PercentOutput, leftVal);

		middleRightmotor.set(ControlMode.PercentOutput, rightVal);
		middleLeftMotor.set(ControlMode.PercentOutput, leftVal);
		
		backRightMotor.set(ControlMode.PercentOutput, rightVal);
		backLeftMotor.set(ControlMode.PercentOutput, leftVal);
	}
	
	/* The Gyro returns between 0-360, after it has been cleaned up
	 * However, normal math dictates that if you are hypotenuse turning
	 * it is between 0 and 90 degrees, so this command does that
	 * by transposing the current position to a grid at 0,0, determining
	 * the coordinate quadrant of the next point, and thus the amount of
	 * degrees that need to be added to get a degree that would match with
	 * the gyro
	 */
	public double calculatesAngleToTurnTo(Path path, int i) {
		double currentGyroAngle = Robot.Gyro.getAngle() % 360;
		double x = Robot.plane.getX();
		double y = Robot.plane.getY();
		double nextX = 0.0508 * path.get(i)[0] - x;
		double nextY = 0.0508 * path.get(i)[1] - y;
		if (nextX > 0 && nextY > 0) {
			return Math.toDegrees(Math.atan(nextY/nextX));
		} else if (nextX > 0 && nextY < 0) {
			return 90 + Math.abs(Math.toDegrees(Math.atan(nextY/nextX)));
		} else if (nextX < 0 && nextY < 0) {
			return 180 + Math.abs(Math.toDegrees(Math.atan(nextY/nextX)));
		} else if (nextX < 0 && nextY > 0) {
			return 270 + Math.abs(Math.toDegrees(Math.atan(nextY/nextX)));
		} else {
			return 0;
		}
	}
	
	public void moveDegrees(double degrees) {
		double conversionFactor = Math.PI/180;
		
		double xVal = -Math.cos(degrees * conversionFactor);
		double yVal = Math.sin(degrees * conversionFactor);
		
		System.out.println(xVal + " " + yVal + " " + degrees);
		
		frontLeftMotor.set(ControlMode.PercentOutput, -xVal - yVal);
		frontRightMotor.set(ControlMode.PercentOutput, -xVal + yVal);
		backLeftMotor.set(ControlMode.PercentOutput, xVal - yVal);
		backRightMotor.set(ControlMode.PercentOutput, xVal + yVal);
	}	
	
	public void moveToPoint(Path path, int i) {
		while (!(Robot.plane.getX() > path.get(i)[0] - 0.0508 && Robot.plane.getX() < path.get(i)[0] + 0.0508
		&& Robot.plane.getY() > path.get(i)[1] - 0.0508 && Robot.plane.getY() < path.get(i)[1] + 0.0508)) {
			frontRightMotor.set(ControlMode.PercentOutput, 1);
			frontLeftMotor.set(ControlMode.PercentOutput, 1);
			middleRightmotor.set(ControlMode.PercentOutput, 1);
			middleLeftMotor.set(ControlMode.PercentOutput, 1);
			backRightMotor.set(ControlMode.PercentOutput, 1);
			backLeftMotor.set(ControlMode.PercentOutput, 1);
		}
	}
	
	public void followPath(Path path) {
		for (int i = 0; i < path.size(); i++) {
			moveDegrees(calculatesAngleToTurnTo(path, i));
			moveToPoint(path, i);
		}
	}
}
