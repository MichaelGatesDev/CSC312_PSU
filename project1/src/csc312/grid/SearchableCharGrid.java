package csc312.grid;

public class SearchableCharGrid extends CharGrid implements GridSearchable<String>
{
    public class GridMatch
    {
        private String       word;
        private GridPosition start;
        private GridPosition end;
        
        
        public GridMatch(String word, GridPosition start, GridPosition end)
        {
            this.word = word;
            this.start = start;
            this.end = end;
        }
        
        
        public String getWord()
        {
            return word;
        }
        
        
        public GridPosition getStart()
        {
            return start;
        }
        
        
        public GridPosition getEnd()
        {
            return end;
        }
    }
    
    
    public SearchableCharGrid(int columns, int rows, Character[][] contents)
    {
        super(columns, rows, contents);
    }
    
    
    @Override
    public boolean matchExists(String s)
    {
        return this.find(s) != null;
    }
    
    
    @Override
    public boolean matchExists(String s, GridDirection gd)
    {
        return this.find(s, gd) != null;
    }
    
    
    public GridMatch find(String word)
    {
        GridMatch h = find(word, GridDirection.HORIZONTAL);
        GridMatch v = find(word, GridDirection.VERTICAL);
        return h != null ? h : v;
    }
    
    
    private GridMatch find(String word, GridDirection direction)
    {
        GridMatch match = null;
        
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
                    match = new GridMatch(word, new GridPosition(startPos, row), new GridPosition(endPos, row));

//                    System.out.println(String.format("\"%s\" found in row %d (%s) starting at pos %d and ending at pos %d", word, row, str, startPos, endPos));
                }
                else
                {
                    col = i;
                    match = new GridMatch(word, new GridPosition(col, startPos), new GridPosition(col, endPos));

//                    System.out.println(String.format("\"%s\" found in row %d (%s) starting at pos %d and ending at pos %d", word, row, str, startPos, endPos));
                }
            }
        }
        return match;
    }
}
