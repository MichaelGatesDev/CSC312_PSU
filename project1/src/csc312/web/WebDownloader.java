package csc312.web;

import csc312.Settings;
import csc312.grid.searchable.SearchableCharGrid;
import csc312.utils.CharUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebDownloader
{
    private static final String GAME_URL_TEMPLATE = "https://wordfinder-001.appspot.com/wordfinder?game=%d&pos=%c%d";
    
    
    public void downloadContent(String url, Callback<WebDownload> callback, int attempts)
    {
        boolean success = false;
        int n = 0;
        while (!success && n < attempts)
        {
//            System.out.println("Download attempt " + n);
            success = downloadContent(url, callback);
            n++;
        }
        callback.setAttempts(n);
    }
    
    
    public boolean downloadContent(String url, Callback<WebDownload> callback)
    {
        URL realURL;
        HttpURLConnection urlConnection;
        InputStream is = null;
        StringBuilder output = new StringBuilder();
        int responseCode = -1;
        try
        {
            realURL = new URL(url);
            urlConnection = (HttpURLConnection) realURL.openConnection();
            is = urlConnection.getInputStream();
            
            responseCode = urlConnection.getResponseCode();
//            String msg = urlConnection.getResponseMessage();
            
            
            int c;
            while ((c = is.read()) != -1)
            {
                output.append((char) c);
            }
//                    System.out.println("SUCCESS for URL " + url);
            callback.onSuccess(new WebDownload<>(output.toString(), WebDownloadResult.SUCCESS));
            return true;
        }
        catch (IOException ignored)
        {
            switch (responseCode)
            {
                default:
                case -1:
                    break;
                case 403:
                {
//                    System.out.println("403!");
                    callback.onFailure(new WebDownload<String>(null, WebDownloadResult.SC_FORBIDDEN));
                    break;
                }
                case 500:
                {
//                    System.out.println("500!");
                    callback.onFailure(new WebDownload<String>(null, WebDownloadResult.SC_INTERNAL_ERROR));
                    break;
                }
            }
            return false;
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
    }
    
    
    public SearchableCharGrid downloadGridFor(int gameNum)
    {
        Character[][] contents = new Character[Settings.COLUMNS][Settings.ROWS];
//        int total = 0;
        for (int x = 0; x < Settings.ROWS; x++)
        {
            for (int y = 0; y < Settings.COLUMNS; y++)
            {
                final String[] s = new String[1];
                
                int finalX = x;
                int finalY = y;
                downloadContent(String.format(GAME_URL_TEMPLATE, gameNum, CharUtils.getPosOfLetterInAlphabet(x), y + 1), new Callback<WebDownload>()
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
                        s[0] = ((String) result.getValue());
                        if (s[0].length() > 0)
                        {
                            contents[finalX][finalY] = s[0].charAt(0);
                        }
                    }
                    
                    
                    @Override
                    public void onFailure(WebDownload result)
                    {
                        System.out.println("The download failed after " + this.getAttempts() + " tries!");
                    }
                }, 5);
//                System.out.println("Downloaded #" + total + ": " + s);
//                total++;
            }
        }
        return new SearchableCharGrid(Settings.COLUMNS, Settings.ROWS, contents);
    }
}
