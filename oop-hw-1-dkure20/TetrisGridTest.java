import junit.framework.TestCase;
import java.util.*;

public class TetrisGridTest extends TestCase {
	
	// Provided simple clearRows() test
	// width 2, height 3 grid
	public void testClear1() {
		boolean[][] before =
		{	
			{true, false, true},
			{false, true, true}
		};
		
		boolean[][] after =
		{
				{true, false, false},
				{false, true, false}
		};
		boolean[][] before2 =
				{
						{true, true, true},
						{true, true, true}
				};

		boolean[][] after2 =
				{
						{false, false, false},
						{false, false, false}
				};
		boolean[][] before3 =
				{
						{true, false, false},
						{true, true, true}
				};

		boolean[][] after3 =
				{
						{false, false, false},
						{true, true, false}
				};
		TetrisGrid tetris = new TetrisGrid(before);
		TetrisGrid tetris2 = new TetrisGrid(before2);
		TetrisGrid tetris3 = new TetrisGrid(before3);
		tetris.clearRows();
		tetris2.clearRows();
		tetris3.clearRows();
		assertTrue(Arrays.deepEquals(after, tetris.getGrid()));
		assertTrue(Arrays.deepEquals(after2, tetris2.getGrid()));
		assertTrue(Arrays.deepEquals(after3, tetris3.getGrid()));
	}
	public void testClear2(){
		boolean[][] before =
				{
						{false},
						{true}
				};

		boolean[][] after =
				{
						{true},
						{false}
				};
		boolean[][] before2 =
				{
						{true},
						{true}
				};

		boolean[][] after2 =
				{
						{false},
						{false}
				};
		boolean[][] before3 =
				{
						{true,false},
						{true,true}
				};

		boolean[][] after3 =
				{
						{false,false},
						{true,false}
				};
		TetrisGrid tetris = new TetrisGrid(before);
		TetrisGrid tetris2 = new TetrisGrid(before2);
		TetrisGrid tetris3 = new TetrisGrid(before3);
		tetris.clearRows();
		tetris2.clearRows();
		tetris3.clearRows();
		assertFalse(Arrays.deepEquals(after, tetris.getGrid()));
		assertTrue(Arrays.deepEquals(after2, tetris2.getGrid()));
		assertTrue(Arrays.deepEquals(after3, tetris3.getGrid()));

	}
	public void testClear3(){
		boolean[][] before =
				{
						{true,true,true},
						{true,true,true}
				};

		boolean[][] after =
				{
						{false,false,false},
						{false,false,false}
				};
		boolean[][] before2 =
				{
						{true,true,true},
						{false,true,false}
				};

		boolean[][] after2 =
				{
						{true,true,false},
						{false,false,false}
				};
		boolean[][] before3 =
				{
						{false,true,true},
						{true,true,true}
				};

		boolean[][] after3 =
				{
						{false,false,false},
						{true,false,false}
				};
		TetrisGrid tetris = new TetrisGrid(before);
		TetrisGrid tetris2 = new TetrisGrid(before2);
		TetrisGrid tetris3 = new TetrisGrid(before3);
		tetris.clearRows();
		tetris2.clearRows();
		tetris3.clearRows();
		assertTrue(Arrays.deepEquals(after, tetris.getGrid()));
		assertTrue(Arrays.deepEquals(after2, tetris2.getGrid()));
		assertTrue(Arrays.deepEquals(after3, tetris3.getGrid()));

	}
	
}
