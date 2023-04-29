import java.util.Random;
/* Manages the game and all of its attributes */
public class GameSystem {
   public final static int DEFAULT_GRID_HEIGHT =    4;
   public final static int DEFAULT_GRID_WIDTH  =    4;
   public final static int DEFAULT_WINNING_VAL = 2048;
   public final static int DEFAULT_WINNING_BKL = 2048;
   
   public final static int LEFT                =    0;
   public final static int UP                  =    1;
   public final static int RIGHT               =    2;
   public final static int DOWN                =    3;
   
   public static int customWinningVal=DEFAULT_WINNING_VAL; //set customWinningValue to default if the user didn't specify the winning value

   private Block[][] grid;                              // The grid of the game
   private Player[] allPlayer;                          // To keep track of the two players
   
   private int winningVal;                              // Value that must be reached to win the game.
   private int winningBlk;
   private Player currPlayer;                           // Keeps track of the current player
   
   //=================== CONSTRUCTOR =====================//
   /* The constructor to initialize the grid. */
   public GameSystem() {
     // initiate grid with default height (row) and width (height)
     Block[][] grid = new Block[DEFAULT_GRID_HEIGHT][DEFAULT_GRID_WIDTH];
     //initiate two players
     Player [] allPlayer = new Player[2];
   }
  
   /* The constructor to customize the winning value.
    * @param winningBlk - The block that the player must reach 
    *                     to win the game.
    * @param winningVal - The value that the player must reach
    *                     to win the game.
    * @param height     - Height of the grid
    * @param width      - Width of the grid */
   public GameSystem(int winningBlk, int winningVal, int height, int width) {
     this.customWinningVal = winningVal;
     Block[][]grid = new Block[height][width];
   }
   
   //================== PRIVATE METHODS ====================//
   /* (Write your private methods here) */
  // to generate random position for new block
  private int generatePos(int maxNum) {   
      //Create new random object
      Random rand = new Random();
      //Generate random number between 0 and max length
      return rand.nextInt(maxNum);
  }
  //check if a position is empty or not to make a move or to place a new block on the grid
  private boolean isEmpty(int row, int column) {
      if(GetGrid()[row],column==null)
      {
         return true;
      }
      else
      {
         return false;
      }
  }
  //return biggest block value in the grid to determine if the game is over or not
  private int biggestVal()
  {
      //initiate biggest value as 2 because all blocks have at least a value of two
      int biggest = 2;
      for(int i =0; i< grid.length,i++)
      {
         for(int j=0;j<grid[i].length;j++)
         {
            if(grid[i][j]>biggest)
            {
               biggest = grid[i][j];
            }
         }
      }
      return biggest;
  }
  //to check if the game is over or not 
  //(logic: if there are no adjacent blocks with same value > game over)
  //(or the wining value is reached)
  private boolean isGameOver(){
   // write code here
  }
         
   //================== PUBLIC METHODS ====================//
   /* Get the whole grid of the game
    * @return - The Block object 2D array */
   public Block[][] GetGrid() {
      return this.grid;
   }
   
   /* Randomize a block and its value (between 2 or 4) and place it 
    * in the grid. Note: Must not randomize a position that already 
    * has a block. */
   public void RandBlock() {

      Random rand = new Random();
      int blkVal = rand.nextInt(2); //generate a random int, 0 or 1
      blkVal = blkVal*2; // multiply it to get 0 or 2
      blkVal = blkVal+2; //add 2 to it to get 2 or 4;

      while (true)
      {
         int row = generatePos(grid.length());
         int column = generatePos(grid[0].length());
         if (isEmpty(row,column))
         {  
            grid[row,column] = new Block(blkVal)//create new block with value of 2 or 4
            break;
         }
      }
   }
   
   /* Move all the blocks to the specified direction. If the direction is
    * invalid, it will not move any blocks.
    * @param dir - The direction that the user wants to move to.
    *              Refer to GameSystem global variables for direction
    *              values.
    * @return    - Returns points earned from this round of move. 
    *              Returns -1 if the direction is invalid, and blocks
    *              will not be moved. */
   public int Move(int dir) {
      return 0;  // Dummy return value. Needs to be changed.
   }
   
   /* Set who will be the player turn
    * @param playerId - The ID of the player */
   public void SetCurrPlayer(int playerId ) {
     this.currPlayer = GetPlayer(playerId)
   }
   
   /* Get the Player who is currently his/her turn
    * @return - The Player object of the player who is currently
    *           his/her turn */
   public Player GetCurrPlayer() {
      return this.currPlayer;  
   }
   
   /* Get the Player with the indicated player ID
    * @param playerId - The ID of the player 
    * @return         - The Player object of the player */
   public Player GetPlayer(int playerId ) {
      for(int i=0; i< allPlayer.length;i++)
      {
         if (allPlayer[i].GetId()==playerId)
         {
            return allPlayer[i];
         }
      }
   }
   
   /* Switch player's turn. If it is player 0 turn, then it will
    * switch to player 1, and vice versa */
   public void SwitchPlayer() {
     if (currPlayer.GetId()==0)
     {
      this.currPlayer = allPlayer[1]; //set currentPlayer to 2nd player if current id is 0
     }
     else
     {
      this.currPlayer = allPlayer[0]; //set currentPlayer to 1st player if current id is 1
     }
   }
   
   /* Check if the player wins or not by reaching the 
    * specified winning value.
    * @return - True if at least one of the blocks is equals to the wining value. 
    *           False otherwise. */
   public boolean CheckWinner() {
      return false;  // Dummy return value. Needs to be changed.
   }
   
   /* Check if the grid is full or not
    * @return - True if the grid has no more empty blocks
    *           False otherwise. */
   public boolean IsGridFull() {
      for (int i=0; i<grid.length;i++)
      {
         for (int j=0;j<grid[i].length;j++)
         {
            if (grid[i][j].isEmpty())
            {
               return false;
            }
            else
            {
               continue;
            }
         }
      }
      return true;  // Dummy return value. Needs to be changed.
   }
}