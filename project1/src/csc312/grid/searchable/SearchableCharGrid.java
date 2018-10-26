package csc312.grid.searchable;

import csc312.grid.GridDirection;
import csc312.grid.GridPosition;
import csc312.utils.CharUtils;
import csc312.utils.StringUtils;

public class SearchableCharGrid extends SearchableGrid<Character>
{
    /**
     * @param columns The number of columns the grid has
     * @param rows    The number of rows the grid has
     */
    public SearchableCharGrid(int columns, int rows)
    {
        super(Character.class, columns, rows);
    }
    
    
    /**
     * @param columns  The number of columns the grid has
     * @param rows     The number of rows the grid has
     * @param contents The contents contained within the grid
     */
    public SearchableCharGrid(int columns, int rows, Character[][] contents)
    {
        super(Character.class, columns, rows, contents);
    }
    
    
    @Override
    public GridMatch<Character> find(Character[] criteria, GridDirection direction, GridSearchStyle gss)
    {
        long start = System.currentTimeMillis();
        GridMatch<Character> gm = null;
        switch (gss)
        {
            case CORNER_TO_CORNER:
                gm = doCornerToCorner(StringUtils.toString(criteria), direction);
                break;
            case CENTER_SPREAD:
                break;
        }
        long finish = System.currentTimeMillis();
        if (gm != null)
        {
            gm.setTimeInMillis(finish - start);
        }
        return gm;
    }
    
    
    private GridMatch<Character> doCornerToCorner(String word, GridDirection direction)
    {
        GridMatch<Character> match = null;
        
        //TODO optimize searching
        // ----------------------------------------------------------------------------------------------------------
        //
        // X X X X X
        // X X X X X
        // X X * X X
        // X X X X X
        // X X X X X
        // Starting from the center may be the best because you can search up up, down down, left left, right right.
        // Requires searching for a word backwards
        //
        //   A B C D E
        // 1 X X X N N
        // 2 X X X N N
        // 3 X X X N N
        // 4 N N N N N
        // 5 N N N N N
        // Horizontal searches can never start from: COL4 or COL5
        // Vertical searches can never start from: ROW4 or ROW5
        //
        // ----------------------------------------------------------------------------------------------------------
        
        boolean h = direction == GridDirection.HORIZONTAL;
        for (int i = 0; i < (h ? this.getRows() : this.getColumns()); i++)
        {
            Character[] cc = this.getContentsOf(direction, i);
            
            StringBuilder sb = new StringBuilder(cc.length);
            for (Character c : cc)
            {
                sb.append(c.charValue());
            }
            String str = sb.toString();
            
            // if word exists in the row
            if (str.contains(word))
            {
                //
                int col;
                int row;
                int startPos = str.indexOf(word);
                int endPos = startPos + word.length() - 1;
                if (h)
                {
                    row = i;
                    match = new GridMatch<>(CharUtils.toCharacterArray(word), new GridPosition(startPos, row), new GridPosition(endPos, row));

//                    System.out.println(String.format("\"%s\" found in row %d (%s) starting at pos %d and ending at pos %d", word, row, str, startPos, endPos));
                }
                else
                {
                    col = i;
                    match = new GridMatch<>(CharUtils.toCharacterArray(word), new GridPosition(col, startPos), new GridPosition(col, endPos));

//                    System.out.println(String.format("\"%s\" found in row %d (%s) starting at pos %d and ending at pos %d", word, row, str, startPos, endPos));
                }
            }
        }
        return match;
    }
    
    
    public String getContentsOfAsString(GridDirection gridDirection, int num)
    {
        return StringUtils.toString(getContentsOf(gridDirection, num));
    }
}