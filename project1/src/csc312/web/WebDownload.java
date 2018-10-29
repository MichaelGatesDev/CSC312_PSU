package csc312.web;

public class WebDownload<T>
{
    private T                 value;
    private WebDownloadResult result;
    
    
    public WebDownload(T value, WebDownloadResult result)
    {
        this.value = value;
        this.result = result;
    }
    
    
    public T getValue()
    {
        return value;
    }
    
    
    public WebDownloadResult getResult()
    {
        return result;
    }
}
