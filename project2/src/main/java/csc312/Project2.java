package main.java.csc312;

import main.java.csc312.servlet.NewContestRoute;
import main.java.csc312.servlet.WordFinderRoute;
import main.java.csc312.servlet.WordsRoute;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Project2
{
    private GameManager gameManager;
    
    
    Project2()
    {
        this.gameManager = new GameManager();
    }
    
    
    void onEnable()
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
        
        
        tomcat.start();
        tomcat.getServer().await();
    }
    
    
    public static void main(String[] args)
    {
        Project2 main = new Project2();
        main.onEnable();
    }
    
}