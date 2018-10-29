package csc312.grid.searchable;

import csc312.grid.GridPosition2D;

import java.util.Arrays;

/**
 * Represents a 'match' within a grid.
 */
public class GridMatch<T>
{
    private final T[]            match;
    private final GridPosition2D start;
    private final GridPosition2D end;
    private       long           timeInMillis = -1L;
    
    
    /**
     * @param match The matching object
     * @param start The position in the grid where the match begins
     * @param end   The position in the grid where the match ends
     */
    GridMatch(T[] match, GridPosition2D start, GridPosition2D end)
    {
        this.match = match;
        this.start = start;
        this.end = end;
    }
    
    
    /**
     * Sets the amount of time it took for the match to find
     *
     * @param l Returns the amount of time in milliseconds or -1 by default
     */
    public void setTimeInMillis(long l)
    {
        this.timeInMillis = l;
    }
    
    
    /**
     * @return The actual matching object
     */
    public T[] getMatch()
    {
        return match;
    }
    
    
    /**
     * @return The position in the grid where the match begins
     */
    public GridPosition2D getStart()
    {
        return start;
    }
    
    
    /**
     * @return The position in the grid where the match ends
     */
    public GridPosition2D getEnd()
    {
        return end;
    }
    
    
    /**
     * The total length of time it took to find the match
     *
     * @return Length of time in milliseconds
     */
    public long getTimeInMillis()
    {
        return timeInMillis;
    }
    
    
    @Override
    public String toString()
    {
        return "GridMatch{" +
                "match=" + Arrays.toString(match) +
                ", start=" + start +
                ", end=" + end +
                ", timeInMillis=" + timeInMillis +
                '}';
    }
}
