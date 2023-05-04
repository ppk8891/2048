import java.util.Scanner;
public class Main2048 {
  public static void main(String[] args){
    GameSystem gs= new GameSystem(2048,3000,5,5);
    Displayer disp = new Displayer();
    char direction;
    int points=1;
    /* Write your code here */

    while (true)
    {
      Scanner input = new Scanner(System.in);
      System.out.println("Which game to play?");
      System.out.println("1. Default Game (2048 winning value and 4x4 grid)");
      System.out.print("Your option: ");
      int inNum  = input.nextInt();
      switch(inNum){
        case 1: gs = new GameSystem();
        Scanner input2= new Scanner(System.in);
        System.out.println("DIRECTION GUIDE:");
        System.out.println("  a - left");
        System.out.println("  w - up");
        System.out.println("  d - right");
        System.out.println("  s - down");
        disp.PrintGrid(gs.GetGrid());
        disp.PrintScores(gs.GetPlayer(0),gs.GetPlayer(1));

        while (gs.IsGridFull()==false || gs.CheckWinner()==false)
        {
          System.out.print("Player "+gs.GetCurrPlayer().GetId()+" turn: ");
          direction=input2.nextLine().charAt(0);
          switch (direction){
            case 'w':
            // move up
            gs.Move(1);
            disp.PrintGrid(gs.GetGrid());
            disp.PrintScores(gs.GetPlayer(0),gs.GetPlayer(1));
            if(gs.IsGridFull()==true || gs.CheckWinner()==true)
            { 
              System.out.println(" w if");
              break;
            }
            continue;
            case 'a':
            //move left
            gs.Move(2);
            disp.PrintGrid(gs.GetGrid());
            disp.PrintScores(gs.GetPlayer(0),gs.GetPlayer(1));
            if(gs.IsGridFull()==true || gs.CheckWinner()==true)
            {
              break;
            }
            continue;

            case 's':
            //move down
            gs.Move(3);
            disp.PrintGrid(gs.GetGrid());
            disp.PrintScores(gs.GetPlayer(0),gs.GetPlayer(1));
            if(gs.IsGridFull()==true || gs.CheckWinner()==true)
            {
              break;
            }
            continue;

            case 'd':
            //move right
            gs.Move(4);
            disp.PrintGrid(gs.GetGrid());
            disp.PrintScores(gs.GetPlayer(0),gs.GetPlayer(1));
            if(gs.IsGridFull()==true || gs.CheckWinner()==true)
            {
              break;
            }
            continue;
            default:
            System.out.println("Invalid move");
            continue;
          }
        }
        //Game over,
        continue;
        case 2: 
        System.out.print("");//ask grid width,length, winningblk,winningval
        //same while loop
      }
    }
  }
}