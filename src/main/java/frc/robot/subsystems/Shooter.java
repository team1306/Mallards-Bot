// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

/** Class to run the rollers over CAN */
public class Shooter extends SubsystemBase {
  private final TalonFX shooterMotor1;
  private final TalonFX shooterMotor2;

  private double shooterSpeed;

  public Shooter() {
    // Set up the roller motor as a brushed motor
    shooterMotor1 = new TalonFX(ShooterConstants.SHOOTER_MOTOR_ID_1);
    shooterMotor2 = new TalonFX(ShooterConstants.SHOOTER_MOTOR_ID_2);

    shooterMotor2.setControl(new Follower(shooterMotor1.getDeviceID(), false));

    TalonFXConfiguration config = new TalonFXConfiguration();
    config.CurrentLimits.SupplyCurrentLimitEnable = true;
    config.CurrentLimits.SupplyCurrentLimit = 60;
    config.CurrentLimits.StatorCurrentLimit = 60;
    config.CurrentLimits.SupplyCurrentLowerLimit = 60;
    config.CurrentLimits.StatorCurrentLimitEnable = true;

    shooterMotor1.getConfigurator().apply(config.CurrentLimits);
    shooterMotor2.getConfigurator().apply(config.CurrentLimits);
    
    shooterMotor1.setNeutralMode(NeutralModeValue.Coast);
    shooterMotor2.setNeutralMode(NeutralModeValue.Coast);

  }

  @Override
  public void periodic() {
    shooterMotor1.set(shooterSpeed);
  }

  public void setSpeed(double speed) {
    this.shooterSpeed = speed;
  }

}
