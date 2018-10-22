package csc312;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Week3Assignment
{
    
    //you must implement the function to retrieve the content of a specific URL at https://wordfinder-001.appspot.com/wordfinder
    //
    //be aware that at random  the  ResponseCode may be SC_INTERNAL_SERVER_ERROR  or SC_INTERNAL_SERVER_ERROR instead of SC_OK
    //
    public Character getURL(String url)
    {
        try
        {
            URL myURL = new URL(url);
            
            HttpURLConnection urlConnection = (HttpURLConnection) myURL.openConnection();
            
            String method = urlConnection.getRequestMethod();
            int responseCode = urlConnection.getResponseCode();
            String message = urlConnection.getResponseMessage();
            
            
            switch (responseCode)
            {
                // forbidden
                case 403:
                    return null;
                // Internal Server Error
                case 500:
                    return null;
                // okay
                case 200:
                    break;
            }
            
            InputStream is = urlConnection.getInputStream();
            
            //all communication in socket are done in 'byte', java is unicode for String/char
            
            StringBuilder output = new StringBuilder();
            int c;
            while ((c = is.read()) != -1)
            {
                output.append((char) c);
            }
            
            is.close();
            
            if (output.length() > 0)
            {
                return output.charAt(0);
            }
            return null;
        }
        catch (IOException ioe)
        {
            return null;
        }
    }
}
