package main.java.csc312.countdowns;

public interface CountdownCallback
{
    /**
     * Called when the countdown begins
     */
    void onBegin();
    
    
    /**
     * Called every time the countdown ticks
     *
     * @param remainingTime The current amount of remaining time at the time of the tick
     */
    void onTick(int remainingTime);
    
    
    /**
     * Called when the countdown ends
     */
    void onComplete();
    
}
