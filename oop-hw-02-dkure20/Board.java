// Board.java

import java.util.Arrays;

/**
 CS108 Tetris Board.
 Represents a Tetris board -- essentially a 2-d grid
 of booleans. Supports tetris pieces and row clearing.
 Has an "undo" feature that allows clients to add and remove pieces efficiently.
 Does not do any drawing or have any idea of pixels. Instead,
 just represents the abstract 2-d board.
*/
public class Board	{
	// Some ivars are stubbed out for you:
	private int width;
	private int height;
	private boolean[][] grid;
	private boolean[][] backUpGrid;
	private int[] widthArr;
	private int[] xWidth;
	private int[] xHeight;
	private int[] heightArr;
	private boolean DEBUG = true;
	private int maxHeight;
	boolean committed;
	
	
	// Here a few trivial methods are provided:
	
	/**
	 Creates an empty board of the given width and height
	 measured in blocks.
	*/
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		grid = new boolean[width][height];
		backUpGrid = new boolean[width][height];
		committed = true;
		widthArr = new int[height];
		xWidth = new int[height];
		xHeight = new int[width];
		heightArr = new int[width];
	}
	
	
	/**
	 Returns the width of the board in blocks.
	*/
	public int getWidth() {
		return width;
	}
	
	
	/**
	 Returns the height of the board in blocks.
	*/
	public int getHeight() {
		return height;
	}
	
	
	/**
	 Returns the max column height present in the board.
	 For an empty board this is 0.
	*/
	public int getMaxHeight() {	 
		int maxH = 0;
		for(int i=0; i< heightArr.length; i++){
			if(heightArr[i] > maxH){
				maxH = heightArr[i];
			}
		}
		maxHeight = maxH;
		return maxHeight;
	}
	
	
	/**
	 Checks the board for internal consistency -- used
	 for debugging.
	*/
	public void sanityCheck() {
		if (DEBUG) {
			int maxHeightCheck = 0;
			int newWidthCheck[] = new int[height];
			int newHeightCheck[] = new int[width];
			for(int i=0; i<width; i++){
				for(int j =0; j< height; j++){
					if(grid[i][j]){
						if(j+1 > newHeightCheck[i]){
							newHeightCheck[i] = j+1;
						}
						if(j+1 > maxHeightCheck) maxHeightCheck = j+1;
						newWidthCheck[j]++;
					}
				}
			}
			if(!Arrays.equals(newWidthCheck,widthArr))throw new RuntimeException("Width arrays are not equal");
			if(!Arrays.equals(newHeightCheck,heightArr))throw new RuntimeException("Height arrays are not equal");
			if(maxHeightCheck!= maxHeight) throw new RuntimeException("max Heights are not equal");


		}
	}
	
	/**
	 Given a piece and an x, returns the y
	 value where the piece would come to rest
	 if it were dropped straight down at that x.
	 
	 <p>
	 Implementation: use the skirt and the col heights
	 to compute this fast -- O(skirt length).
	*/
	public int dropHeight(Piece piece, int x) {
		int res = 0;
		int[] skirt = piece.getSkirt();
		for(int i =0 ; i < skirt.length;i++) {
			int yCor = heightArr[x+i]-skirt[i];
			if(yCor>res)
				res=yCor;
		}
		return res;
	}
	
	
	/**
	 Returns the height of the given column --
	 i.e. the y value of the highest block + 1.
	 The height is 0 if the column contains no blocks.
	*/
	public int getColumnHeight(int x) {
		return heightArr[x];
	}
	
	
	/**
	 Returns the number of filled blocks in
	 the given row.
	*/
	public int getRowWidth(int y) {
		return widthArr[y];
	}
	
	
	/**
	 Returns true if the given block is filled in the board.
	 Blocks outside of the valid width/height area
	 always return true.
	*/
	public boolean getGrid(int x, int y) {
		return ( x<0 || y<0 ||  x >= width || y >= height || grid[x][y] );
	}
	public static final int PLACE_OK = 0;
	public static final int PLACE_ROW_FILLED = 1;
	public static final int PLACE_OUT_BOUNDS = 2;
	public static final int PLACE_BAD = 3;
	
	/**
	 Attempts to add the body of a piece to the board.
	 Copies the piece blocks into the board grid.
	 Returns PLACE_OK for a regular placement, or PLACE_ROW_FILLED
	 for a regular placement that causes at least one row to be filled.
	 
	 <p>Error cases:
	 A placement may fail in two ways. First, if part of the piece may falls out
	 of bounds of the board, PLACE_OUT_BOUNDS is returned.
	 Or the placement may collide with existing blocks in the grid
	 in which case PLACE_BAD is returned.
	 In both error cases, the board may be left in an invalid
	 state. The client can use undo(), to recover the valid, pre-place state.
	*/
	public int place(Piece piece, int x, int y) {
		// flag !committed problem
		if (!committed) throw new RuntimeException("place commit problem");
		committed = false;
		backUpCopy();
		int result = PLACE_OK;
		TPoint[] pieceBody = piece.getBody();
		for(int i=0; i< pieceBody.length; i++){
			int pieceX = x+ pieceBody[i].x;
			int pieceY = y + pieceBody[i].y;
			if(pieceX < 0 || pieceX >= width || pieceY < 0 || pieceY >= height){
				return PLACE_OUT_BOUNDS;
			}
			if(grid[pieceX][pieceY]){
				result = PLACE_BAD;
				break;
			}
			grid[pieceX][pieceY] = true;
			if(pieceY + 1 > heightArr[pieceX]){
				heightArr[pieceX] = pieceY + 1;
			}
			widthArr[pieceY]++;
			if(widthArr[pieceY] == width){
				result = PLACE_ROW_FILLED;
			}
		}
		maxHeight = getMaxHeight();
		sanityCheck();
		return result;
	}

	
	/**
	 Deletes rows that are filled all the way across, moving
	 things above down. Returns the number of rows cleared.
	*/
	public int clearRows() {
		if(committed){
			committed=false;
			backUpCopy();
		}
		int rowsCleared = 0;
		int toRow = 0;
		for(int fromRow = 0; fromRow<maxHeight; fromRow++){
			while(widthArr[fromRow] == width){
				fromRow++;
				rowsCleared++;
			}
			for(int column = 0; column < width; column++){
				grid[column][toRow] = grid[column][fromRow];
			}
			widthArr[toRow] = widthArr[fromRow];
			toRow++;
		}
		fillOtherRowsWithFalse(toRow);
		updateMaxHeightArr();
		maxHeight = this.getMaxHeight();
		sanityCheck();
		return rowsCleared;
	}

	private void backUpCopy() {
		System.arraycopy(widthArr,0,xWidth,0,widthArr.length);
		System.arraycopy(heightArr,0,xHeight,0,heightArr.length);
		for(int i=0; i< grid.length; i++){
			System.arraycopy(grid[i], 0, backUpGrid[i],0,height);
		}

	}

	private void updateMaxHeightArr() {
		Arrays.fill(heightArr,0);
		for(int i=0; i<width; i++){
			for(int j = 0; j<height; j++){
				if(grid[i][j]){
					if(j+1 > heightArr[i]){
						heightArr[i] = j+1;
					}
				}
			}
		}
	}

	private void fillOtherRowsWithFalse(int toRow) {
		for(int i= toRow; i<maxHeight; i++){
			widthArr[i] = 0;
			for(int col = 0; col < width; col++){
				grid[col][i] = false;
			}
		}
	}


	/**
	 Reverts the board to its state before up to one place
	 and one clearRows();
	 If the conditions for undo() are not met, such as
	 calling undo() twice in a row, then the second undo() does nothing.
	 See the overview docs.
	*/
	public void undo() {
		if(!committed){
			swap();
		}
		commit();
		sanityCheck();
	}

	private void swap() {
		int[]temp = widthArr;
		widthArr = xWidth;
		xWidth = temp;
		int[]newTemp = heightArr;
		heightArr = xHeight;
		xHeight = newTemp;
		boolean[][] tmp = grid;
		grid = backUpGrid;
		backUpGrid = tmp;
		maxHeight = this.getMaxHeight();
	}


	/**
	 Puts the board in the committed state.
	*/
	public void commit() {
		committed = true;
	}


	
	/*
	 Renders the board state as a big String, suitable for printing.
	 This is the sort of print-obj-state utility that can help see complex
	 state change over time.
	 (provided debugging utility) 
	 */
	public String toString() {
		StringBuilder buff = new StringBuilder();
		for (int y = height-1; y>=0; y--) {
			buff.append('|');
			for (int x=0; x<width; x++) {
				if (getGrid(x,y)) buff.append('+');
				else buff.append(' ');
			}
			buff.append("|\n");
		}
		for (int x=0; x<width+2; x++) buff.append('-');
		return(buff.toString());
	}
}


