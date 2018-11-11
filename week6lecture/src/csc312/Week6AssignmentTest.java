package csc312;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Annotation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Week6AssignmentTest
{
    
    @Test
    public void testTokenizeUsingStream()
    {
        char charArray[] = new String("abc\ndef\n012345\n67890").toCharArray();
        
        Week6Assignment week6 = new Week6Assignment();
        
        String[] strs = week6.tokenizeUsingStream(charArray);
        
        assertEquals("abc", strs[0]);
        assertEquals("67890", strs[3]);
    }
    
    
    @Test
    public void testReadLastStringFromSerializeString()
    {
        try
        {
            String words[] = { "bee", "yak", "zoo" };
            
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            
            ObjectOutputStream ooStream = new ObjectOutputStream(byteArrayOutputStream);
            
            ooStream.writeObject(words);
            
            ooStream.close();
            
            Week6Assignment week6 = new Week6Assignment();
            
            String str = week6.getLastWord(byteArrayOutputStream.toByteArray());
            
            assertEquals("zoo", str);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            fail();
        }
    }
    
    
    @Test
    public void testUsingEnum()
    {
        Week6Assignment week6 = new Week6Assignment();
        
        String str = week6.getStateName(State.NY);
        
        assertEquals("New York", str);
    }
    
    
    /* See the comment in Week6Assignment on what must be completed for the test to pass */
    @Test
    public void testAnnotationIsImplementedCorrectly()
    {
        Class aClass = Week6Assignment.class;
        Annotation[] annotations = aClass.getAnnotations();
        
        for (Annotation annotation : annotations)
        {
            if (annotation instanceof AuthorshipInformation)
            {
                AuthorshipInformation myAnnotation = (AuthorshipInformation) annotation;
                assertEquals("John Smith", myAnnotation.author());
                assertEquals("CSC312", myAnnotation.course());
            }
        }
    }
}
