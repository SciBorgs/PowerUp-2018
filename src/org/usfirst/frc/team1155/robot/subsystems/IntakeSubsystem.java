package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.PortMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSubsystem extends Subsystem{

	public TalonSRX leftIntakeMotor, rightIntakeMotor;
	public DoubleSolenoid intakePiston;
	
	public void initDefaultCommand() {

		leftIntakeMotor = new TalonSRX(PortMap.INTAKE_LEFT_TALON);
		rightIntakeMotor = new TalonSRX(PortMap.INTAKE_RIGHT_TALON);

		//intakePiston = new DoubleSolenoid(PortMap.INTAKE_SOLENOID_LEFT, PortMap.INTAKE_SOLENOID_RIGHT);
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
		rightIntakeMotor.set(ControlMode.PercentOutput, -speed);
	}
	public void extendPiston() {
		//intakePiston.set(DoubleSolenoid.Value.kForward);
	}

	public void retractPiston() {
		//intakePiston.set(DoubleSolenoid.Value.kReverse);
	}	
	
}
