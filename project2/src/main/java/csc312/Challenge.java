package main.java.csc312;

public class Challenge
{
    private char[][] grid;
    private String   solution;
    
    
    public Challenge(char[][] grid, String solution)
    {
        this.grid = grid;
        this.solution = solution;
    }
    
    
    public char[][] getGrid()
    {
        return grid;
    }
    
    
    public String getSolution()
    {
        return solution;
    }
}
