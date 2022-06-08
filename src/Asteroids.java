import java.awt.*;
import java.util.*;
import java.awt.event.*;

/**
 *
 * @authors Zhsios_Kuriakos and Fekas_Grhgorhs
 * 
 * CLASS: Asteroids
 * DESCRIPTION: Extending Game, Asteroids is where the counting about movement and
 * 				all the painting is done.
 * 
 */

class Asteroids extends Game implements KeyListener {
	//star values
	private static final long serialVersionUID = 1L;//Suggested solution of Eclipse
	private static final int STARS_RAD = 2;
    private static final int NUM_STARS = 400;
    
    //ship values
    private static final int SHIP_POINTS_NUM = 3;
    private static final int SHIP_ANGLE_STEP = 9;
    private static final double SHIP_SPEED_STEP = 9;
  
    //asteroid values
    private static final int MAX_ASTEROID = 10;
    private static final int ASTEROID_SIDE = 8;
    private static final double MIN_ASTEROID_SPEED = 0.5;
	
	//bullet values
	private static final int BULLET_RAD = 5;
	private static final int BULLET_SPEED_STEP = 10;
	
    //ship variables
    public static Ship ship;
	public static Point[] shipPoints;
	public static Point offset;
	public static double rotate;
	private static boolean shipActive;
	
	//asteroid variables
	private static Asteroid[] rock;
	private static Point[][] rockPoints;
	private static Point[] rockPosition;
	private static double[] rockAngle;
	private static boolean[] asterActive,flagleft,flagright;
	private static int asterLeft;
		
	//bullet data
	private Bullet bullet;
	private Point bulletCenter;
	private int bulletRadius;
	private double bulletAngle;
	private boolean activeBullet;
	
	//Stars data
	private Stars[] star;
	private Point[] starCenter;
	private int rad;
	
	//Keys data
	private static boolean left;
	private static boolean right;
	private static boolean up;
	private static boolean down;
	
	//Game data
	public static boolean playing;
	public static boolean paused;
	public static boolean loaded,dispose;
	public static int flag;
	public static int score;
	public GiveName save;
	public static HighscoreManager manage;
	public static String highscore;
	
	public Asteroids() {
		super("Asteroids!",800,600);
		addKeyListener(this);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(true);
		
		//initialize ship
		shipPoints = new Point[SHIP_POINTS_NUM];
		
		//initialize asteroids
		rock = new Asteroid[MAX_ASTEROID];
		rockPoints = new Point[MAX_ASTEROID][ASTEROID_SIDE];
		rockPosition = new Point[MAX_ASTEROID];
		for(int k=0;k<MAX_ASTEROID;k++){
			Random rand = new Random();
			double x,y;
			do{
				x = rand.nextInt(700);
				y = rand.nextInt(500);
			}while(((x>0&&x<300) || (x>500&&x<800))&&(y>0&&y<600));
			
			rockPoints[k][0]= new Point(33+x,y);
			rockPoints[k][1]= new Point(55+x,27+y);
			rockPoints[k][2]= new Point(45+x,60+y);
			rockPoints[k][3]= new Point(27+x,78+y);
			rockPoints[k][4]= new Point(9+x,69+y);
			rockPoints[k][5]= new Point(6+x,48+y);
			rockPoints[k][6]= new Point(12+x,27+y);
			rockPoints[k][7]= new Point(x,9+y);
			
			rockPosition[k] = new Point(x,y);
		}
		
		asterLeft = MAX_ASTEROID;
		asterActive = new boolean[MAX_ASTEROID];
		rockAngle = new double[MAX_ASTEROID];
		flagleft = new boolean[MAX_ASTEROID];
		flagright= new boolean[MAX_ASTEROID];
		for(int i =0;i<MAX_ASTEROID;i++){
			asterActive[i]=false;
			flagleft[i]=false;
			flagright[i]=false;
			rockAngle[i]=0.0;
			rock[i]=new Asteroid(rockPoints[i],rockPosition[i],rockAngle[i]);
		}
		
		
		//initialize stars
		star = new Stars[NUM_STARS];
		starCenter = new Point[NUM_STARS];
		rad = STARS_RAD;
		for(int j=0;j<NUM_STARS;j++){
			starCenter[j] = new Point(Math.random()*800,Math.random()*600);
			star[j] = new Stars(starCenter[j],rad);
		}
		
		//initialize bullets
		bulletCenter =  new Point(0,0);
		bulletRadius = BULLET_RAD;
		bulletAngle = 0.0;
		bullet = new Bullet(bulletCenter,bulletRadius);
		activeBullet = false;
		
		//initialize
		manage = new HighscoreManager();
		flag = 0;
		loaded = false;
  	}
	
