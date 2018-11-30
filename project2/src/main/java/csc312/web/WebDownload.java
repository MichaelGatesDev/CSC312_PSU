package main.java.csc312.web;

/**
 * Represents a download from the web
 *
 * @param <T> The type of the content downloaded
 */
public class WebDownload<T>
{
    private T                 value;
    private WebDownloadResult result;
    
    
    /**
     * @param value  The value of the download
     * @param result The status result of the download
     */
    public WebDownload(T value, WebDownloadResult result)
    {
        this.value = value;
        this.result = result;
    }
    
    
    /**
     * @return The resulting value of type {@link T}
     */
    public T getValue()
    {
        return value;
    }
    
    
    /**
     * @return The status result of the download
     */
    public WebDownloadResult getResult()
    {
        return result;
    }
}
