/**
 *
 * @authors Zhsios_Kuriakos and Fekas_Grhgorhs
 * CLASS: Ship
 * DESCRIPTION: Extending Polygon, Ship class creates a space ship based on Polygon.
 */

public class Ship extends Polygon {
	
	//instance variables
	private Point[] shipPoint;
	private Point shipOffset;
	private double shipRotate;
	private int x[];
	private int y[];
	
	
	public Ship(Point[] inShipPoint,Point inShipOffset,double inShipRotate){
		super(inShipPoint, inShipOffset, inShipRotate);
		shipPoint = inShipPoint;
		shipOffset = inShipOffset;
		shipRotate = inShipRotate;
		x = new int[shipPoint.length];
		y = new int[shipPoint.length];
		
		
	}
	
	/**
	 * 
	 * @param x the x coordinate of the offset of space ship
	 * @param y the y coordinate of the offset of space ship
	 */
	
	public void setShipOffsetX(double x,double y){
		shipOffset.x = x;
		shipOffset.y = y;
	}
	
	/**
	 * 
	 * @return  the x coordinate of the offset of space ship
	 */
	
	public double getShipOffsetX(){
		return shipOffset.x;
	}
	
	/**
	 * 
	 * @return  the y coordinate of the offset of space ship
	 */
	
	public double getShipOffsetY(){
		return shipOffset.y;
	}
	
	/**
	 * Sets the angle zero to have a standard step of the angle
	 * and calls rotate method of the 
	 * parent class. 
	 * @param turn the degrees of the angle of space ship
	 */
	
	public void setRotate (int turn){
		rotation = 0;
		super.rotate(turn);
		shipRotate = rotation;
	}
	
	/**
	 * 
	 * @return the rotation of the spaceship
	 */
	
	public double getRotate(){
		return shipRotate;
	}
	
	/**
	 * Sets all the x coordinates of the space ship to an array.This method helps to 
	 * draw the space ship easier.
	 */
	
	public void setX(){
		shipPoint = getPoints();
		for(int i=0; i<shipPoint.length;i++){
			x[i] = (int)shipPoint[i].x;
		}
	}
	
	/**
	 * 
	 * @return the array of coordinates x
	 */
	
	public int[] getX(){
		return x;
	}
	
	/**
	 * Sets all the y coordinates of the space ship to an array.This method helps to 
	 * draw the space ship easier.
	 */
	
	public void setY(){
		shipPoint = getPoints();
		for(int i=0; i<shipPoint.length;i++){
			y[i] = (int)shipPoint[i].y;
		}
	}
	
	/**
	 * 
	 * @return the array of coordinates y
	 */
	
	public int[] getY(){
		return y;
	}
}