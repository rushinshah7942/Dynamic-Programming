/*
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(2, 2, 1, 1) -> 11
sumRegion(1, 2, 2, 4) -> 12

Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.

*/


// Reference: https://en.wikipedia.org/wiki/Summed_area_table

public class NumMatrix {
    
    int[][] dp;

    public NumMatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0) 
            return;
        
        int row = matrix.length;
        int col = matrix[0].length;
        
        dp = new int[row+1][col+1];
        
        for(int i=1;i<=row;i++){
            for(int j=1;j<=col;j++){
                // sum at (x,y) 
                // Moreover, the summed area table can be computed efficiently in a single pass over the image, 
                // using the fact that the value in the summed area table at (x, y) is just:
                // e.g. I(x,y) = i(x,y) + I(x-1,y) + I(x,y-1) - I(x-1,y-1)
                dp[i][j] = matrix[i-1][j-1] + dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1];
            }
        }
    }
    public int sumRegion(int row1, int col1, int row2, int col2) {
        // D - B - C + A
        // https://en.wikipedia.org/wiki/Summed_area_table
		
		int iMin = Math.min(row1, row2);
		int iMax = Math.max(row1, row2);
		
		int jMin = Math.min(col1, col2);
		int jMax = Math.max(col1, col2);
		
		return dp[iMax + 1][jMax + 1] - dp[iMax + 1][jMin] - dp[iMin][jMax + 1] + dp[iMin][jMin];    
		
		/*
        return dp[row2+1][col2+1] + dp[row1][col1] - dp[row2+1][col1] - dp[row1][col2+1];
		*/
    }
}


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.sumRegion(1, 2, 3, 4);
						
				
				
				

