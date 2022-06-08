/**
 * 
 * @authors Zhsios_Kuriakos and Fekas_Grhgorhs
 * CLASS: Asteroid
 * DESCRIPTION: Extending Polygon, Asteroid class creates an asteroid on the game by
 * 				using Polygon class.
 * 
 */

public class Asteroid extends Polygon {
	
	
	private Point[] asterPoint;
	private Point asterOffset;
	private double asterRotate;
	private int[] x;
	private int[] y;
	
	
	public Asteroid(Point[] inAsteroidPoint,Point inAsteroidOffset,double inAsteroidRotate){
		super(inAsteroidPoint,inAsteroidOffset,inAsteroidRotate);
		asterPoint = inAsteroidPoint;
		asterOffset = inAsteroidOffset;
		asterRotate = inAsteroidRotate;
		x = new int[asterPoint.length];
		y = new int[asterPoint.length];
		for(int i=0;i<asterPoint.length;i++){
			x[i]=0;
			y[i]=0;
		}
	}
	
	/**
	 * 
	 * @param x the x coordinate of the offset of asteroid
	 * @param y	the y coordinate of the offset of asteroid
	 */
	
	public void setAsteroidOffset(double x,double y){
		asterOffset.x = x;
		asterOffset.y = y;
	}
	
	/**
	 * 
	 * @return the x coordinate of the offset of asteroid
	 */
	
	public double getAsteroidOffsetX(){
		return asterOffset.x;
	}
	
	/**
	 * 
	 * @return the y coordinate of the offset of asteroid
	 */
	
	public double getAsteroidOffsetY(){
		return asterOffset.y;
	}
	/**
	 * Sets the angle zero to have a standard step of the angle
	 * and calls rotate method of the 
	 * parent class. 
	 * @param degrees the degrees of angle
	 */
	
	public void setAsteroidRotate(int degrees){
		rotation = 0;
		super.rotate(degrees);
		asterRotate = rotation;
	}
	/**
	 * 
	 * @return degrees the degrees of angle
	 */
	
	public double getAsteroidRotate(){
		return asterRotate;
	}
	
	/**
	 * Sets all the x coordinates of the asteroid to an array.This method helps to 
	 * draw the asteroid easier.
	 */
	
	public void setX(){
		asterPoint = getPoints();
		for(int i=0; i<asterPoint.length;i++){
			x[i] = (int)asterPoint[i].x;
		}
	}
	
	/**
	 * 
	 * @return the x array
	 */
	
	public int[] getX(){
		return x;
	}
	
	/**
	 * Sets all the y coordinates of the asteroid to an array.This method helps to 
	 * draw the asteroid easier.
	 */
	
	public void setY(){
		asterPoint = getPoints();
		for(int i=0; i<asterPoint.length;i++){
			y[i] = (int)asterPoint[i].y;
		}
	}
	
	/**
	 * 
	 * @return the y array
	 */
	
	public int[] getY(){
		return y;
	}
}
