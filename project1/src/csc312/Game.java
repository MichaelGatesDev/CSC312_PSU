package csc312;

import csc312.grid.SearchableCharGrid;

import java.util.List;

public class Game
{
    // The output should be : game<1-3> word:<word found> location:<beginning-end>
    public class GameResult
    {
        private SearchableCharGrid.GridMatch gridMatch;
        
        
        GameResult()
        {
        }
        
        
        void setMatch(SearchableCharGrid.GridMatch gridMatch)
        {
            this.gridMatch = gridMatch;
        }
        
        
        @Override
        public String toString()
        {
            return "game: " + num + " word: " + this.gridMatch.getWord() + " location: " + this.gridMatch.getStart().toGameString() + ":" + this.gridMatch.getEnd().toGameString();
        }
    }
    
    private int                num;
    private SearchableCharGrid grid;
    
    
    Game(int num, SearchableCharGrid grid)
    {
        this.num = num;
        this.grid = grid;
    }
    
    
    GameResult getResult(List<String> words)
    {
        GameResult result = new GameResult();
        for (String word : words)
        {
            SearchableCharGrid.GridMatch gm = this.grid.find(word);
            if (gm != null)
            {
                result.setMatch(gm);
                break;
            }
        }
        return result;
    }
    
    
    int getNum()
    {
        return num;
    }
}
