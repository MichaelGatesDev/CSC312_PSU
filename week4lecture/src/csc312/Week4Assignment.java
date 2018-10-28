package csc312;

import java.util.Calendar;
import java.util.Random;
import java.util.function.BiFunction;

public class Week4Assignment
{
    
    /* must produce a random number between 0 and 4 */
    public int produceRandomNumber()
    {
        return new Random().nextInt(5); // inclusive start, exclusive end
    }
    
    
    //a BiFunction, is a function with 2 parameters and a return value
    //in the lecture, it was a function with a single parameter (Function), BiFunction is for 2 parameters
    //
    //
    //your function must search if the 1st parameter is in the 2nd parameter
    //and it must be case sensitive
    public BiFunction<String, String, Boolean> searchFunction()
    {
        return (String s1, String s2) -> s2.contains(s1);
    }
    
    
    /*
     *  You should expect 5 parameters, all Integer and you must print them with with no space in between them, but
     *  with up to 6 positions (with leading 0s if required) using a Formatter
     *
     */
    public String formatAMessage(Integer... arguments)
    {
        StringBuilder sb = new StringBuilder();
        for (int i : arguments)
        {
            String formatted = String.format("%06d", i);
            sb.append(formatted);
        }
        return sb.toString();
    }
    
    
    /*
     *  For the calendar in input, return its year : https://docs.oracle.com/javase/7/docs/api/java/util/GregorianCalendar.html
     */
    public int datetoYear(Calendar cal)
    {
        return cal.get(Calendar.YEAR);
    }
    
    
    /*
     *  str : it is a 5 letters word
     *
     *  c1 is the 1st character of a 3 characters long string
     *  c3 is the 3rd character of a 3 characters long string
     *
     *  You must check in str, if it is possible that the word beginning with c1 and ending with c3 can be in str
     *
     *  it is case sensitive and the word is always left to right
     */
    public boolean canItBeInThisString(String str, char c1, char c3)
    {
        for (int i = 0; i < str.length(); i++)
        {
            if (i + 3 > str.length())
            {
                break;
            }
            String current = str.substring(i, i + 3);
            if (current.length() == 3 && current.startsWith(String.valueOf(c1)) && current.endsWith(String.valueOf(c3)))
            {
                return true;
            }
        }
        return false;
    }
    
}
