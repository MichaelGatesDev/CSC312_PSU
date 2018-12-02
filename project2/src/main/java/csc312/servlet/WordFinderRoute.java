package main.java.csc312.servlet;

import main.java.csc312.Challenge;
import main.java.csc312.GameManager;
import main.java.csc312.contests.TimedContest;
import main.java.csc312.utils.GameUtils;
import main.java.csc312.utils.WebUtils;

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
        int contestID = WebUtils.getIntParamChecked(req, "contest");
        if (contestID == -1 || !GameManager.getInstance().isIDInUse(contestID))
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        TimedContest contest = GameManager.getInstance().getCurrentContest();
        if (contest == null || !contest.isValid())
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        if (!contest.isInProgress())
        {
            resp.setStatus(HttpServletResponse.SC_GONE);
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
        
        int colEquiv = GameUtils.getColEquiv(col);
        if (colEquiv < 1 || row < 1 || colEquiv > 5 || row > 5)
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        resp.setStatus(HttpServletResponse.SC_OK);
        
        ServletOutputStream out = resp.getOutputStream();

//        System.out.println("Contest: " + game + " | Game: " + game + " | Column: " + col + " | Row: " + row);
        
        Challenge challenge = GameManager.getInstance().getChallenge(game);
        if (challenge == null)
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        char c = challenge.getCharAt(row - 1, colEquiv - 1);
        out.write((c + "").getBytes());
        out.flush();
        out.close();
        
        contest.incrementRequests();
    }
    
}
