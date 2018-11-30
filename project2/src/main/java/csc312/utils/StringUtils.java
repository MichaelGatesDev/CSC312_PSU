package main.java.csc312.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtils
{
    /**
     * Converts a {@link Character} array to a {@link String}
     *
     * @param characters The {@link Character} array to convert
     *
     * @return Returns the string result
     */
    public static String toString(Character[] characters)
    {
        StringBuilder s = new StringBuilder();
        for (char ch : characters)
        {
            s.append(ch);
        }
        return s.toString();
    }
    
    
    /**
     * Converts a {@link String} to a string {@link List}
     *
     * @param str       The string to convert
     * @param delimeter The delimiter that separates the items
     *
     * @return {@link ArrayList}
     */
    public static List<String> stringToList(String str, String delimeter)
    {
        String[] ss = Arrays.stream(str.split(delimeter)).map(String::trim).toArray(String[]::new);
//        return new ArrayList<>(Arrays.asList(ss)); // read/write
        return Arrays.asList(ss); // read-only
    }
}
