package csc312.tests;

import csc312.grid.CharGrid;
import csc312.grid.GridDirection;
import csc312.grid.SearchableCharGrid;
import csc312.utils.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class GridTest
{
    @Test
    public void testSize()
    {
        Character[] abcde = new Character[]{ 'A', 'B', 'C', 'D', 'E' };
        Character[] fghij = new Character[]{ 'F', 'G', 'H', 'I', 'J' };
        Character[] klmno = new Character[]{ 'K', 'L', 'M', 'N', 'O' };
        Character[] pqrst = new Character[]{ 'P', 'Q', 'R', 'S', 'T' };
        Character[] uvwxy = new Character[]{ 'U', 'V', 'W', 'X', 'Y' };
        
        SearchableCharGrid g = new SearchableCharGrid(5, 5, new Character[][]
                {
                        abcde,
                        fghij,
                        klmno,
                        pqrst,
                        uvwxy,
                });
        Assert.assertEquals(5, g.getColumns());
        Assert.assertEquals(5, g.getRows());
    }
    
    
    @Test
    public void testContents()
    {
        Character[] abcde = new Character[]{ 'A', 'B', 'C', 'D', 'E' };
        Character[] fghij = new Character[]{ 'F', 'G', 'H', 'I', 'J' };
        Character[] klmno = new Character[]{ 'K', 'L', 'M', 'N', 'O' };
        Character[] pqrst = new Character[]{ 'P', 'Q', 'R', 'S', 'T' };
        Character[] uvwxy = new Character[]{ 'U', 'V', 'W', 'X', 'Y' };
        
        Character[] afkpu = new Character[]{ 'A', 'F', 'K', 'P', 'U' };
        
        CharGrid g = new CharGrid(5, 5, new Character[][]
                {
                        abcde,
                        fghij,
                        klmno,
                        pqrst,
                        uvwxy,
                });
        
        Character[] c = g.getContentsOf(GridDirection.HORIZONTAL, 0);
        Assert.assertArrayEquals(c, abcde);
        
        Character[] c2 = g.getContentsOf(GridDirection.VERTICAL, 0);
        Assert.assertArrayEquals(c2, afkpu);
        
        Character[] c3 = g.getContentsOf(GridDirection.HORIZONTAL, 2);
        Assert.assertArrayEquals(c3, klmno);
        
        String s = g.getContentsOfAsString(GridDirection.HORIZONTAL, 0);
        Assert.assertEquals(s, StringUtils.toString(abcde));
    }
    
    
    @Test
    public void testSearchRandom()
    {
        Character[] abcde = new Character[]{ 'A', 'B', 'C', 'D', 'E' };
        Character[] fghij = new Character[]{ 'F', 'G', 'H', 'I', 'J' };
        Character[] klmno = new Character[]{ 'K', 'L', 'M', 'N', 'O' };
        Character[] pqrst = new Character[]{ 'P', 'Q', 'R', 'S', 'T' };
        Character[] uvwxy = new Character[]{ 'U', 'V', 'W', 'X', 'Y' };
        
        SearchableCharGrid g = new SearchableCharGrid(5, 5, new Character[][]
                {
                        abcde,
                        fghij,
                        klmno,
                        pqrst,
                        uvwxy,
                });
        
        Assert.assertTrue(g.matchExists("ABCDE")); // forward horizontal
        Assert.assertTrue(g.matchExists("AFKPU")); // forward vertical
        Assert.assertFalse(g.matchExists("AGMSY")); // diagonal
        Assert.assertFalse(g.matchExists("EDCBA")); // backwards
    }
    
    
    @Test
    public void testSearchPractical()
    {
        /*
            A B j a r
            A z D e F
            z f D q W
            P T w V Q
            Z O q H I
         */
        Character[] a = new Character[]{ 'A', 'B', 'j', 'a', 'r' };
        Character[] b = new Character[]{ 'A', 'z', 'D', 'e', 'F' };
        Character[] c = new Character[]{ 'z', 'f', 'D', 'q', 'W' };
        Character[] d = new Character[]{ 'P', 'T', 'w', 'V', 'Q' };
        Character[] e = new Character[]{ 'Z', 'O', 'q', 'H', 'I' };
        
        SearchableCharGrid g = new SearchableCharGrid(5, 5, new Character[][]
                {
                        a,
                        b,
                        c,
                        d,
                        e,
                });
        
        Assert.assertTrue(g.matchExists("jar")); // forward horizontal, pass
        Assert.assertFalse(g.matchExists("JAR")); // uppercase, fail
        Assert.assertFalse(g.matchExists("Jar")); // camcelcase, fail
        Assert.assertTrue(g.matchExists("HI")); // forward horizontal, pass
        Assert.assertFalse(g.matchExists("hi")); // lowercase, fail
    }
}
