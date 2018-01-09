
import java.util.Scanner;
import java.util.Random;
public class holesAndJetpacksGame {
	static Scanner keyboard = new Scanner(System.in);
	static Random generator = new Random();
	static Die dice = new Die();

	public static void main(String[] args) {
				
		boolean keepPlaying;
		// Player's die roll
		int userRoll = 0;
		// Computer's die roll
		int comRoll = 0;
		// Fixed hole space
		final int HOLE = 1;
		// Fixed jetpack space
		final int JETPACK = 2;
		// Player's gamepiece
		final int PLAYER = 111;
		// Computer's gamepiece
		final int COMPUTER = 222;
		
		
		do
		{
			int [] userBoard = new int[50];
			int [] computerBoard = new int[50];
			int [] defaultBoard = new int [50];
			int playerSpace = 0;
			int computerSpace = 0;
		
		printMenu();
		fillHolesJets(userBoard, computerBoard, defaultBoard);
		System.out.println("This is the gameboard. 1s represent holes & 2s represent jetpacks.");
		printBoard(defaultBoard);
		System.out.println("The user always goes first.");
		printLines();
		
			do
		
		{
		// Player's turn: roll die, move up board, resolve hole/jetpack, change previous space in array back to original value, prints the board
		userRoll = playerDie();
		playerSpace = playerMoveUp(userRoll, PLAYER, userBoard, playerSpace, defaultBoard);
		playerSpaceChange(playerSpace, userBoard, defaultBoard, PLAYER);
		printBoard(userBoard);
		
		//Computer's turn: roll die, move up board, resolve hole/jetpack, change previous space in array back to original value, prints the board
		comRoll = computerDie();
		computerSpace = computerMoveUp(comRoll, COMPUTER, computerBoard, computerSpace, defaultBoard);
		computerSpaceChange(computerSpace, computerBoard, defaultBoard, COMPUTER);
		printBoard(computerBoard);
		
		printLines();
		
		winner(playerSpace, computerSpace);
			
		}while(userBoard[49] == 0 && computerBoard[49] == 0);
			
		
		}while(keepPlaying());
		

	}
	//Method to print menu & instructions about the game
	public static void printMenu()
	{
		System.out.println("Welcome to a game of 'Holes & Jetpacks'!");
		System.out.println();
		System.out.println("INSTRUCTIONS:\n The goal of the game is to roll dice and advance up the gameboard while avoiding\n deadly holes and using jetpacks to fly up the board!");
		System.out.println("OBJECTIVE:\n To be the first player to reach the 50th space.");
		System.out.println("ABOUT:\n The user is '111' and the computer is '222'.\n '1s' represent holes & '2s' represent jetpacks.");
		System.out.println();
	}
	
	// Method to continue playing the game
	public static boolean keepPlaying()
	{
		Scanner readIn = new Scanner(System.in);
		boolean playAgain = false; 
		System.out.println("Do you want to play again?");
		String answer = readIn.nextLine();
		char ans = answer.charAt(0);
		if(ans == 'Y' || ans =='y')
			playAgain = true;
		return playAgain;
	}
	// Method for the user to roll their die
	public static int playerDie()
	{
		System.out.println("Press R to roll the die.");
		String press = keyboard.nextLine();
		char r = press.charAt(0);
		int userRoll = 0;
		if(r == 'r' || r == 'R')
		{
			userRoll = dice.roll();
			System.out.println("The die landed on a " + userRoll);
			System.out.println();
		}
		return userRoll;
	}
	
	// Method for the computer to roll its die
	public static int computerDie()
	{
		System.out.println("It's my turn now.");
		int comRoll = dice.roll();
		System.out.println("I rolled a " + comRoll + "!");
		System.out.println();
		return comRoll;
	}
	
	// Method to print the board (array)
	public static void printBoard(int [] defaultBoard)
	{
	for(int i = 0; i < defaultBoard.length; i++)
	{
		 if(i % 10 == 0)
		 {
			 System.out.println();
		 }
		 System.out.printf("%5d",  defaultBoard [i]);
		}
	System.out.println();
	System.out.println();
	}
	
	// Method to print separating lines
	public static void printLines()
	{
		for(int i = 0; i < 60; i++)
		{
			System.out.print("-");
		}
		System.out.println();
	}
	
	// Method to fill the 3 arrays with 0, holes, and jetpack spaces
	public static void fillHolesJets(int [] userBoard, int [] computerBoard, int [] defaultBoard)
	{
		int hole = 1;
		int jetpack = 2;
		int num = 0;
		for(int i = 1; i < 10; i++)
		{
			num = generator.nextInt(49);
			while(userBoard[num] != 0)
			{
				num = generator.nextInt(49);
			}
			userBoard[num] = hole;
			computerBoard[num] = hole;
			defaultBoard[num] = hole;
		}
		for(int i = 1; i < 10; i++)
		{
			num = generator.nextInt(49);
			while(userBoard[num] != 0)
			{
				num = generator.nextInt(49);
			}
			userBoard[num] = jetpack;
			computerBoard[num] = jetpack;
			defaultBoard[num] = jetpack;
		}
		userBoard[0] = 0;
		computerBoard[0] = 0;
		defaultBoard[0] = 0;
	}
	
