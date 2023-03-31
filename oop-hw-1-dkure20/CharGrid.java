// HW1 2-d array Problems
// CharGrid encapsulates a 2-d grid of chars and supports
// a few operations on the grid.

public class CharGrid {
	private char[][] grid;

	/**
	 * Constructs a new CharGrid with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public CharGrid(char[][] grid) {
		this.grid = grid;
	}
	
	/**
	 * Returns the area for the given char in the grid. (see handout).
	 * @param ch char to look for
	 * @return area for given char
	 */
	public int charArea(char ch) {
		boolean contains = false;
		int minRow = Integer.MAX_VALUE;
		int minCol = Integer.MAX_VALUE;
		int maxRow = Integer.MIN_VALUE;
		int maxCol = Integer.MIN_VALUE;
		for(int i=0; i<grid.length; i++){
			for(int j=0; j < grid[i].length; j++){
				if(grid[i][j] == ch){
					contains = true;
					if(i < minRow){
						minRow = i;
					}
					if(i > maxRow){
						maxRow = i;
					}
					if(j < minCol){
						minCol = j;
					}
					if(j > maxCol){
						maxCol = j;
					}
				}
			}
		}
		if(contains){
			return (maxRow - minRow +1) * (maxCol - minCol + 1);
		}
		return 0;
	}

	/**
	 * Returns the count of '+' figures in the grid (see handout).
	 * @return number of + in grid
	 */
	public int countPlus() {
		int crossCounts = 0;
		for(int row=0; row < grid.length; row++) {
			for(int col = 0; col < grid[0].length; col++){
					if(isValidChar(row,col)){
						crossCounts++;
				}
			}
		}
		return crossCounts;
	}

	private boolean isValidChar(int row, int col) {
		int leftNum = findCross(row, col, 0, -1);
		int rightNum = findCross(row, col, 0, 1);
		int downNum = findCross(row, col, 1, 0);
		int upNum = findCross(row, col, -1, 0);
		boolean numOfCross = leftNum != 0;
		boolean equalSizes = (leftNum == rightNum)&& (leftNum == upNum) && (leftNum == downNum);
		return (numOfCross && equalSizes);
	}

	private int findCross(int row, int col, int x1, int y1) {
		int nextX = row + x1;
		int nextY = col + y1;
		if (inBounds(nextX, nextY) && grid[row][col] == grid[nextX][nextY]){
			return 1 + findCross(nextX, nextY, x1, y1);
		}else {
			return 0;
		}
	}

	private boolean inBounds(int i, int col) {
		return (i >= 0 && i < grid.length && col >= 0 && col < grid[0].length);
	}

}
