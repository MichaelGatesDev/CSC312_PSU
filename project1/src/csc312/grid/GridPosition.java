package csc312.grid;

import csc312.utils.CharUtils;

public class GridPosition
{
    private int column;
    private int row;
    
    
    public GridPosition(int column, int row)
    {
        this.column = column;
        this.row = row;
    }
    
    
    @Override
    public String toString()
    {
        return "GridPosition{" +
                "column=" + column +
                ", row=" + row +
                '}';
    }
    
    
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof GridPosition))
        {
            return false;
        }
        GridPosition gp = (GridPosition) o;
        return gp.row == this.row && gp.column == this.column;
    }
    
    
    public String toGameString()
    {
        return CharUtils.getPosOfLetterInAlphabet(column) + "" + row;
    }
}
