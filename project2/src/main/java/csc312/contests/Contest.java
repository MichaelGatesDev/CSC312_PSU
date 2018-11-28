package main.java.csc312.contests;

public interface Contest
{
    void onStart();
    
    boolean inProgress();
    
    void onFinish();
}
