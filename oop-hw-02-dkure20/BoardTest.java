import junit.framework.TestCase;


public class BoardTest extends TestCase {
	Board b;
	Board b2;
	Piece pyr1, pyr2, pyr3, pyr4, s, sRotated,stick,square;


	// This shows how to build things in setUp() to re-use
	// across tests.

	// In this case, setUp() makes shapes,
	// and also a 3X6 board, with pyr placed at the bottom,
	// ready to be used by tests.

	protected void setUp() throws Exception {
		b = new Board(3, 6);
		b2 = new Board(4,8);
		pyr1 = new Piece(Piece.PYRAMID_STR);
		pyr2 = pyr1.computeNextRotation();
		pyr3 = pyr2.computeNextRotation();
		pyr4 = pyr3.computeNextRotation();
		square = new Piece(Piece.SQUARE_STR);
		stick = new Piece(Piece.STICK_STR);
		s = new Piece(Piece.S1_STR);
		sRotated = s.computeNextRotation();

		b.place(pyr1, 0, 0);
	}

	// Check the basic width/height/max after the one placement
	public void testSample1() {
		assertEquals(3, b.getWidth());
		assertEquals(6, b.getHeight());
		assertFalse(b.getGrid(0,1));
		assertTrue(b.getGrid(42,123));
		assertEquals(1, b.getColumnHeight(0));
		assertEquals(2, b.getColumnHeight(1));
		assertEquals(2, b.getMaxHeight());
		assertEquals(3, b.getRowWidth(0));
		assertEquals(1, b.getRowWidth(1));
		assertEquals(0, b.getRowWidth(2));
	}

	// Place sRotated into the board, then check some measures
	public void testSample2() {
		b.commit();
		int result = b.place(sRotated, 1, 1);
		assertEquals(Board.PLACE_OK, result);
		assertEquals(1, b.getColumnHeight(0));
		assertEquals(4, b.getColumnHeight(1));
		assertEquals(3, b.getColumnHeight(2));
		assertEquals(4, b.getMaxHeight());
		b.commit();
		int result1 = b.place(sRotated, 6, 12);
		b.commit();
		assertEquals(Board.PLACE_OUT_BOUNDS, result1);
	}

	public void testClearRows(){
		b.commit();
		int result = b.place(sRotated, 1, 1);
		b.commit();
		int num = b.clearRows();
		assertEquals(2,b.getColumnHeight(2));
		assertEquals(3,b.getColumnHeight(1));
		assertEquals(0,b.getColumnHeight(0));
		assertEquals(1,num);
		assertEquals(3, b.getMaxHeight());
		b.undo();
		assertEquals(1,b.getColumnHeight(0));
		assertEquals(4,b.getColumnHeight(1));
		assertEquals(3,b.getColumnHeight(2));
		int result2 = b.place(stick, 0, 1);
		b.commit();
		int num2 = b.clearRows();
		assertEquals(0,b.getColumnHeight(2));
		assertEquals(1,b.getColumnHeight(1));
		assertEquals(2,b.getColumnHeight(0));
		assertEquals(3,num2);
		assertEquals(2, b.getMaxHeight());
	}
	public void testClearRows2(){
		b.commit();
		int result = b.place(square,0,2);
		b.commit();
		assertEquals(result, Board.PLACE_OK);
		int result3 = b.place(square,0,1);
		b.commit();
		assertEquals(result3, Board.PLACE_BAD);
		assertEquals(4,b.getColumnHeight(0));
		int result2 = b.place(stick,2,1);
		b.commit();
		assertEquals(result2, Board.PLACE_ROW_FILLED);
		assertEquals(b.getMaxHeight(), 5);
		int cl = b.clearRows();
		assertEquals(cl, 4);
		assertEquals(0,b.getColumnHeight(0));
		assertEquals(0,b.getColumnHeight(1));
		assertEquals(1,b.getColumnHeight(2));
		assertEquals(1,b.getMaxHeight());
		assertEquals(1,b.getRowWidth(0));
		assertEquals(0,b.getRowWidth(1));

	}
	public void testDropHeight(){
		b.commit();
		assertEquals(1,b.dropHeight(sRotated,1));
		assertEquals(1,b.dropHeight(stick,2));
		assertEquals(1,b.dropHeight(stick,2));
		assertEquals(2,b.dropHeight(square,0));

	}
	public void testGroup6(){
		b2.commit();
		b2.place(square, 0, 0);
		b2.commit();
		b2.place(stick,2,0);
		b2.commit();
		b2.place(stick,3,0);
		b2.commit();
		b2.place(square,0,2);
		String mechanicBoard =
				"|    |" + "\n" +
				"|    |" + "\n" +
				"|    |" + "\n" +
				"|    |" + "\n" +
				"|++++|" + "\n" +
				"|++++|" + "\n" +
				"|++++|" + "\n" +
				"|++++|" + "\n" +
				"------";
		b2.commit();
		assertEquals(mechanicBoard, b2.toString());

	}
}
