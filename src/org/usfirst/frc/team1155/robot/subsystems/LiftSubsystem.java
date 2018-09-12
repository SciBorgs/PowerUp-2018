package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.PortMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class LiftSubsystem extends PIDSubsystem {

	public LiftSubsystem(double p, double i, double d) {
		super("Lift", p, i, d);

		leftLiftMotor = new TalonSRX(PortMap.LIFT_LEFT_TALON);
		rightLiftMotor = new TalonSRX(PortMap.LIFT_RIGHT_TALON);
		leftLiftEncoderMotor = new TalonSRX(PortMap.LIFT_LEFT_ENCODER_TALON);
		rightLiftEncoderMotor = new TalonSRX(PortMap.LIFT_RIGHT_ENCODER_TALON);

		allTalons = new TalonSRX[] { leftLiftMotor, rightLiftMotor, leftLiftEncoderMotor, rightLiftEncoderMotor };

		//Sets default mode of motors to be braked
		leftLiftMotor.setNeutralMode(NeutralMode.Brake);
		rightLiftMotor.setNeutralMode(NeutralMode.Brake);
		leftLiftEncoderMotor.setNeutralMode(NeutralMode.Brake);
		rightLiftEncoderMotor.setNeutralMode(NeutralMode.Brake);

		//Voltage Ramping
		leftLiftMotor.configOpenloopRamp(0.5, 50);
		rightLiftMotor.configOpenloopRamp(0.5, 50);

		//Sets bounds for PID values
		getPIDController().setInputRange(TICKS_AT_BOTTOM, TICKS_TO_TOP);
		getPIDController().setOutputRange(-0.8, 0.8);
		getPIDController().setContinuous(false);
		stopMovement();
	}

	
	public TalonSRX leftLiftMotor, rightLiftMotor, leftLiftEncoderMotor, rightLiftEncoderMotor;
	public TalonSRX[] allTalons;

	public DigitalInput limitSwitch;

	public final double LIFT_SPEED = .6;
	public final double LIFT_SPEED_ADJUST = .3;
	public final int TICKS_TO_TOP = 45000;// 58070;
	public final int TICKS_TO_SWITCH_HEIGHT = 9500;// 7986;//22420;
	public final int TICKS_AT_BOTTOM = -9500;
	public final int TICKS_AT_MID = 26000;

	public final double MAX_TICK_DIFFERENCE = 200;
	public LiftTarget liftTarget = LiftTarget.Bottom;

	public static enum LiftTarget {
		Bottom, SwitchHeight, ScaleHeight;
	}
	
	public void initDefaultCommand() {

	}

	public void stopMovement() {
		leftLiftMotor.set(ControlMode.PercentOutput, 0);
		rightLiftMotor.set(ControlMode.PercentOutput, 0);
		leftLiftEncoderMotor.set(ControlMode.PercentOutput, 0);
		rightLiftEncoderMotor.set(ControlMode.PercentOutput, 0);
	}

	public void setSpeed(double speed) {		
		leftLiftMotor.set(ControlMode.PercentOutput, -speed);
		leftLiftEncoderMotor.set(ControlMode.PercentOutput, -speed);
		rightLiftMotor.set(ControlMode.PercentOutput, speed);
		rightLiftEncoderMotor.set(ControlMode.PercentOutput, speed);
	}

	public void setLeftSideSpeed(double speed) {
		leftLiftMotor.set(ControlMode.PercentOutput, -speed);
		leftLiftEncoderMotor.set(ControlMode.PercentOutput, -speed);
	}

	public void setRightSideSpeed(double speed) {
		rightLiftMotor.set(ControlMode.PercentOutput, speed);
		rightLiftEncoderMotor.set(ControlMode.PercentOutput, speed);
	}

	public int getEncoderDifference() {
		return getLeftEncPos() - getRightEncPos();
	}

	public int getLeftEncPos() {
		try {
			return leftLiftEncoderMotor.getSensorCollection().getQuadraturePosition();
		} catch (NullPointerException e) {
			System.out.println("LiftSubsystem - resetEncoders - YOU HAVE A NULL POINTER WITH ONE OF THE LIFT MOTORS");
			System.out.println(e);
		}
		return 0;
	}

	public int getRightEncPos() {
		try {
			return -rightLiftEncoderMotor.getSensorCollection().getQuadraturePosition();
		} catch (NullPointerException e) {
			System.out.println("LiftSubsystem - resetEncoders - YOU HAVE A NULL POINTER WITH ONE OF THE LIFT MOTORS");
			System.out.println(e);
		}
		return 0;

	}

	public void resetEncoders() {
		try {
			if (rightLiftEncoderMotor.getSensorCollection() != null)
				rightLiftEncoderMotor.getSensorCollection().setQuadraturePosition(0, 0);
			leftLiftEncoderMotor.getSensorCollection().setQuadraturePosition(0, 0);
		} catch (NullPointerException e) {
			System.out.println("LiftSubsystem - resetEncoders - YOU HAVE A NULL POINTER WITH ONE OF THE LIFT MOTORS");
			System.out.println(e);
		}

	}

	
	@Override
	protected double returnPIDInput() {
		return (getLeftEncPos() + getRightEncPos()) / 2.;
	}

	@Override
	protected void usePIDOutput(double output) {
		output *= .5;
		System.out.println("usee pid output: " + output);
		setSpeed(-output);
	}

	
	public void startAdjustment() {
		getPIDController().setPercentTolerance(10.0);
		switch (liftTarget) {
		case Bottom:
			setSetpoint(TICKS_AT_BOTTOM);
			break;
		case SwitchHeight:
			System.out.println("Setting to switch height");
			setSetpoint(TICKS_TO_SWITCH_HEIGHT);
			break;
		case ScaleHeight:
			setSetpoint(TICKS_TO_TOP);
			break;
		}
		enable();
	}

	public int getAvgEncPos() {
		return (int) ((getLeftEncPos() + getRightEncPos()) / 2.);
	}

	//Checks if lift is extending
	public boolean isAboveMid() {
		return getAvgEncPos() > TICKS_AT_MID;
	}

	public void endAdjustment() {
		getPIDController().disable();
	}

	public boolean getLimit() {
		return false;
	}

	public void zeroEncoders (int encPos) {
		leftLiftEncoderMotor.getSensorCollection().setQuadraturePosition(encPos, 0);
		rightLiftEncoderMotor.getSensorCollection().setQuadraturePosition(-encPos, 0);

	}

}
