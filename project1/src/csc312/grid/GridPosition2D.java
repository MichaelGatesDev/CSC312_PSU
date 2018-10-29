package csc312.grid;

import csc312.utils.CharUtils;

/**
 * Represents a position (x, y) within a grid
 */
public class GridPosition2D
{
    private final int column;
    private final int row;
    
    
    /**
     * @param column The column of the position
     * @param row    The row of the position
     */
    public GridPosition2D(int column, int row)
    {
        this.column = column;
        this.row = row;
    }
    
    
    @Override
    public String toString()
    {
        return "GridPosition2D{" +
                "column=" + column +
                ", row=" + row +
                '}';
    }
    
    
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof GridPosition2D))
        {
            return false;
        }
        GridPosition2D gp = (GridPosition2D) o;
        return gp.row == this.row && gp.column == this.column;
    }
    
    
    /**
     * Converts the grid position into a string which can be used by the game to access the web server
     *
     * @return The converted position
     */
    public String toGameString()
    {
        return CharUtils.getPosOfLetterInAlphabet(column) + "" + row;
    }
}
