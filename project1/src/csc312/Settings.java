package csc312;

import csc312.grid.searchable.GridSearchStyle;

public class Settings
{
    private static final int             MIN_GAME_NUM         = 1; // The first game #
    private static final int             MAX_GAME_NUM         = 3; // The last game #
    public static final  int             COLUMNS              = 5; // The number of columns in the game
    public static final  int             ROWS                 = 5; // The number of rows in the game
    public static final  int             TOTAL_GAMES          = (MAX_GAME_NUM - MIN_GAME_NUM) + 1; // The total number of games
    public static final  GridSearchStyle DEFAULT_SEARCH_STYLE = GridSearchStyle.CORNER_TO_CORNER; // The default search style (method)
}
