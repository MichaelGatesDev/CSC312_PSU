package csc312.tests;

import csc312.grid.GridDirection;
import csc312.grid.GridPosition2D;
import csc312.grid.searchable.GridMatch;
import csc312.grid.searchable.SearchableCharGrid;
import csc312.utils.CharUtils;
import csc312.utils.StringUtils;
import csc312.web.Callback;
import csc312.web.WebDownload;
import csc312.web.WebDownloadResult;
import csc312.web.WebDownloader;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Project1Test
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
        
        SearchableCharGrid g = new SearchableCharGrid(5, 5, new Character[][]
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
        
        Assert.assertTrue(g.matchExists(CharUtils.toCharacterArray("ABCDE"))); // forward horizontal
        Assert.assertTrue(g.matchExists(CharUtils.toCharacterArray("AFKPU"))); // forward vertical
        Assert.assertFalse(g.matchExists(CharUtils.toCharacterArray("AGMSY"))); // diagonal
        Assert.assertFalse(g.matchExists(CharUtils.toCharacterArray("EDCBA"))); // backwards
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
        
        Assert.assertTrue(g.matchExists(CharUtils.toCharacterArray("jar"))); // forward horizontal, pass
        Assert.assertFalse(g.matchExists(CharUtils.toCharacterArray("JAR"))); // uppercase, fail
        Assert.assertFalse(g.matchExists(CharUtils.toCharacterArray("Jar"))); // camcelcase, fail
        Assert.assertTrue(g.matchExists(CharUtils.toCharacterArray("HI"))); // forward horizontal, pass
        Assert.assertFalse(g.matchExists(CharUtils.toCharacterArray("hi"))); // lowercase, fail
    }
    
    
    @Test
    public void testSearchFromList()
    {
        List<String> words = Arrays.asList(
                "bee",
                "this",
                "this",
                "random",
                "list",
                "yes"
        );
        SearchableCharGrid g = new SearchableCharGrid(5, 5, new Character[][]
                {
                        CharUtils.toCharacterArray("ADeeR"),
                        CharUtils.toCharacterArray("AzDeF"),
                        CharUtils.toCharacterArray("AwefJ"),
                        CharUtils.toCharacterArray("abeeK"),
                        CharUtils.toCharacterArray("qoeif"),
                });
        
        for (String word : words)
        {
            GridMatch<Character> match = g.find(CharUtils.toCharacterArray(word));
            if (word.equals("bee"))
            {
                Assert.assertNotNull(match);
                Assert.assertEquals(match.getStart(), new GridPosition2D(1, 3));
            }
        }
    }
    
    
    @Test
    public void test500()
    {
        WebDownloader downloader = new WebDownloader();
        
        Callback<WebDownload> cb = new Callback<WebDownload>()
        {
            private int attempts = 0;
            
            
            @Override
            public void setAttempts(int n)
            {
                this.attempts = n;
            }
            
            
            @Override
            public int getAttempts()
            {
                return this.attempts;
            }
            
            
            @Override
            public void onComplete(WebDownload result)
            {
            }
            
            
            @Override
            public void onSuccess(WebDownload result)
            {
            }
            
            
            @Override
            public void onFailure(WebDownload result)
            {
                WebDownloadResult wdr = result.getResult();
                Assert.assertEquals(wdr, WebDownloadResult.SC_INTERNAL_ERROR);
            }
        };
        downloader.downloadContent("https://wordfinder-001.appspot.com/wordfinder?game=1&pos=Z99", cb, 5);
        Assert.assertEquals(5, cb.getAttempts());
    }
    
    
    @Test
    public void test403()
    {
        WebDownloader downloader = new WebDownloader();
        
        Callback<WebDownload> cb = new Callback<WebDownload>()
        {
            private int attempts = 0;
            
            
            @Override
            public void setAttempts(int n)
            {
                this.attempts = n;
            }
            
            
            @Override
            public int getAttempts()
            {
                return this.attempts;
            }
            
            
            @Override
            public void onComplete(WebDownload result)
            {
            }
            
            
            @Override
            public void onSuccess(WebDownload result)
            {
            }
            
            
            @Override
            public void onFailure(WebDownload result)
            {
                WebDownloadResult wdr = result.getResult();
                Assert.assertEquals(wdr, WebDownloadResult.SC_FORBIDDEN);
            }
        };
        downloader.downloadContent("https://wordfinder-001.appspot.com/wordfinder?game=1&pos=Z88", cb, 5);
        Assert.assertEquals(5, cb.getAttempts());
    }
    
    
    @Test
    public void testRetries()
    {
        WebDownloader downloader = new WebDownloader();
        
        Callback<WebDownload> cba = new Callback<WebDownload>()
        {
            private int attempts = 0;
            
            
            @Override
            public void setAttempts(int n)
            {
                this.attempts = n;
            }
            
            
            @Override
            public int getAttempts()
            {
                return this.attempts;
            }
            
            
            @Override
            public void onComplete(WebDownload result)
            {
            }
            
            
            @Override
            public void onSuccess(WebDownload result)
            {
            }
            
            
            @Override
            public void onFailure(WebDownload result)
            {
            }
        };
        downloader.downloadContent("https://wordfinder-001.appspot.com/wordfinder?game=1&pos=A1", cba, 5);
        Assert.assertEquals(1, cba.getAttempts());
        
        Callback<WebDownload> cbb = new Callback<WebDownload>()
        {
            private int attempts = 0;
            
            
            @Override
            public void setAttempts(int n)
            {
                this.attempts = n;
            }
            
            
            @Override
            public int getAttempts()
            {
                return this.attempts;
            }
            
            
            @Override
            public void onComplete(WebDownload result)
            {
            }
            
            
            @Override
            public void onSuccess(WebDownload result)
            {
            }
            
            
            @Override
            public void onFailure(WebDownload result)
            {
            }
        };
        downloader.downloadContent("https://wordfinder-001.appspot.com/wordfinder?game=1&pos=Z99", cbb, 5);
        Assert.assertEquals(5, cbb.getAttempts());
    }
    
    
}
