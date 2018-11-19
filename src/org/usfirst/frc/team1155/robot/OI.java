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

	// These three instantiations are deprecated 4/7/18
	public static Button xboxWindClimber = new JoystickButton(xbox, PortMap.XBOX_X);
	public static Button xboxUnwindClimber = new JoystickButton(xbox, PortMap.XBOX_BACK);
	public static Button xboxStopClimbing = new JoystickButton(xbox, PortMap.XBOX_B);

	// XBOX CONTROLS	
	public static Button xboxTiltClimber = new JoystickButton(xbox, PortMap.XBOX_A);
	public static Button xboxDeployClimber = new JoystickButton(xbox, PortMap.XBOX_START);

	public static Button xboxShiftGear = new JoystickButton(xbox, PortMap.XBOX_STICK_RIGHT_BUTTON);

	public static Button xboxActuateIntake = new JoystickButton(xbox, PortMap.XBOX_BUMPER_RIGHT);
	public static Button xboxTiltIntake = new JoystickButton(xbox, PortMap.XBOX_SOMETHING);
	public static Button xboxIncreaseLiftHeight = new JoystickButton(xbox, PortMap.XBOX_SOMETHING);
	public static Button xboxDecreaseLiftHeight = new JoystickButton(xbox, PortMap.XBOX_SOMETHING);
	
	// JOYSTICK CONTROLS
	public static Button joystickTiltClimber = new JoystickButton(rightJoystick, PortMap.JOYSTICK_RIGHT_BUTTON);
	public static Button joystickDeployClimber = new JoystickButton(leftJoystick, PortMap.JOYSTICK_LEFT_BUTTON);

	public static Button joystickShiftGear = new JoystickButton(leftJoystick, PortMap.JOYSTICK_LEFT);

	public static Button joystickActuateIntake = new JoystickButton(rightJoystick, PortMap.JOYSTICK_CENTER_BUTTON);
	public static Button joystickTiltIntake = new JoystickButton(leftJoystick, PortMap.JOYSTICK_TRIGGER);
	public static Button joystickIncreaseLiftHeight = new JoystickButton(rightJoystick, PortMap.JOYSTICK_LEFT_BUTTON);
		public static Button joystickDecreaseLiftHeight = new JoystickButton(leftJoystick, PortMap.JOYSTICK_RIGHT_BUTTON);

	
	public OI() {

		if (controllerType == ControllerType.XBOX) {
			xboxActuateIntake.toggleWhenPressed(new ToggleArmCommand());
			xboxShiftGear.toggleWhenActive(new GearShiftCommand());
			xboxTiltIntake.whenPressed(new TiltIntakeCommand());

			xboxTiltClimber.toggleWhenActive(new ExtendClimbServosCommand());
			xboxDeployClimber.toggleWhenActive(new DeployClimberCommand());
			joystickIncreaseLiftHeight.whenPressed(new ChangeLiftHeightCommand("up"));

			joystickDecreaseLiftHeight.whenPressed(new ChangeLiftHeightCommand("down"));

		}
		else if (controllerType == ControllerType.JOYSTICK) {
			joystickActuateIntake.toggleWhenPressed(new ToggleArmCommand());
			joystickShiftGear.toggleWhenActive(new GearShiftCommand());
			xboxTiltIntake.whenPressed(new TiltIntakeCommand());

			joystickTiltClimber.toggleWhenActive(new ExtendClimbServosCommand());
			joystickDeployClimber.toggleWhenActive(new DeployClimberCommand());
			joystickIncreaseLiftHeight.whenPressed(new ChangeLiftHeightCommand("up"));

			joystickDecreaseLiftHeight.whenPressed(new ChangeLiftHeightCommand("down"));	
		}
	}

}




