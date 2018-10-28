package csc312.utils;

import csc312.Settings;
import csc312.grid.searchable.SearchableCharGrid;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloaderOld
{
    private static final String GAME_URL_TEMPLATE = "https://wordfinder-001.appspot.com/wordfinder?game=%d&pos=%c%d";
    
    
    public static SearchableCharGrid downloadEntireGrid(int gameNum)
    {
        Character[][] contents = new Character[Settings.COLUMNS][Settings.ROWS];
//        int total = 0;
        for (int x = 0; x < Settings.ROWS; x++)
        {
            for (int y = 0; y < Settings.COLUMNS; y++)
            {
                String s = download(String.format(GAME_URL_TEMPLATE, gameNum, CharUtils.getPosOfLetterInAlphabet(x), y + 1));
                if (s.length() > 0)
                {
                    contents[x][y] = s.charAt(0);
                }
//                System.out.println("Downloaded #" + total + ": " + s);
//                total++;
            }
        }
        return new SearchableCharGrid(Settings.COLUMNS, Settings.ROWS, contents);
    }
    
    
    public static String download(String url)
    {
        URL myURL;
        HttpURLConnection urlConnection;
        InputStream is = null;
        StringBuilder output = new StringBuilder();
        try
        {
//            System.out.println(url);
            myURL = new URL(url);
            urlConnection = (HttpURLConnection) myURL.openConnection();
            is = urlConnection.getInputStream();
            
            int responseCode = urlConnection.getResponseCode();
            String msg = urlConnection.getResponseMessage();
            
            int c;
            while ((c = is.read()) != -1)
            {
                output.append((char) c);
            }
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        finally
        {
            if (is != null)
            {
                try
                {
                    is.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return output.toString();
    }
    
}
