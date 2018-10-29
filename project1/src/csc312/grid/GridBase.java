package csc312.grid;


import java.lang.reflect.Array;

/**
 * Represents a grid structure with columns and rows.
 * The area of the grid is {@link #columns} * {@link #rows}
 **/
public abstract class GridBase<T>
{
    private final Class<T> type;
    private final int      columns;
    private final int      rows;
    private final T[][]    contents;
    
    
    /**
     * @param type    The type of the contents contained within the grid
     * @param columns The number of columns the grid has
     * @param rows    The number of rows the grid has
     */
    protected GridBase(Class<T> type, int columns, int rows)
    {
        // Use Array native method to create array
        // of a type only known at run time
        // https://stackoverflow.com/a/18111163/1925638
        @SuppressWarnings("unchecked") final T[][] a = (T[][]) Array.newInstance(type, columns, rows);
        this.type = type;
        this.contents = a;
        this.columns = columns;
        this.rows = rows;
    }
    
    
    /**
     * @param type     The type of the contents contained within the grid
     * @param columns  The number of columns the grid has
     * @param rows     The number of rows the grid has
     * @param contents The contents contained within the grid
     */
    protected GridBase(Class<T> type, int columns, int rows, T[][] contents)
    {
        this.type = type;
        this.columns = columns;
        this.rows = rows;
        this.contents = contents;
    }
    
    
    /**
     * @return The number of columns in the grid
     */
    public int getColumns()
    {
        return columns;
    }
    
    
    /**
     * @return The number of rows in the grid
     */
    public int getRows()
    {
        return rows;
    }
    
    
    /**
     * @return All of the contents in the grid
     */
    public T[][] getContents()
    {
        return contents;
    }
    
    
    /**
     * @param gridDirection The direction of the grid to move in
     * @param num           The number of the row/column
     *
     * @return The contents of a grid in a certain row or column
     */
    public abstract T[] getContentsOf(GridDirection gridDirection, int num);
    
    
    /**
     * @return The type of the contents contained within the grid
     */
    protected Class<T> getType()
    {
        return type;
    }
}
