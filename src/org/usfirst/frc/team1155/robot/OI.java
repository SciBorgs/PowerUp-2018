package org.usfirst.frc.team1155.robot;

import org.usfirst.frc.team1155.robot.commands.*;
import org.usfirst.frc.team1155.robot.commands.autoCommands.AutoLiftCommand;
import org.usfirst.frc.team1155.robot.commands.autoCommands.AutonomousCommandGroup;
import org.usfirst.frc.team1155.robot.subsystems.LiftSubsystem.LiftTarget;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public static enum ControllerType{
		JOYSTICK, XBOX;
	}
	public static ControllerType controllerType = ControllerType.XBOX;
	public static Joystick leftJoystick = new Joystick(PortMap.JOYSTICK_LEFT);
	public static Joystick rightJoystick = new Joystick(PortMap.JOYSTICK_RIGHT);
	public static XboxController xbox = new XboxController(PortMap.XBOX);
			
	public static Button extendClimber = new JoystickButton(xbox, PortMap.XBOX_B);
	public static Button stopClimber = new JoystickButton(xbox, PortMap.XBOX_X);
	
	public static Button gearShifter = new JoystickButton(leftJoystick, PortMap.JOYSTICK_CENTER_BUTTON);
	public static Button xboxGearShifter = new JoystickButton(xbox, PortMap.XBOX_STICK_RIGHT_BUTTON);
	public static Button testDriveDist = new JoystickButton(xbox, PortMap.XBOX_BACK);
	//public static Button intakeArmControl = new JoystickButton(rightJoystick, 1);
	public static Button xboxIntakeArmControl = new JoystickButton(xbox, PortMap.XBOX_STICK_LEFT_BUTTON);
	public static Button tiltIntake = new JoystickButton(xbox, PortMap.XBOX_Y);
	public static Button tiltClimber = new JoystickButton(xbox, PortMap.XBOX_A);
	public static Button deployClimber = new JoystickButton(xbox, PortMap.XBOX_START);
	public static Button increaseLiftHeight = new JoystickButton(xbox, PortMap.XBOX_BUMPER_RIGHT);
	public static Button decreaseLiftHeight = new JoystickButton(xbox, PortMap.XBOX_BUMPER_LEFT);
	public OI() {	
		
		if(controllerType == ControllerType.XBOX) {
			xboxIntakeArmControl.toggleWhenPressed(new ToggleArmCommand());
			xboxGearShifter.toggleWhenActive(new ToggleGearCommand());
			tiltIntake.whenPressed(new TiltIntakeCommand());
			
			tiltClimber.toggleWhenActive(new ToggleClimbCommand());
			deployClimber.toggleWhenActive(new DeployClimberCommand());
			testDriveDist.whenPressed(new AutoLiftCommand(LiftTarget.ScaleHeight));
			
			
			
//			climb
		}else{
			gearShifter.toggleWhenActive(new ToggleGearCommand());
			testDriveDist.whenPressed(new DriveDistanceCommand(10));
		}
	}
	
}