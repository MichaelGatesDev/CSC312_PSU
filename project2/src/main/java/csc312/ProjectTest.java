package main.java.csc312;

import main.java.csc312.contests.ContestBase;
import main.java.csc312.contests.TimedContest;
import main.java.csc312.servlet.WordsRoute;
import main.java.csc312.utils.GameUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

@SuppressWarnings("Duplicates")
public class ProjectTest
{
    // -validation that the word list is correct
    @Test
    public void testWordList()
    {
        System.out.println("Testing word list...");
        Set<String> words = new HashSet<>();
        words.add("zap");
        words.add("zep");
        words.add("zip");
        words.add("zig");
        words.add("zag");
        Assert.assertTrue(Arrays.asList(WordsRoute.WORDS).containsAll(words));
        Assert.assertTrue(words.contains("zip"));
        Assert.assertFalse(words.contains("zup"));
        System.out.println("Test of word list complete");
    }
    
    
    // -validation of starting a new contest
    @Test
    public void testNewContest()
    {
        System.out.println("Testing new contest...");
        GameManager gm = new GameManager();
        
        // until a game has been started, it should be null
        Assert.assertNull(gm.getCurrentContest());
        
        // until a game has been started, the current id should be -1
        Assert.assertEquals(gm.getCurrentID(), -1);
        
        // create new game
        int generated = gm.generateRandomID();
        gm.newGame(generated);
        
        // game has been created so its id should not be -1
        Assert.assertNotEquals(gm.getCurrentID(), -1);
        // game has been created so it should not be null
        Assert.assertNotNull(gm.getCurrentContest());
        
        ContestBase cb = gm.getCurrentContest();
        
        // the contest just started so it should be valid
        Assert.assertTrue(cb.isValid());
        
        // the contest just started so it should be in progress
        Assert.assertTrue(cb.isInProgress());
        
        // cancel the game
        TimedContest tc = (TimedContest) cb;
        tc.cancel();
        Assert.assertFalse(tc.isInProgress());
        Assert.assertFalse(tc.isValid()); // TODO is this worth keeping?
        System.out.println("Test of new contest complete");
    }
    
    
    // -validation of the timeout process for a contest
    @Test
    public void testTimer()
    {
        System.out.println("Testing timer...");
        GameManager gm = new GameManager();
        // create new game
        int generated = gm.generateRandomID();
        gm.newGame(generated, 3);
        ContestBase cb = gm.getCurrentContest();
        
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
        
        System.out.println("Test of timer complete");
    }
    
    
    // -validation of requesting for a letter (valid and invalid values for each parameter and combination)
    @Test
    public void testLetterRequestA()
    {
        GameManager gm = new GameManager(); // needed to initialize singleton
        
        String url = "http://localhost:8080/wordfinder?contest=822&game=1&pos=A1";
        String[] rawParams = url.replace("http://localhost:8080/wordfinder?", "").split("&");
        
        Map<String, String> params = new HashMap<>();
        for (String param : rawParams)
        {
            String[] kv = param.split("=");
            Assert.assertEquals(kv.length, 2);
            String key = kv[0];
            String value = kv[1];
            
            params.put(key, value);
        }
        
        Assert.assertTrue(params.containsKey("contest"));
        String rawContest = params.get("contest");
        int contest = Integer.parseInt(rawContest);
        Assert.assertTrue(contest >= 0 && contest <= 1000);
        
        Assert.assertTrue(params.containsKey("game"));
        String rawGame = params.get("game");
        int game = Integer.parseInt(rawGame);
        Assert.assertEquals(game, 1);
        
        Challenge challenge = GameManager.getInstance().getChallenge(game);
        Assert.assertNotNull(challenge);
        
        String rawPos = params.get("pos");
        char rawCol = rawPos.charAt(0);
        int row = Integer.parseInt(rawPos.charAt(1) + "") - 1;
        int col = GameUtils.getColEquiv(rawCol) - 1;
        Assert.assertTrue(col >= 0 && col <= 4);
        Assert.assertTrue(row >= 0 && row <= 4);
        
        Assert.assertEquals(challenge.getCharAt(row, col), 'z'); // A1 is first position
        
        Assert.assertEquals(challenge.getCharAt(0, 0), 'z');
        Assert.assertEquals(challenge.getCharAt(1, 0), 'a');
        Assert.assertEquals(challenge.getCharAt(2, 0), 'p');
    }
    
    
    // -validation of requesting for a letter (valid and invalid values for each parameter and combination)
    @Test
    public void testLetterRequestB()
    {
        GameManager gm = new GameManager(); // needed to initialize singleton
        
        String url = "http://localhost:8080/wordfinder?contest=3138&game=0&pos=D0";
        String[] rawParams = url.replace("http://localhost:8080/wordfinder?", "").split("&");
        
        Map<String, String> params = new HashMap<>();
        for (String param : rawParams)
        {
            String[] kv = param.split("=");
            Assert.assertEquals(kv.length, 2);
            String key = kv[0];
            String value = kv[1];
            
            params.put(key, value);
        }
        
        Assert.assertTrue(params.containsKey("contest"));
        String rawContest = params.get("contest");
        int contest = Integer.parseInt(rawContest);
        Assert.assertFalse(contest >= 0 && contest <= 1000);
        
        Assert.assertTrue(params.containsKey("game"));
        String rawGame = params.get("game");
        int game = Integer.parseInt(rawGame);
        Assert.assertFalse(game >= 1 && game <= 3);
        
        Challenge challenge = GameManager.getInstance().getChallenge(game);
        Assert.assertNull(challenge);
        
        String rawPos = params.get("pos");
        char rawCol = rawPos.charAt(0);
        int row = Integer.parseInt(rawPos.charAt(1) + "") - 1;
        int col = GameUtils.getColEquiv(rawCol) - 1;
        Assert.assertFalse(row >= 0 && row <= 4); // invalid position
        Assert.assertTrue(col >= 0 && col <= 4);
    }
    
    
    // -validation of submitting a solution (valid and invalid submission + management of the contest)
    @Test
    public void testSolution()
    {
        GameManager gm = new GameManager(); // needed to initialize singleton
        
        String url = "http://localhost:8080/solution?contest=313&game=1&solution=zap";
        String[] rawParams = url.replace("http://localhost:8080/solution?", "").split("&");
        
        Map<String, String> params = new HashMap<>();
        for (String param : rawParams)
        {
            String[] kv = param.split("=");
            Assert.assertEquals(kv.length, 2);
            String key = kv[0];
            String value = kv[1];
            
            params.put(key, value);
        }
        
        Assert.assertTrue(params.containsKey("contest"));
        String rawContest = params.get("contest");
        int contest = Integer.parseInt(rawContest);
        Assert.assertTrue(contest >= 0 && contest <= 1000);
        
        Assert.assertTrue(params.containsKey("game"));
        String rawGame = params.get("game");
        int game = Integer.parseInt(rawGame);
        Assert.assertTrue(game >= 1 && game <= 3);
        
        Challenge challenge = GameManager.getInstance().getChallenge(game);
        Assert.assertNotNull(challenge);
        
        Assert.assertEquals(challenge.getSolution(), params.get("solution"));
    }
    
    
    // -validation of the top score
    @Test
    public void testTopScores()
    {
        GameManager gm = new GameManager(); // needed to initialize singleton
        
        int idA = GameManager.getInstance().generateRandomID();
        int idB = GameManager.getInstance().generateRandomID();
        int idC = GameManager.getInstance().generateRandomID();
        int idD = GameManager.getInstance().generateRandomID();
        int idE = GameManager.getInstance().generateRandomID();
        int idF = GameManager.getInstance().generateRandomID();
        int idG = GameManager.getInstance().generateRandomID();
        gm.addScore(idA, 10);
        gm.addScore(idB, 20);
        gm.addScore(idC, 40);
        gm.addScore(idD, 80);
        gm.addScore(idE, 160);
        gm.addScore(idF, 300);
        gm.addScore(idG, 500);
        
        LinkedHashMap<Integer, Integer> scoresSorted = gm.getScoresAscending();
        
        Object[] values = scoresSorted.values().toArray();
        Assert.assertEquals(values[0], 10);
        Assert.assertEquals(values[1], 20);
        Assert.assertEquals(values[2], 40);
        Assert.assertEquals(values[3], 80);
        Assert.assertEquals(values[4], 160);
        
        
    }
    
}
