import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

import javax.swing.*;

/**
 * 
 * @authors Zhsios_Kuriakos and Fekas_Grhgorhs
 * 
 * CLASS: HighscoreWindow
 * DESCRIPTION: This class creates a new text file where the highscores are and after
 * 				the player, who has lost, enter his name pop-ups a window which shows
 * 				the 10 best highscores.
 *
 */

public class HighscoreWindow {
	private String filename = "highscores.txt";

	public HighscoreWindow(){
		HighscoreManager hm = new HighscoreManager();
		FileOutputStream output= null;
		PrintStream print = null;
		boolean visible = false;
		
		int i=0;
		String[] printL = new String[10];
		
		
		for(int j=0;j<10;j++){
		try {
	        output = new FileOutputStream(filename,visible);
	        print = new PrintStream(output);
	        print.println(hm.getHighscoreString()[j]);
	        visible = true;
	    } catch (FileNotFoundException e) {  
	        System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");  
	    } finally {  
	        try {  
	            if (output != null) {  
	                output.flush();  
	                output.close();  
	            }  
	        } catch (IOException e) {  
	            System.out.println("[Update] Error: " + e.getMessage());  
	        }  
	    } 
		}
		final JFrame f = new JFrame("Highscores");
		Box box = new Box(BoxLayout.Y_AXIS);
		
		do{
			printL[i] = hm.getHighscoreString()[i];
			box.add(new JLabel(printL[i]));
			i++;
		}while(i<10);
		
		f.setContentPane(box);
		f.pack();
		f.setSize(250,250);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
	    	public void windowClosing(WindowEvent e) {f.dispose();}
	    });
	}
}
