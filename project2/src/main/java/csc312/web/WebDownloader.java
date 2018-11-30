package main.java.csc312.web;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebDownloader
{
    public boolean downloadContent(String url, Callback<WebDownload> callback)
    {
        URL realURL;
        HttpURLConnection urlConnection;
        InputStream is = null;
        StringBuilder output = new StringBuilder();
        try
        {
            realURL = new URL(url);
            urlConnection = (HttpURLConnection) realURL.openConnection();
            is = urlConnection.getInputStream();
            
            int c;
            while ((c = is.read()) != -1)
            {
                output.append((char) c);
            }
            callback.onSuccess(new WebDownload<>(output.toString(), WebDownloadResult.SUCCESS));
            return true;
        }
        catch (IOException ignored)
        {
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
    
}
