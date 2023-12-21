import robocode.*;

public class Leopard4 extends AdvancedRobot {
    public void run() {
        setAdjustRadarForRobotTurn(true);
        setAdjustGunForRobotTurn(true);
        while (true) {
            turnRadarRightRadians(Double.POSITIVE_INFINITY);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        double enemyBearing = this.getHeading() + e.getBearing();
        double enemyX = getX() + e.getDistance() * Math.sin(Math.toRadians(enemyBearing));
        double enemyY = getY() + e.getDistance() * Math.cos(Math.toRadians(enemyBearing));
        double dx = enemyX - this.getX();
        double dy = enemyY - this.getY();
        double theta = Math.toDegrees(Math.atan2(dx, dy));
        turnRadarRight(normalizeBearing(getHeading() - (getRadarHeading() + e.getBearing())));
        double gunTurnAmt = normalizeBearing(theta - getGunHeading());
        setTurnGunRight(gunTurnAmt);
        if (Math.abs(gunTurnAmt) <= 3) {
            setFire(Math.min(400 / e.getDistance(), 3));
        }
        if (e.getDistance() > 150) {
            double goTo = enemyBearing + Math.random() - 0.5;
            setTurnRight(normalizeBearing(goTo - getHeading()));
            setAhead((e.getDistance() - 140) * Math.random());
        }
    }

    public void onHitByBullet(HitByBulletEvent e) {
        setTurnRight(normalizeBearing(e.getBearing() + 180 - (15 * Math.random())));
        setAhead(100 * Math.random());
    }

    double normalizeBearing(double angle) {
        while (angle > 180) angle -= 360;
        while (angle < -180) angle += 360;
        return angle;
    }
}