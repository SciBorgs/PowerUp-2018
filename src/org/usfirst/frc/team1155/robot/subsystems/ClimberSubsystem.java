package org.usfirst.frc.team1155.robot.subsystems;
import org.usfirst.frc.team1155.robot.PortMap;
import edu.wpi.first.wpilibj.TalonSRX;

public class ClimberSubsytem extends Subsystem {
	
	public TalonSRX leftTalon, rightTalon, angleTalon;
	
	public double speedAngle = 1.0;
	
	public void initDefaultCommand() {
		leftTalon = new TalonSRX(PortMap.LEFT_CLIMB_TALON);
		rightTalon = new TalonSRX(PortMap.RIGHT_CLIMB_TALON);
		angleTalon = new TalonSRX(PortMap.ANGLE_CLIMB_TALON);
	}
	
	public void setExtensionSpeed(double speed) {
		leftTalon.set(ControlMode.PercentOutput,speed);
		rightTalon.set(ControlMode.PercentOutput,speed);
	}
	
	public void changeAngle(int direction) {
		angleTalon.set(ControlMode.PrecentOutput,speedAngle * direction);
	}
}
