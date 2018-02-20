package org.usfirst.frc.team1155.robot;

public class ActualPortMap {
		
	//*****************JOYSTICKS*****************//

	public static final int JOYSTICK_LEFT = 0;
	public static final int JOYSTICK_RIGHT = 1;
	public static final int XBOX = 2;
	
	//*****************XBOX*****************//

	public static final int XBOX_A = 1;
	public static final int XBOX_B = 2;
	public static final int XBOX_X = 3;
	public static final int XBOX_Y = 4;
	
	public static final int XBOX_BUMPER_LEFT = 5;
	public static final int XBOX_BUMPER_RIGHT = 6;
	
	public static final int XBOX_BACK = 7;
	public static final int XBOX_START = 8;

	public static final int XBOX_STICK_LEFT_BUTTON = 9;
	public static final int XBOX_STICK_RIGHT_BUTTON = 10;

	public static final int XBOX_TRIGGER_LEFT = 2;
	public static final int XBOX_TRIGGER_RIGHT = 3;
	
	public static final int XBOX_LEFT_JOY_X = 0;
	public static final int XBOX_LEFT_JOY_Y = 1;

	public static final int XBOX_RIGHT_JOY_X = 4;
	public static final int XBOX_RIGHT_JOY_Y = 5;

	
	//*******************DRIVE*******************//
	
	public static final int DRIVE_FRONT_LEFT_TALON = 1;
	public static final int DRIVE_MIDDLE_LEFT_TALON = 2;
	public static final int DRIVE_BACK_LEFT_TALON = 3;
	
	public static final int DRIVE_FRONT_RIGHT_TALON = 9;
	public static final int DRIVE_MIDDLE_RIGHT_TALON = 10;
	public static final int DRIVE_BACK_RIGHT_TALON = 11;
	
	public static final int[] GEAR_SHIFTER_SOLENOID = {0,1};

	//*******************LIFT********************//	
	
	public static final int LIFT_LEFT_TALON = 4;
	public static final int LIFT_LEFT_ENCODER_TALON = 5;
	
	public static final int LIFT_RIGHT_TALON = 12;
	public static final int LIFT_RIGHT_ENCODER_TALON = 13;
	
	//*****************INTAKE*******************//	
	
	public static final int INTAKE_LEFT_TALON = 28;
	public static final int INTAKE_RIGHT_TALON = 26;
	
	public static final int INTAKE_ARM_LEFT_TALON = 31;
	public static final int INTAKE_ARM_RIGHT_TALON = 20;
	
	public static final int[] INTAKE_SOLENOID = {2,3};
	
	//*****************CLIMBER******************//	
	
	public static final int LEFT_CLIMB_TALON = 7;
	public static final int RIGHT_CLIMB_TALON = 6;
	
	public static final int TILT_CLIMB_SERVO_LEFT = 9;
	public static final int TILT_CLIMB_SERVO_RIGHT = 1;

}