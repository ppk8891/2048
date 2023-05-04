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
     // initiate grid with default height (row) and width (height)
     this.grid = new Block[DEFAULT_GRID_HEIGHT][DEFAULT_GRID_WIDTH];
     winningVal = DEFAULT_WINNING_VAL;
     winningBlk = DEFAULT_WINNING_BKL;
     RandBlock();
     //initiate two players
     this.allPlayer = new Player[2];
      //Add two players with IDs 0 and 1 respectively
     this.allPlayer[0] = new Player(0);
     this.allPlayer[1] =new Player(1);
     //player 0 will alawys be the first player
     SetCurrPlayer(0);
   }
  
   /* The constructor to customize the winning value.
    * @param winningBlk - The block that the player must reach 
    *                     to win the game.
    * @param winningVal - The value that the player must reach
    *                     to win the game.
    * @param height     - Height of the grid
    * @param width      - Width of the grid */
   public GameSystem(int winningBlk, int winningVal, int height, int width) {
     this.winningVal = winningVal;
     this.grid = new Block[height][width];
     this.winningBlk = winningBlk;
     RandBlock();
     this.allPlayer = new Player[2];
     //Add two players with IDs 0 and 1 respectively
     this.allPlayer[0] = new Player(0);
     this.allPlayer[1] =new Player(1);
     SetCurrPlayer(0);
   }
   
   //================== PRIVATE METHODS ====================//
   /* (Write your private methods here) */
   private void printGameOverMessagge(int gameOvr)
   {
      switch (gameOvr){
         case 1:
         System.out.println("Player "+ GetCurrPlayer().GetId()+" wins by reaching the winning block!");
         break;
         case 2:
         System.out.println("Player "+GetCurrPlayer().GetId()+" wins by reaching the winning value!");
         break;
         case 3:
         System.out.println("The grid is full! Player "+GetCurrPlayer().GetId()+" lose.");
      }
   }
   //to set all isNew to false for all blocks 
   private void isNewFalse()
   { 
      for (int i=0; i<grid.length;i++)
      {
         for(int j=0;j<grid[i].length;j++)
         {
            if(!isEmpty(i,j))
            {
               grid[i][j].SetIsNew(false);
            }
         }
      }
   }
  // to generate random position for new block
  private int generatePos(int maxNum) {   
      //Create new random object
      Random rand = new Random();
      //Generate random number between 0 and max length
      return rand.nextInt(maxNum);
  }
  //check if a position is empty or not to make a move or to place a new block on the grid
  private boolean isEmpty(int row, int column) {
      if(this.grid[row][column]==null)
      {
         return true;
      }
      else
      {
         return false;
      }
  }
  //return biggest block value in the grid to determine if the game is over or not
  private int biggestBlk()
  {
      //initiate biggest value as 2 because all blocks have at least a value of two
      int biggest = 2;
      for(int i =0; i< grid.length;i++)
      {
         for(int j=0;j<grid[i].length;j++)
         {
            if(grid[i][j].GetVal()>biggest)
            {
               biggest = grid[i][j].GetVal();
            }
         }
      }
      return biggest;
  }
  //to check if there are any valid moves
  private boolean checkAdjacentBlocks() {
   for (int i = 0; i < grid.length; i++) {
       for (int j = 0; j < grid[i].length; j++) {
           if (grid[i][j] != null) {
               // check the block to the right
               if (j < grid[i].length - 1 && grid[i][j + 1] != null && grid[i][j].GetVal() == grid[i][j + 1].GetVal()) {
                   return true;
               }
               // check the block to the left
               if (j > 0 && grid[i][j - 1] != null && grid[i][j].GetVal() == grid[i][j - 1].GetVal()) {
                   return true;
               }
               // check the block above
               if (i > 0 && grid[i - 1][j] != null && grid[i][j].GetVal() == grid[i - 1][j].GetVal()) {
                   return true;
               }
               // check the block below
               if (i < grid.length - 1 && grid[i + 1][j] != null && grid[i][j].GetVal() == grid[i + 1][j].GetVal()) {
                   return true;
               }
           }
       }
   }
   return false;
}

  //to check if the game is over or not 
  //(logic: if there are no adjacent blocks with same value > game over)
  //(or the wining value is reached)
  //there will be three game over situations
  //winningBlk is reached or winningVal is reached or no more available moves
  //return 0 for not game over
  //return 1 for game over by reaching winningBlk
  //return 2 for game over by reaching winningVal
  //return 2 for game over by no more available moves
  private int isGameOver(){
     // If any block's value is equal to the winning value, the game is won
  for(int i = 0; i < grid.length; i++) {
   for(int j = 0; j < grid[0].length; j++) {
     if(grid[i][j] != null && grid[i][j].GetVal() == winningBlk) {
       return 1;
     }   
   }
  } 
  //check if there are available moves
   if (checkAdjacentBlocks()==false && IsGridFull()==true) {
   return 3;
   }
   //check if the winningvalue is reached
   if(currPlayer.GetScore()==winningVal)
   { 
      return 2;
   }
   //game is not over
   return 0;
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
      //Only add random block if the gird is not full
      if (IsGridFull()!=true){
      while (true)
      {
         int row = generatePos(grid.length);
         int column = generatePos(grid[0].length);
         if (isEmpty(row,column))
         {  
            this.grid[row][column] = new Block(blkVal);//create new block with value of 2 or 4
            break;
         }
      }
   }
   }
   public int moveUp() {
      int points = 0;
      boolean moved = false;
      for (int col = 0; col < grid[0].length; col++) {
          for (int row = 1; row < grid.length; row++) {
              if (grid[row][col] != null) {
                  int r = row;
                  while (r > 0 && (grid[r - 1][col] == null || grid[r - 1][col].GetVal() == grid[r][col].GetVal())) {
                      if (grid[r - 1][col] != null && grid[r - 1][col].GetVal() == grid[r][col].GetVal()) {
                          points += grid[r - 1][col].GetVal();
                          grid[r - 1][col].SetVal(2 * grid[r - 1][col].GetVal());
                          grid[r][col] = null;
                      } 
                      else {
                          grid[r - 1][col] = grid[r][col];
                          grid[r][col] = null;
                      }
                      r--;
                  }
              }
          }
      }

      if(isGameOver()>0){
            printGameOverMessagge(isGameOver());
            return points;
         }
         //add gained points to current player
         GetCurrPlayer().SetScore(GetCurrPlayer().GetScore()+points);
         //change player to next player
         SwitchPlayer();         
         //set isNew for all other block as false
         isNewFalse();
         //add new random block
         RandBlock();
 
      return points;
  }
   
  public int moveLeft() {
   int points = 0;
   boolean moved = false;
   for (int row = 0; row < grid.length; row++) {
       for (int col = 1; col < grid[0].length; col++) {
           if (grid[row][col] != null) {
               int c = col;
               while (c > 0 && (grid[row][c - 1] == null || grid[row][c - 1].GetVal() == grid[row][c].GetVal())) {
                   if (grid[row][c - 1] != null && grid[row][c - 1].GetVal() == grid[row][c].GetVal()) {
                       points += grid[row][c - 1].GetVal();
                       grid[row][c - 1].SetVal(2 * grid[row][c - 1].GetVal());
                       grid[row][c] = null;
                   } else {
                       grid[row][c - 1] = grid[row][c];
                       grid[row][c] = null;
                   }
                   c--;
                   moved = true;
               }
           }
       }
   }

   if (isGameOver() > 0) {
       printGameOverMessagge(isGameOver());
       return points;
   }
   //add gained points to current player
   GetCurrPlayer().SetScore(GetCurrPlayer().GetScore() + points);
   //change player to next player
   SwitchPlayer();
   //set isNew for all other block as false
   isNewFalse();
   //add new random block
   RandBlock();

   return points;
}
public int moveDown() {
   int points = 0;
   boolean moved = false;
   for (int col = 0; col < grid[0].length; col++) {
       for (int row = grid.length - 2; row >= 0; row--) {
           if (grid[row][col] != null) {
               int r = row;
               while (r < grid.length - 1 && (grid[r + 1][col] == null || grid[r + 1][col].GetVal() == grid[r][col].GetVal())) {
                   if (grid[r + 1][col] != null && grid[r + 1][col].GetVal() == grid[r][col].GetVal()) {
                       points += grid[r + 1][col].GetVal();
                       grid[r + 1][col].SetVal(2 * grid[r + 1][col].GetVal());
                       grid[r][col] = null;
                   } else {
                       grid[r + 1][col] = grid[r][col];
                       grid[r][col] = null;
                   }
                   r++;
               }
           }
       }
   }

   if (isGameOver() > 0) {
       printGameOverMessagge(isGameOver());
       return points;
   }
   // add gained points to current player
   GetCurrPlayer().SetScore(GetCurrPlayer().GetScore() + points);
   // change player to next player
   SwitchPlayer();
   // set isNew for all other block as false
   isNewFalse();
   // add new random block
   RandBlock();

   return points;
}