	/**
	 * initialize all the value to start the game correctly
	 */
	
	public static void initialize(){
		playing = true;
		paused = false;
		shipActive = true;
		score = 0;
		highscore = manage.getHighscoreString()[0].substring(manage.getHighscoreString()[0].length()-4);
		asterLeft = MAX_ASTEROID;
		dispose = false;
		shipPoints[0] = new Point (400,300);
		shipPoints[1] = new Point (410,324);
		shipPoints[2] = new Point (390,324);
		offset = new Point(390,300);
		rotate = 0.0; 
		ship = new Ship (shipPoints,offset,rotate);
		createAsteroids();
		updateShip();
	}
	
	/**
	 * 
	 * 
	 * @param e the key that user presses
	 */
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		switch(code){
			case KeyEvent.VK_ENTER:
				if(!loaded){
					initialize();
					loaded = true;
				}
				break;
			case KeyEvent.VK_LEFT:
				
				left= true;
				if(!paused){
					updateShip();
				}
				break;
			case KeyEvent.VK_RIGHT:
				
				right = true;
				if(!paused){
					updateShip();
				}
				break;
			case KeyEvent.VK_UP:
				
				up= true;
				if(!paused){
					updateShip();
				}
				break;
			case KeyEvent.VK_DOWN:
				
				down= true;
				if(!paused){
					updateShip();
				}
				break;
			case KeyEvent.VK_SPACE:
				activeBullet = true;
				if(!paused){
				initBullet();
				updateBullet();
				}
				break;
			case KeyEvent.VK_P:
				pause(); 
				break;
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;
		}
		repaint();
		
	}

	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		try{
		switch(code){
			case KeyEvent.VK_LEFT:
				left= false;
				break;
			case KeyEvent.VK_RIGHT:
				right = false;
				break;
			case KeyEvent.VK_UP:
				up= false;
				break;
			case KeyEvent.VK_DOWN:
				down= false;
				break;
		}
		}
		catch(NullPointerException npex){
		}
	}

	public void keyTyped(KeyEvent e) {
	}
	
	/**
	 * The method which compute the movement of the space ship.
	 */
	
	public static void updateShip(){
		
		if (left){
			ship.setRotate((int)ship.getRotate()-SHIP_ANGLE_STEP);
			rotate = ship.getRotate();
		}
		if (right){
			ship.setRotate((int)ship.getRotate()+SHIP_ANGLE_STEP);
			rotate = ship.getRotate();
		}
		
		if (up){
			offset.x = ship.getShipOffsetX()+SHIP_SPEED_STEP*Math.sin(Math.toRadians(rotate));
			offset.y = ship.getShipOffsetY()+SHIP_SPEED_STEP*-Math.cos(Math.toRadians(rotate));
			ship.setShipOffsetX(offset.x, offset.y);
		}
		if (down){
			offset.x = ship.getShipOffsetX()-SHIP_SPEED_STEP*Math.sin(Math.toRadians(rotate));
			offset.y = ship.getShipOffsetY()-SHIP_SPEED_STEP*-Math.cos(Math.toRadians(rotate));
			ship.setShipOffsetX(offset.x, offset.y);
		}
		
		if(ship.getShipOffsetX()>800){
			offset.x = 0;
		}
		if(ship.getShipOffsetX()<0){
			offset.x = 800;
		}
		if(ship.getShipOffsetY()>560){
			offset.y = 0;
		}
		if(ship.getShipOffsetY()<0){
			offset.y = 560;
		}
		ship.setShipOffsetX(offset.x, offset.y);
	}
	
	/**
	 * 
	 * @param brush the helping object for drawing
	 */
	
	private void shipMove(Graphics brush){
		brush.setColor(Color.GREEN);
		ship.setX();
		ship.setY();
    	brush.fillPolygon(ship.getX(),ship.getY(),shipPoints.length);
    	
	}
	
	/**
	 * The method that initialize the bullet object(the bow of the ship).
	 */

	public void initBullet(){
		int[] x = new int[SHIP_POINTS_NUM];
		int[] y = new int[SHIP_POINTS_NUM];
		x = ship.getX();
		y = ship.getY();
		Point p = new Point(x[0],y[0]); 
		bullet.setCenter(p);
		bulletCenter = bullet.getCenter();
		bulletAngle = ship.getRotate();
	}
	/**
	 * The method that moves the bullet into a straight line.
	 */
	public void updateBullet(){
		
		bulletCenter.x = bulletCenter.x+BULLET_SPEED_STEP*Math.sin(Math.toRadians(bulletAngle));
		bulletCenter.y = bulletCenter.y+BULLET_SPEED_STEP*-Math.cos(Math.toRadians(bulletAngle));
	}
	/**
	 * 
	 * @param brush the helping object to draw the bullet
	 */
	public void bulletMove(Graphics brush){
		if (activeBullet){
    		if(bulletCenter.x != 800 || bulletCenter.y != 600){
    			if(!paused){
    				updateBullet();
    			}
    			brush.setColor(Color.RED);
    			brush.fillOval((int)bulletCenter.x, (int)bulletCenter.y, bulletRadius, bulletRadius);
    		}
    	}
    	else{
    		activeBullet = false;
    	}
	}
	
	/**
	 * The method that creates randomly one the screen the asteroids.
	 */
	public static void createAsteroids(){
		int i;
		//Create an asteroid and give a random rotation
		
		for(i=0;i<MAX_ASTEROID;i++){
			
			//Place asteroids at one edge of screen
			if(Math.random()<0.5){
				rockPosition[i].x = -800/4;
				if(Math.random()<0.5){
					rockPosition[i].x = 800/4;
				} 
				rockPosition[i].y =Math.random()* 600;
				rock[i].setAsteroidOffset(rockPosition[i].x, rockPosition[i].y);
			}
			else{
				rockPosition[i].x = Math.random()*800;
				rockPosition[i].y = -600/4;
				if(Math.random()<0.5){
					rockPosition[i].y = 600/4;
				}
				rock[i].setAsteroidOffset(rockPosition[i].x, rockPosition[i].y);
			}
			asterActive[i]=true;
		}
	}

	/**
	 * The method which describes the movement of the asteroids. 
	 */
	
	public void updateAsteroids(){
		int i;
		for(i=0;i<MAX_ASTEROID;i++){
			if(asterActive[i]){
				
				if(Math.random() < 0.25){
					rockPosition[i].x = rock[i].getAsteroidOffsetX() + MIN_ASTEROID_SPEED*Math.sin(Math.toRadians(135));
					rockPosition[i].y = rock[i].getAsteroidOffsetY() + MIN_ASTEROID_SPEED*-Math.cos(Math.toRadians(135));
					rock[i].setAsteroidOffset(rockPosition[i].x, rockPosition[i].y);
				}
				else if(Math.random()>0.25 && Math.random()<0.5){
					rockPosition[i].x = rock[i].getAsteroidOffsetX() + MIN_ASTEROID_SPEED*-Math.sin(Math.toRadians(135));
					rockPosition[i].y = rock[i].getAsteroidOffsetY() + MIN_ASTEROID_SPEED*Math.cos(Math.toRadians(135));
					rock[i].setAsteroidOffset(rockPosition[i].x, rockPosition[i].y);
				}
				else if(Math.random()>0.5&& Math.random()<0.75){
					rockPosition[i].x = rock[i].getAsteroidOffsetX() + MIN_ASTEROID_SPEED*Math.sin(Math.toRadians(135));
					rockPosition[i].y = rock[i].getAsteroidOffsetY() + MIN_ASTEROID_SPEED*-Math.cos(Math.toRadians(135));
					rock[i].setAsteroidOffset(rockPosition[i].x, rockPosition[i].y);
				}
				else{
					rockPosition[i].x = rock[i].getAsteroidOffsetX() - MIN_ASTEROID_SPEED*-Math.sin(Math.toRadians(135));
					rockPosition[i].y = rock[i].getAsteroidOffsetY() - MIN_ASTEROID_SPEED*Math.cos(Math.toRadians(135));
					rock[i].setAsteroidOffset(rockPosition[i].x, rockPosition[i].y);
				}
				if(rock[i].getAsteroidOffsetX()>800){
					rockPosition[i].x=0;
				}
				if(rock[i].getAsteroidOffsetY()>600){
					rockPosition[i].y=0;
				}
				rock[i].setAsteroidOffset(rockPosition[i].x, rockPosition[i].y);
				
				if(activeBullet && asterActive[i] && rock[i].contains(bulletCenter)){
					asterLeft--;
					asterActive[i]=false;
					activeBullet = false;
					score += 10;
					if(asterLeft<=0){
						asterLeft = MAX_ASTEROID;
						createAsteroids();
					}
				}
				
				if(shipActive && asterActive[i] && rock[i].contains(offset)){
					playing = false;
					if(!dispose){
						new GiveName();
						dispose = true;
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @param brush the helping object to draw the asteroids
	 */
	
	public void asteroidsMove(Graphics brush){
		brush.setColor(Color.WHITE);
		for(int i=0;i<MAX_ASTEROID;i++){
			if(!paused){
				updateAsteroids();
			}
			rock[i].setX();
			rock[i].setY();
			if(asterActive[i]){
				brush.drawPolygon(rock[i].getX(), rock[i].getY(), rockPoints[i].length);
			}
		}
	}
	/**
	 * the method that pauses the game
	 */
	public static void pause(){
		if(flag == 0){
			paused = true;
			flag = 1;
		}
		else {
			paused = false;
			flag = 0;
		}
	}
	
	/**
	 *@param brush the helping object to draw the current snapshot of the window
	 */
 	public void paint(Graphics brush) {
		
 		//here is 
		if (playing == true){
		int i;
		
		
		brush.setColor(Color.BLACK);
    	brush.fillRect(0,0,width,height);
    	
    	for (i=0;i<NUM_STARS;i++){
            brush.setColor(Color.YELLOW);
            brush.fillOval((int)starCenter[i].x,(int)starCenter[i].y, star[i].getRadiusX(), star[i].getRadiusY());
            
        }
    	
    	bulletMove(brush);
    	
    	asteroidsMove(brush);
    	
    	shipMove(brush);
    	
    	brush.drawString("Score: "+score,700,20);
    	brush.drawString("Highscore: "+highscore,20,20);
		}
		
		else{
			FontMetrics fm = brush.getFontMetrics();
			int i,h;
			
			
			String str ="Asteroids Game";
			String[] start = { "Press 'Enter' to start", 
					"Press 'P' to pause the  game", 
					"Press 'Esc' to exit","DETAILS:","!!Click on the window to begin!!",
					"Use arrow keys to move and 'spacebar' to fire",
					"If you lose and you want to play again",
					"press 'New Game' from Menu bar","ENJOY!!"};
			
			h=height-start.length*fm.getHeight();
			
			brush.setColor(Color.BLACK);
			brush.fillRect(0,0,width,height);
		    	
			brush.setColor(Color.CYAN);
			brush.setFont(new Font(str,Font.BOLD,22));
			brush.drawString(str,width/2-fm.stringWidth(str)/2-15,height/6);
			for(i=0;i<9;i++, h +=fm.getHeight() +12){
				brush.setFont(new Font(start[i],Font.BOLD,16));
				if(i<3){
					brush.setColor(Color.WHITE);
					brush.drawString(start[i],width/2-fm.stringWidth(start[i])/2-5,h/2);
				}
				else{
					brush.setColor(Color.RED);
					brush.drawString(start[i],width/2-fm.stringWidth(start[i])/2-5,4*h/6);
				}
			}
			for (i=0;i<NUM_STARS;i++){
	            brush.setColor(Color.YELLOW);
	            brush.fillOval((int)starCenter[i].x,(int)starCenter[i].y, star[i].getRadiusX(), star[i].getRadiusY());
			}
		}
	}
}