package mirrg.beryllium.lang;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public interface NumberUtil
{

	public static OptionalInt parseInt(String s)
	{
		try {
			return OptionalInt.of(Integer.parseInt(s));
		} catch (NumberFormatException e) {
			return OptionalInt.empty();
		}
	}

	public static OptionalInt parseInt(String s, int radix)
	{
		try {
			return OptionalInt.of(Integer.parseInt(s, radix));
		} catch (NumberFormatException e) {
			return OptionalInt.empty();
		}
	}

	public static OptionalLong parseLong(String s)
	{
		try {
			return OptionalLong.of(Long.parseLong(s));
		} catch (NumberFormatException e) {
			return OptionalLong.empty();
		}
	}

	public static OptionalLong parseLong(String s, int radix)
	{
		try {
			return OptionalLong.of(Long.parseLong(s, radix));
		} catch (NumberFormatException e) {
			return OptionalLong.empty();
		}
	}

	public static OptionalDouble parseDouble(String s)
	{
		try {
			return OptionalDouble.of(Double.parseDouble(s));
		} catch (NumberFormatException e) {
			return OptionalDouble.empty();
		}
	}

	//

	/**
	 * valueをmin以上max以下の数値に丸めます。
	 * minがmaxより大きい場合、maxの方をmin、minの方をmaxとして解釈されます。
	 */
	public static int trim(int value, int min, int max)
	{
		if (min > max) return trim(value, max, min);

		if (value < min) return min;
		if (value > max) return max;
		return value;
	}

	/**
	 * valueをmin以上max以下の数値に丸めます。
	 * minがmaxより大きい場合、maxの方をmin、minの方をmaxとして解釈されます。
	 */
	public static long trim(long value, long min, long max)
	{
		if (min > max) return trim(value, max, min);

		if (value < min) return min;
		if (value > max) return max;
		return value;
	}

	/**
	 * valueをmin以上max以下の数値に丸めます。
	 * minがmaxより大きい場合、maxの方をmin、minの方をmaxとして解釈されます。
	 */
	public static float trim(float value, float min, float max)
	{
		if (min > max) return trim(value, max, min);

		if (value < min) return min;
		if (value > max) return max;
		return value;
	}

	/**
	 * valueをmin以上max以下の数値に丸めます。
	 * minがmaxより大きい場合、maxの方をmin、minの方をmaxとして解釈されます。
	 */
	public static double trim(double value, double min, double max)
	{
		if (min > max) return trim(value, max, min);

		if (value < min) return min;
		if (value > max) return max;
		return value;
	}

}
