package csc312;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.function.BiFunction;

import static org.junit.Assert.*;

public class TestWeek4Assignment
{
    
    @Test
    public void testRandomNumber()
    {
        Random r = new Random();
        for (int x = 0; x < 100; x++)
        {
//            System.out.println(r.nextInt(4));
        }
        
        Week4Assignment week4Assignment = new Week4Assignment();
        
        int x = week4Assignment.produceRandomNumber();
        assertTrue(x >= 0 && x <= 4);
    }
    
    
    @Test
    public void testSearchFunction()
    {
        Week4Assignment week4Assignment = new Week4Assignment();
        
        //a BiFunction, is a function with 2 parameters and a return value
        //in the lecture, it was a function with a single parameter (Function), BiFunction is for 2 parameters
        //
        //
        //your function must search if the 1st parameter is in the 2nd parameter
        //and it must be case sensitive
        BiFunction<String, String, Boolean> searchFunction = week4Assignment.searchFunction();
        
        assertTrue(searchFunction.apply("abc", "ZabcZ"));
        assertFalse(searchFunction.apply("abc", "ZZZZZ"));
    }
    
    
    @Test
    public void testFormatAMessage()
    {
        
        Integer i1 = new Integer(11);
        Integer i2 = new Integer(22);
        Integer i3 = new Integer(33);
        Integer i4 = new Integer(44);
        Integer i5 = new Integer(55);
        
        Week4Assignment week4Assignment = new Week4Assignment();
        
        assertEquals("000011000022000033000044000055", week4Assignment.formatAMessage(i1, i2, i3, i4, i5));
    }
    
    
    @Test
    public void testDateToYear()
    {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        Week4Assignment week4Assignment = new Week4Assignment();
        
        assertEquals(2018, week4Assignment.datetoYear(cal));
        
    }
    
    
    @Test
    public void searchForAString()
    {
        
        Week4Assignment week4Assignment = new Week4Assignment();
        
        assertFalse(week4Assignment.canItBeInThisString("ZZZZZ", 'm', 'n'));
        
        assertTrue(week4Assignment.canItBeInThisString("ZZmin", 'm', 'n'));
        
        assertTrue(week4Assignment.canItBeInThisString("ABCdogEFG", 'd', 'g'));
        
        assertTrue(week4Assignment.canItBeInThisString("LONGSTRINGyesSOMETHING", 'y', 's'));
        
        assertFalse(week4Assignment.canItBeInThisString("LONGSTRINGyseOMETHING", 'y', 's'));
    }
}
