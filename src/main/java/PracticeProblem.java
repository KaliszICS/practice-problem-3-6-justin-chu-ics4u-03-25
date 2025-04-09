public class PracticeProblem {

	public static void main(String args[]) {

	}

	public static int searchMazeMoves(String[][] maze) {
		int rows = maze.length;
		int cols = maze[0].length;

		//start pos bottom left
		int startRow = rows - 1;
		int startCol = 0;

		//visited array to track visited squares in the current path search
		boolean[][] visited = new boolean[rows][cols];

		//recursive helper call
		int result = searchMazeMovesHelper(maze, startRow, startCol, visited);

		//if "F" not reachable return int max val
		return (result == Integer.MAX_VALUE) ? -1 : result;
	}

	private static int searchMazeMovesHelper(String[][] maze, int row, int col, boolean[][] visited) {
		int rows = maze.length;
		int cols = maze[0].length;

		//out of bounds base case
		if (row < 0 || row >= rows || col < 0 || col >= cols) {
			return Integer.MAX_VALUE; //inval path
		}

		//hit wall or alr visited path base case
		if (maze[row][col].equals("*") || visited[row][col]) {
			return Integer.MAX_VALUE; //inval path or cycle/revisit
		}

		//found finish (F) base case
		if (maze[row][col].equals("F")) {
			return 0; //0 moves needed from the finish itself
		}

		//mark current square as visited for this path exploration
		visited[row][col] = true;

		//exploratiin
		int upMoves = searchMazeMovesHelper(maze, row - 1, col, visited); //up
		int rightMoves = searchMazeMovesHelper(maze, row, col + 1, visited); //right

		//backtrack and unmark current square so other paths can use it
		visited[row][col] = false;

		//find the minimum moves among the valid paths from neighbors
		int minNeighborMoves = Math.min(upMoves, rightMoves);

		//if no neighbor reached "F" (both returned max val), path is a dead end
		if (minNeighborMoves == Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}

		//otherwise return minimum moves from neighbors + 1 (for the current step)
		return minNeighborMoves + 1;
	}


	public static int noOfPaths(String[][] maze) {
		int rows = maze.length;
		int cols = maze[0].length;

		//start pos bottom left
		int startRow = rows - 1;
		int startCol = 0;

		//visited array to keep track of squares visited in the current path exploration
		boolean[][] visited = new boolean[rows][cols];

		//helper f(x)
		return noOfPathsHelper(maze, startRow, startCol, visited);
	}

	private static int noOfPathsHelper(String[][] maze, int row, int col, boolean[][] visited) {
		int rows = maze.length;
		int cols = maze[0].length;

		//out of bounds base case
		if (row < 0 || row >= rows || col < 0 || col >= cols) {
			return 0; //no more paths
		}

		//hit wall or alr visited base case
		if (maze[row][col].equals("*") || visited[row][col]) {
			return 0; //no more paths
		}

		//found the finish "F" base case
		if (maze[row][col].equals("F")) {
			return 1; //found a valid path
		}

		//mark current square as visited for this path exploration
		visited[row][col] = true;

		//explore
		int paths = 0;
		paths += noOfPathsHelper(maze, row - 1, col, visited); //up
		paths += noOfPathsHelper(maze, row, col + 1, visited); //right

		//backtrak and unmark cell as unvisited
		visited[row][col] = false;

		//return total count of paths found through neighbors
		return paths;
	}

	
}
