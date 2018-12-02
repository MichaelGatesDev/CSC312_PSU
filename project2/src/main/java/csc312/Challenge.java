package main.java.csc312;

public class Challenge
{
    private char[][] grid;
    private String   solution;
    
    
    Challenge(char[][] grid, String solution)
    {
        this.grid = grid;
        this.solution = solution;
    }
    
    
    public char getCharAt(int row, int col)
    {
        return grid[row][col];
    }
    
    
    public String getSolution()
    {
        return solution;
    }
}
