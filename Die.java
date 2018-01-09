import java.util.Random;
public class Die 
{
	
	private int sides;
	private int value;

	//Constructor
	public Die()
	{
		sides = 6;
		roll();
	}
	public Die(int numSides)
	{
		sides = numSides;
		roll();
	}

	public int getValue()
	{
		return value;
	}
	// Return method to roll die
	public int roll()
	{
	Random generator = new Random();
	return value = generator.nextInt(sides) + 1;
	}
	
}
