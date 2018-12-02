package main.java.csc312;

import main.java.csc312.servlet.*;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

class Project2
{
    
    
    private Project2()
    {
        GameManager gameManager = new GameManager();
    }
    
    
    private void onEnable()
    {
        try
        {
            this.startServer();
        }
        catch (LifecycleException e)
        {
            e.printStackTrace();
        }
    }
    
    
    private void startServer() throws LifecycleException
    {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        
        Context ctx = tomcat.addContext("", new File(".").getAbsolutePath());
        
        
        // /newcontest
        Tomcat.addServlet(ctx, "newcontest", new NewContestRoute());
        ctx.addServletMapping("/newcontest", "newcontest");
        
        // /wordfinder
        Tomcat.addServlet(ctx, "wordfinder", new WordFinderRoute());
        ctx.addServletMapping("/wordfinder", "wordfinder");
        
        // /words
        Tomcat.addServlet(ctx, "words", new WordsRoute());
        ctx.addServletMapping("/words", "words");
        
        // /solution
        Tomcat.addServlet(ctx, "solution", new SolutionRoute());
        ctx.addServletMapping("/solution", "solution");
        
        // /topscore
        Tomcat.addServlet(ctx, "topscore", new TopScoreRoute());
        ctx.addServletMapping("/topscore", "topscore");
        
        
        tomcat.start();
        tomcat.getServer().await();
    }
    
    
    public static void main(String[] args)
    {
        Project2 main = new Project2();
        main.onEnable();
    }
    
}