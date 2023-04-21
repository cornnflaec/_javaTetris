
package _tetris;

import java.util.logging.Level;
import java.util.logging.Logger;


public class GameThread extends Thread 
{
    private GameArea ga;
    private GameForm gf;
    private int score;
    private int level  = 1;
    private int scorePerLevel = 3; 
    
    private int pause = 1000;
    private int speedupPerLevel = 100;
    
    public GameThread(GameArea ga, GameForm gf)
    {
        this.ga = ga;
        this.gf = gf;
    }
    
   @Override
   public void run()
   {
       while(true)
       {
                  ga.spawnBlock();
                  while(ga.moveBlockDown())
                  {
                        try {
                    Thread.sleep(pause);  // initial int is 1000 ms and decrease 100ms per elvel
                } 
                        catch (InterruptedException ex) 
                        {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                  
            if(ga.isBlockOutOfBounds())
            {
                System.out.println("Game Over");
                break;
            }
            
            ga.moveBlockToBackground();
            score += ga.clearLines();
            gf.updateScore(score); // update score 
            
            int lvl = score / scorePerLevel + 1; 
            if (lvl > level)
            {
                level = lvl;
                gf.updateLevel(level); // update level 
                pause -= speedupPerLevel;
            }
     }
                  
   }
}
