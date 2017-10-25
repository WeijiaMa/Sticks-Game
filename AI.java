// Last edited by Weijia Ma and Claudia Naughton, 1/18/17
// Class AI implements the behaviour and the training process for a self-trained AI.

public class AI implements Player
{
   private String name;
   private String AIName;
   private int[][] hats;
   // array that holds the successful picks that the AI has done
   private int[][] playLog;
   // array that keeps track of which stick is picked in order to update hats at end of round
   private int numEntries;
   // keeps track of number of entries in playLog
   public int numWins;
   //counts up number of games it wins during trainAI() 
   
   public AI(String name, String AIName)
   //constructor
   {
      this.name = name;
      this.AIName = AIName;
      this.hats = makeHats();
      this.playLog = new int[100][2];
      // an array of length 100, each element an array that holds two values: numSticks and corresponding pick
      this.numEntries = 0;  
      this.numWins = 0;
   }
   
   public int move(int numSticks)
   //method that returns number of sticks computer will take
   {
      int pick = this.choiceFromHats(numSticks) + 1;
      //runs method choiceFromHats to get a pick, adds one so that value is between 1 & 3
      if (pick > numSticks)
      // choose again if the pick cannot be done
      {
         pick = move(numSticks);
      }      
      else
      {        
         this.playLog[this.numEntries][0] = numSticks;
         this.playLog[this.numEntries][1] = pick;
         //adds numSticks and pick to playLog
         this.numEntries ++;
         //updates numEntries for next choice
      }
      return pick;
      
   }
   
   public int[][] makeHats()
   //method that initially makes array of hats with length 100- each element starts at array[1,1,1]
      {
      hats = new int[101][];
      for(int i = 0; i < 101; i++)
      {
         hats[i] = new int[3];
         for (int j = 0; j < 3; j++)
         {
            hats[i][j] = 1;
         }
      }
      return hats;
   }
   
   public int choiceFromHats(int numSticks)
   {
	   // Takes in the number of sticks and returns AI's choice of sticks: an integer between 0 - 2 unless there is not enough sticks
      int sum = 0;
      for (int i = 0; i < 3; i++)
      // Sums up all the numbers in the hat at index numSticks 
      {
         sum += this.hats[numSticks][i];
      }
      int choice = (int) (Math.random() * sum) + 1;
      //chooses a random value between 1 and the sum
      for (int i = 0; i < 3; i++)
      {
         if (choice <= this.hats[numSticks][i])
         // Returns the index if choice value is less than or equal to the number at index i
         {
            return i;
         } else {
         //if choice was greater, number at index i is subtracted 
         //from choice and the loop moves forward
            choice -= this.hats[numSticks][i];
         }
      } 
      return 40;
      // code should never reach this point when run correctly, 
      //but included so that there is a return in all scenarios
   }
   
   public void changeHats()
   //method updates hats with information in playLog
   {
      for (int i = 0; i < this.numEntries; i++)
      {
         this.hats[this.playLog[i][0]][this.playLog[i][1] - 1] ++;
      	
      }  	
   }
   public void clearLog()
   //clears playLog and numEntries after each round of playing
   {
      int[][] newPlayLog;
      newPlayLog = new int [100][2]; 
      this.playLog = newPlayLog;
      this.numEntries = 0;
   }
   
   public void startGame()
   //prints computer's message to human player
   {
      System.out.println(this.name + " says: 'HUMAN, PREPARE TO LOSE.'");
      
   }
   
   public int getNumWins()
   //getter function for the number of times an AI has won- used in trainAI to determine the more successful AI
   {
	   return this.numWins;
   }
   
   public void endGame(boolean win)
   //runs clearLog and runs changeHats when AI wins
   {
      if (win)
      {
         changeHats();
         clearLog();  
         this.numWins ++;
      } 
      else {
         clearLog();
      }
   }
}
