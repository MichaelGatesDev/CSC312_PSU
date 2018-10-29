package csc312;

import csc312.utils.StringUtils;
import csc312.web.WebDownloader;

import java.util.ArrayList;
import java.util.List;

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
        downloader.downloadContent("https://wordfinder-001.appspot.com/word.txt", result ->
        {
            if (!(result.getValue() instanceof String))
            {
                return;
            }
            rawWords[0] = ((String) result.getValue()).toLowerCase().trim();
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
