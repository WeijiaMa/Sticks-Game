
import java.util.Scanner;

// Last edited by Weijia Ma and Claudia Naughton, 1/18/17
// Class Human implements the behaviour for a human player.

public class Human implements Player
{
   private String name;
   int numWins;
   
   public Human(String name)
   // Constructor
   {
      this.name = name;
   }
   
   public int move(int numSticks)
   //method that lets human choose number of sticks to take and returns that value
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.println("There are " + numSticks + " stick(s) on the board.");
      System.out.print(this.name + ": How many sticks do you take (1-3)? ");
      int pick = keyboard.nextInt();
      if (numSticks < 3){
    	  while (pick > numSticks)
          {
             System.out.print("Please enter a number between 1 and " + numSticks + ": ");
             pick = keyboard.nextInt();
             //while loop for situation where user enters a number >numSticks
          }
      } else {
    	  while (pick < 1 || pick > 3)
          {
             System.out.print("Please enter a number between 1 and 3: ");
             pick = keyboard.nextInt();
             //if the user enters an invalid number- less than 3 or greater than 1
          }
      }
     
      
      System.out.println();
      return pick;
   }
   
   public int getNumWins()
   //getter method that returns number of times human has won
   {
	   return this.numWins;
   }
   
   public void startGame()
   //lets user write message to opponent
   {
      Scanner startGameScanner = new Scanner(System.in);
      System.out.print(this.name + ", write a message to your opponent: ");
      String message = startGameScanner.nextLine();
      System.out.println(this.name + " says: '" + message + "'");
   }
   
   public void endGame(boolean win)
   //prints out final results for human player
   {
      if (win)
      {
         System.out.println(this.name + ": You win!");
         this.numWins ++;
      } 
      else { 
         System.out.println(this.name + ": You lose.");
      }
   }
}