	// Method that moves the player up the board, and checks if they fall in a hole or land on a jetpack. 
	// Also checks condition that player is less than space 49
	public static int playerMoveUp(int die, int player, int [] userBoard, int playerSpace, int[] defaultBoard)
	{
		
			playerSpace += die;
			
			if(playerSpace < 49)
			{
			if(defaultBoard[playerSpace] == 1)
			{
				System.out.println("You fell in a hole. Go back 1 space.");
				playerSpace -= 1;
			}
			else if(defaultBoard[playerSpace] == 2)
			{
				System.out.println("You picked up a jetpack. Fly forward one extra space!");
				playerSpace += 1;
			}
			userBoard[playerSpace] = player;
			}
			else
			{
				playerSpace = 49;
			}
			userBoard[playerSpace] = player;
		return playerSpace;
			
	}
	
	// Method that moves the computer up the board, and checks if they fall in a hole or land on a jetpack
	// Also checks condition that computer is less than space 49
	public static int computerMoveUp(int die, int computer, int [] computerBoard, int computerSpace, int[] defaultBoard)
	{
		computerSpace += die;
		
		if(computerSpace < 49)
		{
		if(defaultBoard[computerSpace] == 1)
		{
			System.out.println("Nooo! I fell in a hole. I went back 1 space.");
			computerSpace -= 1;
		}
		else if(defaultBoard[computerSpace] == 2)
		{
			System.out.println("Wooo! I picked up a jetpack. I'm flying forward one extra space! ");
			computerSpace += 1;
		}
		computerBoard[computerSpace] = computer;
		}
		else
		{
			computerSpace = 49;
		}
		computerBoard[computerSpace] = computer;
	return computerSpace;
	}
	
	// Method that changes previous spaces back to their original values after they've been landed on for player
	public static void playerSpaceChange(int playerSpace, int[] userBoard, int[] defaultBoard, int player)
	{
		for(int i = 0; i < userBoard[playerSpace]; i++) 
		{
			userBoard[i] = defaultBoard[i];
		}
		userBoard[playerSpace] = player;
	}
	
	// Method that changes previous spaces back to their original values after they've been landed on for computer
	public static void computerSpaceChange(int computerSpace, int[] computerBoard, int[] defaultBoard, int computer)
	{
		for(int i = 0; i < computerBoard[computerSpace]; i++) 
		{
			computerBoard[i] = defaultBoard[i];
		}
		computerBoard[computerSpace] = computer;
	}
	

	// Checks the winner of the game
	public static void winner(int playerSpace, int computerSpace)
	{
		if(playerSpace == 49)
		{
			System.out.println("You won the game!");
		}
		else if(computerSpace == 49)
		{
			System.out.println("Sorry. The computer won the game.");
		}
	}
		
	
}

