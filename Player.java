// Last edited by Weijia Ma and Claudia Naughton, 1/18/17
// Interface Player specifies a few common behaviours players of the Sticks Game need to implement

interface Player
{
   int move(int numSticks);
   void startGame();
   void endGame(boolean win);
   int getNumWins();
}