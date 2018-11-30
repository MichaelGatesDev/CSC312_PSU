package main.java.csc312;

import main.java.csc312.contests.ContestBase;
import main.java.csc312.contests.TimedContest;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameManager
{
    private static final Random RANDOM = new Random();
    private static final int    MAX_ID = 1000;
    
    private static GameManager instance;
    
    public static final int ROUND_TIME = 120; // in seconds
    public static final int MIN_GAME   = 1;
    public static final int MAX_GAME   = 3;
    
    private Map<Integer, ContestBase> currentGames;
    private int                       currentID = -1;
    private TimedContest              currentGame;
    
    
    private Map<Integer, Challenge> challenges;
    
    
    GameManager()
    {
        instance = this;
        this.currentGames = new HashMap<>();
        
        
        challenges = new HashMap<>();
        challenges.put(1, new Challenge(new char[][]{
                        { 'z', 'B', 'C', 'D', 'a' },
                        { 'a', 'b', 'B', 'b', 'E' },
                        { 'p', 'B', 'c', 'b', 'B' },
                        { 'E', 'b', 'Z', 'e', 'E' },
                        { 'a', 'E', 'E', 'e', 'Z' },
                }, "zap")
        );
        challenges.put(2, new Challenge(new char[][]{
                        { 'a', 'B', 'c', 'D', 'e' },
                        { 'B', 'B', 'B', 'c', 'E' },
                        { 'e', 'Z', 'c', 'B', 'z' },
                        { 'E', 'E', 'd', 'b', 'i' },
                        { 'a', 'E', 'y', 'a', 'g' },
                }, "zap")
        );
        challenges.put(3, new Challenge(new char[][]{
                        { 'a', 'B', 'c', 'D', 'e' },
                        { 'B', 'z', 'z', 'o', 'E' },
                        { 'a', 'Z', 'a', 'B', 'e' },
                        { 'E', 'E', 'g', 'E', 'E' },
                        { 'a', 'E', 'e', 'A', 'e' },
                }, "zap")
        );
        
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> instance = null));
    }
    
    
    public void newGame(int generated)
    {
        newGame(generated, ROUND_TIME);
    }
    
    
    public void newGame(int generated, int lengthInSeconds)
    {
        this.removeGame(this.currentID);
        
        System.out.println(MessageFormat.format("Beginning a new game with ID {0}", generated));
        TimedContest tc = new TimedContest(lengthInSeconds);
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
    
    
    public int generateRandomID()
    {
        int generated = -1;
        while (generated == -1 || GameManager.getInstance().isIDInUse(generated))
        {
            System.out.println(MessageFormat.format("ID #{0} is unavailable. Generating a new one.", generated));
            generated = RANDOM.nextInt(MAX_ID);
        }
        return generated;
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
    
    
    public Challenge getChallenge(int game)
    {
        return challenges.get(game);
    }
    
    
    public static GameManager getInstance()
    {
        return instance;
    }
    
    
}
