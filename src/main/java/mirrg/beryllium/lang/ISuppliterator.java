package mirrg.beryllium.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * {@link Enumeration} や {@link Iterator} がもつメソッドを1個にまとめたものです。
 */
public interface ISuppliterator<T>
{

	/**
	 * @return
	 * 		列挙がリストの末端に達して要素が存在しない場合、empty。
	 */
	public Optional<T> next();

	//

	public default void forEach(Consumer<? super T> consumer)
	{
		while (true) {
			Optional<T> next = next();
			if (next.isPresent()) {
				consumer.accept(next.get());
			} else {
				break;
			}
		}
	}

	public default <O> ISuppliterator<O> map(Function<? super T, ? extends O> mapper)
	{
		return () -> next().map(mapper);
	}

	public default ISuppliterator<T> filter(Predicate<? super T> predicate)
	{
		return new SuppliteratorBase<T>() {
			@Override
			protected Optional<T> nextImpl()
			{
				while (true) {
					Optional<T> next = next();
					if (!next.isPresent()) return Optional.empty();
					if (predicate.test(next.get())) return next;
				}
			}
		};
	}


	public default <O> ISuppliterator<O> apply(Function<? super ISuppliterator<? super T>, ? extends ISuppliterator<O>> function)
	{
		return function.apply(this);
	}

	public default T[] toArray(IntFunction<T[]> sArray)
	{
		ArrayList<T> list = toCollection();
		return list.toArray(sArray.apply(list.size()));
	}

	public default <C extends Collection<? super T>> C toCollection(Supplier<? extends C> sCollection)
	{
		C collection = sCollection.get();
		forEach(collection::add);
		return collection;
	}

	public default ArrayList<T> toCollection()
	{
		return toCollection(ArrayList::new);
	}

	public static <T> ISuppliterator<T> flatten(ISuppliterator<ISuppliterator<T>> suppliterator)
	{
		return new SuppliteratorBase<T>() {

			private ISuppliterator<T> current = null;

			@Override
			protected Optional<T> nextImpl()
			{
				while (true) {
					if (current == null) {
						Optional<ISuppliterator<T>> oCurrent = suppliterator.next();
						if (oCurrent.isPresent()) {
							current = oCurrent.get();
						} else {
							return Optional.empty();
						}
					}
					Optional<T> oNext = current.next();
					if (oNext.isPresent()) {
						return oNext;
					} else {
						current = null;
					}
				}
			}

		};
	}

	@SuppressWarnings("unchecked")
	public static <I extends O, O> ISuppliterator<O> cast(ISuppliterator<I> suppliterator)
	{
		return (ISuppliterator<O>) suppliterator;
	}

	//

	public default Stream<T> stream()
	{
		return LambdaUtil.toStream(iterator());
	}

	public default Iterator<T> iterator()
	{
		return new Iterator<T>() {

			private Optional<T> next = null;

			@Override
			public T next()
			{
				if (next == null) next = ISuppliterator.this.next();
				T t = next.get();
				next = null;
				return t;
			}

			@Override
			public boolean hasNext()
			{
				if (next == null) next = ISuppliterator.this.next();
				return next.isPresent();
			}

		};
	}

	public default Enumeration<T> enumerate()
	{
		return LambdaUtil.toEnumeration(iterator());
	}

	/**
	 * @param ts
	 *            nullを含まないTの配列
	 */
	@SafeVarargs
	public static <T> ISuppliterator<T> of(T... ts)
	{
		return new SuppliteratorBase<T>() {

			private int index = 0;

			@Override
			protected Optional<T> nextImpl()
			{
				if (index < ts.length) {
					T next = ts[index];
					index++;
					return Optional.of(next);
				} else {
					return Optional.empty();
				}
			}

		};
	}

	/**
	 * @param enumeration
	 *            nullを含まないTのEnumeration
	 */
	public static <T> ISuppliterator<T> of(Enumeration<T> enumeration)
	{
		return of(LambdaUtil.toIterator(enumeration));
	}

	/**
	 * @param iterator
	 *            nullを含まないTのIterator
	 */
	public static <T> ISuppliterator<T> of(Iterator<T> iterator)
	{
		return new SuppliteratorBase<T>() {
			@Override
			protected Optional<T> nextImpl()
			{
				return iterator.hasNext() ? Optional.of(iterator.next()) : Optional.empty();
			}
		};
	}

	@SafeVarargs
	public static <T> ISuppliterator<T> concat(ISuppliterator<T>... ts)
	{
		return flatten(of(ts));
	}

}
