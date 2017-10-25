import java.util.Scanner;

/* Game of sticks
   Last edited by Weijia Ma and Claudia Naughton, 1/18/17, CS201
   This game allows user to play the game of sticks in the console
   with another human user, a simple AI, or a self-trained AI.
   The self-trained AI remembers the patterns it has chosen to win the game,
   and then try to duplicate the winning pattern.
   The learning algorithm makes use of the data structure bag.
*/


public class SticksGame
{
   private int player1pick, player2pick;
   
   public static void playGameOnce(Player player1, Player player2, int totalSticks)
   // Play one round of the game
   {
      boolean continueplaying = true; 
      //boolean to keep track of whether or not one player has already lost  
      boolean player1Win = false;
      if (player1 instanceof Human)
      {
      player1.startGame();
      player2.startGame();
      // Prints player messages to the screen, only if player1 is human
      }
   
      while(totalSticks > 0)
      // Plays rounds before anyone loses
      {
         int player1pick = player1.move(totalSticks);
         totalSticks -= player1pick;
         if (totalSticks == 0)
         // Player 1 loses
         {
            player1Win = false;
            continueplaying = false;
         }
         
         if (continueplaying)
         {
            if (player1 instanceof Human && player2 instanceof AI)
            {
            	//in situation where a human player plays against a AI 
            	//runs the following code that includes print statements
        	 System.out.println("There are " + totalSticks + " stick(s) on the board.");
        	 int player2pick = player2.move(totalSticks);
             System.out.println("Player 2 selects " + player2pick + " stick(s)");
             System.out.println();
             totalSticks -= player2pick;
            } else 
            {
            	int player2pick = player2.move(totalSticks);
	            totalSticks -= player2pick;
            }
            if (totalSticks == 0)
            // Player 2 loses
            {
               player1Win = true;
            }
         }
      } 
      player1.endGame(player1Win);
      player2.endGame(!player1Win);
      // Prints game results
   }
   
   public static void playAgain(Player player1, Player player2, int totalSticksReplay)
   //method that lets user decide to play again
   {
      System.out.println("*****************");
      System.out.println("Play again?");
      System.out.println("Options:");
      System.out.println("Yes (Y)");
      System.out.println("No (N)");
      System.out.print("Which option do you take (Y/N)? ");
      Scanner playAgain = new Scanner(System.in);
      String playAgainChoice = playAgain.next();
      if (playAgainChoice.equals("Y") || playAgainChoice.equals("y"))
      {
         playGameOnce(player1, player2, totalSticksReplay);
      } 
      else if (playAgainChoice.equals("N") || playAgainChoice.equals("n")){
         System.out.println("Ok. Goodbye!");
      } 
      else {
         System.out.println("Not an option. Goodbye!");
      }
      // ask the player whether to play again with the same choice of totalSticks and players.
      // If the answer is yes, run playGameOnce again
   }
   
   public static Player trainAI(int totalSticks)
   //runs through playGameOnce many times to train AIs, returns the AI that won the most games
   {
       Player AI1 = new AI("Player 2", "AI1");
       Player AI2 = new AI("Player 2", "AI2");
	   for (int i = 0; i < 10000; i++)
	   {
		   playGameOnce(AI1, AI2, totalSticks);
	   }
	   if (AI1.getNumWins() >= AI2.getNumWins())
	   {
		   return AI1;
	   } else {
		   return AI2;
	   }
   }
   
// Main method
// Written by Dave Musicant
   public static void main(String[] args)
   {
      // Initial setup
      System.out.println("Welcome to the game of sticks!");
      Scanner scanner = new Scanner(System.in);
      System.out.print("How many sticks are there on the table initally? (10-100)? ");
      int totalSticks = scanner.nextInt();
      while (totalSticks < 10 || totalSticks > 100)
      {
         System.out.println("Please enter a number between 10 and 100.");
         System.out.print("How many sticks are there on the table initally? (10-100)? ");
         totalSticks = scanner.nextInt();
      }
      int totalSticksReplay = totalSticks;
      
      System.out.println("Options:");
      System.out.println("Play against a friend (1)");
      System.out.println("Play against the computer (2)");
      System.out.println("Play against the trained computer (3)");
      System.out.print("Which option do you take (1-3)? ");
      int option = scanner.nextInt();
      // Choose which game: 1 = human v human, 2 = human v AI  
      if (option == 1)
      {         
         Player player1 = new Human("Player 1");
         Player player2 = new Human("Player 2");
         playGameOnce(player1, player2, totalSticks);
         playAgain(player1, player2, totalSticksReplay);
      }
      else if (option == 2)
      {
         Player player1 = new Human("Player 1");
         Player player2 = new AI("Player 2", "AI");
         playGameOnce(player1, player2, totalSticks);
         playAgain(player1, player2, totalSticksReplay);
      }
      else if (option == 3)
    	  //runs through trainAI and has user play against most successful AI
      {
          Player player1 = new Human("Player 1");
          System.out.println("Training AI, please wait...");
          Player player2 = trainAI(totalSticks);
          playGameOnce(player1, player2, totalSticks);
          playAgain(player1, player2, totalSticksReplay);
      }
      else
      {
         System.out.println("Bad choice.");
      }
   
   }
}