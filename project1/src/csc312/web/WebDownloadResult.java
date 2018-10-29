package csc312.web;

public enum WebDownloadResult
{
    /**
     * Unused
     */
    UNKNOWN,
    /**
     * Download was a success
     */
    SUCCESS,
    /**
     * Downloaded yielded SC_INTERNAL_ERROR
     */
    SC_INTERNAL_ERROR,
    /**
     * Download yielded SC_FORBIDDEN
     */
    SC_FORBIDDEN
}
