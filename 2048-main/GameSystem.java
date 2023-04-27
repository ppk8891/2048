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
   
   private Block[][] grid;                              // The grid of the game
   private Player[] allPlayer;                          // To keep track of the two players
   
   private int winningVal;                              // Value that must be reached to win the game.
   private int winningBlk;
   private Player currPlayer;                           // Keeps track of the current player
   
   //=================== CONSTRUCTOR =====================//
   /* The constructor to initialize the grid. */
   public GameSystem() {
     block[][] grid = new block[DEFAULT_GRID_HEIGHT][DEFAULT_GRID_WIDTH];

   }
  
   /* The constructor to customize the winning value.
    * @param winningBlk - The block that the player must reach 
    *                     to win the game.
    * @param winningVal - The value that the player must reach
    *                     to win the game.
    * @param height     - Height of the grid
    * @param width      - Width of the grid */
   public GameSystem(int winningBlk, int winningVal, int height, int width) {
     
   }
   
   //================== PRIVATE METHODS ====================//
   /* (Write your private methods here) */
  private int generatePos(int maxNum) {   
      //Create new random object
      Random rand = new Random();
      //Generate random number between 0 and max length
      return rand.nextInt(maxNum);
  }
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
      while (true)
      {
         int row = generatePos(grid.length());
         int column = generatePos(grid[0].length());
         if (isEmpty(row,column))
         {  
            grid[row,column] = 
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
     
   }
   
   /* Get the Player who is currently his/her turn
    * @return - The Player object of the player who is currently
    *           his/her turn */
   public Player GetCurrPlayer() {
      return null;  // Dummy return value. Needs to be changed.
   }
   
   /* Get the Player with the indicated player ID
    * @param playerId - The ID of the player 
    * @return         - The Player object of the player */
   public Player GetPlayer(int playerId ) {
      return null;  // Dummy return value. Needs to be changed.
   }
   
   /* Switch player's turn. If it is player 0 turn, then it will
    * switch to player 1, and vice versa */
   public void SwitchPlayer() {
     
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
      return false;  // Dummy return value. Needs to be changed.
   }
}