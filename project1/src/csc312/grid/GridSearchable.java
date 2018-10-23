package csc312.grid;

public interface GridSearchable<T>
{
    /**
     * Searches for a specific criterion within a Grid
     *
     * @param criterion The criterion to search for
     *
     * @return True if a match is found, otherwise false.
     */
    boolean matchExists(T criterion);
    
    
    /**
     * Searches for a specific criterion within a Grid
     *
     * @param criterion The criterion to search for
     * @param direction The direction to search in
     *
     * @return True if a match is found in the given direction, otherwise false.
     */
    boolean matchExists(T criterion, GridDirection direction);
}
