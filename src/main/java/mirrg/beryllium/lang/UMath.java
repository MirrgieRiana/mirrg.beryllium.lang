package mirrg.beryllium.lang;

import java.util.Random;

public class UMath
{

	public static int randomBetween(Random random, int min, int max)
	{
		return (int) (random.nextDouble() * (max - min + 1) + min);
	}

	public static int randomBetween(int min, int max)
	{
		return (int) (Math.random() * (max - min + 1) + min);
	}

}
