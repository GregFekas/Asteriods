/**
 * 
 * @author Zhsios_Kuriakos and Fekas_Grhgorhs
 * CLASS: Circle
 * DESCRIPTION: Circle creates an oval according to the given center and the rays.
 * USAGE: Extend by Bullet and Stars.
 *
 */

public class Circle {
	private int radiusX,radiusY;
	private Point center;
	
	public Circle(Point inCenter,int inRadiusX,int inRadiusY){
		center=new Point (inCenter.x,inCenter.y);
		radiusX=inRadiusX;
		radiusY=inRadiusY;
	}
	
	/**
	 * 
	 * @return the X distance from the center of the circle
	 */
	
	public int getRadiusX(){
		return radiusX;
	}
	
	/**
	 * 
	 * @return the Y distance from the center of the circle
	 */
	
	public int getRadiusY(){
		return radiusY;
	}
	
	/**
	 * 
	 * @return the (Point type) Center
	 */
	
	public Point getCenter(){
		return center;
	}
	/**
	 * @return the area of the Circle 
	 * 
	 */
	public double getArea() {
		return Math.PI * radiusX * radiusY;
	}
		
	}