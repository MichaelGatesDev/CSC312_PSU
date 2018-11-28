package csc312;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestWeek7Assignment
{
    
    @Test
    public void testOpenDoSomethingClose()
    {
        
        Week7Assignment week7Assignment = new Week7Assignment();
        
        assertTrue(week7Assignment.open());
        assertTrue(week7Assignment.doSomething());
        assertTrue(week7Assignment.doSomething());
        assertTrue(week7Assignment.doSomething());
        assertTrue(week7Assignment.close());
    }
    
    
    @Test
    public void tesMultiOpen()
    {
        
        Week7Assignment week7Assignment = new Week7Assignment();
        
        assertTrue(week7Assignment.open());
        assertFalse(week7Assignment.open());
        assertTrue(week7Assignment.doSomething());
        assertTrue(week7Assignment.doSomething());
        assertTrue(week7Assignment.close());
    }
    
    
    @Test
    public void testMultiClose()
    {
        
        Week7Assignment week7Assignment = new Week7Assignment();
        
        assertTrue(week7Assignment.open());
        assertTrue(week7Assignment.doSomething());
        assertTrue(week7Assignment.doSomething());
        assertTrue(week7Assignment.close());
        assertFalse(week7Assignment.close());
    }
    
    
    @Test
    public void testCloseWhenNotOpen()
    {
        
        Week7Assignment week7Assignment = new Week7Assignment();
        
        assertFalse(week7Assignment.close());
        assertTrue(week7Assignment.open());
        assertTrue(week7Assignment.doSomething());
        assertTrue(week7Assignment.doSomething());
        assertTrue(week7Assignment.close());
    }
    
    
    @Test
    public void testDoSomethingWhenNotOpen()
    {
        
        Week7Assignment week7Assignment = new Week7Assignment();
        
        assertFalse(week7Assignment.doSomething());
        assertTrue(week7Assignment.open());
        assertTrue(week7Assignment.doSomething());
        assertTrue(week7Assignment.doSomething());
        assertTrue(week7Assignment.close());
    }
    
    
    @Test
    public void testDoSomethingAfterClose()
    {
        
        Week7Assignment week7Assignment = new Week7Assignment();
        
        assertTrue(week7Assignment.open());
        assertTrue(week7Assignment.doSomething());
        assertTrue(week7Assignment.doSomething());
        assertTrue(week7Assignment.close());
        assertFalse(week7Assignment.doSomething());
    }
    
    
    @Test
    public void test2Cycle()
    {
        
        Week7Assignment week7Assignment = new Week7Assignment();
        
        assertTrue(week7Assignment.open());
        assertTrue(week7Assignment.doSomething());
        assertTrue(week7Assignment.doSomething());
        assertTrue(week7Assignment.close());
        assertTrue(week7Assignment.open());
        assertTrue(week7Assignment.doSomething());
        assertTrue(week7Assignment.doSomething());
        assertTrue(week7Assignment.close());
    }
    
}
