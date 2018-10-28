package csc312.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebDownloader
{
    /*
    The system you are accessing to extract the letter, may produce status code SC_INTERNAL_SERVER_ERROR=500 or SC_FORBIDDEN=403 at random interval, if this happen, you must retry accessing the resource up to 5 times.
    
    To test your error handling for  SC_INTERNAL_SERVER_ERROR and  SC_FORBIDDEN, there are specific pos that will generate it all the time:
    
    If you use pos=Z99, the status code will be SC_INTERNAL_SERVER_ERROR.
    
    If you use pos=Z88, the status code will be SC_FORBIDDEN.
     */
    public enum WebDownloadResult
    {
        UNKNOWN,
        SUCCESS,
        SC_INTERNAL_ERROR,
        SC_FORBIDDEN
    }
    
    private class WebDownload
    {
        WebDownloadResult result;
        String            content;
        
        
        public WebDownload(WebDownloadResult result, String content)
        {
            this.result = result;
            this.content = content;
        }
    }
    
    
    public WebDownload downloadContent(String url)
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
            
            int responseCode = urlConnection.getResponseCode();
            String msg = urlConnection.getResponseMessage();
            
            switch (responseCode)
            {
                case 500:
                    System.out.println("500!");
                    return new WebDownload(WebDownloadResult.UNKNOWN, "????");
            }
            
            
            int c;
            while ((c = is.read()) != -1)
            {
                output.append((char) c);
            }
            return new WebDownload(WebDownloadResult.SUCCESS, output.toString());
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
        return null;
    }
}
