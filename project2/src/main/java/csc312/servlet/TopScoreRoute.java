package main.java.csc312.servlet;

import main.java.csc312.GameManager;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;


public class TopScoreRoute extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        LinkedHashMap<Integer, Integer> scores = GameManager.getInstance().getScoresSorted();
        
        ServletOutputStream out = resp.getOutputStream();
        
        for (int i : scores.keySet())
        {
            long value = scores.get(i);
            
            out.write(("ID: " + i + " Time: " + value + "\n").getBytes());
        }
        
        out.flush();
        out.close();
        
        resp.setStatus(HttpServletResponse.SC_OK);
        
    }
}
