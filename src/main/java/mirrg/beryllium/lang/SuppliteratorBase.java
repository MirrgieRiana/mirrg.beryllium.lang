package mirrg.beryllium.lang;

import java.util.Optional;

public abstract class SuppliteratorBase<T> implements ISuppliterator<T>
{

	private boolean closed = false;

	@Override
	public Optional<T> next()
	{
		if (closed) return Optional.empty();
		Optional<T> next = nextImpl();
		if (next.isPresent()) {
			return next;
		} else {
			closed = true;
			return Optional.empty();
		}
	}

	/**
	 * このメソッドはemptyを一度でも返したら呼ばれることはありません。
	 */
	protected abstract Optional<T> nextImpl();

}
