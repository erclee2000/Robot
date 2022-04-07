// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {


	/**
	 * Which PID slot to pull gains from. Starting 2018, you can choose from
	 * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
	 * configuration.
	 */
	public static final int kSlotIdx = 0;

	/**
	 * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For
	 * now we just want the primary one.
	 */
	public static final int kPIDLoopIdx = 0;

	/**
	 * Set to zero to skip waiting for confirmation, set to nonzero to wait and
	 * report to DS if action fails.
	 */
    public static final int kTimeoutMs = 30;

	/**
	 * PID Gains may have to be adjusted based on the responsiveness of control loop.
     * kF: 1023 represents output value to Talon at 100%, 7200 represents Velocity units at 100% output
     *
	 * 	                                    			  kP   kI   kD   kF          Iz    PeakOut */
    public final static Gains kGains_Velocit = new Gains( 0.25, 0.001, 20, 1023.0/7200.0,  300,  1.00);

    // Height of the high target in feet.
    public final static double targetHeight = 8.0f;

	// Height from which the ball launches
	public final static double launchHeight = 44.0f / 12;

    // Height of the center of limelight camera from the ground.
    public final static double limelightMountHeight = 30.25f / 12;

    // Mount angle in degrees of the limelight from horizontal.
    public final static double limelightMountAngle = 22.0f;

    //These are the solenoid valve numbers for the variable launch
    public final static int launchSolenoidReverseID = 1;
    public final static int launchSolenoidForewardID = 0;

    // Wheel size for drive in Feet (6 inches).
    public final static double wheelDiameterFeet = .5f;

	// time for teleop during a match in seconds (2:15)
	public final static int telopTime = 135;
}
