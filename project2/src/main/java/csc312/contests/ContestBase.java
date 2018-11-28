package main.java.csc312.contests;

public abstract class ContestBase implements Contest
{
    private boolean inProgress;
    private boolean valid;
    
    
    public void setInProgress(boolean inProgress)
    {
        this.inProgress = inProgress;
    }
    
    
    public void setValid(boolean valid)
    {
        this.valid = valid;
    }
    
    
    public boolean isInProgress()
    {
        return inProgress;
    }
    
    
    public boolean isValid()
    {
        return valid;
    }
}
