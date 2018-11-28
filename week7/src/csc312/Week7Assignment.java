package csc312;

public class Week7Assignment
{
    private boolean open;
    
    
    //return true if it was closed, otherwise, it must return false (such as doing multiple open)
    public boolean open()
    {
        if (!open)
        {
            open = true;
            return true;
        }
        return false;
    }
    
    
    //return true if it was open, otherwise, it must return false (such as doing multiple close multiple time, or close if it is not open)
    public boolean close()
    {
        if (open)
        {
            open = false;
            return true;
        }
        return false;
    }
    
    
    //return true if it is open, otherwise, it must return false
    public boolean doSomething()
    {
        return open;
    }
}