public int moveRight() {
   int points = 0;
   boolean moved = false;
   for (int row = 0; row < grid.length; row++) {
       for (int col = grid[0].length - 2; col >= 0; col--) {
           if (grid[row][col] != null) {
               int c = col;
               while (c < grid[0].length - 1 && (grid[row][c + 1] == null || grid[row][c + 1].GetVal() == grid[row][c].GetVal())) {
                   if (grid[row][c + 1] != null && grid[row][c + 1].GetVal() == grid[row][c].GetVal()) {
                       points += grid[row][c + 1].GetVal();
                       grid[row][c + 1].SetVal(2 * grid[row][c + 1].GetVal());
                       grid[row][c] = null;
                   } else {
                       grid[row][c + 1] = grid[row][c];
                       grid[row][c] = null;
                   }
                   c++;
               }
           }
       }
   }

   if (isGameOver() > 0) {
       printGameOverMessagge(isGameOver());
       return points;
   }
   // add gained points to current player
   GetCurrPlayer().SetScore(GetCurrPlayer().GetScore() + points);
   // change player to next player
   SwitchPlayer();
   // set isNew for all other block as false
   isNewFalse();
   // add new random block
   RandBlock();

   return points;
}

 
   
   /* Move all the blocks to the specified direction. If the direction is
    * invalid, it will not move any blocks.
    * @param dir - The direction that the user wants to move to.
    *              Refer to 
    tem global variables for direction
    *              values.
    * @return    - Returns points earned from this round of move. 
    *              Returns -1 if the direction is invalid, and blocks
    *              will not be moved. */
   public int Move(int dir) {
      int points=0;
      switch(dir){
         case 1: points=moveUp();
         break;
         case 2: points = moveLeft();
         break;
         case 3: points = moveDown();
         break;
         case 4: points = moveRight();
         break;
      }
      return points;
   }
   
   /* Set who will be the player turn
    * @param playerId - The ID of the player */
   public void SetCurrPlayer(int playerId ) {
     this.currPlayer = GetPlayer(playerId);
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
      return null;
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
      //Check it the winningblk is reached, return true if so
      for(int i = 0; i < grid.length; i++) {
         for(int j = 0; j < grid[0].length; j++) {
           if(grid[i][j] != null && grid[i][j].GetVal() == winningBlk) {
             return true;
           }   
         }
        }
      // check if the winning value is reached, return true if so  
      if(currPlayer.GetScore()==winningVal){
         return true;
      }
      else{
         return false;
      }
   }
   
   /* Check if the grid is full or not
    * @return - True if the grid has no more empty blocks
    *           False otherwise. */
   public boolean IsGridFull() {
      for (int i=0; i<grid.length;i++)
      {
         for (int j=0;j<grid[i].length;j++)
         {
            if (isEmpty(i,j))
            {
               return false;
            }
            else
            {
               continue;
            }
         }
      }
      return true;
   }
}
