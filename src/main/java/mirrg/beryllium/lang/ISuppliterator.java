package mirrg.beryllium.lang;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Optional;
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

	public static <T> ISuppliterator<T> of(Enumeration<T> enumeration)
	{
		return of(LambdaUtil.toIterator(enumeration));
	}

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

}
