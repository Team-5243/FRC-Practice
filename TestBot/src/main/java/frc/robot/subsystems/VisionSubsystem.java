/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase {
  private NetworkTable networkTable = NetworkTableInstance.getDefault().getTable("limelight");
  private Pose2d poseOffset;

  /**
   * Creates a new VisionSubsystem.
   */
  public VisionSubsystem() {
    setPoseOffset(new Pose2d());
    System.out.println(networkTable == null);
  }

  @Override
  public void periodic() {
    setPoseOffset(new Pose2d(translationOffset(), new Rotation2d()));
  }

  public Translation2d translationOffset() {
    return new Translation2d(networkTable.getEntry("tx").getDouble(0d), networkTable.getEntry("ty").getDouble(0d));
  }

  public void setPoseOffset(Pose2d poseOffset) {
    this.poseOffset = poseOffset;
  }

  public Pose2d getPoseOffset() {
    return poseOffset;
  }
}
