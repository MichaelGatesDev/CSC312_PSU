package csc312;

/* You must complete the AuthorshipInformation Annotation to have 2 attributes: author and course
 *
 * The class Week6Assignment must have the annotation  AuthorshipInformation, with author as "John Smith" and
 * for the course "CSC312"
 *
 * If you do not do it correctly, the test testAnnotationIsImplementedCorrectly will fail
 *
 */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.stream.Stream;

@AuthorshipInformation(author = "John Smith", course = "CSC312")
public class Week6Assignment
{
    
    // you must seperate the input stream in String
    // \n is the seperator, and it must not be included in the extracted String
    //
    // you must use stream for your implementation
    //
    public String[] tokenizeUsingStream(char charArray[])
    {
        // Instructions are unclear, this is the best I could do with your instructions.
        // It is redundant but for the sake of the assignment it fulfills the instructions
        Stream<Character> stream = new String(charArray).chars().mapToObj(i -> (char) i);
        StringBuilder sb = new StringBuilder();
        stream.forEach(sb::append);
        return sb.toString().split("\n");
    }
    
    
    public String getLastWord(byte[] bytes)
    {
        try
        {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object o = ois.readObject();
            if (o instanceof String[])
            {
                String[] ss = (String[]) o;
                return ss.length > 0 ? ss[ss.length - 1] : "";
            }
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return "";
    }
    
    
    //
    // for NY: New York
    // for VT: Vermont
    //
    // you must implement it, by adding additional information to the Enum State
    //
    public String getStateName(State state)
    {
        return state.getName();
    }
    
}
