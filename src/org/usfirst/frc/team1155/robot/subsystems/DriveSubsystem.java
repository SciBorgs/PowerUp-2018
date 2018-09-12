package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.PortMap;
import org.usfirst.frc.team1155.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveSubsystem extends PIDSubsystem {

	public TalonSRX frontLeftMotor, middleLeftMotor, frontRightMotor, backLeftMotor, middleRightMotor, backRightMotor;
	public DoubleSolenoid gearShifter;
	public TalonSRX talonWithPigeon;
	public final double TICKS_PER_ROTATION = 4096;
	public final double WHEEL_RADIUS = 3./12.; //(0.25) 3 inches over 12 inches is wheel radius in feet
	public final double ENC_WHEEL_RATIO = (4./25.) * (1./1.2); //(0.16) 4 rotations of the wheel is 25 rotations of the encoder
	
	public final int CONTCURRENTLIMIT = 12; //amps
	public final int PEAKCURRENTLIMIT = 16;
	public final int PEAKCURRENTDURATION = 250; //ms
	
	public final double PIXEL_TO_FEET = 0.127/12.;
	public double currentAngle = 0;
	
	public final double ANGLE_BUFFER = 1.;
	public final double ADJUST_SPEED_DELTA = .1;
	
	public final double[] DRIVE_PID = {1.0, 0, 0.6};
	public final double[] TURN_PID = {0.1, 0.01, .7};
	
	public final double PID_TOLERANCE = .01;
	public final double SHIFT_SPEED = 5.; //shift the gear at 5 feet per second
	public DoubleSolenoid.Value lowGearValue = DoubleSolenoid.Value.kForward;
	public DoubleSolenoid.Value highGearValue = DoubleSolenoid.Value.kReverse;
	
	double driveLiftingSpeedScale = .5;
	
	static double encOffset = 0;
	
	public static enum PIDMode {
		TurnDegree, DriveStraight, DriveDistance;
	}

	public PIDMode pidMode;

	// Initialize your subsystem here
	public DriveSubsystem() {
		super("Drive", 1.0, 0, 0.6);
		pidMode = PIDMode.TurnDegree;

		//P is 1.0, I is 0.0, D is 0.6
		
		
		frontLeftMotor = new TalonSRX(PortMap.DRIVE_FRONT_LEFT_TALON);
		middleLeftMotor = new TalonSRX(PortMap.DRIVE_MIDDLE_LEFT_TALON);
		backLeftMotor = new TalonSRX(PortMap.DRIVE_BACK_LEFT_TALON);
		
		frontRightMotor = new TalonSRX(PortMap.DRIVE_FRONT_RIGHT_TALON);
		middleRightMotor = new TalonSRX(PortMap.DRIVE_MIDDLE_RIGHT_TALON);
		backRightMotor = new TalonSRX(PortMap.DRIVE_BACK_RIGHT_TALON);
		
		
		//Set default mode of wheels to be stopped
		frontLeftMotor.setNeutralMode(NeutralMode.Brake);
		middleLeftMotor.setNeutralMode(NeutralMode.Brake);
		backLeftMotor.setNeutralMode(NeutralMode.Brake);

		frontRightMotor.setNeutralMode(NeutralMode.Brake);
		middleRightMotor.setNeutralMode(NeutralMode.Brake);
		backRightMotor.setNeutralMode(NeutralMode.Brake);

		//Voltage Ramping
		frontLeftMotor.configOpenloopRamp(0.1, 50); 
		backLeftMotor.configOpenloopRamp(0.1, 50); 
		middleLeftMotor.configOpenloopRamp(0.1, 50); 
		middleRightMotor.configOpenloopRamp(0.1, 50);
		frontRightMotor.configOpenloopRamp(0.1, 50); 
		backRightMotor.configOpenloopRamp(0.1, 50);

		
		talonWithPigeon = new TalonSRX(2);
		
		gearShifter = new DoubleSolenoid(PortMap.GEAR_SHIFTER_SOLENOID[0], PortMap.GEAR_SHIFTER_SOLENOID[1]);
		
		
		getPIDController().setContinuous(false);
		

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

	//Uses output from PID libraries as control outputs
	protected void usePIDOutput(double output) {
		switch (pidMode) {
		// For reference, a CW gyro correction is positive by default
		// TODO: Check if the robot goes in the right direction.
		// If it doesn't, flip the negations on the outputs.
		case TurnDegree:
			output *= -0.4;
			setSpeed(output, -output);
			break;
		case DriveStraight:
			output *= -0.1;
			correctSpeed(output);
			break;
		case DriveDistance:
			output *= 0.5;
			setSpeed(output, output);
			break;
		default:
			setSpeed(0, 0);
			break;
		}
	}

	//Sets speed for distance drive mode
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
		if(Robot.liftSubsystem.isAboveMid()) {
			leftSpeed *= driveLiftingSpeedScale;
			rightSpeed *= driveLiftingSpeedScale;
		}
		
		frontLeftMotor.set(ControlMode.PercentOutput, -leftSpeed);
		middleLeftMotor.set(ControlMode.PercentOutput, -leftSpeed);
		backLeftMotor.set(ControlMode.PercentOutput, -leftSpeed);

		frontRightMotor.set(ControlMode.PercentOutput, rightSpeed);
		middleRightMotor.set(ControlMode.PercentOutput, rightSpeed);
		backRightMotor.set(ControlMode.PercentOutput, rightSpeed);
	}
 
	//Corrects left motor speeds to equal right motor speeds based on offset
	public void correctSpeed(double offset) {
		double rightOutput = frontRightMotor.getMotorOutputPercent();
		double leftOutput = frontLeftMotor.getMotorOutputPercent();
		frontLeftMotor.set(ControlMode.PercentOutput, leftOutput + ((rightOutput >= 0) ? offset : -offset));
		middleLeftMotor.set(ControlMode.PercentOutput, leftOutput + ((rightOutput >= 0) ? offset : -offset));
		backLeftMotor.set(ControlMode.PercentOutput, leftOutput + ((rightOutput >= 0) ? offset : -offset));

	}

	//Starts PID adjustment
	public void startAdjustment(double current, double setPoint) {
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
		
		double x1 = currentPoint[0];
		double y1 = currentPoint[1];
		
		double x2 = destPoint[0];
		double y2 = destPoint[1];
		
		double relativeX = -(x2 - x1); //Made negative because the paths are made with the origin on the left
		double relativeY = y2 - y1;
		
		System.out.println("calculating angle...");
		
		System.out.println("p1(" + x1 + ", " + y1 + ")");
		
		System.out.println("relativeX" + relativeX);
		System.out.println("relativeY" + relativeY);
		
		double angle = Math.toDegrees(Math.atan2(relativeY, relativeX));
		System.out.println("Finished with angle of " + angle);

		
		
		return angle;
	}
	
	
	public void endAdjustment() {
		getPIDController().disable();
	}
	
	//Get the yaw angle of robot
	public double getPigeonAngle(){
		double[] yawPitchRoll = new double[3];
		Robot.pigeon.getYawPitchRoll(yawPitchRoll);
		return yawPitchRoll[0] % 360.;
	}
	
	///Get avg encoder position
	public double getEncPosition() {
		return (getLeftEncPosition() + getRightEncPosition()) / 2.;
	}
	
	//Set PID gains based on SmartDashboard input
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
	
	public double getEncVelocity(String side){
		double velTicks = 0;
		switch(side){
		case "Left":
			velTicks = -middleLeftMotor.getSensorCollection().getQuadratureVelocity();
			break;
		case "Right":
			velTicks = middleRightMotor.getSensorCollection().getQuadratureVelocity();
			break;
		case "Average":
			return (getEncVelocity("Left") + getEncVelocity("Right") / 2);
		}
		velTicks /= 10.;
		velTicks *= ENC_WHEEL_RATIO;
		
		return velTicks;
	}
	
	public double applyDriveCurve(double raw) {	
		if(raw < 0) {
			return -Math.pow(raw, 2.4);
		}
		return Math.pow(raw, 2.4);
	}
	
}