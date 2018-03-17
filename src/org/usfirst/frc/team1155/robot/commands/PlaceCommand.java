package org.usfirst.frc.team1155.robot.commands;

import org.usfirst.frc.team1155.robot.OI;
import org.usfirst.frc.team1155.robot.PortMap;
import org.usfirst.frc.team1155.robot.Robot;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PlaceCommand extends Command{
	
	private GenericHID controller;
	
	public PlaceCommand(GenericHID controller) {
		// Use requires() here to declare subsystem dependencies
		this.controller = controller;
		// requires(Robot.intakeSubsystem);
	}
	
	public PlaceCommand() {
		
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double speed = SmartDashboard.getNumber("Intake Speed", 0.45);
		
		if(OI.controllerType == OI.ControllerType.JOYSTICK) {
			System.out.println("here");
			System.out.println(OI.leftJoystick.getRawButton(PortMap.JOYSTICK_TRIGGER));
			if(OI.leftJoystick.getRawButton(PortMap.JOYSTICK_TRIGGER)) {
				new PlaceIntakeCommand().start();
			}else if(OI.rightJoystick.getRawButton(PortMap.JOYSTICK_TRIGGER)) {
				new PlaceOutputCommand().start();

			}else {
				Robot.intakeSubsystem.stop();
			}
		}else {
//			
//			if (controller.getRawAxis(PortMap.XBOX_TRIGGER_RIGHT) != 0) {
//				new PlaceIntakeCommand(controller, speed).start();
//			} else if (controller.getRawAxis(PortMap.XBOX_TRIGGER_LEFT) != 0) {
//				new PlaceOutputCommand(controller, speed).start();
//			} else if (controller.getRawAxis(PortMap.XBOX_TRIGGER_LEFT) == 0 && controller.getRawAxis(PortMap.XBOX_TRIGGER_RIGHT) == 0){
//				Robot.intakeSubsystem.stop();
//			} else if (controller.getRawAxis(PortMap.XBOX_TRIGGER_LEFT) != 0 && controller.getRawAxis(PortMap.XBOX_TRIGGER_RIGHT) != 0){
//				Robot.intakeSubsystem.stop();
//			} else {
//				Robot.intakeSubsystem.stop();
//			}
			
			if(controller.getRawAxis(PortMap.XBOX_TRIGGER_LEFT) != 0 && controller.getRawAxis(PortMap.XBOX_TRIGGER_RIGHT) != 0) {
				Robot.intakeSubsystem.stop();
			}else if(controller.getRawAxis(PortMap.XBOX_TRIGGER_LEFT) != 0) {
				System.out.println("here");
				new PlaceIntakeCommand(controller, speed).start();
			}else if(controller.getRawAxis(PortMap.XBOX_TRIGGER_RIGHT) != 0) {
				new PlaceOutputCommand(controller, speed).start();
			}else {
				Robot.intakeSubsystem.stop();
			}
			
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.intakeSubsystem.setSpeed(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}