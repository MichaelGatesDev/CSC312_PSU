package main.java.csc312.countdowns;

import java.util.TimerTask;

/**
 * Performs a countdown in seconds
 */
public class CountdownTask extends TimerTask
{
    private final CountdownCallback countdownCallback;
    private final long              minTime;
    private final long              totalTime;
    private       long              remainingTime; // the amount of time remaining in the countdown
    
    
    /**
     * @param countdownCallback
     * @param minTime           The minimum possible time (this is usually 0)
     * @param totalTime         The total amount of time to run the task
     */
    public CountdownTask(CountdownCallback countdownCallback, long minTime, long totalTime)
    {
        this.countdownCallback = countdownCallback;
        this.minTime = minTime;
        this.totalTime = totalTime;
        this.remainingTime = totalTime;
    }
    
    
    /**
     * @param countdownCallback
     * @param totalTime         The total amount of time to run the task
     */
    public CountdownTask(CountdownCallback countdownCallback, long totalTime)
    {
        this.countdownCallback = countdownCallback;
        this.minTime = 0;
        this.totalTime = totalTime;
        this.remainingTime = totalTime;
    }
    
    
    @Override
    public void run()
    {
        // begin
        if (this.remainingTime == this.totalTime)
        {
            this.countdownCallback.onBegin();
        }
        // end
        if (this.remainingTime <= this.minTime)
        {
            this.countdownCallback.onComplete();
            this.cancel();
            return;
        }
        // tick
        remainingTime--;
        countdownCallback.onTick(remainingTime);
    }
}

