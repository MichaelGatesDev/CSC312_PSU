package csc312;

/*
    -find in a grid of 5 x 5 of letters, if there is a word from the list of given words present in that grid.
    -the words can be in column or in a row, not in diagonal or in reverse
    -the words are case sensitive and all are composed of 3 letters
    - Your program must download the list of words at each execution, as it may change over time. The list of words to search for is accessible thru: https://wordfinder-001.appspot.com/word.txt ; (List of words extracted from: http://wordfinder.yourdictionary.com/letter-words/3)
    - The main class must be named: project1.java and be in the package csc312.
    - When you locate a word from the list, you must output its beginning position and its ending position <column><row>:<column><row>.
    - As an example, if you search for jar in this array:
        A B j a r
        A z D e F
        z f  D q W
        P T w V Q
        Z O q H I
        The word jar is located at : C1:E1
    - The output should be : game<1-3> word:<word found> location:<beginning-end>
    - To access a letter at a specific location: https://wordfinder-001.appspot.com?game=<1 to 3>&pos=<column><row>
        Column: a-e
        Row: 1-5
    - As an example : https://wordfinder-001.appspot.com/wordfinder?game=1&pos=b5 will return the letter E, which is the letter in the 2nd column, the 5th row.
    
    
    The system you are accessing to extract the letter, may produce status code SC_INTERNAL_SERVER_ERROR=500 or SC_FORBIDDEN=403 at random interval, if this happen, you must retry accessing the resource up to 5 times.
    
    To test your error handling for  SC_INTERNAL_SERVER_ERROR and  SC_FORBIDDEN, there are specific pos that will generate it all the time:
    If you use pos=Z99, the status code will be SC_INTERNAL_SERVER_ERROR.
    If you use pos=Z88, the status code will be SC_FORBIDDEN.
*/

import csc312.utils.Downloader;
import csc312.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

class Project1
{
    
    public static void main(String[] args)
    {
        // Download the games
        List<Game> games = new ArrayList<>();
        for (int i = 0; i < Settings.TOTAL_GAMES; i++)
        {
            System.out.println("Downloading game #" + (i + 1));
            games.add(new Game(i + 1, Downloader.downloadGrid(i + 1)));
            System.out.println("Downloaded game #" + (i + 1));
        }
        
        // Download the words
        System.out.println("Downloading words...");
        String rawWords = Downloader.download("https://wordfinder-001.appspot.com/word.txt").toLowerCase().trim();
        List<String> words = StringUtils.stringToList(rawWords, "\n");
        System.out.println("Downloaded  " + words.size() + " words");
        
        // Process the games
        for (Game g : games)
        {
            GameResult result = g.getResult(words);
            System.out.println(result.toString());
        }
        
    }
    
}
