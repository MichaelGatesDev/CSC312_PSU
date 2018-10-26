package csc312.grid.searchable;

import csc312.grid.GridPosition;

import java.util.Arrays;

/**
 * <p>Represents a 'match' within a grid.</p>
 */
public class GridMatch<T>
{
    private final T[]          match;
    private final GridPosition start;
    private final GridPosition end;
    private       long         timeInMillis = -1L;
    
    
    /**
     * @param match The matching object
     * @param start The position in the grid where the match begins
     * @param end   The position in the grid where the match ends
     */
    GridMatch(T[] match, GridPosition start, GridPosition end)
    {
        this.match = match;
        this.start = start;
        this.end = end;
    }
    
    
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
    public GridPosition getStart()
    {
        return start;
    }
    
    
    /**
     * @return The position in the grid where the match ends
     */
    public GridPosition getEnd()
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
