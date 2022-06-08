import java.util.*;  
import java.io.*;  
  
/**
 * 
 * @authors Zhsios_Kuriakos and Fekas_Grhgorhs
 * CLASS: HighscoreManager
 * DESCRIPTON: This class creates and manage the table of highscores.
 * USAGE: Every time a new player plays the game and losing, after he writes his name,
 * 		  his score should be placed in the appropriate on table,in case it is bigger
 * 		  than the others, and erase the last one.
 *
 */

public class HighscoreManager {  
    /* An arraylist of the type "score" we will use to work with the scores inside
     *  the class  
     */
    private ArrayList<Score> scores;  
  
    // The name of the file where the highscores will be saved  
    static final String HIGHSCORE_FILE = "scores.dat";  
  
    //Initialising an inputStream and outputStream for working with the file  
    ObjectOutputStream outputStream = null;  
    ObjectInputStream inputStream = null;  
  
    public HighscoreManager() {  
       	//initialising the scores-arraylist  
       	scores = new ArrayList<Score>();  
    }  

    /**
     * This is a function that will return an arraylist with the scores in it.
     * It contains calls to the function loadScoreFile() and sort(), these functions
     * will make sure you have the scores from your high-score file in a sorted
     * order. We will be writing these functions later on.
     * 
     * @return an arraylist with names and scores in it
     */

    public ArrayList<Score> getScores() {  
    	loadScoreFile();  
    	sort();  
    	return scores;  
	} 

    /**
     * This function will create a new object "comparator" 
     * from the class ScoreComparator.
	 * the Collections.sort() function is in the Java Collections Class 
	 * (a part of java.util). It allows you to sort the arraylist "scores" 
	 * with help of "comparator".
     */
	private void sort() {  
    	ScoreComparator comparator = new ScoreComparator();  
    	Collections.sort(scores, comparator);  
	} 
	
	/**
	 * This method is to add scores to the scorefile.
	 * First the scores that are allready in the high-score file are loaded into
	 * the "scores"-arraylist.After that the new scores are added to the arraylist
	 * and the high-score file is updated with it.
	 * 
	 * @param name	the name that player gives when he loses
	 * @param score	the score he achived
	 */

	public void addScore(String name, int score) {  
    	loadScoreFile();  
    	scores.add(new Score(name, score));  
    	updateScoreFile();  
	}
	
	/**
	 * This function will load the arraylist that is in the high-score file and
	 * will put it in the "scores"-arraylist.The try-catch structure will avoid
	 * that your program crashes when there is something wrong while loading the
	 * file (like when the file is corrupted or doesn't exist).
	 */

	@SuppressWarnings("unchecked")//suggested solution of Eclipse
	public void loadScoreFile() {  
    	try {  
        	inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));  
        	scores = (ArrayList<Score>) inputStream.readObject();
    	} catch (FileNotFoundException e) {  
        	System.out.println("[Load] FNF Error: " + e.getMessage());  
    	} catch (IOException e) {  
        	System.out.println("[Load] IO Error: " + e.getMessage());  
    	} catch (ClassNotFoundException e) {  
        	System.out.println("[Load] CNF Error: " + e.getMessage());  
    	} finally {  
        	try {  
            	if (outputStream != null) {  
                	outputStream.flush();  
                	outputStream.close();  
            	}  
        	} catch (IOException e) {  
            	System.out.println("[Laad] IO Error: " + e.getMessage());  
        	}  
    	}  
	}

	/**
	 * This is about the same as loadScoreFile(), but instead of reading the file
	 * it will be writing the "score"-arraylist to the file.
	 */
	
	public void updateScoreFile() {  
    	try {  
        	outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));  
        	outputStream.writeObject(scores);
    	} catch (FileNotFoundException e) {  
        	System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");  
    	} catch (IOException e) {  
        	System.out.println("[Update] IO Error: " + e.getMessage());  
    	} finally {  
        	try {  
            	if (outputStream != null) {  
                	outputStream.flush();  
                	outputStream.close();  
            	}  
        	} catch (IOException e) {  
            	System.out.println("[Update] Error: " + e.getMessage());  
        	}  
    	}  
	}

	/**
	 * It can be used for both console and GUI (in GUI you can put the high-score
	 * string into a label).The function will only have the top 10 players.
	 * 
	 * @return a string array with the top 10 highscores and the appropriate names
	 */
	
	public String[] getHighscoreString() {  
		int max = 10;  
    	String[] highscoreString = new String[max];  
    	String empty = "Not burned enough! :P";
    

    	ArrayList<Score> scores;  
    	scores = getScores();  

    	int i = 0;  
    	int x = scores.size();  
    	if (x > max) {  
        	x = max;  
    	}  
    	while (i < max) {
    		highscoreString[i] = "";
    		if(i<x)
    			highscoreString[i] += (i + 1) + ". \t" + scores.get(i).getName() + ":  \t" + scores.get(i).getScore() + "\n"; 
        	else
        		highscoreString[i] += (i + 1) + ". \t" + empty + " ->  \t" + 0 + "\n"; 
        	i++;  
    	}  
    	return highscoreString;  
	}
}