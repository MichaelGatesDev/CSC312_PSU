package main.java.csc312.contests;

public abstract class ContestBase implements Contest
{
    private boolean inProgress;
    private boolean valid;
    
    
    /**
     * Sets the state of the contest
     *
     * @param inProgress {@link Boolean} value for the state (true means in-progress, false means not in-progress)
     */
    public void setInProgress(boolean inProgress)
    {
        this.inProgress = inProgress;
    }
    
    
    /**
     * Sets the validity of the contest
     *
     * @param valid {@link Boolean} value for the validity (true means valid, false means not valid)
     */
    public void setValid(boolean valid)
    {
        this.valid = valid;
    }
    
    
    /**
     * @return Returns true if the contest is in-progress otherwise false
     */
    public boolean isInProgress()
    {
        return inProgress;
    }
    
    
    /**
     * @return Returns true if the contest is valid otherwise false
     */
    public boolean isValid()
    {
        return valid;
    }
    
    
    /**
     * @return Returns the total amount of letter requests that have been made to the contest
     */
    public abstract int getTotalLetterRequests();
}
