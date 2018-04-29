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

	//

	/**
	 * valueがminからmaxの範囲に収まるように、適切に余りを求めます。
	 * value = -1の場合maxを返し、以下流れに沿って適切な値を返します。
	 * minがmaxよりも大きな値だった場合、minとmaxを逆に解釈します。
	 */
	public static int torus(int value, int min, int max)
	{
		if (min > max) return torus(value, max, min);

		int n = max - min + 1;

		if (value < 0) {
			return (n - 1) - (-value - 1) % n + min;
		} else {
			return value % n + min;
		}
	}

}
