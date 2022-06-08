/**
 * 
 * @authors Zhsios_Kuriakos and Fekas_Grhgorhs
 * CLASS: Bullet
 * DESCRIPTION: Extending Circle, creates the bullets of space ship by using Circle
 * 				class.
 *
 */

public class Bullet extends Circle {
	private Point bulletCenter;
	
	public Bullet(Point inBulletCenter, int inBulletRadius) {
		super(inBulletCenter, inBulletRadius,inBulletRadius);
		bulletCenter = inBulletCenter;
	}
	
	/**
	 * 
	 * @param p the current Center of the bullet
	 */
	
	public void setCenter(Point p){
		bulletCenter = p;
	}
	
	/**
	 * 
	 * @return the current Center of the bullet
	 */
	
	public Point getCenter(){
		return bulletCenter;
	}
}