package csc312.grid;

public class SearchableCharGrid extends CharGrid implements GridSearchable<String>
{
    public SearchableCharGrid(int columns, int rows, Character[][] contents)
    {
        super(columns, rows, contents);
    }
    
    
    public boolean matchExists(String s, GridDirection direction)
    {
        for (int i = 0; i < (direction == GridDirection.HORIZONTAL ? this.getRows() : this.getColumns()); i++)
        {
            Character[] cc = this.getContentsOf(direction, i);
            
            StringBuilder sb = new StringBuilder(cc.length);
            for (Character c : cc)
            {
                sb.append(c.charValue());
            }
            String str = sb.toString();
            if (str.contains(s))
            {
                return true;
            }
        }
        return false;
    }
    
    
    public boolean matchExists(String s)
    {
        return this.matchExists(s, GridDirection.HORIZONTAL) || this.matchExists(s, GridDirection.VERTICAL);
    }
}
