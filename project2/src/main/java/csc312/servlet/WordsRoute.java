package main.java.csc312.servlet;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@SuppressWarnings("Duplicates")
public class WordsRoute extends HttpServlet
{
    public static final String[] WORDS = {
            "zap",
            "zep",
            "zip",
            "zag",
            "zig"
    };
    
    
    /*
    http://localhost:8080/words
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        resp.setStatus(HttpServletResponse.SC_OK);
        
        ServletOutputStream out = resp.getOutputStream();
        for (String s : WORDS)
        {
            out.write((s + "\n").getBytes());
        }
        out.flush();
        out.close();
        
    }
}
