import java.awt.*;
import java.awt.event.*;

/**
 *
 * @authors Zhsios_Kuriakos and Fekas_Grhgorhs
 * CLASS: Game
 * DESCRIPTION: A painted canvas in its own window, updated every tenth second.
 * USAGE: Extended by Asteroids.
 * 
 */

abstract class Game extends Canvas implements ActionListener,KeyListener {
  
	private static final long serialVersionUID = 1L;
	protected boolean on = true;
	protected int game_over = 0;
	protected int width, height;
	protected Image buffer;
	protected Frame frame;
	
	public void actionPerformed(ActionEvent e){
		  System.out.println(e.getActionCommand());
	}

	public Game(String name, int inWidth, int inHeight) {
		width = inWidth;
		height = inHeight;
		
	    // Frame can be read as 'window' here.
	    frame = new Frame(name);
	    MenuBar bar = new MenuBar();
		Menu menu = new Menu("Game");
	  	MenuItem newGame = new MenuItem("New Game");
		MenuItem pause = new MenuItem("Pause");
		MenuItem exit = new MenuItem("Exit");
	    
	    frame.add(this);
	    
	    menu.add(newGame);
	    menu.add(pause);
	    menu.addSeparator();
	    menu.add(exit);
	    /**
	     * Set the appropriate action to menu bar 
	     */
	    newGame.addActionListener(new ActionListener (){
	    	public void actionPerformed(ActionEvent e){
	    		Asteroids.initialize();
	    		update(buffer.getGraphics());
	    	}
	    });
	    pause.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		Asteroids.pause();
	    	}
	    });
	    exit.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		System.exit(0);
	    	}
	    });
	    
	    bar.add(menu);
	  
	    frame.setMenuBar(bar);
	    frame.setSize(width,height);
	    frame.setVisible(true);
	    frame.setEnabled(true);
	    frame.setResizable(false);
	    frame.setFocusable(true);
	    frame.setFocusableWindowState(true);
	    frame.setFocusTraversalKeysEnabled(true);
	    
	    frame.addWindowListener(new WindowAdapter() {
	    	public void windowClosing(WindowEvent e) {System.exit(0);}
	    });
	    
	    buffer = createImage(width, height);
	}

	// 'paint' will be called every tenth of a second that the game is on.
	abstract public void paint(Graphics brush);

	/**
	 *  'update' paints to a buffer then to the screen, then waits a tenth of
	 *  a second before repeating itself, assuming the game is on. This is done
	 *  to avoid a choppy painting experience if repainted in pieces.
	 *  
	 *  @param brush parametre that help us to draw
	 */
	 
	public void update(Graphics brush) {
		
		paint(buffer.getGraphics());
		brush.drawImage(buffer,0,0,this);
		if (on) {sleep(10); repaint();}
	}

	   // 'sleep' is a simple helper function used in 'update'.
	private void sleep(int time) {
	   try {Thread.sleep(time);} catch(Exception exc){};
	}
}