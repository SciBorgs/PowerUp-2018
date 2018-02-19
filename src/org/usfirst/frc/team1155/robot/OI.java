package org.usfirst.frc.team1155.robot;

import org.usfirst.frc.team1155.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public static Joystick leftJoystick = new Joystick(PortMap.JOYSTICK_LEFT);
	public static Joystick rightJoystick = new Joystick(PortMap.JOYSTICK_RIGHT);
	public static XboxController xbox = new XboxController(PortMap.XBOX);
		
	public static Button extendClimber = new JoystickButton(xbox, PortMap.XBOX_B);
	public static Button stopClimber = new JoystickButton(xbox, PortMap.XBOX_X);
	
//	public static Button gearShifter = new JoystickButton(leftJoystick, 2);
	public static Button xboxGearShifter = new JoystickButton(xbox, PortMap.XBOX_STICK_RIGHT_BUTTON);

	//public static Button intakeArmControl = new JoystickButton(rightJoystick, 1);
	public static Button xboxIntakeArmControl = new JoystickButton(xbox, PortMap.XBOX_BUMPER_RIGHT);
	
	public OI() {	
		xboxIntakeArmControl.toggleWhenPressed(new ToggleArmCommand());
		xboxGearShifter.toggleWhenActive(new ToggleGearCommand());
	}
	
}