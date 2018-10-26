package csc312;

import csc312.grid.searchable.GridMatch;
import csc312.grid.searchable.SearchableCharGrid;
import csc312.utils.CharUtils;

import java.util.List;

class Game
{
    private final int                num;
    private final SearchableCharGrid grid;
    
    
    Game(int num, SearchableCharGrid grid)
    {
        this.num = num;
        this.grid = grid;
    }
    
    
    GameResult getResult(List<String> words)
    {
        GameResult result = new GameResult(num);
        for (String word : words)
        {
            GridMatch gm = this.grid.find(CharUtils.toCharacterArray(word));
            if (gm != null)
            {
                result.setMatch(gm);
                break;
            }
        }
        return result;
    }
}
