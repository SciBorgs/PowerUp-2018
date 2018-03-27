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

	public static enum ControllerType {
		JOYSTICK, XBOX;
	}

	public static ControllerType controllerType = ControllerType.XBOX;
	public static Joystick leftJoystick = new Joystick(PortMap.JOYSTICK_LEFT);
	public static Joystick rightJoystick = new Joystick(PortMap.JOYSTICK_RIGHT);
	public static XboxController xbox = new XboxController(PortMap.XBOX);

	public static Button windClimber = new JoystickButton(xbox, PortMap.XBOX_X);
	public static Button unwindClimber = new JoystickButton(xbox, PortMap.XBOX_BACK);
	public static Button stopClimbing = new JoystickButton(xbox, PortMap.XBOX_B);
		
	public static Button tiltClimber = new JoystickButton(xbox, PortMap.XBOX_A);
	public static Button deployClimber = new JoystickButton(xbox, PortMap.XBOX_START);

	public static Button shiftGear = new JoystickButton(xbox, PortMap.XBOX_STICK_RIGHT_BUTTON);

	public static Button actuateIntake = new JoystickButton(xbox, PortMap.XBOX_BUMPER_RIGHT);
	public static Button tiltIntake = new JoystickButton(rightJoystick, PortMap.JOYSTICK_TRIGGER);
	public static Button increaseLiftHeight = new JoystickButton(rightJoystick, PortMap.JOYSTICK_LEFT_BUTTON);
	public static Button decreaseLiftHeight = new JoystickButton(rightJoystick, PortMap.JOYSTICK_RIGHT_BUTTON);

	public OI() {

		if (controllerType == ControllerType.XBOX) {
			actuateIntake.toggleWhenPressed(new ToggleArmCommand());
			shiftGear.toggleWhenActive(new ToggleGearCommand());
			tiltIntake.whenPressed(new TiltIntakeCommand());

			tiltClimber.toggleWhenActive(new ToggleClimbCommand());
			deployClimber.toggleWhenActive(new DeployClimberCommand());
			increaseLiftHeight.whenPressed(new ChangeLiftHeightCommand("up"));

			decreaseLiftHeight.whenPressed(new ChangeLiftHeightCommand("down"));
			

		}
	}

}