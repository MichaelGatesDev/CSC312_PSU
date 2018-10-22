package csc312;

import java.util.*;

public class Week2Assignment
{
    
    //you must take the character in the string, and reverse then (1st character becomes last, 2nd character becomes 2nd to last...)
    public String reverseTheCharacter(String str)
    {
        StringBuilder reversed = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--)
        {
            reversed.append(str.toCharArray()[i]);
        }
        return reversed.toString();
    }
    
    
    //you must transfer the 'values' in the appropriate type of collection to optimize the search
    //of the key. If  you find the key (5 in the test), you must return it, otherwise, you return null
    public Integer findIntegerInACollection(int values[], int valueToFind)
    {
        Set<Integer> s = new HashSet<>();
        for (int n : values)
        {
            s.add(n);
        }
        if (s.contains(valueToFind))
        {
            return valueToFind;
        }
        return null;
    }
    
    
    //you must take the 'values', transfer them to an ArrayList, then sort it in ascending order and return the ArrayList
    public ArrayList<Integer> sortAndReturnAnArrayList(int values[])
    {
        ArrayList<Integer> al = new ArrayList<>();
        for (int n : values)
        {
            al.add(n);
        }
        Collections.sort(al);
        return al;
    }
    
    
    //you must take the 'staff', transfer them to an ArrayList, and sort it in ascending order of bannerid
    //
    //check https://www.tutorialspoint.com/java/java_using_comparator.htm
    //
    public ArrayList<Staff> testSortAndReturnAnArrayListOfStaffByBannerId(Staff staffs[])
    {
        ArrayList<Staff> al = new ArrayList<>(Arrays.asList(staffs));
        Collections.sort(al);
        return al;
    }
}
