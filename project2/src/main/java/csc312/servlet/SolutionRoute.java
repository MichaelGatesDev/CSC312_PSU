package main.java.csc312.servlet;

import main.java.csc312.GameManager;
import main.java.csc312.WebUtils;

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
        
        String solution = req.getParameter("solution");
        if (solution == null || solution.isEmpty())
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        resp.setStatus(HttpServletResponse.SC_OK);
        
        ServletOutputStream out = resp.getOutputStream();
        
        out.write(("Contest: " + game + " | Game: " + game + " | Solution: " + solution).getBytes());
        out.flush();
        out.close();
        
    }
}
