package main.java.csc312.servlet;

import main.java.csc312.GameManager;
import main.java.csc312.WebUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@SuppressWarnings("Duplicates")
public class WordFinderRoute extends HttpServlet
{
    /*
    http://localhost:8080/wordfinder?contest=<contest id>&game=<1 to 3>&pos=<column><row>
    http://localhost:8080/wordfinder?contest=822&game=1&pos=A1
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        int contest = WebUtils.getIntParamChecked(req, "contest");
        if (contest == -1 || !GameManager.getInstance().isIDInUse(contest))
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        int game = WebUtils.getIntParamChecked(req, "game");
        if (game < GameManager.MIN_GAME || game > GameManager.MAX_GAME)
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        String rawPos = req.getParameter("pos");
        if (rawPos == null || rawPos.length() < 2)
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        rawPos = rawPos.substring(0, 2);
        char col = rawPos.charAt(0);
        int row = Integer.parseInt(rawPos.charAt(1) + "");
        
        
        resp.setStatus(HttpServletResponse.SC_OK);
        
        ServletOutputStream out = resp.getOutputStream();
        
        System.out.println("Contest: " + game + " | Game: " + game + " | Column: " + col + " | Row: " + row);
        out.write(("Contest: " + game + " | Game: " + game + " | Column: " + col + " | Row: " + row).getBytes());
        out.flush();
        out.close();
        
    }
}
