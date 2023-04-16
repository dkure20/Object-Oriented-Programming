import junit.framework.TestCase;

import java.util.*;

/*
  Unit test for Piece class -- starter shell.
 */
public class PieceTest extends TestCase {
	// You can create data to be used in the your
	// test cases like this. For each run of a test method,
	// a new PieceTest object is created and setUp() is called
	// automatically by JUnit.
	// For example, the code below sets up some
	// pyramid and s pieces in instance variables
	// that can be used in tests.
	private Piece pyr1, pyr2, pyr3, pyr4;
	private Piece stick1, stick2, stick3;
	private Piece l2, l21, l22;
	private Piece square1, square2, square3;
	private Piece s, sRotated;
	private Piece[] pieces;

	protected void setUp() throws Exception {
		super.setUp();
		pyr1 = new Piece(Piece.PYRAMID_STR);
		pyr2 = pyr1.computeNextRotation();
		pyr3 = pyr2.computeNextRotation();
		pyr4 = pyr3.computeNextRotation();
		stick1 = new Piece(Piece.STICK_STR);
		stick2 = stick1.computeNextRotation();
		stick3 = stick2.computeNextRotation();
		l2 = new Piece(Piece.L2_STR);
		l21 = l2.computeNextRotation();
		l22 = l21.computeNextRotation().computeNextRotation().computeNextRotation();
		square1 = new Piece(Piece.SQUARE_STR);
		square2= square1.computeNextRotation();
		square3 = square2.computeNextRotation();
		s = new Piece(Piece.S1_STR);
		sRotated = s.computeNextRotation();
		pieces = Piece.getPieces();
	}
	
	// Here are some sample tests to get you started
	
	public void testSampleSize() {
		// Check size of pyr piece
		assertEquals(3, pyr1.getWidth());
		assertEquals(2, pyr1.getHeight());
		
		// Now try after rotation
		// Effectively we're testing size and rotation code here
		assertEquals(2, pyr2.getWidth());
		assertEquals(3, pyr2.getHeight());
		assertEquals(3, pyr3.getWidth());
		assertEquals(2, pyr3.getHeight());
		assertEquals(2, pyr4.getWidth());
		assertEquals(3, pyr4.getHeight());
		
		// Now try with some other piece, made a different way
		assertEquals(2, l2.getWidth());
		assertEquals(3, l2.getHeight());
		assertEquals(2, l21.getHeight());
		assertEquals(3, l21.getWidth());
		assertEquals(3, l22.getHeight());
		assertEquals(2, l22.getWidth());


		assertEquals(1, stick1.getWidth());
		assertEquals(4, stick1.getHeight());
		assertEquals(1, stick2.getHeight());
		assertEquals(4, stick2.getWidth());

		assertEquals(2, square1.getWidth());
		assertEquals(2, square1.getHeight());
		assertEquals(3, s.getWidth());
		assertEquals(2, s.getHeight());
		assertEquals(2, sRotated.getWidth());
		assertEquals(3, sRotated.getHeight());
	}
	
	
	// Test the skirt returned by a few pieces
	public void testSampleSkirt() {
		// Note must use assertTrue(Arrays.equals(... as plain .equals does not work
		// right for arrays.
		assertTrue(Arrays.equals(new int[] {0, 0, 0}, pyr1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 0, 1}, pyr3.getSkirt()));
		
		assertTrue(Arrays.equals(new int[] {0, 0, 1}, s.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 0}, sRotated.getSkirt()));

		assertTrue(Arrays.equals(new int[] {0}, stick1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0,0,0,0}, stick2.getSkirt()));

		assertTrue(Arrays.equals(new int[] {0,0}, l2.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1,1,0}, l21.getSkirt()));

		assertTrue(Arrays.equals(new int[] {0,0}, square1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0,0}, square2.getSkirt()));
	}
	public void testPiecesEquality(){

		assertTrue(l2.equals(l22));
		assertFalse(l21.equals(l22));
		assertTrue(l21.equals(new Piece("0 1 1 1 2 0 2 1")));
		//sticks
		assertTrue(stick1.equals(stick3));
		assertFalse(stick1.equals(stick2));
		assertTrue(!stick2.equals(stick3));
		assertTrue(stick1.equals(new Piece("0 0 0 2 0 3 0 1")));

		//squares
		assertTrue(square2.equals(square1));
		assertTrue(square2.equals(square3));
		assertFalse(square2.equals(s));
		assertTrue(square1.equals(new Piece("1 0 0 0 1 1 0 1")));

		//pyramid
		assertFalse(pyr1.equals(pyr2));
		assertFalse(pyr2.equals(pyr3));
		assertTrue(pyr1.equals(pyr4.computeNextRotation()));

		//s
		assertFalse(s.equals(s.computeNextRotation()));
		assertTrue(s.equals(s.computeNextRotation().computeNextRotation()));
		assertTrue(s.equals(new Piece("0 0 1 0 1 1 2 1")));
		assertFalse(s.equals(new Piece("0 0 1 0 1 3 2 1")));

	}
	public void testFastRotations(){
		//sticks
		assertTrue(stick1.equals(pieces[Piece.STICK]));
		assertFalse(stick1.equals(pieces[Piece.STICK].fastRotation()));
		assertTrue(stick2.equals(pieces[Piece.STICK].fastRotation()));
		assertTrue(stick1.equals(pieces[Piece.STICK].fastRotation().fastRotation()));
		assertFalse(pyr1.equals(pieces[Piece.PYRAMID].fastRotation().fastRotation()));
        //square and pyr
		assertTrue(square1.equals(pieces[Piece.SQUARE].fastRotation().fastRotation().fastRotation()));
		assertTrue(square1.equals(pieces[Piece.SQUARE].fastRotation().fastRotation().fastRotation().fastRotation()));
		assertTrue(pyr1.equals(pieces[Piece.PYRAMID].fastRotation().fastRotation().fastRotation().fastRotation()));
		assertTrue(pyr2.equals(pieces[Piece.PYRAMID].fastRotation().fastRotation().fastRotation().fastRotation().fastRotation()));
		//test l2
		assertTrue(l2.equals(pieces[Piece.L2].fastRotation().fastRotation().fastRotation().fastRotation()));
		assertFalse(l21.equals(pieces[Piece.L2].fastRotation().fastRotation().fastRotation().fastRotation()));
		assertFalse(l22.equals(pieces[Piece.L2].fastRotation().fastRotation().fastRotation().fastRotation().fastRotation().fastRotation().fastRotation()));
		assertTrue(l22.equals(pieces[Piece.L2].fastRotation().fastRotation().fastRotation().fastRotation()));
	}

}
