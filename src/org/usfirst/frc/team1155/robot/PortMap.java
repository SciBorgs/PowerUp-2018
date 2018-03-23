package org.usfirst.frc.team1155.robot;

public class PortMap {
		
	//*****************JOYSTICKS*****************//

	public static final int JOYSTICK_LEFT = 1;
	public static final int JOYSTICK_RIGHT = 0;
	
	public static final int JOYSTICK_TRIGGER = 1;
	public static final int JOYSTICK_CENTER_BUTTON = 2;
	public static final int JOYSTICK_LEFT_BUTTON = 3;
	public static final int JOYSTICK_RIGHT_BUTTON = 4;
	
	public static final int[][] JOYSTICK_BUTTON_MATRIX_LEFT = {
															   {5, 6, 7},
															   {10, 9, 8}
															  };
	
	public static final int[][] JOYSTICK_BUTTON_MATRIX_RIGHT = {
															   {13, 12, 11},
															   {14, 15, 16}
															  };
	
	//*****************XBOX*****************//

	public static final int XBOX = 2;

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
	
	// Talon 1 is bad 3/22/18: Stuck on 0.00 current output
	// Talon 2 is bad 3/22/18: Stuck on 0.125 current output
	
	public static final int DRIVE_FRONT_LEFT_TALON = 6;
	public static final int DRIVE_MIDDLE_LEFT_TALON = 7;
	public static final int DRIVE_BACK_LEFT_TALON = 8;
	
	public static final int DRIVE_FRONT_RIGHT_TALON = 16;
	public static final int DRIVE_MIDDLE_RIGHT_TALON = 15;
	public static final int DRIVE_BACK_RIGHT_TALON = 14;
	
	public static final int[] GEAR_SHIFTER_SOLENOID = {6,7};

	//*******************LIFT********************//	
	
	public static final int LIFT_LEFT_TALON = 3;
	public static final int LIFT_LEFT_ENCODER_TALON = 4;
	
	public static final int LIFT_RIGHT_TALON = 11;
	public static final int LIFT_RIGHT_ENCODER_TALON = 10;
	
	//*****************INTAKE*******************//	
	
//	public static final int INTAKE_LEFT_TALON = 3;
//	public static final int INTAKE_RIGHT_TALON = 11;
	
	public static final int INTAKE_ARM_LEFT_TALON = 2;
	public static final int INTAKE_ARM_RIGHT_TALON = 12;
	
	public static final int[] INTAKE_ARM_SOLENOID = {4,5};//{0, 1};
	public static final int[] INTAKE_TILT_SOLENOID = {0,1};//{0, 1};
	
	public static final int[] INTAKE_ULTRASONIC = {1, 0}; //1 = input, 0 = output

	
	//*****************CLIMBER******************//	
	
	public static final int[] CLIMB_SOLENOID = {2, 3};
	
	public static final int LEFT_CLIMB_TALON = 5;
	public static final int RIGHT_CLIMB_TALON = 13;
	public static final int TILT_CLIMB_SERVO_LEFT = 0;
	public static final int TILT_CLIMB_SERVO_RIGHT = 1;

}