package csc312.grid;


import csc312.utils.StringUtils;

/**
 * <p>Represents a grid structure made up of characters with columns and rows.</p>
 * <p>The area of the grid is {@link #columns} * {@link #rows}</p>
 **/
public class CharGrid extends GridBase<Character>
{
    public CharGrid(int columns, int rows, Character[][] contents)
    {
        super(columns, rows);
        this.setContents(contents);
    }
    
    
    @Override
    public Character[] getContentsOf(GridDirection gridDirection, int num)
    {
        boolean h = gridDirection == GridDirection.HORIZONTAL;
        Character[] found = new Character[(h ? this.getRows() : this.getColumns())]; // Creates a character array the size of the row or column
        // traverse horizontally or vertically
        for (int j = 0; j < (h ? this.getColumns() : this.getRows()); j++)
        {
            Character obj = this.getContents()[(h ? num : j)][(h ? j : num)];
            found[j] = obj;
        }
        return found;
    }
    
    
    public String getContentsOfAsString(GridDirection gridDirection, int num)
    {
        return StringUtils.toString(getContentsOf(gridDirection, num));
    }
    
    
}
