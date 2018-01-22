package org.usfirst.frc.team1155.robot.subsystems;

import org.usfirst.frc.team1155.robot.PortMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CarriageSubsystem extends Subsystem{

	public TalonSRX forwardLeftCarriageMotor, forwardRightCarriageMotor, 
	reverseLeftCarriageMotor, reverseRightCarriageMotor;
	public DoubleSolenoid leftCarriageSolenoid, rightCarriageSolenoid;
	
	public void initDefaultCommand() {

		forwardLeftCarriageMotor = new TalonSRX(PortMap.CARRIAGE_FORWARD_LEFT_TALON);
		forwardRightCarriageMotor = new TalonSRX(PortMap.CARRIAGE_FORWARD_RIGHT_TALON);
		reverseLeftCarriageMotor = new TalonSRX(PortMap.CARRIAGE_REVERSE_LEFT_TALON);
		reverseRightCarriageMotor = new TalonSRX(PortMap.CARRIAGE_REVERSE_RIGHT_TALON);
		
		armSolenoid = new DoubleSolenoid(PortMap.CARRIAGE_SOLENOID_FIRST[0], PortMap.CARRIAGE_SOLENOID_FIRST[1]);
		rightCarriageSolenoid = new DoubleSolenoid(PortMap.CARRIAGE_SOLENOID_SECOND[0], PortMap.CARRIAGE_SOLENOID_SECOND[1]);
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		stop();
	}	
	
	public void stop() {
		forwardLeftCarriageMotor.set(ControlMode.PercentOutput, 0);
		forwardRightCarriageMotor.set(ControlMode.PercentOutput, 0);
		reverseLeftCarriageMotor.set(ControlMode.PercentOutput, 0);
		reverseRightCarriageMotor.set(ControlMode.PercentOutput, 0);
	}
	
	public void setSpeed(double speed){
		forwardLeftCarriageMotor.set(ControlMode.PercentOutput, speed);
		forwardRightCarriageMotor.set(ControlMode.PercentOutput, speed);
		reverseLeftCarriageMotor.set(ControlMode.PercentOutput, -speed);
		reverseRightCarriageMotor.set(ControlMode.PercentOutput, -speed);
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
