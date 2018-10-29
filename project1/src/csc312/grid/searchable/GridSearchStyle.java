package csc312.grid.searchable;

/**
 * There are a number of methods (search styles) that the grid can search in.
 * e.g. searching corner to corner may be slower than searching from the center
 */
public enum GridSearchStyle
{
    
    /**
     * <p>Z A B C D E</p>
     * <p>1 * X X X X</p>
     * <p>2 X X X X X</p>
     * <p>3 X X X X X</p>
     * <p>4 X X X X X</p>
     * <p>5 X X X X X</p>
     * <p>The search starts from the asterisk (*) and continues through each row/column until
     * the match is found or the end of th grid is reached</p>
     */
    CORNER_TO_CORNER,
    
    /**
     * <p>Z A B C D E</p>
     * <p>1 X X X X X</p>
     * <p>2 X X X X X</p>
     * <p>3 X X * X X</p>
     * <p>4 X X X X X</p>
     * <p>5 X X X X X</p>
     * <p>The search starts from the asterisk (*) and first checks if it is a (1st/2nd/3rd/etc.)
     * position of a word. If it is, then depending on the position it branches out to the next letter.
     * For example, if it's the 2nd letter of a word then it will search above and below for 1st and 3rd character.
     * If that fails, it searches to the left and the right. If both of those fail, then the search has malfunctioned
     * as those are the only possibilities.</p>
     */
    CENTER_SPREAD
}
