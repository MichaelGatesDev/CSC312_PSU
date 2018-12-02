package main.java.csc312.countdowns;

public interface CountdownCallback
{
    
    void onBegin();
    
    void onTick(int remainingTime);
    
    void onComplete();
    
}
