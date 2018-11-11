package csc312;

public enum State
{
    
    NY("New York"),
    VT("Vermont");
    
    
    private final String name;
    
    
    State(String name)
    {
        this.name = name;
    }
    
    
    public String getName()
    {
        return name;
    }
}
