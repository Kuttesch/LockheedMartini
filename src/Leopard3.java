package LockheedMartini;
import robocode.*;
import java.util.*;
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

		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		setAdjustRadarForRobotTurn(true);


		// Robot main loop
		while(true) {
			turnRadarRight(360);
			this.turnGunRight(360.0D);
			int red = (int)(Math.random() * 255);
			int green = (int)(Math.random() * 255);
			int blue = (int)(Math.random() * 255);
			setColors(new Color(red, green, blue), new Color(red, green, blue), new Color(red, green, blue));
			movement();
		}
	}
	public void movement(){
		Random random = new Random();
		int max_turn = 135;
		int min_turn = 0;
        int randomDegree = random.nextInt(max_turn - min_turn + 1);
		int randomDirection = random.nextInt(2);
		if (randomDirection == 0){
			turnRight(randomDegree);
		}
		else turnLeft(randomDegree);
		ahead(50);
	}
	public void onScannedRobot(ScannedRobotEvent e) {

		double distance = e.getDistance();
		double energy = e.getEnergy();
		double bearing = e.getBearing();
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
		back(50);
	}

	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(50);
		turnRight(90);
	}


	public void onHitRobot(HitRobotEvent e) {
		double bearing = e.getBearing();
		double gunTurn = getHeading() - getGunHeading() + bearing;
		double radarTurn = getHeading() - getRadarHeading() + bearing;
		turnGunRight(gunTurn);
		turnRadarRight(radarTurn);
		fire(3);
		ahead(10);
		scan();
	}
}