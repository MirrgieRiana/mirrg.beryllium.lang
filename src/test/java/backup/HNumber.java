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
