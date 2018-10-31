package csc312.web;

/**
 * A simple Callback interface which can be instantiated and used as an anonymous inner class
 *
 * @param <T> The type to include as a result in the Callback
 */
public interface Callback<T>
{
    /**
     * Called when the callback completes
     *
     * @param result The result of the completed callback
     */
    void onComplete(T result);
    
    
    /**
     * Called when the callback fails
     *
     * @param result The result of the failed callback
     */
    void onFailure(T result);
    
    
    /**
     * Called when the callback completes successfully
     *
     * @param result The result of the completed callback
     */
    void onSuccess(T result);
    
    
    int getAttempts();
    
    
    void setAttempts(int n);
}
