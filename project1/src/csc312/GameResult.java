package csc312;

import csc312.grid.searchable.GridMatch;
import csc312.utils.StringUtils;

/**
 * Represents the result of the game as an object
 */
class GameResult
{
    private final int                  num;
    private       GridMatch<Character> gridMatch;
    
    
    /**
     * @param num The number of the game
     */
    GameResult(int num)
    {
        this.num = num;
    }
    
    
    /**
     * Sets the resulting match of the game
     *
     * @param gridMatch The matchF
     */
    void setMatch(GridMatch<Character> gridMatch)
    {
        this.gridMatch = gridMatch;
    }
    
    
    @Override
    public String toString()
    {
        // The output should be : game<1-3> word:<word found> location:<beginning-end>
        return "game: " + num + " word: " + StringUtils.toString(this.gridMatch.getMatch()) + " location: " + this.gridMatch.getStart().toGameString() + ":" + this.gridMatch.getEnd().toGameString();
    }
}