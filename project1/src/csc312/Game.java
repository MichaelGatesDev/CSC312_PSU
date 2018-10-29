package csc312;

import csc312.grid.searchable.GridMatch;
import csc312.grid.searchable.SearchableCharGrid;
import csc312.utils.CharUtils;

import java.util.List;

/**
 * Represents the game as an object
 */
class Game
{
    private final int                num;
    private final SearchableCharGrid grid;
    
    
    /**
     * @param num  The number of the game
     * @param grid The {@link SearchableCharGrid}
     */
    Game(int num, SearchableCharGrid grid)
    {
        this.num = num;
        this.grid = grid;
    }
    
    
    /**
     * Fetches the result of a game.
     *
     * @param words The list of words to search the game for
     *
     * @return Returns the game result
     */
    GameResult getResult(List<String> words)
    {
        GameResult result = new GameResult(num);
        for (String word : words)
        {
            GridMatch<Character> gm = this.grid.find(CharUtils.toCharacterArray(word));
            if (gm != null)
            {
                result.setMatch(gm);
                break;
            }
        }
        return result;
    }
}
