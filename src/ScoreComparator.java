import java.util.Comparator;  
  
/**
 * 
 * @authors Zhsios_Kuriakos and Fekas_Grhgorhs
 * CLASS: ScoreComparator
 * DESCRIPTION: This class is used to compare 2 objects of the type Score.
 * 				-1 means that the 1st object is greater than the 2nd
 * 				1 means that the 1st object is smaller than the 2nd
 * 				and 0 means that they are equal
 *
 */

public class ScoreComparator implements Comparator<Score> {  
	public int compare(Score score1, Score score2) {  
  
		int sc1 = score1.getScore();  
        int sc2 = score2.getScore();  
  
        if (sc1 > sc2){  
        	return -1;  
        }else if (sc1 < sc2){  
        	return 1;  
        }else{  
            return 0;  
        }  
	}    
}  