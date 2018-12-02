package main.java.csc312;

public class Challenge
{
    private final char[][] grid;
    private final String   solution;
    
    
    /**
     * @param grid     The Grid (2D Character Array)
     * @param solution The solution to the challenge
     */
    Challenge(char[][] grid, String solution)
    {
        this.grid = grid;
        this.solution = solution;
    }
    
    
    /**
     * Gets the character at the specified position in the grid
     *
     * @param row The row #
     * @param col The column #
     *
     * @return The character at the given position
     */
    public char getCharAt(int row, int col)
    {
        return grid[row][col];
    }
    
    
    /**
     * Gets the solution for the challenge
     *
     * @return The solution for the challenge
     */
    public String getSolution()
    {
        return solution;
    }
}
