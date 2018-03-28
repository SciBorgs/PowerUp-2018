package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.PortMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class LiftSubsystem extends PIDSubsystem{

	public LiftSubsystem(double p, double i, double d) {
		super("Lift", p, i, d);
		// TODO Auto-generated constructor stub
	}

	public TalonSRX leftLiftMotor, rightLiftMotor, leftLiftEncoderMotor, rightLiftEncoderMotor;
	public TalonSRX[] allTalons;
	
	public DigitalInput limitSwitch; 
	
	public final double LIFT_SPEED = .6;
	public final double LIFT_SPEED_ADJUST = .3;
	public final double TICKS_TO_TOP = 45000;//58070;
	public final double TICKS_TO_SWITCH_HEIGHT = 8500;//7986;//22420;
	public final double TICKS_AT_BOTTOM = -9500;
	public final double TICKS_AT_MID = 26000;

	public final double MAX_TICK_DIFFERENCE = 200;
	public LiftTarget liftTarget = LiftTarget.Bottom;
	
	public static enum LiftTarget {
		Bottom, SwitchHeight, ScaleHeight;
	}	
	
	public void initDefaultCommand() {

		leftLiftMotor = new TalonSRX(PortMap.LIFT_LEFT_TALON);
		rightLiftMotor = new TalonSRX(PortMap.LIFT_RIGHT_TALON);
		leftLiftEncoderMotor = new TalonSRX(PortMap.LIFT_LEFT_ENCODER_TALON);
		rightLiftEncoderMotor = new TalonSRX(PortMap.LIFT_RIGHT_ENCODER_TALON);
		
		allTalons = new TalonSRX[] {leftLiftMotor, rightLiftMotor, leftLiftEncoderMotor, rightLiftEncoderMotor};

		limitSwitch = new DigitalInput(PortMap.LIMIT_SWITCH);
		
		leftLiftMotor.setNeutralMode(NeutralMode.Brake);
		rightLiftMotor.setNeutralMode(NeutralMode.Brake);
		leftLiftEncoderMotor.setNeutralMode(NeutralMode.Brake);
		rightLiftEncoderMotor.setNeutralMode(NeutralMode.Brake);
		
//		frontRightMotor.configContinuousCurrentLimit(CONTCURRENTLIMIT, 0);
//		frontRightMotor.configPeakCurrentLimit(PEAKCURRENTLIMIT, 0);
//		frontRightMotor.configPeakCurrentDuration(PEAKCURRENTDURATION, 0);
//		frontRightMotor.enableCurrentLimit(true);
		
		getPIDController().setInputRange(TICKS_AT_BOTTOM, TICKS_TO_TOP);
		getPIDController().setOutputRange(-0.8, 0.8);
		getPIDController().setContinuous(false);
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		stop();
	}	
	
	public void stop() {
		leftLiftMotor.set(ControlMode.PercentOutput, 0);
		rightLiftMotor.set(ControlMode.PercentOutput, 0);
		leftLiftEncoderMotor.set(ControlMode.PercentOutput, 0);
		rightLiftEncoderMotor.set(ControlMode.PercentOutput, 0);
	}
	
	public void setSpeed(double speed){
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
	
	public int getEncoderDifference(){
		return getLeftEncPos() - getRightEncPos();
	}
	
	public int getLeftEncPos() {
		return leftLiftEncoderMotor.getSensorCollection().getQuadraturePosition();
	}
	
	public int getRightEncPos() {
		return -rightLiftEncoderMotor.getSensorCollection().getQuadraturePosition();
	}
	
	public void resetEncoders() {
		if(rightLiftEncoderMotor.getSensorCollection() != null)
			rightLiftEncoderMotor.getSensorCollection().setQuadraturePosition(0, 0);
		leftLiftEncoderMotor.getSensorCollection().setQuadraturePosition(0, 0);
	}

	@Override
	protected double returnPIDInput() {
//		System.out.println( (getLeftEncPos() + getRightEncPos()) / 2.);
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
		switch(liftTarget) {
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
	
	public boolean isAboveMid() {
		return getAvgEncPos() > TICKS_AT_MID;
	}
	
	public void endAdjustment() {
		getPIDController().disable();
	}
	
	public boolean getLimit() {
		return limitSwitch.get();
	}
}
