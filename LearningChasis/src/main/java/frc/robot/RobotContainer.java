/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final FlywheelSubsystem m_flywheelSubsystem = new FlywheelSubsystem();

  private final XboxController m_controller = new XboxController(Constants.CONTROLLER);

  private final TankDriveCommand m_tankDriveCommand = new TankDriveCommand(m_driveSubsystem, m_controller);
  private final DriveCommand m_driveCommand = new DriveCommand(m_driveSubsystem, m_controller);
  private final FlywheelCommand m_flywheelCommand = new FlywheelCommand(m_flywheelSubsystem, m_controller);
  

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }

  public XboxController getController() {
    return m_controller;
  }

  public DriveSubsystem getDriveSubsystem() {
    return m_driveSubsystem;
  }
  public DriveCommand getDriveCommand() {
    return m_driveCommand;
  }
  public FlywheelSubsystem getFlywheelSubsystem() {
    return m_flywheelSubsystem;
  }
  public FlywheelCommand getFlywheelCommand() {
    return m_flywheelCommand;
  }
}
