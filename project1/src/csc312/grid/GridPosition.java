package csc312.grid;

import csc312.utils.CharUtils;

public class GridPosition
{
    private int column;
    private int row;
    
    
    GridPosition(int column, int row)
    {
        this.column = column;
        this.row = row;
    }
    
    
    @Override
    public String toString()
    {
        return column + "" + row;
    }
    
    
    public String toGameString()
    {
        return CharUtils.getPosOfLetterInAlphabet(column) + "" + row;
    }
}
