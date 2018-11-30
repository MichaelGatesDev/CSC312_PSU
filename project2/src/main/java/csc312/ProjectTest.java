package main.java.csc312;

import main.java.csc312.contests.ContestBase;
import main.java.csc312.contests.TimedContest;
import main.java.csc312.servlet.WordsRoute;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
    
    -validation of submitting a solution (valid and invalid submission + management of the contest)
    
    -validation of the top score
 */

public class ProjectTest
{
    // -validation that the word list is correct
    @Test
    public void testWordList()
    {
        Set<String> words = new HashSet<>();
        words.add("zap");
        words.add("zep");
        words.add("zip");
        words.add("zig");
        words.add("zag");
        Assert.assertTrue(Arrays.asList(WordsRoute.WORDS).containsAll(words));
        Assert.assertTrue(words.contains("zip"));
        Assert.assertFalse(words.contains("zup"));
    }
    
    
    // -validation of starting a new contest
    @Test
    public void testNewContest()
    {
        GameManager gm = new GameManager();
        
        // until a game has been started, it should be null
        Assert.assertNull(gm.getCurrentGame());
        
        // until a game has been started, the current id should be -1
        Assert.assertEquals(gm.getCurrentID(), -1);
        
        // create new game
        int generated = gm.generateRandomID();
        gm.newGame(generated);
        
        // game has been created so its id should not be -1
        Assert.assertNotEquals(gm.getCurrentID(), -1);
        // game has been created so it should not be null
        Assert.assertNotNull(gm.getCurrentGame());
        
        ContestBase cb = gm.getCurrentGame();
        
        // the contest just started so it should be valid
        Assert.assertTrue(cb.isValid());
        
        // the contest just started so it should be in progress
        Assert.assertTrue(cb.isInProgress());
        
        // cancel the game
        TimedContest tc = (TimedContest) cb;
        tc.cancel();
        Assert.assertFalse(tc.isInProgress());
        Assert.assertFalse(tc.isValid()); // TODO is this worth keeping?
    }
    
    
    // -validation of the timeout process for a contest
    @Test
    public void testTimer()
    {
        GameManager gm = new GameManager();
        // create new game
        int generated = gm.generateRandomID();
        gm.newGame(generated, 3);
        ContestBase cb = gm.getCurrentGame();
        
        Assert.assertTrue(cb.isInProgress());
        Assert.assertTrue(cb.isValid());
        
        try
        {
            Thread.sleep(3100); // 3 seconds is too short, so 3.1 works
            
            Assert.assertFalse(cb.isInProgress());
            Assert.assertTrue(cb.isValid()); // TODO is this worth keeping?
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    
    // -validation of requesting for a letter (valid and invalid values for each parameter and combination)
    @Test
    public void testLetterRequest()
    {
        // http://localhost:8080/wordfinder?contest=822&game=1&pos=A1
        
        
    }
    
    
}
