package csc312.grid;


/**
 * <p>Represents a grid structure with columns and rows.</p>
 * <p>The area of the grid is {@link #columns} * {@link #rows}</p>
 **/
public abstract class GridBase<T>
{
    private int   columns;
    private int   rows;
    private T[][] contents;
    
    
    /**
     * @param columns The number of columns
     * @param rows    The number of rows
     */
    protected GridBase(int columns, int rows)
    {
        this.columns = columns;
        this.rows = rows;
    }
    
    
    /**
     * Sets the contents of the grid
     *
     * @param objects The contents to set into the grid
     */
    void setContents(T[][] objects)
    {
        this.contents = objects;
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
    T[][] getContents()
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
}
