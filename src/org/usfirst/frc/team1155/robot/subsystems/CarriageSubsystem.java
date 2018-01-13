package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.PortMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CarriageSubsystem extends Subsystem{

	public TalonSRX leftCarriageMotor, rightCarriageMotor;
	public DoubleSolenoid leftCarriageSolenoid, rightCarriageSolenoid;
	
	public void initDefaultCommand() {

		leftCarriageMotor = new TalonSRX(PortMap.LIFT_LEFT_TALON);
		rightCarriageMotor = new TalonSRX(PortMap.LIFT_RIGHT_TALON);

		leftCarriageSolenoid = new DoubleSolenoid(PortMap.CARRIAGE_SOLENOID_FIRST_LEFT, PortMap.CARRIAGE_SOLENOID_FIRST_RIGHT);
		rightCarriageSolenoid = new DoubleSolenoid(PortMap.CARRIAGE_SOLENOID_SECOND_LEFT, PortMap.CARRIAGE_SOLENOID_SECOND_RIGHT);
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		stop();
	}	
	
	public void stop() {
		leftCarriageMotor.set(ControlMode.PercentOutput, 0);
		rightCarriageMotor.set(ControlMode.PercentOutput, 0);
	}
	
	public void setSpeed(double speed){
		leftCarriageMotor.set(ControlMode.PercentOutput, speed);
		rightCarriageMotor.set(ControlMode.PercentOutput, speed);
	}

	public void extendPiston() {
		leftCarriageSolenoid.set(DoubleSolenoid.Value.kForward);
		rightCarriageSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	public void retractPiston() {
		leftCarriageSolenoid.set(DoubleSolenoid.Value.kReverse);
		rightCarriageSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
}
