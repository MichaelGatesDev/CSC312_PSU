package main.java.csc312;

import main.java.csc312.contests.ContestBase;
import main.java.csc312.contests.TimedContest;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class GameManager
{
    private static GameManager instance;
    
    public static final int ROUND_TIME = 120; // in seconds
    public static final int MIN_GAME   = 1;
    public static final int MAX_GAME   = 3;
    
    private Map<Integer, ContestBase> currentGames;
    private int                       currentID;
    private TimedContest              currentGame;
    
    
    GameManager()
    {
        instance = this;
        this.currentGames = new HashMap<>();
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> instance = null));
    }
    
    
    public void newGame(int generated)
    {
        this.removeGame(this.currentID);
        
        System.out.println(MessageFormat.format("Beginning a new game with ID {0}", generated));
        TimedContest tc = new TimedContest(ROUND_TIME);
        tc.onStart();
        this.addGame(generated, tc);
    }
    
    
    private void addGame(int id, TimedContest c)
    {
        this.currentID = id;
        this.currentGame = c;
        this.currentGames.put(id, c);
    }
    
    
    private void removeGame(int id)
    {
        if (isIDInUse(this.currentID))
        {
            this.currentGames.remove(id);
        }
        if (this.currentGame != null)
        {
            this.currentGame.cancel();
        }
    }
    
    
    public boolean isIDInUse(int n)
    {
        return this.currentGames.containsKey(n);
    }
    
    
    public int getCurrentID()
    {
        return this.currentID;
    }
    
    
    public ContestBase getCurrentGame()
    {
        return this.currentGames.get(this.currentID);
    }
    
    
    public static GameManager getInstance()
    {
        return instance;
    }
    
}
