package csc312.utils;

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
}
