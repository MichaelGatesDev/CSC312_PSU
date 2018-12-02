package main.java.csc312.servlet;

import main.java.csc312.Challenge;
import main.java.csc312.GameManager;
import main.java.csc312.contests.TimedContest;
import main.java.csc312.utils.WebUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@SuppressWarnings("Duplicates")
public class SolutionRoute extends HttpServlet
{
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
        Challenge challenge = GameManager.getInstance().getChallenge(game);
        
        String solution = req.getParameter("solution");
        if (solution == null || solution.isEmpty())
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        // the contest must terminate
        contest.cancel();
        
        if (!solution.equals(challenge.getSolution())) // not a valid solution
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        
        resp.setStatus(HttpServletResponse.SC_OK);
        
        ServletOutputStream out = resp.getOutputStream();
        out.write(("Time: " + contest.getElapsedTime() + "\n").getBytes());
        out.write(("Letter Requests: " + contest.getTotalLetterRequests() + "\n").getBytes());
        out.flush();
        out.close();
        
        GameManager.getInstance().addScore(contestID, contest.getElapsedTime());
    }
}
