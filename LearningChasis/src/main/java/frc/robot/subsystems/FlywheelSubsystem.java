/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class FlywheelSubsystem extends SubsystemBase {

  CANSparkMax leftFly, rightFly;

  public FlywheelSubsystem() {
    leftFly = new CANSparkMax(Constants.LEFTFLY, MotorType.kBrushless);
    rightFly = new CANSparkMax(Constants.RIGHTFLY, MotorType.kBrushless);

    leftFly.follow(rightFly, true);
  }

  public void setFlywheels(boolean on) {
    if (on) {
      leftFly.set(1);
    }
  }

  public void stopFlywheels() {
    setFlywheels(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
