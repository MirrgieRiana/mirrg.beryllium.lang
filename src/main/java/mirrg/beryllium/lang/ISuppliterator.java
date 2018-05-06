package mirrg.beryllium.lang;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Optional;

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

}
