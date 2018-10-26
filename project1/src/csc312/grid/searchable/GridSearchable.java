package csc312.grid.searchable;

import csc312.grid.GridDirection;

public interface GridSearchable<T>
{
    /**
     * Searches for specific criteria within a {@link csc312.grid.GridBase}
     * This is the default method which searches in both directions and uses the default {@link GridSearchStyle}
     *
     * @param criterion The criterion to search for
     *
     * @return True if a match is found, otherwise false.
     */
    boolean matchExists(T[] criterion);
    
    
    /**
     * Searches for a specific criterion within a Grid
     * This is the alternate default method which searches in both directions
     *
     * @param criterion The criterion to search for
     * @param gss       The search style (method) to use for searching
     *
     * @return True if a match is found, otherwise false.
     */
    boolean matchExists(T[] criterion, GridSearchStyle gss);
    
    
    /**
     * Searches for a specific criterion within a Grid
     *
     * @param criterion The criterion to search for
     * @param direction The direction to search in
     * @param gss       The search style (method) to use for searching
     *
     * @return True if a match is found in the given direction, otherwise false.
     */
    boolean matchExists(T[] criterion, GridDirection direction, GridSearchStyle gss);
}
