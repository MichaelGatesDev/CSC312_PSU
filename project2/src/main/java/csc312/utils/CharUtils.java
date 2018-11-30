package main.java.csc312.utils;

public class CharUtils
{
    private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    
    
    /**
     * Grabs the letter at the specified position from the alphabet (lowercase)
     *
     * @param n The index to use in the alphabet
     *
     * @return Returns a lowercase letter at that position or '\0' if none (null)
     */
    public static char getPosOfLetterInAlphabet(int n)
    {
//        System.out.println("pos is " + n);
//        System.out.println("char " + n + " is " + ALPHABET[n]);
        if (n < 0 || n >= ALPHABET.length)
        {
            return '\0'; // null character
        }
        return ALPHABET[n];
    }
    
    
    /**
     * Converts a {@link String} to an array of {@link Character}s.
     *
     * @param str The string to convert
     *
     * @return The new and converted {@link Character} array
     */
    public static Character[] toCharacterArray(String str)
    {
        return str.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
    }
}
