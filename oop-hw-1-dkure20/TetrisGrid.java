//
// TetrisGrid encapsulates a tetris board and has
// a clearRows() capability.



public class TetrisGrid {
	private boolean[][] grid;
	/**
	 * Constructs a new instance with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public TetrisGrid(boolean[][] grid) {
		this.grid = grid;
	}
	
	
	/**
	 * Does row-clearing on the grid (see handout).
	 */
	public void clearRows() {
		int count = 0;
		int curCol = 0;
		boolean [][] newGrid = new boolean[grid.length][grid[0].length];
		for(int col = 0; col<grid[0].length;col++){
			for(int row = 0; row < grid.length; row++){
				newGrid[row][curCol] = grid[row][col];
				if(grid[row][col])count++;
			}
			if(count!=grid.length){
				curCol++;
			}
			count=0;
		}
		for(int i = curCol; i<grid[0].length; i++){
			for(int row = 0; row<grid.length; row++){
				newGrid[row][i] = false;
			}
		}
		grid= newGrid;
	}

	/**
	 * Returns the internal 2d grid array.
	 * @return 2d grid array
	 */
	boolean[][] getGrid() {
		return grid;
	}
}
