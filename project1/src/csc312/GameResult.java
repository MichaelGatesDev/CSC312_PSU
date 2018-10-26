package csc312;

import csc312.grid.searchable.GridMatch;
import csc312.utils.StringUtils;

// The output should be : game<1-3> word:<word found> location:<beginning-end>
class GameResult
{
    private final int                  num;
    private       GridMatch<Character> gridMatch;
    
    
    GameResult(int num)
    {
        this.num = num;
    }
    
    
    void setMatch(GridMatch<Character> gridMatch)
    {
        this.gridMatch = gridMatch;
    }
    
    
    @Override
    public String toString()
    {
        return "game: " + num + " word: " + StringUtils.toString(this.gridMatch.getMatch()) + " location: " + this.gridMatch.getStart().toGameString() + ":" + this.gridMatch.getEnd().toGameString();
    }
}