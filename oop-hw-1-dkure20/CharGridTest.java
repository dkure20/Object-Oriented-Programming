
// Test cases for CharGrid -- a few basic tests are provided.

import junit.framework.TestCase;

public class CharGridTest extends TestCase {
	
	public void testCharArea1() {
		char[][] grid = new char[][] {
				{'a', 'y', ' '},
				{'x', 'a', 'z'}
			};
		char[][] grid2 = new char[][] {
				{'a', 'y', ' '},
				{'x', 'x', 'z'}
		};
		char[][] grid3 = new char[][] {
				{'a', 'a', 'a'},
				{'a', 'a', 'a'}
		};
		char[][] grid4 = new char[][] {
				{'b', 'b', 'a'},
				{'z', 'x', 'a'},
				{'z', 'x', 'a','b','b'}
		};
		
		
		CharGrid cg = new CharGrid(grid);
		CharGrid cg2 = new CharGrid(grid2);
		CharGrid cg3 = new CharGrid(grid3);
		CharGrid cg4 = new CharGrid(grid4);
		assertEquals(6, cg3.charArea('a'));
		assertEquals(1, cg2.charArea('a'));
		assertEquals(4, cg.charArea('a'));
		assertEquals(1, cg.charArea('z'));
		assertEquals(15, cg4.charArea('b'));
	}
	
	
	public void testCharArea2() {
		char[][] grid = new char[][] {
				{'c', 'a', ' '},
				{'b', ' ', 'b'},
				{'b', ' ', 'a'}
			};
		char[][] grid2 = new char[][] {
				{'a', 'a', 'a'},
				{'b', ' ', 'b'},
				{'b', 'c', 'c'}
		};
		
		CharGrid cg = new CharGrid(grid);
		CharGrid cg2 = new CharGrid(grid2);
		assertEquals(3, cg2.charArea('a'));
		assertEquals(0, cg.charArea('x'));
		assertEquals(1, cg.charArea('c'));
		assertEquals(6, cg.charArea('a'));
		assertEquals(6, cg.charArea('b'));
		assertEquals(3, cg2.charArea('a'));
		assertEquals(2, cg2.charArea('c'));
	}
	public void testCountPlus(){
		char[][] grid = new char[][] {
				{' ', 'x', 'r', 'a', 'o',' ', 'x', ' ', 'e'},
				{' ', 'p', 'x', 'a', ' ',' ', 'x', ' ', ' '},
				{'p', 'p', 'p', ' ','x','x','x', 'x', 'x'},
				{' ', 'p', ' ', 'z', ' ',' ', 'x', ' ', ' '},
				{' ', 'l', 't', 'q', 'a',' ', 'x', ' ', ' '},
				{' ', 'z', ' ', 'q', 'x','a', ' ', ' ', ' '},
				{'a', 'x', ' ', 'r', 'a','p', 'l', 'a', 'z'}
		};
		char[][] grid2 = new char[][] {
				{' ', 'x', 'r', 'a', 'o',' ', 'x', ' ', 'e'},
				{' ', 'p', 'x', 'a', ' ',' ', 'x', ' ', ' '},
				{'p', 'x', 'p', 'p','x','z','x', 'x', 'x'},
				{' ', 'p', ' ', 'z', ' ',' ', 'x', ' ', ' '},
				{' ', 'l', 't', 'q', 'a',' ', 'x', ' ', ' '},
				{' ', 'z', ' ', 'q', 'x','a', ' ', ' ', ' '},
				{'a', 'x', ' ', 'r', 'a','p', 'l', 'a', 'z'}
		};
		char[][] grid3 = new char[][] {
				{' ', 'x', 'r', 'a', 'o',' ', 'x', ' ', 'e'},
				{' ', 'p', 'x', 'a', ' ',' ', 'x', ' ', ' '},
				{'p', 'p', 'p', 'p','x','x','x', 'x', 'x'},
				{' ', 'p', ' ', 'z', ' ',' ', 'x', ' ', ' '},
				{' ', 'l', 't', 'q', 'a',' ', 'x', ' ', ' '},
				{' ', 'z', ' ', 'q', 'x','a', ' ', ' ', ' '},
				{'a', 'x', ' ', 'r', 'a','p', 'l', 'a', 'z'}
		};
		char[][] grid4 = new char[][] {
				{' ', 'x', 'r', 'a', 'o',' ', 'x', ' ', 'e'},
				{' ', 'p', 'x', 'a', ' ',' ', 'x', ' ', ' '},
				{'p', 'p', 'p', 'z','x','x','x', 'x', 'x'},
				{' ', 'p', ' ', 'z', ' ',' ', 'x', ' ', ' '},
				{' ', 'l', 't', 'q', 'a',' ', 'x', ' ', ' '},
				{' ', 'z', ' ', 'a', 'a','a', ' ', ' ', ' '},
				{'a', 'x', ' ', 'r', 'a','p', 'l', 'a', 'z'}
		};
		CharGrid cg = new CharGrid(grid);
		CharGrid cg2 = new CharGrid(grid2);
		CharGrid cg3 = new CharGrid(grid3);
		CharGrid cg4 = new CharGrid(grid4);
		assertEquals(2, cg.countPlus());
		assertEquals(0, cg2.countPlus());
		assertEquals(1, cg3.countPlus());
		assertEquals(3, cg4.countPlus());

	}

}
