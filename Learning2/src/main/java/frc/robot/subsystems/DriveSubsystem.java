/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;

/**
 * Add your docs here.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  CANSparkMax frontLeft, frontRight, backLeft, backRight;

  public DriveSubsystem() {
    frontLeft = new CANSparkMax(Constants.FRONTLEFT, MotorType.kBrushless);
    frontRight = new CANSparkMax(Constants.FRONTRIGHT, MotorType.kBrushless);
    backLeft = new CANSparkMax(Constants.BACKLEFT, MotorType.kBrushless);
    backRight = new CANSparkMax(Constants.BACKRIGHT, MotorType.kBrushless);

    backLeft.follow(frontLeft);
    backRight.follow(frontRight);
  }

  public void setMotors(double rightSpeed, double leftSpeed) {
    frontLeft.set(rightSpeed);
    frontRight.set(leftSpeed);
  }

  @Override
  public void initDefaultCommand() {
    
  }
}
