// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Launch extends SubsystemBase {
  private WPI_VictorSPX feed = new WPI_VictorSPX(7);
  private TalonSRX bottom = new TalonSRX(10);
  private TalonSRX top = new TalonSRX(11);

  private boolean angleClose = true;

  double feedSpeed = 0.2;

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
  public void runFeed(){
    feed.set(ControlMode.PercentOutput, feedSpeed);
  }

  public void stopFeed(){
    feed.set(ControlMode.PercentOutput, 0.0);
  }

  public void setLaunchSpeed(double target){
    /* Get Talon/Victor's current output percentage */
    double motorOutput = bottom.getMotorOutputPercent();

    double curVelo = top.getSelectedSensorVelocity(Constants.kPIDLoopIdx);

    /* Velocity Closed Loop */

    /**
     * Convert 500 RPM to units / 100ms.
     * 4096 Units/Rev * 500 RPM / 600 100ms/min in either direction:
     * velocity setpoint is in units/100ms
     */
    double leftYstick = 1.0;
    double targetVelocity_UnitsPer100ms = leftYstick * target * 4096 / 600;
    /* 500 RPM in either direction */
    bottom.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);
    //top.set(ControlMode.Velocity, -targetVelocity_UnitsPer100ms);

    SmartDashboard.putNumber("Bottom Velocity [cur]", curVelo);
    SmartDashboard.putNumber("Bottom Velocity [tar]", targetVelocity_UnitsPer100ms);
  }

  public double getCurVelo(){
    return bottom.getSelectedSensorVelocity(Constants.kPIDLoopIdx) * 600 / 4096;
  }

  public void stopLaunch(){
    top.set(ControlMode.PercentOutput, 0);
    bottom.set(ControlMode.PercentOutput, 0);
  }


  public boolean getAngle(){
    return angleClose;
  }


  public void doInit() {
    bottom.configFactoryDefault();

             /* Config sensor used for Primary PID [Velocity] */
    bottom.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
                                         Constants.kPIDLoopIdx,
                                         Constants.kTimeoutMs);

    /**
     * Phase sensor accordingly.
      * Positive Sensor Reading should match Green (blinking) Leds on Talon
     */
    bottom.setSensorPhase(true);

    /* Config the peak and nominal outputs */
    bottom.configNominalOutputForward(0, Constants.kTimeoutMs);
    bottom.configNominalOutputReverse(0, Constants.kTimeoutMs);
    bottom.configPeakOutputForward(1, Constants.kTimeoutMs);
    bottom.configPeakOutputReverse(-1, Constants.kTimeoutMs);

    /* Config the Velocity closed loop gains in slot0 */
    bottom.config_kF(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kF, Constants.kTimeoutMs);
    bottom.config_kP(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kP, Constants.kTimeoutMs);
    bottom.config_kI(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kI, Constants.kTimeoutMs);
    bottom.config_kD(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kD, Constants.kTimeoutMs);
  }
}
