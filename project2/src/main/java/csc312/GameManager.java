package main.java.csc312;

import main.java.csc312.contests.TimedContest;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

public class GameManager
{
    private static final Random RANDOM = new Random();
    private static final int    MAX_ID = 1000;
    
    private static GameManager instance;
    
    private static final int ROUND_TIME = 120; // in seconds
    public static final  int MIN_GAME   = 1;
    public static final  int MAX_GAME   = 3;
    
    private final Map<Integer, Challenge> challenges;
    
    private final Map<Integer, TimedContest> currentContests;
    private       int                        currentID = -1;
    
    private final Map<Integer, Integer> scores;
    
    
    GameManager()
    {
        instance = this;
        
        // -- BEGIN --
        // create challenges
        challenges = new HashMap<>();
        challenges.put(1, new Challenge(new char[][]{
                        // A    B    C    D    E
                        { 'z', 'B', 'C', 'D', 'a' }, // 1
                        { 'a', 'b', 'B', 'b', 'E' }, // 2
                        { 'p', 'B', 'c', 'b', 'B' }, // 3
                        { 'E', 'b', 'Z', 'e', 'E' }, // 4
                        { 'a', 'E', 'E', 'e', 'Z' }, // 5
                }, "zap")
        );
        challenges.put(2, new Challenge(new char[][]{
                        { 'a', 'B', 'c', 'D', 'e' },
                        { 'B', 'B', 'B', 'c', 'E' },
                        { 'e', 'Z', 'c', 'B', 'z' },
                        { 'E', 'E', 'd', 'b', 'i' },
                        { 'a', 'E', 'y', 'a', 'g' },
                }, "zig")
        );
        challenges.put(3, new Challenge(new char[][]{
                        { 'a', 'B', 'c', 'D', 'e' },
                        { 'B', 'z', 'z', 'o', 'E' },
                        { 'a', 'Z', 'a', 'B', 'e' },
                        { 'E', 'E', 'g', 'E', 'E' },
                        { 'a', 'E', 'e', 'A', 'e' },
                }, "zag")
        );
        // -- END --
        
        // instantiate collections
        this.currentContests = new HashMap<>();
        this.scores = new HashMap<>();
        
        // kills the singleton because the GC may not collect it
        Runtime.getRuntime().addShutdownHook(new Thread(() -> instance = null));
    }
    
    
    /**
     * Creates a new contest with the specified contest ID
     *
     * @param generated The ID to be used for the new contest
     */
    public void newContest(int generated)
    {
        newContest(generated, ROUND_TIME);
    }
    
    
    /**
     * Creates a new contest with the specified contest ID
     *
     * @param generated       The ID to be used for the new contest
     * @param lengthInSeconds The amount of time for the contest
     */
    public void newContest(int generated, int lengthInSeconds)
    {
        // if there exists a contest with the current ID, remove it
        this.removeContest(this.currentID);
        
        System.out.println(MessageFormat.format("Beginning a new game with ID {0}", generated));
        TimedContest tc = new TimedContest(lengthInSeconds);
        tc.onStart();
        this.addContest(generated, tc);
    }
    
    
    /**
     * Registers a contest
     *
     * @param id The ID of the contest
     * @param c  The {@link TimedContest} object
     */
    private void addContest(int id, TimedContest c)
    {
        this.currentID = id;
        TimedContest currentGame = c;
        this.currentContests.put(id, c);
    }
    
    
    /**
     * Unregisters a contest
     *
     * @param id The ID of the contest
     */
    private void removeContest(int id)
    {
        // make sure the ID is actually in use, even though error-checking is implemented in hashmaps
        if (this.isIDInUse(this.currentID))
        {
            // cancel the contest
            this.currentContests.get(currentID).cancel();
            // remove the contest
            this.currentContests.remove(id);
        }
    }
    
    
    /**
     * Generates a random ID number
     *
     * @return Returns a randomly generated number within game constraints
     */
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
    
    
    /**
     * Adds a score
     *
     * @param contestID   The contest ID that the score was achieved in
     * @param elapsedTime The total time that it took to complete the contest
     */
    public void addScore(int contestID, int elapsedTime)
    {
        scores.put(contestID, elapsedTime);
        System.out.println("Added contest " + contestID + " with time " + elapsedTime);
    }
    
    
    /**
     * Takes the scores {@link HashMap} and sorts it by value, smallest->largest
     *
     * @return A a sorted {@link LinkedHashMap}
     */
    public LinkedHashMap<Integer, Integer> getScoresSorted()
    {
        return scores.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(5)
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new
                ));
    }
    
    
    /**
     * Checks if a contest ID is in use
     *
     * @param id The contest ID
     *
     * @return Returns true if the contest ID is currently in use
     */
    public boolean isIDInUse(int id)
    {
        return this.currentContests.containsKey(id);
    }
    
    
    /**
     * Gets the current contest's ID
     *
     * @return Returns the current contest ID
     */
    public int getCurrentID()
    {
        return this.currentID;
    }
    
    
    /**
     * Gets the current contest
     *
     * @return Returns the current {@link TimedContest}
     */
    public TimedContest getCurrentContest()
    {
        return this.currentContests.get(this.currentID);
    }
    
    
    /**
     * Gets the challenge for the specified game
     *
     * @param game The game #
     *
     * @return The challenge for the specified game #
     */
    public Challenge getChallenge(int game)
    {
        return challenges.get(game);
    }
    
    
    /**
     * @return Singleton instance
     */
    public static GameManager getInstance()
    {
        return instance;
    }
    
}
