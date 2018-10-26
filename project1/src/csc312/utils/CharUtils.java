package csc312.utils;

public class CharUtils
{
    private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    
    
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
    
    
    public static Character[] toCharacterArray(String s)
    {
        return s.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
    }
}
