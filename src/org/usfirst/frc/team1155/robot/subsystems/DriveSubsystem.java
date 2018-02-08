package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.PortMap;
import org.usfirst.frc.team1155.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *
 */
public class DriveSubsystem extends PIDSubsystem {

	public TalonSRX frontLeftMotor, middleLeftMotor, frontRightMotor, backLeftMotor, middleRightMotor, backRightMotor;
	public Gyro gyro;
	public DoubleSolenoid gearShifter;

	public static enum PIDMode {
		TurnDegree, DriveStraight, DriveDistance;
	}

	public PIDMode pidMode;

	// Initialize your subsystem here
	public DriveSubsystem() {
		super("Drive", 0.1, 0, 0.1);
		pidMode = PIDMode.TurnDegree;

		frontLeftMotor = new TalonSRX(PortMap.DRIVE_FRONT_LEFT_TALON);
		middleRightMotor = new TalonSRX(PortMap.DRIVE_MIDDLE_RIGHT_TALON);
		frontRightMotor = new TalonSRX(PortMap.DRIVE_FRONT_RIGHT_TALON);
		backLeftMotor = new TalonSRX(PortMap.DRIVE_BACK_LEFT_TALON);
		middleLeftMotor = new TalonSRX(PortMap.DRIVE_MIDDLE_LEFT_TALON);
		backRightMotor = new TalonSRX(PortMap.DRIVE_BACK_RIGHT_TALON);
		backRightMotor.set(ControlMode.Follower, frontRightMotor.getDeviceID());
		backLeftMotor.set(ControlMode.Follower, frontLeftMotor.getDeviceID());
		middleRightMotor.set(ControlMode.Follower, frontRightMotor.getDeviceID());
		middleLeftMotor.set(ControlMode.Follower, frontLeftMotor.getDeviceID());

		gearShifter = new DoubleSolenoid(PortMap.GEAR_SHIFTER_SOLENOID[0], PortMap.GEAR_SHIFTER_SOLENOID[1]);
		
		getPIDController().setContinuous(false);

		gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
		gyro.reset();
	}
	
	public void shiftDown() {
		gearShifter.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void shiftUp() {
		gearShifter.set(DoubleSolenoid.Value.kForward);
	}
	protected void initDefaultCommand() {

	}

	protected double returnPIDInput() {
		switch (pidMode) {
		case TurnDegree:
		case DriveStraight:
			return gyro.getAngle();
		case DriveDistance:
			return getEncPosition();
		default:
			return 0;
		}
	}

	protected void usePIDOutput(double output) {
		switch (pidMode) {
		// For reference, a CW gyro correction is positive by default
		// TODO: Check if the robot goes in the right direction.
		// If it doesn't, flip the negations on the outputs.
		case TurnDegree:
			output *= 0.5;
			setSpeed(output, -output);
			break;
		case DriveStraight:
			output *= 0.1;
			correctSpeed(output);
			break;
		case DriveDistance:
			output *= -0.5;
			setSpeed(output, output);
		default:
			setSpeed(0, 0);
			break;
		}
	}

	public void setSpeed(double leftSpeed, double rightSpeed) {
		frontLeftMotor.set(ControlMode.PercentOutput, -leftSpeed);
		frontRightMotor.set(ControlMode.PercentOutput, rightSpeed);
	}

	public void correctSpeed(double offset) {
		double rightOutput = frontRightMotor.getMotorOutputPercent();
		double leftOutput = frontLeftMotor.getMotorOutputPercent();
		frontLeftMotor.set(ControlMode.PercentOutput, leftOutput + ((rightOutput >= 0) ? offset : -offset));

	}

	public void startAdjustment(double current, double setPoint) {
		switch (pidMode) {
		case TurnDegree:
			getPIDController().setPercentTolerance(5.0);
			setPoint %= 360;
			setSetpoint((int) (((current - setPoint >= 0 ? 180 : -180) + current - setPoint) / 360) * 360 + setPoint);
			break;
		case DriveStraight:
		case DriveDistance:
			getPIDController().setPercentTolerance(0.5);
			setSetpoint(setPoint);
			break;
		default:
			setSetpoint(setPoint);
			break;
		}
		enable();
	}
	
	/* The Gyro returns between 0-360, after it has been cleaned up
	 * However, normal math dictates that if you are hypotenuse turning
	 * it is between 0 and 90 degrees, so this command does that
	 * by transposing the current position to a grid at 0,0, determining
	 * the coordinate quadrant of the next point, and thus the amount of
	 * degrees that need to be added to get a degree that would match with
	 * the gyro
	 */
	public double calculatesAngleToTurnTo(int[] coordArr) {
//		double currentGyroAngle = Robot.Gyro.getAngle() % 360;
//		double x = Robot.plane.getX();
//		double y = Robot.plane.getY();
//		double nextX = 0.0127 * coordArr[0] - x;
//		double nextY = 0.0127 * coordArr[1] - y;
//		if (nextX > 0 && nextY > 0) {
//			return Math.toDegrees(Math.atan(nextY/nextX));
//		} else if (nextX > 0 && nextY < 0) {
//			return 90 + Math.abs(Math.toDegrees(Math.atan(nextY/nextX)));
//		} else if (nextX < 0 && nextY < 0) {
//			return 180 + Math.abs(Math.toDegrees(Math.atan(nextY/nextX)));
//		} else if (nextX < 0 && nextY > 0) {
//			return 270 + Math.abs(Math.toDegrees(Math.atan(nextY/nextX)));
//		} else {
//			return 0;
//		}
		return 0;
	}
	
	public void endAdjustment() {
		getPIDController().disable();
	}
	
	public void resetGyro() {
		gyro.reset();
	}
	
	public double getEncPosition() {
		// TODO: Find Gear Ratio and use to convert sensor position into actual distance.
		return frontRightMotor.getSensorCollection().getQuadraturePosition();
	}
	
}