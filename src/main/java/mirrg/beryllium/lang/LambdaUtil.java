package mirrg.beryllium.lang;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class LambdaUtil
{

	public static <T> T process(T t, Consumer<T> consumer)
	{
		consumer.accept(t);
		return t;
	}

	public static <T> T get(Supplier<T> supplier)
	{
		return supplier.get();
	}

	//

	/**
	 * 添え字番号付きfor。
	 */
	public static <O> void forEach(Stream<O> stream, ObjIntConsumer<O> consumer)
	{
		Integer[] index = new Integer[] {
			0,
		};
		stream.sequential().forEach(object -> {
			consumer.accept(object, index[0]);
			index[0]++;
		});
	}

	/**
	 * 添え字番号付きmap。
	 */
	public static <O1, O2> Stream<O2> map(Stream<O1> stream, Obj1IntToObj2<O1, O2> function)
	{
		Integer[] index = new Integer[] {
			0,
		};
		return stream.sequential().map(object -> {
			O2 o2 = function.apply(object, index[0]);
			index[0]++;
			return o2;
		});
	}

	@FunctionalInterface
	public static interface Obj1IntToObj2<O1, O2>
	{

		public O2 apply(O1 o1, int i);

	}

	/**
	 * 添え字番号付きfilter。
	 */
	public static <O> Stream<O> filter(Stream<O> stream, ObjIntToBoolean<O> predicate)
	{
		Integer[] index = new Integer[] {
			0,
		};
		return stream.sequential().filter(object -> {
			boolean flag = predicate.test(object, index[0]);
			index[0]++;
			return flag;
		});
	}

	@FunctionalInterface
	public static interface ObjIntToBoolean<O>
	{

		public boolean test(O o, int i);

	}

	//

	public static <T> Stream<T> toStream(Enumeration<T> enumeration)
	{
		return StreamSupport.stream(
			Spliterators.spliteratorUnknownSize(
				new Iterator<T>() {
					@Override
					public T next()
					{
						return enumeration.nextElement();
					}

					@Override
					public boolean hasNext()
					{
						return enumeration.hasMoreElements();
					}
				},
				Spliterator.ORDERED), false);
	}

	public static IntStream rangeReverse(int min, int max)
	{
		return IntStream.range(min, max)
			.map(i -> max - i + min - 1);
	}

	/**
	 * このメソッドは一旦ストリームのすべての要素を取得します。
	 */
	public static <T> Stream<T> reverse(Stream<T> stream)
	{
		ArrayList<T> list = stream
			.collect(Collectors.toCollection(ArrayList::new));
		return rangeReverse(0, list.size())
			.mapToObj(list::get);
	}

	public static <I, O> Stream<O> filter(Stream<I> stream, Class<O> clazz)
	{
		return stream
			.filter(clazz::isInstance)
			.map(clazz::cast);
	}

}