/* Welcome to a game of 'Holes & Jetpacks'!

INSTRUCTIONS:
 The goal of the game is to roll dice and advance up the gameboard while avoiding
 deadly holes and using jetpacks to fly up the board!
OBJECTIVE:
 To be the first player to reach the 50th space.
ABOUT:
 The user is '111' and the computer is '222'.
 '1s' represent holes & '2s' represent jetpacks.

This is the gameboard. 1s represent holes & 2s represent jetpacks.

    0    2    0    0    2    1    2    0    0    0
    0    1    0    2    1    0    0    0    0    0
    0    1    0    2    0    2    0    1    0    2
    0    0    0    0    1    1    0    0    0    2
    0    1    0    0    0    0    1    0    2    0

The user always goes first.
------------------------------------------------------------
Press R to roll the die.
r
The die landed on a 3


    0    2    0  111    2    1    2    0    0    0
    0    1    0    2    1    0    0    0    0    0
    0    1    0    2    0    2    0    1    0    2
    0    0    0    0    1    1    0    0    0    2
    0    1    0    0    0    0    1    0    2    0

It's my turn now.
I rolled a 3!


    0    2    0  222    2    1    2    0    0    0
    0    1    0    2    1    0    0    0    0    0
    0    1    0    2    0    2    0    1    0    2
    0    0    0    0    1    1    0    0    0    2
    0    1    0    0    0    0    1    0    2    0

------------------------------------------------------------
Press R to roll the die.
r
The die landed on a 2

You fell in a hole. Go back 1 space.

    0    2    0    0  111    1    2    0    0    0
    0    1    0    2    1    0    0    0    0    0
    0    1    0    2    0    2    0    1    0    2
    0    0    0    0    1    1    0    0    0    2
    0    1    0    0    0    0    1    0    2    0

It's my turn now.
I rolled a 4!


    0    2    0    0    2    1    2  222    0    0
    0    1    0    2    1    0    0    0    0    0
    0    1    0    2    0    2    0    1    0    2
    0    0    0    0    1    1    0    0    0    2
    0    1    0    0    0    0    1    0    2    0

------------------------------------------------------------
Press R to roll the die.
r
The die landed on a 3


    0    2    0    0    2    1    2  111    0    0
    0    1    0    2    1    0    0    0    0    0
    0    1    0    2    0    2    0    1    0    2
    0    0    0    0    1    1    0    0    0    2
    0    1    0    0    0    0    1    0    2    0

It's my turn now.
I rolled a 2!


    0    2    0    0    2    1    2    0    0  222
    0    1    0    2    1    0    0    0    0    0
    0    1    0    2    0    2    0    1    0    2
    0    0    0    0    1    1    0    0    0    2
    0    1    0    0    0    0    1    0    2    0

------------------------------------------------------------
Press R to roll the die.
r
The die landed on a 4

You fell in a hole. Go back 1 space.

    0    2    0    0    2    1    2    0    0    0
  111    1    0    2    1    0    0    0    0    0
    0    1    0    2    0    2    0    1    0    2
    0    0    0    0    1    1    0    0    0    2
    0    1    0    0    0    0    1    0    2    0

It's my turn now.
I rolled a 4!

Wooo! I picked up a jetpack. I'm flying forward one extra space! 

    0    2    0    0    2    1    2    0    0    0
    0    1    0    2  222    0    0    0    0    0
    0    1    0    2    0    2    0    1    0    2
    0    0    0    0    1    1    0    0    0    2
    0    1    0    0    0    0    1    0    2    0

------------------------------------------------------------
Press R to roll the die.
r
The die landed on a 4

You fell in a hole. Go back 1 space.

    0    2    0    0    2    1    2    0    0    0
    0    1    0  111    1    0    0    0    0    0
    0    1    0    2    0    2    0    1    0    2
    0    0    0    0    1    1    0    0    0    2
    0    1    0    0    0    0    1    0    2    0

It's my turn now.
I rolled a 4!


    0    2    0    0    2    1    2    0    0    0
    0    1    0    2    1    0    0    0  222    0
    0    1    0    2    0    2    0    1    0    2
    0    0    0    0    1    1    0    0    0    2
    0    1    0    0    0    0    1    0    2    0

------------------------------------------------------------
Press R to roll the die.
r
The die landed on a 1

You fell in a hole. Go back 1 space.

    0    2    0    0    2    1    2    0    0    0
    0    1    0  111    1    0    0    0    0    0
    0    1    0    2    0    2    0    1    0    2
    0    0    0    0    1    1    0    0    0    2
    0    1    0    0    0    0    1    0    2    0

It's my turn now.
I rolled a 1!


    0    2    0    0    2    1    2    0    0    0
    0    1    0    2    1    0    0    0    0  222
    0    1    0    2    0    2    0    1    0    2
    0    0    0    0    1    1    0    0    0    2
    0    1    0    0    0    0    1    0    2    0

------------------------------------------------------------
Press R to roll the die.
r
The die landed on a 4


    0    2    0    0    2    1    2    0    0    0
    0    1    0    2    1    0    0  111    0    0
    0    1    0    2    0    2    0    1    0    2
    0    0    0    0    1    1    0    0    0    2
    0    1    0    0    0    0    1    0    2    0

It's my turn now.
I rolled a 4!

Wooo! I picked up a jetpack. I'm flying forward one extra space! 

    0    2    0    0    2    1    2    0    0    0
    0    1    0    2    1    0    0    0    0    0
    0    1    0    2  222    2    0    1    0    2
    0    0    0    0    1    1    0    0    0    2
    0    1    0    0    0    0    1    0    2    0

------------------------------------------------------------
Press R to roll the die.
r
The die landed on a 2


    0    2    0    0    2    1    2    0    0    0
    0    1    0    2    1    0    0    0    0  111
    0    1    0    2    0    2    0    1    0    2
    0    0    0    0    1    1    0    0    0    2
    0    1    0    0    0    0    1    0    2    0

It's my turn now.
I rolled a 6!


    0    2    0    0    2    1    2    0    0    0
    0    1    0    2    1    0    0    0    0    0
    0    1    0    2    0    2    0    1    0    2
  222    0    0    0    1    1    0    0    0    2
    0    1    0    0    0    0    1    0    2    0

------------------------------------------------------------
Press R to roll the die.
r
The die landed on a 3


    0    2    0    0    2    1    2    0    0    0
    0    1    0    2    1    0    0    0    0    0
    0    1  111    2    0    2    0    1    0    2
    0    0    0    0    1    1    0    0    0    2
    0    1    0    0    0    0    1    0    2    0

It's my turn now.
I rolled a 2!


    0    2    0    0    2    1    2    0    0    0
    0    1    0    2    1    0    0    0    0    0
    0    1    0    2    0    2    0    1    0    2
    0    0  222    0    1    1    0    0    0    2
    0    1    0    0    0    0    1    0    2    0

------------------------------------------------------------
Press R to roll the die.
r
The die landed on a 1

You picked up a jetpack. Fly forward one extra space!

    0    2    0    0    2    1    2    0    0    0
    0    1    0    2    1    0    0    0    0    0
    0    1    0    2  111    2    0    1    0    2
    0    0    0    0    1    1    0    0    0    2
    0    1    0    0    0    0    1    0    2    0

It's my turn now.
I rolled a 6!


    0    2    0    0    2    1    2    0    0    0
    0    1    0    2    1    0    0    0    0    0
    0    1    0    2    0    2    0    1    0    2
    0    0    0    0    1    1    0    0  222    2
    0    1    0    0    0    0    1    0    2    0

------------------------------------------------------------
Press R to roll the die.
r
The die landed on a 3

You fell in a hole. Go back 1 space.

    0    2    0    0    2    1    2    0    0    0
    0    1    0    2    1    0    0    0    0    0
    0    1    0    2    0    2  111    1    0    2
    0    0    0    0    1    1    0    0    0    2
    0    1    0    0    0    0    1    0    2    0

It's my turn now.
I rolled a 3!

Nooo! I fell in a hole. I went back 1 space.

    0    2    0    0    2    1    2    0    0    0
    0    1    0    2    1    0    0    0    0    0
    0    1    0    2    0    2    0    1    0    2
    0    0    0    0    1    1    0    0    0    2
  222    1    0    0    0    0    1    0    2    0

------------------------------------------------------------
Press R to roll the die.
r
The die landed on a 4


    0    2    0    0    2    1    2    0    0    0
    0    1    0    2    1    0    0    0    0    0
    0    1    0    2    0    2    0    1    0    2
  111    0    0    0    1    1    0    0    0    2
    0    1    0    0    0    0    1    0    2    0

It's my turn now.
I rolled a 1!

Nooo! I fell in a hole. I went back 1 space.

    0    2    0    0    2    1    2    0    0    0
    0    1    0    2    1    0    0    0    0    0
    0    1    0    2    0    2    0    1    0    2
    0    0    0    0    1    1    0    0    0    2
  222    1    0    0    0    0    1    0    2    0

------------------------------------------------------------
Press R to roll the die.
r
The die landed on a 5

You fell in a hole. Go back 1 space.

    0    2    0    0    2    1    2    0    0    0
    0    1    0    2    1    0    0    0    0    0
    0    1    0    2    0    2    0    1    0    2
    0    0    0    0  111    1    0    0    0    2
    0    1    0    0    0    0    1    0    2    0

It's my turn now.
I rolled a 1!

Nooo! I fell in a hole. I went back 1 space.

    0    2    0    0    2    1    2    0    0    0
    0    1    0    2    1    0    0    0    0    0
    0    1    0    2    0    2    0    1    0    2
    0    0    0    0    1    1    0    0    0    2
  222    1    0    0    0    0    1    0    2    0

------------------------------------------------------------
Press R to roll the die.
r
The die landed on a 2


    0    2    0    0    2    1    2    0    0    0
    0    1    0    2    1    0    0    0    0    0
    0    1    0    2    0    2    0    1    0    2
    0    0    0    0    1    1  111    0    0    2
    0    1    0    0    0    0    1    0    2    0

It's my turn now.
I rolled a 6!

Nooo! I fell in a hole. I went back 1 space.

    0    2    0    0    2    1    2    0    0    0
    0    1    0    2    1    0    0    0    0    0
    0    1    0    2    0    2    0    1    0    2
    0    0    0    0    1    1    0    0    0    2
    0    1    0    0    0  222    1    0    2    0

------------------------------------------------------------
Press R to roll the die.
r
The die landed on a 2


    0    2    0    0    2    1    2    0    0    0
    0    1    0    2    1    0    0    0    0    0
    0    1    0    2    0    2    0    1    0    2
    0    0    0    0    1    1    0    0  111    2
    0    1    0    0    0    0    1    0    2    0

It's my turn now.
I rolled a 6!


    0    2    0    0    2    1    2    0    0    0
    0    1    0    2    1    0    0    0    0    0
    0    1    0    2    0    2    0    1    0    2
    0    0    0    0    1    1    0    0    0    2
    0    1    0    0    0    0    1    0    2  222

------------------------------------------------------------
Sorry. The computer won the game.
Do you want to play again?
*/