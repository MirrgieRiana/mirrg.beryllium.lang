package backup;

import java.util.Comparator;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;

/**
 * 数学的な関数を使わないが数値に関することを行うクラス。
 */
public class HNumber
{

	/**
	 * valueがminかmaxと等しい場合に1、minからmaxの範囲内の場合に2、それ以外の場合に0を返します。
	 */
	public static int contains(int value, int min, int max)
	{
		if (min > max) return contains(value, max, min);

		if (value == min) return 1;
		if (value == max) return 1;

		if (value > min && value < max) return 1;

		return 0;
	}

	/**
	 * valueがminかmaxと等しい場合に1、minからmaxの範囲内の場合に2、それ以外の場合に0を返します。
	 */
	public static int contains(double value, double min, double max)
	{
		if (min > max) return contains(value, max, min);

		if (value == min) return 1;
		if (value == max) return 1;

		if (value > min && value < max) return 1;

		return 0;
	}

	public static int contains(int valueX, int valueY, int minX, int maxX, int minY, int maxY)
	{
		if (minX > maxX) return contains(valueX, valueY, maxX, minX, minY, maxY);
		if (minY > maxY) return contains(valueX, valueY, minX, maxX, maxY, minY);

		return Math.min(contains(valueX, minX, maxX), contains(valueY, minY, maxY));
	}

	public static int contains(double valueX, double valueY, double minX, double maxX, double minY, double maxY)
	{
		if (minX > maxX) return contains(valueX, valueY, maxX, minX, minY, maxY);
		if (minY > maxY) return contains(valueX, valueY, minX, maxX, maxY, minY);

		return Math.min(contains(valueX, minX, maxX), contains(valueY, minY, maxY));
	}

	/**
	 * @see Comparator
	 */
	public static int compare(double a, double b)
	{
		if (a < b) return -1;
		if (a > b) return 1;
		return 0;
	}

	public static <T> Comparator<T> createComparator(ToIntFunction<T> function)
	{
		return (a, b) -> compare(function.applyAsInt(a), function.applyAsInt(b));
	}

	public static <T> Comparator<T> createComparator(ToDoubleFunction<T> function)
	{
		return (a, b) -> compare(function.applyAsDouble(a), function.applyAsDouble(b));
	}

	public static <T> Comparator<T> createComparatorNegate(ToIntFunction<T> function)
	{
		return (a, b) -> -compare(function.applyAsInt(a), function.applyAsInt(b));
	}

	public static <T> Comparator<T> createComparatorNegate(ToDoubleFunction<T> function)
	{
		return (a, b) -> -compare(function.applyAsDouble(a), function.applyAsDouble(b));
	}

}
