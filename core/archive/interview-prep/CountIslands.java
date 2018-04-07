/*
Given a 2d array of 1's and 0's, count the number of islands (NSEW connected 1's)
*/
public class CountIslands{

  public static int countIslands(int[][] A){
    if(A.length == 0 || A[0].length == 0) return -1;
    int islands = 0;
    for(int i=0; i<A.length; i++){
      for(int j=0; j<A[0].length; j++){
        if(A[i][j] == 1){
          islands++;
          depthFirstSearch(A, i, j);
        }
      }
    }
    return islands;
  }

  private static void depthFirstSearch(int[][] A, int row, int col){
    if(row < 0 || row >= A.length) return;
    if(col < 0 || col >= A[0].length) return;
    if(A[row][col] == 0) return;
    A[row][col] = 0;
    depthFirstSearch(A, row-1, col);
    depthFirstSearch(A, row+1, col);
    depthFirstSearch(A, row, col-1);
    depthFirstSearch(A, row, col+1);
  }

  public static void main(String[] args){
      int[][] A = {
        {1, 0, 0, 0, 0},
        {1, 1, 1, 0, 0},
        {0, 0, 0, 1, 0},
        {1, 1, 1, 1, 0},
        {1, 1, 0, 0, 1}
      };

      System.out.println(countIslands(A));
  }
}
