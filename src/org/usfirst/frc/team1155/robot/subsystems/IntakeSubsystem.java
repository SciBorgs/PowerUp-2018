package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.PortMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSubsystem extends Subsystem{

	public TalonSRX leftIntakeMotor, rightIntakeMotor;
	public DoubleSolenoid intakePistonFirst, intakePistonSecond;
	
	public void initDefaultCommand() {

		leftIntakeMotor = new TalonSRX(PortMap.INTAKE_LEFT_TALON);
		rightIntakeMotor = new TalonSRX(PortMap.INTAKE_RIGHT_TALON);

		intakePistonFirst = new DoubleSolenoid(PortMap.INTAKE_SOLENOID_LEFT[0], PortMap.INTAKE_SOLENOID_LEFT[1]);
		intakePistonSecond = new DoubleSolenoid(PortMap.INTAKE_SOLENOID_RIGHT[0], PortMap.INTAKE_SOLENOID_RIGHT[1]);
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		stop();
	}	
	
	public void stop() {
		leftIntakeMotor.set(ControlMode.PercentOutput, 0);
		rightIntakeMotor.set(ControlMode.PercentOutput, 0);
	}
	
	public void setSpeed(double speed){
		leftIntakeMotor.set(ControlMode.PercentOutput, speed);
		rightIntakeMotor.set(ControlMode.PercentOutput, speed);
	}
	public void extendPiston() {
		intakePistonFirst.set(DoubleSolenoid.Value.kForward);
		intakePistonSecond.set(DoubleSolenoid.Value.kForward);
	}

	public void retractPiston() {
		intakePistonFirst.set(DoubleSolenoid.Value.kReverse);
		intakePistonSecond.set(DoubleSolenoid.Value.kReverse);
	}	
	
}
