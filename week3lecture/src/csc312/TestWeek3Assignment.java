package csc312;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestWeek3Assignment {
	
	
	@Test
	public void testValidURL() {
		
		Week3Assignment week3Assignment = new Week3Assignment();
		
		assertEquals( new Character('c'), week3Assignment.getURL( "https://wordfinder-001.appspot.com/wordfinder?game=2&pos=c3" ) );
		
	}
	
	@Test
	public void testServerError() {
		
		Week3Assignment week3Assignment = new Week3Assignment();
		//
		assertEquals( null, week3Assignment.getURL( "https://wordfinder-001.appspot.com/wordfinder?game=2&pos=Z99" ) );
		
	}
	
	@Test
	public void testServerForbidden() {
		
		Week3Assignment week3Assignment = new Week3Assignment();
		
		assertEquals( null, week3Assignment.getURL( "https://wordfinder-001.appspot.com/wordfinder?game=2&pos=Z88" ) );
		
	}
	
	@Test
	public void testInvalidDomain() {
		
		Week3Assignment week3Assignment = new Week3Assignment();
		
		assertEquals( null, week3Assignment.getURL( "https://aninvaliddomainname.com" ) );
		
	}
	
	
}