package application;

public interface MinHeapInterface<A extends Comparable<A>> {
	public void add(A newEntry);

	public A removeMin();

	public A getMin();

	public boolean isEmpty();

	public int getSize();

	public void clear();
}
