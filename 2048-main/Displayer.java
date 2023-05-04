/* A class that handles all the printing of
 * the game. */
public class Displayer {
   public Displayer() {
   }
   
   /* Prints the grid and its content on the screen
    * @param grid - The Block object 2D array. */
    public void PrintGrid(Block[][] grid) {
      int numRows = grid.length;
      int numCols = grid[0].length;

      // Print the top border of the grid
      for (int j = 0; j < numCols; j++) {
          System.out.print("+--------");
      }
      System.out.println("+");

      // Print the contents of the grid
      for (int i = 0; i < numRows; i++) {
          System.out.print("|");
          for (int j = 0; j < numCols; j++) {
              if (grid[i][j] != null) {
               if(grid[i][j].GetIsNew())
               {
                  System.out.printf("%7s |", "{" + grid[i][j].GetVal() + "}");
               }
               else {
                  System.out.printf("%7d |", grid[i][j].GetVal());}
              } else {
                  System.out.print("        |");
              }
          }
          System.out.println();

          // Print the border between rows
          for (int j = 0; j < numCols; j++) {
              System.out.print("+--------");
          }
          System.out.println("+");
      }
  }
   
   /* Prints the scores of the two players
    * @param p1 - First player's Player object
    * @param p2 - Second player's Player object */
   public void PrintScores( Player p1, Player p2 ) {
      System.out.println("========================= Scores =========================");
      System.out.printf("|Player 0:%14d   |    Player 1:%10d      |\n", p1.GetScore(), p2.GetScore());
      System.out.println("==========================================================");
         }
}