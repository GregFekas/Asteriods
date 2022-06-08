import java.io.Serializable;  
  
/**
 * 
 * @authors Zhsios_Kuriakos and Fekas_Grhgorhs
 * CLASS: Score
 * DESCRIPTION: Implements Serializable,this class creates an object with a name and
 * 				a score.
 *
 */

public class Score implements Serializable {  
    
	private static final long serialVersionUID = 1L;
	private int score;  
    private String name;  
    
    public void setScore(int score){
    	this.score = score;
    }
  
    public int getScore() {  
        return score;  
    }
    
    public void setName(String name){
    	this.name = name;
    }
  
    public String getName() {  
        return name;  
    }  
  
    public Score(String name, int score) {  
        this.score = score;  
        this.name = name;  
    }  
}  