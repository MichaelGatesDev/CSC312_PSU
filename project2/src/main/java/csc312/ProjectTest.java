package main.java.csc312;

import main.java.csc312.countdowns.CountdownCallback;
import main.java.csc312.countdowns.CountdownTask;
import org.junit.Test;

import java.util.Timer;

public class ProjectTest
{
    @Test
    public void testTimer()
    {
        Timer timer = new Timer();
        int secs = 3;
        CountdownTask ct = new CountdownTask(new CountdownCallback()
        {
            @Override
            public void onBegin()
            {
                System.out.println("Beginning countdown");
            }
            
            
            @Override
            public void onTick(long remainingTime)
            {
                System.out.println("Tick");
            }
            
            
            @Override
            public void onComplete()
            {
                System.out.println("Countdown completed");
            }
        }, secs);
        timer.scheduleAtFixedRate(ct, 0L, secs * 1000L);
    }
}
