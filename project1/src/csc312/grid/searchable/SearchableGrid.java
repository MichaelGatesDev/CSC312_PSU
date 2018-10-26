package csc312.grid.searchable;

import csc312.Settings;
import csc312.grid.GridBase;
import csc312.grid.GridDirection;

import java.lang.reflect.Array;

public abstract class SearchableGrid<T> extends GridBase<T> implements GridSearchable<T>
{
    /**
     * @param type    The type of the contents contained within the grid
     * @param columns The number of columns the grid has
     * @param rows    The number of rows the grid has
     */
    SearchableGrid(Class<T> type, int columns, int rows)
    {
        super(type, columns, rows);
    }
    
    
    /**
     * @param type     The type of the contents contained within the grid
     * @param columns  The number of columns the grid has
     * @param rows     The number of rows the grid has
     * @param contents The contents contained within the grid
     */
    SearchableGrid(Class<T> type, int columns, int rows, T[][] contents)
    {
        super(type, columns, rows, contents);
    }
    
    
    /**
     * @param criterion The linked criterion to search for
     *
     * @return Returns a {@link GridMatch}
     */
    public GridMatch<T> find(T[] criterion)
    {
        GridMatch<T> h = find(criterion, GridDirection.HORIZONTAL, Settings.DEFAULT_SEARCH_STYLE);
        GridMatch<T> v = find(criterion, GridDirection.VERTICAL, Settings.DEFAULT_SEARCH_STYLE);
        return h != null ? h : v;
    }
    
    
    /**
     * @param criterion The linked criterion to search for
     * @param gss       The style (method) to use when searching
     *
     * @return Returns a {@link GridMatch}
     */
    private GridMatch<T> find(T[] criterion, GridSearchStyle gss)
    {
        GridMatch<T> h = find(criterion, GridDirection.HORIZONTAL, gss);
        GridMatch<T> v = find(criterion, GridDirection.VERTICAL, gss);
        return h != null ? h : v;
    }
    
    
    /**
     * @param criterion The linked criterion to search for
     * @param direction The direction to perform the search in
     * @param gss       The style (method) to use when searching
     *
     * @return Returns a {@link GridMatch}
     */
    protected abstract GridMatch<T> find(T[] criterion, GridDirection direction, GridSearchStyle gss);
    
    
    @Override
    public T[] getContentsOf(GridDirection gridDirection, int num)
    {
        boolean h = gridDirection == GridDirection.HORIZONTAL;
        
        @SuppressWarnings("unchecked") final T[] found = (T[]) Array.newInstance(this.getType(), h ? this.getColumns() : this.getRows());
        // traverse horizontally or vertically
        for (int j = 0; j < (h ? this.getColumns() : this.getRows()); j++)
        {
            T obj = this.getContents()[(h ? num : j)][(h ? j : num)];
            found[j] = obj;
        }
        return found;
    }
    
    
    @Override
    public boolean matchExists(T[] criterion)
    {
        return find(criterion) != null;
    }
    
    
    @Override
    public boolean matchExists(T[] criterion, GridSearchStyle gss)
    {
        return find(criterion, gss) != null;
    }
    
    
    @Override
    public boolean matchExists(T[] criterion, GridDirection direction, GridSearchStyle gss)
    {
        return find(criterion, direction, gss) != null;
    }
}
