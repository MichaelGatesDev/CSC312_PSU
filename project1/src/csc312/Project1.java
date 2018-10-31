package csc312;

import csc312.utils.StringUtils;
import csc312.web.Callback;
import csc312.web.WebDownload;
import csc312.web.WebDownloader;

import java.util.ArrayList;
import java.util.List;

/**
 * Michael Gates
 * CSC312
 * 'word search' project
 * November 2018
 */
class Project1
{
    
    public static void main(String[] args)
    {
        WebDownloader downloader = new WebDownloader();
        
        // Download the games
        List<Game> games = new ArrayList<>();
        for (int i = 0; i < Settings.TOTAL_GAMES; i++)
        {
//            System.out.println("Downloading game #" + (i + 1));
            games.add(new Game(i + 1, downloader.downloadGridFor(i + 1)));
//            System.out.println("Downloaded game #" + (i + 1));
        }
        
        // Download the words
//        System.out.println("Downloading words...");
        final String[] rawWords = new String[1];
        downloader.downloadContent("https://wordfinder-001.appspot.com/word.txt", new Callback<WebDownload>()
        {
            private int attempts = 0;
            
            
            @Override
            public void setAttempts(int n)
            {
                this.attempts = n;
            }
            
            
            @Override
            public int getAttempts()
            {
                return this.attempts;
            }
            
            
            @Override
            public void onComplete(WebDownload result)
            {
            }
            
            
            @Override
            public void onSuccess(WebDownload result)
            {
                if (!(result.getValue() instanceof String))
                {
                    return;
                }
                rawWords[0] = ((String) result.getValue()).toLowerCase().trim();
            }
            
            
            @Override
            public void onFailure(WebDownload result)
            {
                System.out.println("Failed to download words list. Exiting...");
                System.exit(0);
            }
        });
        List<String> words = StringUtils.stringToList(rawWords[0], "\n");
//        System.out.println("Downloaded  " + words.size() + " words");
        
        // Process the games
        for (Game g : games)
        {
            GameResult result = g.getResult(words);
            System.out.println(result.toString());
        }
    }
    
    
}
