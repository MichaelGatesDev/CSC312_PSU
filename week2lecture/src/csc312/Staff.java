package csc312;

import java.util.Comparator;

public class Staff implements Comparator<Staff>, Comparable<Staff>
{
    
    private String  name;
    private Integer bannerid;
    
    
    public Staff(int bannerid, String name)
    {
        this.bannerid = bannerid;
        this.name = name;
    }
    
    
    public String getName()
    {
        return name;
    }
    
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    
    public Integer getBannerid()
    {
        return bannerid;
    }
    
    
    public void setBannerid(Integer bannerid)
    {
        this.bannerid = bannerid;
    }
    
    
    @Override
    public int compareTo(Staff staff)
    {
        return (this.bannerid).compareTo(staff.bannerid);
    }
    
    
    @Override
    public int compare(Staff staff, Staff t1)
    {
        return staff.bannerid.compareTo(t1.bannerid);
    }
}
