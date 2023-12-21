package LockheedMartini;
import robocode.*;
import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Leopard3 - a robot by (your name here)
 */
public class Leopard3 extends Robot
{
	/**
	 * run: Leopard3's default behavior
	 */
	public void run() {

		setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			ahead(100);
			turnGunRight(360);
			back(100);
			turnGunRight(360);
		}
	}

	public void radar() {
		while(true) {
			turnRadarRight(360);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {

		double distance = e.getDistance();
		double energy = e.getEnergy();
		double bearing = e.getBearing();
		double heading = e.getHeading();
		double velocity = e.getVelocity();
		double time = distance / (20 - (3 * energy));
		double gunTurn = getHeading() - getGunHeading() + bearing;
		double radarTurn = getHeading() - getRadarHeading() + bearing;

		// Replace the next line with any behavior you would like
		turnGunRight(gunTurn);
		turnRadarRight(radarTurn);
		fire(3);
		ahead(velocity * time);
		scan();
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(20);
	}	
}
