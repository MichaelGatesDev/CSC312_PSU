package main.java.csc312.contests;

interface Contest
{
    /**
     * Called when the contest begins
     */
    void onStart();
    
    
    /**
     * Called when the contest ends
     */
    void onFinish();
}
