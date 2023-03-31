// StringCodeTest
// Some test code is provided for the early HW1 problems,
// and much is left for you to add.

import junit.framework.TestCase;

public class StringCodeTest extends TestCase {
	//
	// blowup
	//
	public void testBlowup1() {
		// basic cases
		assertEquals("xxaaaabb", StringCode.blowup("xx3abb"));
		assertEquals("xxxZZZZ", StringCode.blowup("2x3Z"));
		assertEquals("aabbbbbaaaa", StringCode.blowup("1a3bb3a3"));
		assertEquals("aaa", StringCode.blowup("aaa"));
		assertEquals("", StringCode.blowup(""));
	}
	
	public void testBlowup2() {
		// things with digits
		
		// digit at end
		assertEquals("axxx", StringCode.blowup("a2x3"));
		
		// digits next to each other
		assertEquals("a33111", StringCode.blowup("a231"));
		assertEquals("aaB1112", StringCode.blowup("aaB312"));
		// try a 0
		assertEquals("aabb", StringCode.blowup("aa0bb"));
	}
	
	public void testBlowup3() {
		// weird chars, empty string
		assertEquals("AB&&,- ab", StringCode.blowup("AB&&,- ab"));
		assertEquals("", StringCode.blowup(""));
		assertEquals("&&#!,", StringCode.blowup("&&#!,1"));
		// string with only digits
		assertEquals("", StringCode.blowup("2"));
		assertEquals("33", StringCode.blowup("23"));
	}
	
	
	//
	// maxRun
	//
	public void testRun1() {
		assertEquals(2, StringCode.maxRun("hoopla"));
		assertEquals(3, StringCode.maxRun("hoopllla"));
		assertEquals(1, StringCode.maxRun("h"));
		assertEquals(2, StringCode.maxRun("hooppla"));
		assertEquals(4, StringCode.maxRun("pppp"));
	}
	
	public void testRun2() {
		assertEquals(3, StringCode.maxRun("abbcccddbbbxx"));
		assertEquals(0, StringCode.maxRun(""));
		assertEquals(3, StringCode.maxRun("hhhooppoo"));
		assertEquals(1, StringCode.maxRun("abcde"));
	}
	
	public void testRun3() {
		// "evolve" technique -- make a series of test cases
		// where each is change from the one above.
		assertEquals(1, StringCode.maxRun("123"));
		assertEquals(2, StringCode.maxRun("1223"));
		assertEquals(2, StringCode.maxRun("112233"));
		assertEquals(3, StringCode.maxRun("1112233"));
	}

	public void testIntersect1(){
		assertTrue(StringCode.stringIntersect("dato", "dbbbbbb", 1));
		assertFalse(StringCode.stringIntersect("dato", "tatuli", 3));
		assertFalse(StringCode.stringIntersect("", "gio", 2));
		assertFalse(StringCode.stringIntersect("", "", 0));
		assertFalse(StringCode.stringIntersect("hello", "world", 2));
		assertTrue(StringCode.stringIntersect("abbceqsd", "aporbbcesz", 3));

	}
}
