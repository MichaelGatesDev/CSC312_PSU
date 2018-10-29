package csc312.web;

/**
 * A simple Callback interface which can be instantiated and used as an anonymous inner class
 *
 * @param <T> The type to include as a result in the Callback
 */
public interface Callback<T>
{
    void onComplete(T result);
}
