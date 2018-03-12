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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveSubsystem extends PIDSubsystem {

	public TalonSRX frontLeftMotor, middleLeftMotor, frontRightMotor, backLeftMotor, middleRightMotor, backRightMotor;
	//public Gyro gyro;
	public DoubleSolenoid gearShifter;
	public TalonSRX talonWithPigeon;
	public final double TICKS_PER_ROTATION = 4096;
	public final double WHEEL_RADIUS = 3./12.; //(0.25) 3 inches over 12 inches is wheel radius in feet
	public final double ENC_WHEEL_RATIO = (4./25.) * (1./1.2); //(0.16) 4 rotations of the wheel is 25 rotations of the encoder
	
	public final int CONTCURRENTLIMIT = 1; //amps
	public final int PEAKCURRENTLIMIT = 2;
	public final int PEAKCURRENTDURATION = 0; //ms
	
	public final double PIXEL_TO_FEET = 0.127/12.;
	public double currentAngle = 0;
	
	public final double ANGLE_BUFFER = 1.;
	public final double ADJUST_SPEED_DELTA = .1;
	
	public final double[] DRIVE_PID = {1.0, 0, 0.6};
	public final double[] TURN_PID = {0.1, 0.01, .7};
	
	public final double PID_TOLERANCE = .01;

	
	public static enum PIDMode {
		TurnDegree, DriveStraight, DriveDistance;
	}

	public PIDMode pidMode;

	// Initialize your subsystem here
	public DriveSubsystem() {
		//super("Drive", 0.1, 0.01, 0.1);
		super("Drive", 1.0, 0, 0.6);
		pidMode = PIDMode.TurnDegree;

		//P is 1.0, I is 0.0, D is 0.6
		
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
		
//		frontLeftMotor.configClosedloopRamp(2, 0); //2 seconds from neutral to full
//		backLeftMotor.configClosedloopRamp(2, 0); //2 seconds from neutral to full
//		middleLeftMotor.configClosedloopRamp(2, 0); //2 seconds from neutral to full
//		middleRightMotor.configClosedloopRamp(2, 0); //2 seconds from neutral to full
//		frontRightMotor.configClosedloopRamp(2, 0); //2 seconds from neutral to full
//		backRightMotor.configClosedloopRamp(2, 0); //2 seconds from neutral to full		
		
//		frontRightMotor.configContinuousCurrentLimit(CONTCURRENTLIMIT, 0);
//		frontRightMotor.configPeakCurrentLimit(PEAKCURRENTLIMIT, 0);
//		frontRightMotor.configPeakCurrentDuration(PEAKCURRENTDURATION, 0);
//		frontRightMotor.enableCurrentLimit(true);
//		
//		middleRightMotor.configContinuousCurrentLimit(CONTCURRENTLIMIT, 0);
//		middleRightMotor.configPeakCurrentLimit(PEAKCURRENTLIMIT, 0);
//		middleRightMotor.configPeakCurrentDuration(PEAKCURRENTDURATION, 0);
//		middleRightMotor.enableCurrentLimit(true);
//		
//		backRightMotor.configContinuousCurrentLimit(CONTCURRENTLIMIT, 0);
//		backRightMotor.configPeakCurrentLimit(PEAKCURRENTLIMIT, 0);
//		backRightMotor.configPeakCurrentDuration(PEAKCURRENTDURATION, 0);
//		backRightMotor.enableCurrentLimit(true);
//		
//		frontLeftMotor.configContinuousCurrentLimit(CONTCURRENTLIMIT, 0);
//		frontLeftMotor.configPeakCurrentLimit(PEAKCURRENTLIMIT, 0);
//		frontLeftMotor.configPeakCurrentDuration(PEAKCURRENTDURATION, 0);
//		frontLeftMotor.enableCurrentLimit(true);
//		
//		middleLeftMotor.configContinuousCurrentLimit(CONTCURRENTLIMIT, 0);
//		middleLeftMotor.configPeakCurrentLimit(PEAKCURRENTLIMIT, 0);
//		middleLeftMotor.configPeakCurrentDuration(PEAKCURRENTDURATION, 0);
//		middleLeftMotor.enableCurrentLimit(true);
//		
//		backLeftMotor.configContinuousCurrentLimit(CONTCURRENTLIMIT, 0);
//		backLeftMotor.configPeakCurrentLimit(PEAKCURRENTLIMIT, 0);
//		backLeftMotor.configPeakCurrentDuration(PEAKCURRENTDURATION, 0);
//		backLeftMotor.enableCurrentLimit(true);
		
		talonWithPigeon = new TalonSRX(5);
		
		gearShifter = new DoubleSolenoid(PortMap.GEAR_SHIFTER_SOLENOID[0], PortMap.GEAR_SHIFTER_SOLENOID[1]);
		
		getPIDController().setContinuous(false);

	//	gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
	//	gyro.reset();
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
			return getPigeonAngle();
		case DriveStraight:
			return getPigeonAngle();
		case DriveDistance:
			return getEncPosition();
		default:
			return 0;
		}
	}

	protected void usePIDOutput(double output) {
		//System.out.println("PID output: " + output);

		switch (pidMode) {
		// For reference, a CW gyro correction is positive by default
		// TODO: Check if the robot goes in the right direction.
		// If it doesn't, flip the negations on the outputs.
		case TurnDegree:
			output *= -0.5;
			setSpeed(output, -output);
			break;
		case DriveStraight:
			output *= -0.1;
			correctSpeed(output);
			break;
		case DriveDistance:
			output *= 0.5;
			driveDistSetSpeed(output, output);
		default:
			setSpeed(0, 0);
			break;
		}
	}

	public void driveDistSetSpeed(double leftSpeed, double rightSpeed) {
		
		
		double deviance = getPigeonAngle() - currentAngle;
		
		if(Math.abs(deviance) > ANGLE_BUFFER) {
			if(deviance > 0) {
				if(rightSpeed < 0) {
					rightSpeed += ADJUST_SPEED_DELTA;
				}else if(rightSpeed > 0) {
					rightSpeed -= ADJUST_SPEED_DELTA;
				}
			}else if(deviance < 0) {
				if(leftSpeed < 0) {
					leftSpeed += ADJUST_SPEED_DELTA;
				}else if(leftSpeed > 0) {
					leftSpeed -= ADJUST_SPEED_DELTA;
				}
			}
		}
		
		leftSpeed = -leftSpeed;
		
		frontLeftMotor.set(ControlMode.PercentOutput, leftSpeed);
		middleLeftMotor.set(ControlMode.PercentOutput, leftSpeed);
		backLeftMotor.set(ControlMode.PercentOutput, leftSpeed);
		
		frontRightMotor.set(ControlMode.PercentOutput, rightSpeed);
		middleRightMotor.set(ControlMode.PercentOutput, rightSpeed);
		backRightMotor.set(ControlMode.PercentOutput, rightSpeed);
	}
	
	public void setSpeed(double leftSpeed, double rightSpeed) {
		frontLeftMotor.set(ControlMode.PercentOutput, -leftSpeed);
		frontRightMotor.set(ControlMode.PercentOutput, rightSpeed);
	}
 
	public void correctSpeed(double offset) {
		double rightOutput = frontRightMotor.getMotorOutputPercent();
		double leftOutput = frontLeftMotor.getMotorOutputPercent();
		frontLeftMotor.set(ControlMode.PercentOutput, leftOutput + ((rightOutput >= 0) ? offset : -offset));
		middleLeftMotor.set(ControlMode.PercentOutput, leftOutput + ((rightOutput >= 0) ? offset : -offset));
		backLeftMotor.set(ControlMode.PercentOutput, leftOutput + ((rightOutput >= 0) ? offset : -offset));

	}

	public void startAdjustment(double current, double setPoint) {
		//updatePID();
		getPIDController().setContinuous(false);
		switch (pidMode) {
		case TurnDegree:
			getPIDController().setPercentTolerance(1);
			setPoint %= 360;
			setSetpoint((int) (((current - setPoint >= 0 ? 180 : -180) + current - setPoint) / 360) * 360 + setPoint);
			break;
		case DriveStraight:
			getPIDController().setContinuous(true);
		case DriveDistance:
			getPIDController().setPercentTolerance(1);
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
	public double calculatesAngleToTurnTo(double[] currentPoint, double[] destPoint) {
//		double x = Robot.positioningHandler.position.getX();
//		double y = Robot.positioningHandler.position.getY();
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
		
		double x1 = currentPoint[0];
		double y1 = currentPoint[1];
		
		double x2 = destPoint[0];
		double y2 = destPoint[1];
		
		double relativeX = x2 - x1;
		double relativeY = y2 - y1;
		
		double angle = Math.toDegrees(Math.atan2(relativeY, relativeX)) - 90;
		
		//double angle = 90 - Math.toDegrees(Math.atan2(y2-y1, x2-x1));
		
//		double angle = Math.toDegrees(Math.acos((x1*(x2-x1) + y1*(y2-y1))/(getMagnitude(x1, x2, y1, y2)*getMagnitude(x1, x2, y1, y2))));
	//	double angle = 90 - Math.toDegrees(Math.atan((y2 - y1) / (x2-x1)));
		
		
		return angle;
	}
	
	private double getMagnitude(double x1, double x2, double y1, double y2) {
		return Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
	}
	
	
	public void endAdjustment() {
		getPIDController().disable();
	}
	
	public void resetGyro() {
//		gyro.reset();
	}
	
	public double getPigeonAngle(){
		double[] yawPitchRoll = new double[3];
		Robot.pigeon.getYawPitchRoll(yawPitchRoll);
		//System.out.println("yaw: " + yawPitchRoll[0] + " pitch: " + yawPitchRoll[1] + " roll: " + yawPitchRoll[2]);
		return yawPitchRoll[0] % 360.;
	}
	
	public double getEncPosition() {
		return (getLeftEncPosition() + getRightEncPosition()) / 2.;
	}
	
	public void updatePID() {
		getPIDController().setP(SmartDashboard.getNumber("P Value", 1.0));
		getPIDController().setI(SmartDashboard.getNumber("I Value", 0.1));
		getPIDController().setD(SmartDashboard.getNumber("D Value", 0.1));
	}
	
	public void resetEncoders() {
		middleRightMotor.getSensorCollection().setQuadraturePosition(0, 0);
		middleLeftMotor.getSensorCollection().setQuadraturePosition(0, 0);
	}
	
	public double getEncPositionTicks() {
		return (middleRightMotor.getSensorCollection().getQuadraturePosition() - middleLeftMotor.getSensorCollection().getQuadraturePosition()) / 2.;
	}
	
	public double getLeftEncPosition() {
		double dist = (-middleLeftMotor.getSensorCollection().getQuadraturePosition() / TICKS_PER_ROTATION) * ENC_WHEEL_RATIO * (2 * Math.PI * WHEEL_RADIUS);
		return dist;

	}
	
	public double getRightEncPosition() {
		double dist = (middleRightMotor.getSensorCollection().getQuadraturePosition() / TICKS_PER_ROTATION) * ENC_WHEEL_RATIO * (2 * Math.PI * WHEEL_RADIUS);
		return dist;

	}
	
	public double getLeftEncPositionTicks() {
		return -middleLeftMotor.getSensorCollection().getQuadraturePosition();
	}

	public double getRightEncPositionTicks() {
		return middleRightMotor.getSensorCollection().getQuadraturePosition();
	}

	
	
	public double feetToEncTicks(double feet){
		double wheelRotations = feet / (2 * Math.PI * WHEEL_RADIUS);
		double encTicks = wheelRotations / ENC_WHEEL_RATIO;
		return encTicks;
	}
	
	public double applyDriveCurve(double raw) {	
/*		if(raw <= -0.5)
			return -Math.sqrt(.25 - (raw + 1) * (raw + 1)) - 0.5;
		else if(raw > -0.5 && raw <= 0)
			return Math.sqrt(.25 - raw * raw) - 0.5;
		else if(raw > 0 && raw <= 0.5)
			return -Math.sqrt(.25 - raw * raw) + 0.5;
		else
			return Math.sqrt(.25 - (raw - 1) * (raw - 1)) + 0.5; */	
		if(raw < 0) {
			return -Math.pow(-raw, 2.4);
		}
		return Math.pow(raw, 2.4);
	}
	
}