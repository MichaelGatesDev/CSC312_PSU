package main.java.csc312.utils;

public class GameUtils
{
    private static final char[] COLUMNS = { 'A', 'B', 'C', 'D', 'E' };
    
    
    public static int getColEquiv(char c)
    {
        for (int i = 0; i < COLUMNS.length; i++)
        {
            if (COLUMNS[i] == c)
            {
                return i + 1;
            }
        }
        return -1;
    }
}
