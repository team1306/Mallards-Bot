package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IndexerConstants;

public class Indexer extends SubsystemBase {
    private final SparkMax MOTOR;

    private double targetSpeed;

    public Indexer() {
        MOTOR = new SparkMax(IndexerConstants.INDEXER_MOTOR_ID, MotorType.kBrushed);
        MOTOR.setCANTimeout(250);
    }

    @Override
    public void periodic() {
        MOTOR.set(targetSpeed);
    }

    public void setTargetSpeed(double targetSpeed) {
        this.targetSpeed = targetSpeed;
    }
}
