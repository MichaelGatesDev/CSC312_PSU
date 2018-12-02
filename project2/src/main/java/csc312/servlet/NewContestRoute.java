package main.java.csc312.servlet;

import main.java.csc312.GameManager;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    1-Starting a contest: an user must start a contest, before requesting a letter, with  a url such as :
    http://localhost:8080/newcontest
    
    The response to newcontest, will return a random number ranging between 1-1000,
    that will be used for subsequent request as contestid.
    
    You must make sure, you do not used a contest id which is currently in used.
    For each subsequent request to the server, the contestid must be specified on the url.
    
    For each contest, a 120 seconds timer countdown must be started. When 0 is reached, subsequent request will be returned the status code HttpServletResponse.SC_GONE.
    

 */
public class NewContestRoute extends HttpServlet
{
    private int requests = 0;
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        int generated = GameManager.getInstance().generateRandomID();
        GameManager.getInstance().newGame(generated);
        
        resp.setStatus(HttpServletResponse.SC_OK);
        
        ServletOutputStream out = resp.getOutputStream();
        
        out.write((generated + "").getBytes());
        out.flush();
        out.close();
        
        requests++;
    }
}
