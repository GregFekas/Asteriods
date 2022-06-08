import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * 
 * @authors Zhsios_Kuriakos and Fekas_Grhgorhs
 * CLASS: GiveName
 * DESCRIPTION: Extending JFrame and implements ActionListener,this class creates a
 * 				new window where you can type.
 * USAGE: In the case of losing one player, pop-ups this window to enter his name or
 * 		  anything else he wants and put his name and score to highscore table.
 *
 */

public class GiveName extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private String givenName;

	public GiveName(){
		final JFrame string = new JFrame();  
		JLabel label = new JLabel("Give your name:");
		JPanel panel = new JPanel();
		JTextField text = new JTextField(15);
		
		string.add(panel);
		text.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JTextField input = (JTextField) e.getSource();
				HighscoreManager hm = new HighscoreManager(); 
				givenName = input.getText();
				
				hm.addScore(givenName, Asteroids.score);
				string.dispose();
				new HighscoreWindow();
			}
		});
		panel.add(label,BorderLayout.WEST);
		panel.add(text,BorderLayout.EAST);
		
		string.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		string.setSize(200,100);
		string.setVisible(true);
		string.setResizable(false);
	}
	
	public String getName(){
		return givenName;
	}

	public void actionPerformed(ActionEvent a) {
		
	}

}