package main.java.csc312.contests;

import main.java.csc312.GameManager;
import main.java.csc312.countdowns.CountdownCallback;
import main.java.csc312.countdowns.CountdownTask;

import java.text.MessageFormat;
import java.util.Timer;

public class TimedContest extends ContestBase
{
    private long  totalTimeInSecs;
    private Timer timer;
    
    
    public TimedContest(int totalTime)
    {
        this.totalTimeInSecs = totalTime;
    }
    
    
    public void cancel()
    {
        if (timer != null)
        {
            timer.cancel();
            this.timer = null;
        }
        this.onFinish();
    }
    
    
    @Override
    public void onStart()
    {
//        System.out.println("Creating timer for game");
        timer = new Timer();
        timer.scheduleAtFixedRate(new CountdownTask(new CountdownCallback()
        {
            @Override
            public void onBegin()
            {
                System.out.println(MessageFormat.format("Countdown for game {0} started.", GameManager.getInstance().getCurrentID()));
            }
            
            
            @Override
            public void onTick(long remainingTime)
            {
                System.out.println(MessageFormat.format("Game {0} has {1} seconds remaining", GameManager.getInstance().getCurrentID(), remainingTime));
            }
            
            
            @Override
            public void onComplete()
            {
                System.out.println(MessageFormat.format("Countdown for game {0} finished.", GameManager.getInstance().getCurrentID()));
                onFinish();
            }
        }, this.totalTimeInSecs), 0, 1000L); // run after 0ms delay every 1s
    }
    
    
    @Override
    public void onFinish()
    {
        this.setInProgress(false);
        this.setValid(true);
    }
    
    
}
