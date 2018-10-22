package csc312;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestWeek2Assignment
{
    
    @Test
    public void testReverseString()
    {
        String str = "0123456789";
        
        Week2Assignment week2Assignment = new Week2Assignment();
        
        //you must take the character in the string, and reverse then (1st character becomes last, 2nd character becomes 2nd to last...)
        assertEquals("9876543210", week2Assignment.reverseTheCharacter(str));
    }
    
    
    @Test
    public void testFindIntegerInACollection()
    {
        int values[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
        
        Week2Assignment week2Assignment = new Week2Assignment();
        
        //you must transfer the 'values' in the appropriate type of collection to optimize the search
        //of the key. If  you find the key (5 in the test), you must return it, otherwise, you return null
        assertNotNull(week2Assignment.findIntegerInACollection(values, 5));
        assertEquals(new Integer(5), week2Assignment.findIntegerInACollection(values, 5));
        
        //key 555, is not in the collection, it must return null
        assertNull(week2Assignment.findIntegerInACollection(values, 555));
        
    }
    
    
    @Test
    public void testSortAndReturnAnArrayListOfInteger()
    {
        int values[] = { 1, 5, 0, 4, 3, 2 };
        
        Week2Assignment week2Assignment = new Week2Assignment();
        
        //you must take the 'values', transfer them to an ArrayList, then sort it in ascending order
        //and return the ArrayList
        ArrayList<Integer> sortedValues = week2Assignment.sortAndReturnAnArrayList(values);
        for (int x = 0; x < values.length; x++)
        {
            assertEquals(new Integer(x), sortedValues.get(x));
        }
    }
    
    
    @Test
    public void testSortAndReturnAnArrayListOfStaffByBannerId()
    {
        Staff values[] = { new Staff(1, "Jane"),
                new Staff(3, "John"),
                new Staff(0, "Tim"),
                new Staff(2, "Tom"),
        };
        
        Week2Assignment week2Assignment = new Week2Assignment();
        
        //you must take the 'staff', transfer them to an ArrayList, and sort it in ascending order of bannerid
        //
        //check https://www.tutorialspoint.com/java/java_using_comparator.htm
        //
        ArrayList<Staff> sortedValues = week2Assignment.testSortAndReturnAnArrayListOfStaffByBannerId(values);
        for (int x = 0; x < sortedValues.size(); x++)
        {
            assertEquals(new Integer(x), sortedValues.get(x).getBannerid());
        }
    }
    
}
